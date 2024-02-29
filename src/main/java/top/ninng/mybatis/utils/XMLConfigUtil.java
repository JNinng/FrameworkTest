package top.ninng.mybatis.utils;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import top.ninng.mybatis.Resources;
import top.ninng.mybatis.annotations.Select;
import top.ninng.mybatis.cfg.Configuration;
import top.ninng.mybatis.cfg.Mapper;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析配置文件
 */
public class XMLConfigUtil {

    /**
     * 解析配置文件
     */
    public static Configuration loadConfiguration(InputStream config) {
        try {
            // 定义封装连接信息的配置对象（mybatis的配置对象）
            Configuration configuration = new Configuration();

            // 1.获取SAXReader对象
            SAXReader reader = new SAXReader();
            // 2.根据字节输入流获取Document对象
            Document document = reader.read(config);
            // 3.获取根节点
            Element root = document.getRootElement();
            // 4.使用xpath中选择指定节点的方式，获取所有property节点
            List<Element> propertyElements = root.selectNodes("//property");
            // 5.遍历节点
            for (Element propertyElement : propertyElements) {
                // 判断节点是连接数据库的哪部分信息
                // 取出 name 属性的值
                String name = propertyElement.attributeValue("name");
                if ("driver".equals(name)) {
                    // 表示驱动
                    // 获取 property 标签 value 属性的值
                    String driver = propertyElement.attributeValue("value");
                    configuration.setDriver(driver);
                }
                if ("url".equals(name)) {
                    // 表示连接字符串
                    String url = propertyElement.attributeValue("value");
                    configuration.setUrl(url);
                }
                if ("username".equals(name)) {
                    // 表示用户名
                    String username = propertyElement.attributeValue("value");
                    configuration.setUsername(username);
                }
                if ("password".equals(name)) {
                    // 表示密码
                    String password = propertyElement.attributeValue("value");
                    configuration.setPassword(password);
                }
            }
            // 取出 mappers 中的所有 mapper 标签，判断他们使用了 resource 还是 class 属性
            List<Element> mapperElements = root.selectNodes("//mappers/mapper");
            // 遍历集合
            for (Element mapperElement : mapperElements) {
                // 判断 mapperElement 使用的是哪个属性
                Attribute attribute = mapperElement.attribute("resource");
                if (attribute != null) {
                    // 有 resource 属性，使用的 XML

                    // 获取属性的值 "mappers/IStudentDao.xml"
                    String mapperPath = attribute.getValue();
                    // 把映射配置文件的内容获取出来，封装成一个 map
                    Map<String, Mapper> mappers = loadMapperConfiguration(mapperPath);
                    // 给 configuration中 的 mappers 赋值
                    configuration.setMappers(mappers);
                } else {
                    // 没有 resource 属性，则使用的是注解

                    // 获取 class 属性的值
                    String daoClassPath = mapperElement.attributeValue("class");
                    // 根据 daoClassPath 获取封装的必要信息
                    Map<String, Mapper> mappers = loadMapperAnnotation(daoClassPath);
                    // 给 configuration中的 mappers 赋值
                    configuration.setMappers(mappers);
                }
            }
            // 返回 Configuration
            return configuration;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                config.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 根据传入的参数，得到 dao 中所有被 {@link Select} 注解标注的方法。
     * 根据方法名称和类名，以及方法上注解 value 属性的值，组成 Mapper 的必要信息
     *
     * @param daoClassPath
     * @return
     */
    private static Map<String, Mapper> loadMapperAnnotation(String daoClassPath) throws Exception {
        // 定义返回值对象
        Map<String, Mapper> mappers = new HashMap<>();

        // 1.得到 dao 接口的字节码对象
        Class daoClass = Class.forName(daoClassPath);
        // 2.得到 dao 接口中的方法数组
        Method[] methods = daoClass.getMethods();
        // 3.遍历 Method 数组
        for (Method method : methods) {
            // 取出每一个方法，判断是否有注解
            boolean isAnnotated = method.isAnnotationPresent(Select.class);
            if (isAnnotated) {
                // 创建 Mapper 对象
                Mapper mapper = new Mapper();
                // 取出注解的 value 属性值
                Select selectAnno = method.getAnnotation(Select.class);
                String queryString = selectAnno.value();
                mapper.setQueryString(queryString);
                // 获取当前方法的返回值，还要求必须带有泛型信息
                // List<Student>
                Type type = method.getGenericReturnType();
                // 判断 type 是不是参数化的类型
                if (type instanceof ParameterizedType) {
                    ParameterizedType ptype = (ParameterizedType) type;
                    // 得到参数化类型中的实际类型参数
                    Type[] types = ptype.getActualTypeArguments();
                    // 取出第一个
                    Class domainClass = (Class) types[0];
                    // 获取 domainClass 的类名
                    String resultType = domainClass.getName();
                    // 给 Mapper 赋值
                    mapper.setResultType(resultType);
                }
                // 组装 key 的信息
                // 获取方法的名称
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                mappers.put(className + "." + methodName, mapper);
            }
        }
        return mappers;
    }

    /**
     * 根据传入的参数，解析 XML，并且封装到 Map 中
     *
     * @param mapperPath 映射配置文件的位置
     * @return map 中包含了获取的唯一标识（key 是由 dao 的全限定类名和方法名组成）
     * 以及执行所需的必要信息（value 是一个 Mapper 对象，里面存放的是执行的 SQL 语句和要封装的实体类全限定类名）
     */
    private static Map<String, Mapper> loadMapperConfiguration(String mapperPath) {
        // 1.根据路径获取字节输入流
        try (InputStream in = Resources.getResourceAsStream(mapperPath)) {
            // 定义返回值对象
            Map<String, Mapper> mappers = new HashMap<>();
            // 2.根据字节输入流获取 Document 对象
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            // 3.获取根节点
            Element root = document.getRootElement();
            // 4.获取根节点的 namespace 属性取值
            // 是组成 map 中 key 的部分
            String namespace = root.attributeValue("namespace");
            // 5.获取所有的 select 节点
            List<Element> selectElements = root.selectNodes("//select");
            // 6.遍历 select 节点集合
            for (Element selectElement : selectElements) {
                // 取出 id 属性的值，组成 map 中 key 的部分
                String id = selectElement.attributeValue("id");
                // 取出 resultType 属性的值,组成 map 中 value 的部分
                String resultType = selectElement.attributeValue("resultType");
                // 取出文本内容，组成 map 中 value 的部分
                String queryString = selectElement.getText();
                // 创建 Value
                Mapper mapper = new Mapper();
                mapper.setQueryString(queryString);
                mapper.setResultType(resultType);
                mappers.put(namespace + "." + id, mapper);
            }
            return mappers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
