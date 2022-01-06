package com.service;

import com.dao.PublicDto;
import com.domain.Pet;
import com.domain.Student;
import org.springframework.stereotype.Service;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package com.service
 * @File ：StudentService.java
 * @date 2022/1/6 13:51
 */

public interface StudentService {

    PublicDto<Student> getID(int id);

    PublicDto<Student> findByName(String  name);
}
