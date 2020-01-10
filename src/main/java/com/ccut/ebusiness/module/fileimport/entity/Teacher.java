package com.ccut.ebusiness.module.fileimport.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: Teacher
 *   @Description: 教师实体类
 *   @date 2019/01/09
*/
@TableName("teacher")
@Data
public class Teacher {

    private int id;
    private String name;
    private int sex;
    private int age;
    private int t_number;
    private String birthday;
    private Date c_time;
    private Date m_time;
    private String deltag;

}
