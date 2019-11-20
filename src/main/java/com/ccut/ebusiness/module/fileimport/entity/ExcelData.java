package com.ccut.ebusiness.module.fileimport.entity;


import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title:
 *   @Description: 表格数据导入实体类
 *   @date 2019/11/20
*/
@Data
@TableName("excel_data")
public class ExcelData implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;

    @Id
    private Integer id;

    private String name;

    private String sex;

    private String age;

}
