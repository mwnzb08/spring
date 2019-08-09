package com.neo.mapper;

import com.neo.model.Coffer;
import com.neo.model.CofferExample;
import org.apache.ibatis.annotations.Param;

public interface CofferMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Coffer record);

    int insertSelective(Coffer record);

    Coffer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Coffer record, @Param("example") CofferExample example);

    int updateByExample(@Param("record") Coffer record, @Param("example") CofferExample example);

    int updateByPrimaryKeySelective(Coffer record);

    int updateByPrimaryKey(Coffer record);
}