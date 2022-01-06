package com.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package com.domain
 * @File ：Student.java
 * @date 2022/1/5 17:49
 */
@Entity(name = "s_student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int sid;
    @Column
    private String sname;
    @ManyToOne//表示多个学生有一个班级
    @JoinColumn(name = "cid") //指定了班级的外键，没有指定名称默认使用 clazz
    private Clazz clazz;

}
