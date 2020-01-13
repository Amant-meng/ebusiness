package com.ccut.ebusiness.module.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class ExportExcelUtilTest {
    public static void main(String[] args) {

        // 1.封装数据
        List<ExportExcelView> list = new LinkedList<>();
        ExportExcelView b1 = new ExportExcelView();
        b1.setDeclsno("201810251706470169854601");
        b1.setDecdt("2020-01-10");
        b1.setEleacno("1209394999");
        b1.setCustName("张三");
        b1.setEntName("酒店帮");
        b1.setSaleName("杨总");
        b1.setSaleTel("17342064227");
        b1.setRealsumretbal("1000");
        b1.setDecutionFee("100");

        ExportExcelView b2 = new ExportExcelView();
        b2.setDeclsno("201810251706470176052618");
        b2.setDecdt("2020-01-10");
        b2.setEleacno("1209394999");
        b2.setCustName("赵四");
        b2.setEntName("酒店帮");
        b2.setSaleName("杨总");
        b2.setSaleTel("17342064227");
        b2.setRealsumretbal("2000");
        b2.setDecutionFee("200");
        list.add(b1);
        list.add(b2);


        // 实体类转换为map
        List<LinkedHashMap<String, String>> result = new ArrayList<>();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (ExportExcelView e : list) {
            map.put("declsno", e.getDeclsno());
            map.put("decdt", e.getDecdt());
            map.put("eleacno", e.getEleacno());
            map.put("custName",e.getCustName());
            map.put("entName",e.getEntName());
            map.put("saleName",e.getSaleName());
            map.put("saleTel",e.getSaleTel());
            map.put("realsumretbal",e.getRealsumretbal());
            map.put("decutionFee",e.getDecutionFee());
            result.add(map);
        }

        // 2.定义变量值 创建Excel文件
        // 定义文件名
        String fileName = "酒店帮_202001代扣费用表.xls";
        // 定义表格标题
        String headString = "酒店帮_202001代扣费用表";
        // 定义工作表表名
        String sheetName = "酒店帮_202001代扣费用表";
        // 文件本地保存路径
        String filePath = "D:\\";
        String[] thead = { "扣款流水", "扣款日期", "发电户号", "用户姓名", "开发商",
                "业务员姓名","业务员手机号","扣款金额(元)", "代扣费用(元)" };
        // 定义每一列宽度
        int[] sheetWidth = { 7500, 4000, 3000, 3000, 4000, 3000, 5000, 5000,5000};

        HSSFWorkbook wb = new HSSFWorkbook(); // 创建Excel文档对象
        HSSFSheet sheet = wb.createSheet(sheetName); // 创建工作表
        // 3.生成表格
        // ①创建表格标题
        ExportExcelUtil.createHeadTittle(wb, sheet, headString, 8);
        // ②创建表头
        ExportExcelUtil.createThead(wb, sheet, thead, sheetWidth);
        // ③填入数据
        ExportExcelUtil.createTable(wb, sheet, result);

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(new File(filePath + fileName));
            wb.write(fos);
            fos.close();
            System.out.println("导出excel成功");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }

}
