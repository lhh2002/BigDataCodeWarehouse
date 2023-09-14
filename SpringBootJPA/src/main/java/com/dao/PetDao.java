package com.dao;

import com.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package com.com.dao
 * @File ：PetDao.java
 * @date 2022/1/5 15:31
 * <T, ID> k：指定具体实现类  V 指定主键的字段类型或者说带有ID字段的类型
 */
public interface PetDao extends JpaRepository<Pet, Integer> {

    //使用自定义查询

    // 根据name查询
    List<Pet> findByName(String PetName);

    // 使用 jpql 查询

    @Query("select pet from com.domain.Pet pet where  pet.name = ?1")
    List<Pet> getByName(String  PetName);

}
