package top.ninng.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import top.ninng.dao.IStudentDao;
import top.ninng.service.IStudentService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * {@link Component} 将对象存入容器
 * {@link Controller} 表现层
 * {@link Service} 服务层
 * {@link Repository} 持久层
 */
@Service(value = "studentService")
@Scope(value = "singleton")
public class StudentService implements IStudentService {

    // 按照类型注入
//    @Autowired
    // 按类型注入同时按名注入 与 @Autowired 同时使用
//    @Qualifier(value = "studentDao")
    @Resource(name = "studentDao")
    private IStudentDao studentDao;

    @Override
    public void select(String target) {
        studentDao.select("studentService");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("studentService destroy....");
    }

    @PostConstruct
    private void init() {
        System.out.println("studentService init....");
    }
}
