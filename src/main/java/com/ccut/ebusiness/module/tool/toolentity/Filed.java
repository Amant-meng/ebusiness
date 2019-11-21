package com.ccut.ebusiness.module.tool.toolentity;

import java.util.Map;

/**
 * @author Meng.yang
 * @Title: Filed
 * @ProjectName ebusiness
 * @Description: TODO
 * @date 2018/11/14
 */
public abstract class Filed {
    public String name;
    public Object value;
    public Map<String,Object> valueMap;
    public Filed(String name, Map<String,Object> map){
        this.name = name;
        this.value = map.get(name);
        this.valueMap = map;
    }
    public abstract String getQuery();
}
