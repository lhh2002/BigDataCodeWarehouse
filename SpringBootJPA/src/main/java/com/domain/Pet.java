package com.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package com.domain
 * @File ：Pet.java
 * @date 2022/1/5 11:30
 */
@Data
@Entity(name = "t_pet")
public class Pet {

    @Id // 设置主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置表示自增
    private int id;
    @Column // 普通字段
    private String color;

    @Column
    private String name;
}
