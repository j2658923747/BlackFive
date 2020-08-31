package com.socket.test.socketdemo.dao;


import java.util.List;

import com.socket.test.socketdemo.domain.TRooms;
import com.socket.test.socketdemo.domain.TRoomsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TRoomsMapper {
    int countByExample(TRoomsExample example);

    int deleteByExample(TRoomsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRooms record);

    int insertSelective(TRooms record);

    List<TRooms> selectByExample(TRoomsExample example);

    TRooms selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRooms record, @Param("example") TRoomsExample example);

    int updateByExample(@Param("record") TRooms record, @Param("example") TRoomsExample example);

    int updateByPrimaryKeySelective(TRooms record);

    int updateByPrimaryKey(TRooms record);
}