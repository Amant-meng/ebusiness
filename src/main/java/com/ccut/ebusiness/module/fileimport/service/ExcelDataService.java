package com.ccut.ebusiness.module.fileimport.service;

import com.ccut.ebusiness.module.fileimport.entity.ExcelData;
import com.ccut.ebusiness.module.fileimport.mapper.ExcelDataMapper;
import com.ccut.ebusiness.module.tool.Message;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ExcelDataService {

    @Autowired
    private ExcelDataMapper excelDataMapper;

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
                excelData.setName(row.getCell(0).getStringCellValue());
                excelData.setSex(row.getCell(1).getStringCellValue());
                excelData.setAge(row.getCell(2).getStringCellValue());
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
}
