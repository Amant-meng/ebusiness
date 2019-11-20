package com.ccut.ebusiness.module.sys;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Huan.Xia
 * @Title: SysUser
 * @ProjectName merchant-management
 * @Description: 系统用户实体
 * @date 2018/10/30/0309:22
 */
@TableName("sys_user")
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;

    private String id;
    private String username;
    private String password;
    private String truename;
    private String phone;
    private String gender;
    private String enabled;
    private Date createtime;
    private Date last_login_time;
    private String createuser;
    private Date modifytime;
    private String modifyuser;
    private String deltag;
    private String issystem;
    @TableField(value = "issystem_name")
    private String issystem_name;
    @TableField(value = "gender_name")
    private String gender_name;
    @TableField(value = "enabled_name")
    private String enabled_name;
    @TableField(value = "createuser_name")
    private String createusername;
    @TableField(value = "modifyuser_name")
    private String modifyusername;
    private String hiId;
    @TableField(value = "hiId_name")
    private String hiId_name;
    private String blocId;
    @TableField(value = "blocId_name")
    private String blocId_name;
    @TableField(exist = false)
    private List<String> premission;
    @TableField(exist = false)
    private String imgcode;
    private String role;


}
