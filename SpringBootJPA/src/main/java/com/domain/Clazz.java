package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package com.com.domain
 * @File ：Classzz.java
 * @date 2022/1/5 17:47
 */
@Entity(name = "t_clazz")
@Data
//@ToString
@EqualsAndHashCode(exclude= {"previous", "next"})
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置自增
    private int cid;
    @OneToMany(mappedBy = "clazz",fetch = FetchType.EAGER)
//    @OneToMany(mappedBy = "clazz")
    @JsonIgnore
    private List<Student> list;
    @Column

    private String cname;

    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
