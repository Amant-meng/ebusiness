package com.ccut.ebusiness.module.school.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 *   @author Meng.yang
 *   @ProjectName ebusiness
 *   @title: Student
 *   @Description: 学生实体类
 *   @date 2019/11/21
*/
@Data
@TableName
public class Student implements Serializable {

    private static final long serialVersionUID = 8841433872811285796L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String stu_number;
    private String stu_name;
    private String stu_sex;
    private String stu_phone;
    private String stu_adderss;
    private Date c_time;
    private Date m_time;
    private String deltag;

}
