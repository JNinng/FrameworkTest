package top.ninng.mybatis.session.defaults;

import top.ninng.mybatis.cfg.Configuration;
import top.ninng.mybatis.session.MapperProxy;
import top.ninng.mybatis.session.SqlSession;
import top.ninng.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * {@link SqlSession} 默认实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Connection connection;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        connection = DataSourceUtil.getConnection(configuration);
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建代理对象
     *
     * @param daoInterfaceClass 代理接口字节码
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass}, new MapperProxy(configuration.getMappers(), connection));
    }
}
