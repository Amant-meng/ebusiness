package com.ccut.ebusiness.module.fileimport.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ccut.ebusiness.module.fileimport.entity.ExcelData;
import com.ccut.ebusiness.module.tool.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName ebusiness
 *   @title:
 *   @Description:
 *   @date 2019/11/20
*/
@Mapper
@Repository
public interface ExcelDataMapper extends BaseMapper<ExcelData> {

    List<Map> execSQL(Page page);

}
