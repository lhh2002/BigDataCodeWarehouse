package com.dao;

import com.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package com.dao
 * @File ：StudentDao.java
 * @date 2022/1/5 17:56
 */
public interface StudentDao extends JpaRepository<Student, Integer> {
}
