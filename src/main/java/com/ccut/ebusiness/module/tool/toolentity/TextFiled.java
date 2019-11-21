package com.ccut.ebusiness.module.tool.toolentity;

import java.util.Map;

/**
 * @author Meng.yang
 * @Title: TextFiled
 * @ProjectName ebusiness
 * @Description: TODO
 * @date 2018/11/14
 */
public class TextFiled extends Filed {
    public TextFiled(String name, Map<String, Object> map) {
        super(name, map);
    }

    @Override
    public String getQuery() {
        return (super.value == null || super.value.toString().length() <=0 )  ? "" : (" and " + this.name +" like CONCAT('%',#{params." + this.name + "},'%')");
    }
}

