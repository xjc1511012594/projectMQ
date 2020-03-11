package com.mq.workapi.mybatis.handler;

import com.mq.workapi.Message;
import com.mq.workapi.util.FastJsonConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xulei
 * @date 2020-3-11 16:33
 */
public class MessageJsonTypeHandler extends BaseTypeHandler<Message> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Message message, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,FastJsonConvertUtil.objectToJsonString(message));
    }

    @Override
    public Message getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if(null!=value&&!StringUtils.isBlank(value)){
            return FastJsonConvertUtil.jsonStringToObject(value,Message.class);
        }
        return null;
    }

    @Override
    public Message getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if(null!=value && !StringUtils.isBlank(value)){
            return FastJsonConvertUtil.jsonStringToObject(value,Message.class);
        }
        return null;
    }

    @Override
    public Message getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if(null!=value && !StringUtils.isBlank(value)){
            return FastJsonConvertUtil.jsonStringToObject(value,Message.class);
        }
        return null;
    }
}
