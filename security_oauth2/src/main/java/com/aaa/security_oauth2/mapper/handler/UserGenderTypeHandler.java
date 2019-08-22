/*
package com.aaa.security_oauth2.mapper.handler;

import com.aaa.security_oauth2.constants.Gender;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

*/
/**
 * mybatis 枚举转换输出 直接实现 原始基类
 * @param
 * @return
 *//*

@MappedTypes({Gender.class})
public class UserGenderTypeHandler implements TypeHandler<Gender> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Gender gender, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, gender.getCode());
    }

    @Override
    public Gender getResult(ResultSet resultSet, String s) throws SQLException {
        Integer code = resultSet.getInt(s);
        return Gender.getStatusByCode(code);
    }

    @Override
    public Gender getResult(ResultSet resultSet, int i) throws SQLException {
        Integer code = resultSet.getInt(i);
        return Gender.getStatusByCode(code);
    }

    @Override
    public Gender getResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer code = callableStatement.getInt(i);
        return Gender.getStatusByCode(code);
    }
}
*/
