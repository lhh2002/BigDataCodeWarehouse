import com.SpringbootJPAApplication;
import com.dao.ClazzDao;
import com.dao.StudentDao;
import com.domain.Clazz;
import com.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package PACKAGE_NAME
 * @File ：SpringbootAppplicationOneToMany.java
 * @date 2022/1/5 17:57
 */
@SpringBootTest(classes = SpringbootJPAApplication.class)
public class SpringbootApplicationOneToMany {
    @Test
    void testOnToManyCreateDataTable() {
        System.out.println("创建 clazz on student success .....");
    }

    @Autowired
    ClazzDao clazzDao;
    @Autowired
    StudentDao studentDao;


    // 往班级表插入班级数据
    @Test
    void testOnToManyInsertClazz() {
        Clazz clazz1 = new Clazz();
        clazz1.setCname("大数据01");
        Clazz clazz2 = new Clazz();
        clazz2.setCname("大数据02");
        ArrayList<Clazz> clazzs = new ArrayList<>();
        clazzs.add(clazz1);
        clazzs.add(clazz2);
        clazzDao.saveAll(clazzs);
    }

    //向学生表插入数据
    @Test
    void testOnToManyInsertStudent() {
        Clazz clazz = clazzDao.findById(1).get();
        Student student = new Student();
        student.setSname("张三");
        student.setClazz(clazz);
        studentDao.save(student);
    }

    //修改学生表中的数据
    @Test
    void testOnToManyUpdateStudent() {
        Clazz clazz = clazzDao.findById(1).get();
        Student student = new Student();
        student.setSname("zhangsan");
        student.setClazz(clazz);
        studentDao.save(student);
    }

    // 查看所有学生
    @Test
    void testOnToManyGetStudent() {
        List<Student> all = studentDao.findAll();
        System.out.println(all);
    }
}
