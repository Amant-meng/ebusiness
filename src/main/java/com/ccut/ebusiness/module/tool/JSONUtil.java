package com.ccut.ebusiness.module.tool;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Meng.yang
 * @Title: JSONUtil
 * @ProjectName ebusiness
 * @Description: TODO
 * @date 2018/11/14/01413:43
 */
public class JSONUtil {
    /**
     * 字符串转Map
     * @param jsonstr
     * @return
     */
    public static Map<String,Object> parseMap(String jsonstr){
        Map<String,Object> result = new HashMap<String,Object>();
        JSONObject obj = JSONObject.parseObject(jsonstr);
        for (String key:
                obj.keySet()) {
            result.put(key,obj.get(key));
        }
        return result;
    }
}
