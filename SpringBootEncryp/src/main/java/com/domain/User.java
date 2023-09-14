package com.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.handler.EncryptHandler;
import lombok.Data;

import javax.naming.Name;

@Data
@TableName(value = "db_user",autoResultMap = true)
public class User {
    private Integer id;
    private String name;
    @TableField(typeHandler = EncryptHandler.class)
    private String phone;
}
