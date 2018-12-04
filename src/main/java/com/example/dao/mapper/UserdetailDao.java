package com.example.dao.mapper;

import com.example.dao.entity.Userdetail;
import com.example.dao.entity.UserdetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserdetailDao {
    long countByExample(UserdetailExample example);

    int deleteByExample(UserdetailExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(Userdetail record);

    int insertSelective(Userdetail record);

    List<Userdetail> selectByExampleWithBLOBs(UserdetailExample example);

    List<Userdetail> selectByExample(UserdetailExample example);

    Userdetail selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") Userdetail record, @Param("example") UserdetailExample example);

    int updateByExampleWithBLOBs(@Param("record") Userdetail record, @Param("example") UserdetailExample example);

    int updateByExample(@Param("record") Userdetail record, @Param("example") UserdetailExample example);

    int updateByPrimaryKeySelective(Userdetail record);

    int updateByPrimaryKeyWithBLOBs(Userdetail record);
}