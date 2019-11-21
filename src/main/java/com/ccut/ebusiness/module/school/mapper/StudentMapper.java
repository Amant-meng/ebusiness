package com.ccut.ebusiness.module.school.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ccut.ebusiness.module.school.entity.Student;
import com.ccut.ebusiness.module.tool.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName ebusiness
 *   @title: StudentMapper
 *   @Description:
 *   @date 2019/11/21
*/
@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    List<Map> execSQL(Page page);

}
