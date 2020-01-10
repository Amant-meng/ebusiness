package com.ccut.ebusiness.module.fileimport.service;

import com.ccut.ebusiness.module.fileimport.entity.ExcelData;
import com.ccut.ebusiness.module.fileimport.entity.Teacher;
import com.ccut.ebusiness.module.fileimport.mapper.ExcelDataMapper;
import com.ccut.ebusiness.module.school.entity.Student;
import com.ccut.ebusiness.module.tool.Message;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *   @author Meng.yang
 *   @ProjectName ebusiness
 *   @title: ExcelDataService
 *   @Description:
 *   @date 2019/11/20
*/
@Slf4j
@Service
public class ExcelDataService {

    @Autowired
    private ExcelDataMapper excelDataMapper;

    /**
     * Excel表格数据导入
     * @param file
     * @return
     * @throws IOException
     */
    public Message importLocks(MultipartFile file) throws IOException {
        List<ExcelData> objectList = new ArrayList<>();
        Message message = Message.N();

        try{
            InputStream inputStream = file.getInputStream();

            Workbook workbook = WorkbookFactory.create(inputStream);
            //获取列数
            int sheets = workbook.getNumberOfSheets();
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
            //获取多少行
            int rows = sheet.getPhysicalNumberOfRows();
            System.out.println("总共："+rows+"行 "+ sheets+"列");


            //遍历每一行，注意：第 0 行为标题
            for (int j = 1; j < rows; j++) {
                //获得第 j 行
                XSSFRow row = sheet.getRow(j);
                row.getCell(0).setCellType(CellType.STRING);
                row.getCell(1).setCellType(CellType.STRING);
                row.getCell(2).setCellType(CellType.STRING);
                ExcelData excelData = new ExcelData();
                excelData.setStu_number(row.getCell(0).getStringCellValue());
                excelData.setStu_name(row.getCell(1).getStringCellValue());
                excelData.setStu_sex(row.getCell(2).getStringCellValue());
                objectList.add(excelData);
                excelDataMapper.insert(excelData);
            }


            workbook.close();
            inputStream.close();

        }catch (Exception e){
            log.info("文件上传异常");
        }

        message.setSuccess(true);
        message.setData(objectList);
        return message;
    }

