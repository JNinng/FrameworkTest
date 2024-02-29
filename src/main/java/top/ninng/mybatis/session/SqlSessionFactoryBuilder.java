package top.ninng.mybatis.session;

import top.ninng.mybatis.cfg.Configuration;
import top.ninng.mybatis.session.defaults.DefaultSqlSessionFactory;
import top.ninng.mybatis.utils.XMLConfigUtil;

import java.io.InputStream;

/**
 * 构建 {@link SqlSessionFactory}
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据配置信息输入流构建
     *
     * @param inputStream 配置信息输入流
     * @return
     */
    public SqlSessionFactory build(InputStream inputStream) {
        Configuration configuration = XMLConfigUtil.loadConfiguration(inputStream);
        return new DefaultSqlSessionFactory(configuration);
    }
}
