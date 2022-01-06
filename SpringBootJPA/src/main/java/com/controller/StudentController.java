package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.dao.PublicDto;
import com.domain.Pet;
import com.domain.Student;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package com.controller
 * @File ：StudentController.java
 * @date 2022/1/6 13:53
 */
@RestController
@RequestMapping("/user")
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping("/student/{id}")
    public ResponseEntity<PublicDto<Student>> getByid(@PathVariable("id") Integer id) {
        System.out.println("传入的参数是====>" + id);
        PublicDto<Student> student = null;
        try {
            student = studentService.getID(id);
            System.out.println("根据id返回的数据是" + id + "   ===>" + student);
            return ResponseEntity.ok(student); // 获取数据成功
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/student1/{id}")
    public String getByid2(@PathVariable("id") Integer id) {
        System.out.println("传入的参数是====>" + id);
        PublicDto<Student> student = null;
        try {
            student = studentService.getID(id);
            System.out.println("根据id返回的数据是" + id + "   ===>" + student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ik";


    }
}
