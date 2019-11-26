package com.ccut.ebusiness.module.fileimport.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *   @author Meng.yang
 *   @ProjectName ebusiness
 *   @title: ExcelData
 *   @Description: 表格数据导入实体类
 *   @date 2019/11/20
*/
@Data
@TableName("excel_data")
public class ExcelData implements Serializable {

    private static final long serialVersionUID = 8841433872811285796L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 学号
     */
    private String stu_number;
    /**
     * 姓名
     */
    private String stu_name;
    /**
     * 性别
     */
    private String stu_sex;
    /**
     * 电话
     */
    private String stu_phone;
    /**
     * 地址
     */
    private String stu_adderss;
    private Date c_time;
    private Date m_time;
    private String deltag;

}
