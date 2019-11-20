package com.ccut.ebusiness.module.fileimport.controller;

import com.ccut.ebusiness.module.aspect.AnnotationLog;
import com.ccut.ebusiness.module.fileimport.service.ExcelDataService;
import com.ccut.ebusiness.module.system.BaseController;
import com.ccut.ebusiness.module.tool.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title:
 *   @Description: 表格数据导入接口
 *   @date 2019/11/20
*/

@Api(description = "数据导入")
@RequestMapping("/cfTiLock")
@RestController
public class ExcelDataController extends BaseController {

    @Autowired
    private ExcelDataService excelDataService;

    @AnnotationLog(value = "文件上传")
    @PostMapping("upload")
    @ApiOperation(value = "文件上传", notes = "文件上传")
    public Message upload(@RequestParam("file") MultipartFile file) throws IOException {
        return excelDataService.importLocks(file);
    }

}
