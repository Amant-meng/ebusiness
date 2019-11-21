package com.ccut.ebusiness.module.tool.toolentity;

import java.util.Map;

/**
 * @author Meng.yang
 * @Title: DateRangeFiled
 * @ProjectName ebusiness
 * @Description: TODO
 * @date 2018/11/14
 */
public class NumberRangeFiled extends Filed {
    public NumberRangeFiled(String name, Map<String, Object> map) {
        super(name, map);
    }

    @Override
    public String getQuery() {
        if(this.value != null){
            String[] array = this.value.toString().split(" - ");
            String paramStartKey = "param" + super.valueMap.size();
            this.valueMap.put(paramStartKey, array[0]);
            String paramEndKey = "param" + super.valueMap.size();
            this.valueMap.put(paramEndKey, array[1]);
            return String.format(" and %s >= #{params.%s} and %s <= #{params.%s}", this.name,paramStartKey,this.name,paramEndKey);
        }else{
            return  "";
        }
    }
}