    /**
     * 导出学生信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/studentInfoExcelOut")
    public Message studentInfoExcelOut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Message message  = new Message();
        try {

            List<Teacher> list = new ArrayList<>();//查询需要导出的数据

            exportExcelBook(request,response,list);

            message.setMessage("导出成功");
            message.setSuccess(true);

        }catch (Exception e){

            log.info("数据导出异常"+e.getMessage());
        }


        return message;

    }


    /**
     * 导出数据生成EXCEL方法
     * @param request
     * @param response
     * @param list
     * @throws IOException
     */
    public void exportExcelBook(HttpServletRequest request, HttpServletResponse response,List<Teacher> list)
            throws IOException {
        try {
            if (CollectionUtils.isEmpty(list)) {
                return;
            }
            //文件名称，客户端传来的参数，防止中文文件名乱码参数编码因此这里需要解码
            String fileName = URLDecoder.decode(request.getParameter("fileName"),"UTF-8");
            //创建Excel工作薄对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建Excel工作表对象Sheet
            HSSFSheet sheet = workbook.createSheet();
            //设置列宽 第一个参数代表列id(从0开始),第2个参数代表宽度值  参考 ："2012-08-10"的宽度为2500
            sheet.setColumnWidth(0, 3000);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 2500);
            sheet.setColumnWidth(4, 3000);
            sheet.setColumnWidth(5, 6000);
            sheet.setColumnWidth(6, 6000);

            // 设置字体
            HSSFFont font = workbook.createFont();
            //设置字体样式
            font.setFontName("宋体");
            //设置字体大小
            font.setFontHeightInPoints((short) 10);
            //设置字体为粗体
            font.setBold(true);


            // 列头的样式
            HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
            //选择需要用到的字体格式  font
            columnHeadStyle.setFont(font);
            // 左右居中
            columnHeadStyle.setAlignment(HorizontalAlignment.CENTER);
            // 上下居中
            columnHeadStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            columnHeadStyle.setLocked(true);
            columnHeadStyle.setWrapText(true);
            // 左边框的颜色
            columnHeadStyle.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
            // 边框的大小
            columnHeadStyle.setBorderLeft(BorderStyle.THIN);
            // 右边框的颜色
            columnHeadStyle.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
            // 边框的大小
            columnHeadStyle.setBorderRight(BorderStyle.THIN);
            // 设置单元格的边框为粗体
            columnHeadStyle.setBorderBottom(BorderStyle.DOUBLE);
            // 设置单元格的边框颜色
            columnHeadStyle.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
            // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
            columnHeadStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            // 设置普通单元格字体样式
//        HSSFFont font = workbook.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 10);

            //创建Excel工作表第一行
            HSSFRow row0 = sheet.createRow(0);
            // 设置行高
            row0.setHeight((short) 750);
            HSSFCell cell = row0.createCell(0);
            //设置单元格内容
            cell.setCellValue(new HSSFRichTextString("学生id"));
            //设置单元格字体样式
            cell.setCellStyle(columnHeadStyle);
            cell = row0.createCell(1);
            cell.setCellValue(new HSSFRichTextString("姓名"));
            cell.setCellStyle(columnHeadStyle);
            cell = row0.createCell(2);
            cell.setCellValue(new HSSFRichTextString("性别"));
            cell.setCellStyle(columnHeadStyle);
            cell = row0.createCell(3);
            cell.setCellValue(new HSSFRichTextString("年龄"));
            cell.setCellStyle(columnHeadStyle);
            cell = row0.createCell(4);
            cell.setCellValue(new HSSFRichTextString("学号"));
            cell.setCellStyle(columnHeadStyle);
            cell = row0.createCell(5);
            cell.setCellValue(new HSSFRichTextString("出生年月"));
            cell.setCellStyle(columnHeadStyle);
            cell = row0.createCell(6);
            cell.setCellValue(new HSSFRichTextString("创建时间"));
            cell.setCellStyle(columnHeadStyle);

            // 循环写入数据
            for (int i = 0; i < list.size(); i++) {
                Teacher teacher = list.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(new HSSFRichTextString(String.valueOf(teacher.getId())));
                cell.setCellStyle(columnHeadStyle);
                cell = row.createCell(1);
                cell.setCellValue(new HSSFRichTextString(teacher.getName()));
                cell.setCellStyle(columnHeadStyle);
                cell = row.createCell(2);
                if(teacher.getSex() == 1){
                    cell.setCellValue(new HSSFRichTextString("男"));
                }else{
                    cell.setCellValue(new HSSFRichTextString("女"));
                }
                cell.setCellStyle(columnHeadStyle);
                cell = row.createCell(3);
                cell.setCellValue(new HSSFRichTextString(String.valueOf(teacher.getAge())));
                cell.setCellStyle(columnHeadStyle);
                cell = row.createCell(4);
                cell.setCellValue(new HSSFRichTextString(String.valueOf(teacher.getT_number())));
                cell.setCellStyle(columnHeadStyle);
                cell = row.createCell(5);
                cell.setCellValue(new HSSFRichTextString(teacher.getBirthday()));
                cell.setCellStyle(columnHeadStyle);
                cell = row.createCell(6);
                cell.setCellValue(new HSSFRichTextString());
                //cell.setCellStyle(columnHeadStyle);
                //cell.setCellValue(new HSSFRichTextString(DateUtils.format(teacher.getC_time(), "yyyy-MM-dd HH:mm:ss")));
            }
            // 获取输出流
            OutputStream os = response.getOutputStream();
            // 重置输出流
            response.reset();
            // 设定输出文件头
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String(fileName.getBytes("GB2312"), "8859_1") + ".xls");
            // 定义输出类型
            response.setContentType("application/msexcel");
            workbook.write(os);
            os.close();
            return;
        }catch (Exception e){

        }

    }

}
