package com.ccut.ebusiness.module.system;

import org.apache.shiro.SecurityUtils;

/**
 * @author Meng.yang
 * @Title: BaseController
 * @ProjectName ebusiness
 * @Description: TODO
 * @date 2018/11/13
 */
public class BaseController {
    public SysUser getCurtUser() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
