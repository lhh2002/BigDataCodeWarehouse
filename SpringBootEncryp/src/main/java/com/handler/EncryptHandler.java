package com.handler;

import com.baomidou.mybatisplus.core.toolkit.AES;
import com.utils.SM4Utils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EncryptHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        System.out.println(s);
        System.out.println(preparedStatement);
        preparedStatement.setString(i, SM4Utils.encrypt(s));
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String columnValue = resultSet.getString(s);
        return SM4Utils.decode(columnValue);
    }


    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String columnValue = resultSet.getString(i);
        return SM4Utils.decode(columnValue);
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String columnValue = callableStatement.getString(i);
        return SM4Utils.decode(columnValue);
    }
}
