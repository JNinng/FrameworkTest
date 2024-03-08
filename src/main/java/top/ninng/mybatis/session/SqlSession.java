package top.ninng.mybatis.session;

/**
 * 与数据库交互核心
 */
public interface SqlSession {

    void close();

    /**
     * 创建代理对象
     *
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);
}
