package top.ninng.mybatis.session;


import top.ninng.mybatis.cfg.Mapper;
import top.ninng.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {

    /**
     * key: 全限定类名.方法名
     */
    private Map<String, Mapper> mappers;
    private Connection connection;

    public MapperProxy(Map<String, Mapper> mappers, Connection connection) {
        this.mappers = mappers;
        this.connection = connection;
    }

    /**
     * 方法增强，增强实际为调用 selectList 方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 获取方法名
        String methodName = method.getName();
        // 获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        // 获取mappers中的Mapper对象
        Mapper mapper = mappers.get(className + "." + methodName);
        // 判断是否有mapper
        if (mapper == null) {
            throw new IllegalArgumentException("传入的参数有误");
        }
        // 调用工具类执行查询所有
        return new Executor().selectList(mapper, connection);
    }
}
