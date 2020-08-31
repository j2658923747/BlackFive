package com.socket.test.socketdemo.dao;

import com.socket.test.socketdemo.domain.TUser;
import com.socket.test.socketdemo.domain.TUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TUserMapper {
    int countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    //查询token
    //@Select("select * from t_user where username=#{username} and password=#{password}")
    TUser selectByAll(@Param("username") String username,@Param("password") String password);


    @Select("select * from t_user where username=#{username}")
    TUser selectByUsername(String username);

    @Select("select * from t_user where token=#{token}")
    TUser selectByToken(String token);
}