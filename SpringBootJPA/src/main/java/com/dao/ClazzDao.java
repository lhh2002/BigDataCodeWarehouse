package com.dao;

import com.domain.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package com.com.dao
 * @File ：ClazzDao.java
 * @date 2022/1/5 17:55
 */
public interface ClazzDao extends JpaRepository<Clazz, Integer> {
}
