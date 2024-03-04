package top.ninng.factory;

import top.ninng.dao.IStudentDao;
import top.ninng.dao.impl.StudentDao;

import java.util.Date;

/**
 * Dao 类创建工厂，模拟第三方引入不可修改源码的包
 */
public class DaoFactory {

    /**
     * 若 {@link IStudentDao} 仅能此方法获取
     *
     * @return
     */
    public IStudentDao getStudentDao() {
        return new StudentDao("DaoFactory", 1, new Date());
    }
}
