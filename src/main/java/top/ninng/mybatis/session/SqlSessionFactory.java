package top.ninng.mybatis.session;

public interface SqlSessionFactory {

    /**
     * 打开一个 {@link SqlSession}
     *
     * @return
     */
    SqlSession openSession();
}
