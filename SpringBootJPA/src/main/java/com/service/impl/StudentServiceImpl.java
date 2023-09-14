package com.service.impl;

import com.dao.PetDao;
import com.dao.PublicDto;
import com.dao.StudentDao;
import com.domain.Pet;
import com.domain.Student;
import com.service.StudentService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package com.com.service
 * @File ：StudentServiceImpl.java
 * @date 2022/1/6 13:51
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional
    public PublicDto<Student> getID(int id) {
        PublicDto<Student> studentPublicDto = new PublicDto<>();
        studentPublicDto.setCode(200);
        studentPublicDto.getData().add(studentDao.findById(id).get());
        studentPublicDto.setTotal(studentPublicDto.getData().size());
        studentPublicDto.setMsg("success");
        return studentPublicDto;
    }

    @Override
    public PublicDto<Student> findByName(String name) {
        PublicDto<Student> studentPublicDto = new PublicDto<>();
        studentPublicDto.setCode(200);
        studentPublicDto.setData(studentDao.findBySname(name) );
        studentPublicDto.setTotal(studentPublicDto.getData().size());
        studentPublicDto.setMsg("success");
        return studentPublicDto;
    }
}
