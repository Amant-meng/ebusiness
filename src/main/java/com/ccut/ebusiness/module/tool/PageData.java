package com.ccut.ebusiness.module.tool;

import java.util.List;
import java.util.Map;

/**
 * @author Meng.yang
 * @Title: PageData
 * @ProjectName ebusiness
 * @Description: TODO
 * @date 2018/11/14/01415:38
 */
public class PageData {
    private Integer recordsTotal;
    private Integer recordsFiltered;
    private Object data;
    private Map<String,Object> attachData;
    private int count;
    private List<Map> listMap;

    public void setCount(int count) {
        this.count = count;
    }

    public void setListMap(List<Map> listMap) {
        this.listMap = listMap;
    }

    public int getCount() {
        return count;
    }

    public List<Map> getListMap() {
        return listMap;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public Object getAttachData() {
        return attachData;
    }

    public void setAttachData(Map<String,Object> attachData) {
        this.attachData = attachData;
    }
}
