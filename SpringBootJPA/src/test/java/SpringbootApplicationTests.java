import com.SpringbootJPAApplication;
import com.dao.PetDao;
import com.domain.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package PACKAGE_NAME
 * @File ：SprinbootApplicationTests.java
 * @date 2022/1/5 11:34
 */
@SpringBootTest(classes = SpringbootJPAApplication.class)
public class SpringbootApplicationTests {
    @Test
    void contestLoads() {
        System.out.println("表创建成功....");
    }

    @Autowired
    PetDao petDao;


    // 对JPA提供的方式进行测试
    @Test
    void contestAdd() {
        System.out.println("Pet add");
        Pet pet = new Pet();
        pet.setColor("红色");
        pet.setName("小红");
        petDao.save(pet);
        System.out.println("Pet add  success");
    }

    @Test
        // 使用Jpa 查询操作
    void contestFind() {
        // 根据主键查询
        Optional<Pet> byId = petDao.findById(1);
        Pet pet = byId.get();
        System.out.println("根据主键查询 +++++ " + pet);
        List<Pet> petDaoAll = petDao.findAll();
        System.out.println("查询所有 +++++ " + petDaoAll);
    }

    @Test
        // 使用自定义查询
    void contestFindByName() {
        List<Pet> byName = petDao.findByName("小红");
        System.out.println(byName);

    }

    @Test
        //使用自定义SQL 查询
    void contestFindQuery() {
        List<Pet> byName = petDao.getByName("小红");
        System.out.println(byName);
    }
}
