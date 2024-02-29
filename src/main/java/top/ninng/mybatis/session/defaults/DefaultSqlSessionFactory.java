package top.ninng.mybatis.session.defaults;

import top.ninng.mybatis.cfg.Configuration;
import top.ninng.mybatis.session.SqlSession;
import top.ninng.mybatis.session.SqlSessionFactory;


public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 用于创建一个新的操作数据库对象
     *
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
