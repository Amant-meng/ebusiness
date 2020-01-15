package com.ccut.ebusiness.module.utils.excelutil;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: ExportExcelUtil
 *   @Description: Excel表格导出工具类
 *   @date 2020/01/10
*/
public class ExportExcelUtil {

    /**
     *  创建表格标题
     * @param wb Excel文档对象
     * @param sheet 工作表对象
     * @param headString 标题名称
     * @param col 标题占用列数
     */
    public static void createHeadTittle(HSSFWorkbook wb, HSSFSheet sheet, String headString, int col) {
        HSSFRow row = sheet.createRow(0); // 创建Excel工作表的行
        HSSFCell cell = row.createCell(0); // 创建Excel工作表指定行的单元格
        row.setHeight((short) 1000); // 设置高度

        cell.setCellType(CellType.STRING); // 定义单元格为字符串类型
        cell.setCellValue(new HSSFRichTextString(headString));

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, col)); // 指定标题合并区域

        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 指定单元格水平居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 指定单元格垂直居中个对齐
        cellStyle.setWrapText(true); // 指定单元格自动换行

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBold(true);//设置字体为粗体
        font.setFontName("微软雅黑");
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());//字体颜色
        font.setFontHeightInPoints((short) 16); // 字体大小

        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

    /**
     *  创建表头
     * @param wb Excel文档对象
     * @param sheet 工作表对象
     * @param thead 表头内容
     * @param sheetWidth 每一列宽度
     */
    public static void createThead(HSSFWorkbook wb, HSSFSheet sheet, String[] thead, int[] sheetWidth) {
        HSSFRow row1 = sheet.createRow(1);
        row1.setHeight((short) 600);
        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中对齐
        cellStyle.setWrapText(true);
        //设置背景色灰色25%
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex()); // 设置背景色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderRight(BorderStyle.THIN); // 设置右边框类型
        cellStyle.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex()); // 设置右边框颜色

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBold(true);//设置字体为粗体
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);

        // 设置表头内容
        for (int i = 0; i < thead.length; i++) {
            HSSFCell cell1 = row1.createCell(i);
            cell1.setCellType(CellType.STRING);//定义单元格为字符串类型
            cell1.setCellValue(new HSSFRichTextString(thead[i]));
            cell1.setCellStyle(cellStyle);
        }

        // 设置每一列宽度
        for (int i = 0; i < sheetWidth.length; i++) {
            sheet.setColumnWidth(i, sheetWidth[i]);
        }
    }

    /**
     * 填入数据
     * @param wb Excel文档对象
     * @param sheet 工作表对象
     * @param result 表数据
     */
    public static void createTable(HSSFWorkbook wb, HSSFSheet sheet, List<LinkedHashMap<String, String>> result) {
        // 定义单元格格式，添加单元格表样式，并添加到工作薄
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);

        // 单元格字体
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中

        // 循环插入数据
        for (int i = 0; i < result.size(); i++) {
            HSSFRow row = sheet.createRow(i + 2);
            row.setHeight((short) 400); // 设置高度
            HSSFCell cell = null;
            int j = 0;
            for (String key : (result.get(i).keySet())) {
                cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(new HSSFRichTextString(result.get(i).get(key)));
                j++;
            }
        }
    }


    /**
     * 导出并下载
     * 输出创建的Excel
     * @param fileName
     * @param wb
     * @param resp
     */
    private void respOutPutExcel(String fileName, HSSFWorkbook wb, HttpServletResponse resp) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            wb.write(os);
            System.out.println("导出excel成功");
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            resp.reset();
            resp.setContentType("application/vnd.ms-excel;charset=utf-8");
            resp.setHeader("Content-Disposition",
                    "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = resp.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}


