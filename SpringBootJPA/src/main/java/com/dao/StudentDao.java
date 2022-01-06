package com.dao;

import com.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package com.dao
 * @File ：StudentDao.java
 * @date 2022/1/5 17:56
 */
public interface StudentDao extends JpaRepository<Student, Integer> {



    List<Student> findBySname(String name);
}
