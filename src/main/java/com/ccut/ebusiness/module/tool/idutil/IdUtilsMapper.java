package com.ccut.ebusiness.module.tool.idutil;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IdUtilsMapper {

    @Select("select next_val(#{sequenceName})")
    Long getValue(String sequenceName);
}
