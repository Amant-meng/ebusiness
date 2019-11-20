package com.ccut.ebusiness.module.log;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YangMeng
 * @Title: AnnotationLog
 * @ProjectName merchant-management
 * @Description: 系统日志
 * @date 2018/12/14
 */
@TableName("sys_log")
@Data
public class SysLog implements Serializable {

    private static final long serialVersionUID = 8841433872811285796L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String uid;
    private String uname;
    private String operation;
    private String method;
    private String params;
    private String ip;
    private Integer taketime;
    private String deltag;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifytime;

}
