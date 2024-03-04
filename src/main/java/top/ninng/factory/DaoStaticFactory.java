package top.ninng.factory;

import top.ninng.dao.IStudentDao;
import top.ninng.dao.impl.StudentDao;

import java.util.Date;

/**
 * Dao 类创建工厂（静态），模拟第三方引入不可修改源码的包
 */
public class DaoStaticFactory {

    /**
     * 若 {@link IStudentDao} 仅能此静态方法获取
     *
     * @return
     */
    public static IStudentDao getStudentDao() {
        return new StudentDao("DaoStaticFactory", 1, new Date());
    }
}
