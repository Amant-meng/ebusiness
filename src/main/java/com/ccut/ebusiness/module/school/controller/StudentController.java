package com.ccut.ebusiness.module.school.controller;

import com.ccut.ebusiness.module.school.entity.Student;
import com.ccut.ebusiness.module.school.service.StudentService;
import com.ccut.ebusiness.module.tool.Message;
import com.ccut.ebusiness.module.tool.Page;
import com.ccut.ebusiness.module.tool.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(description = "学生接口")
@RequestMapping( "/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "分页查询学生")
    @RequestMapping(value = "queryStudentList",method = RequestMethod.GET)
    public PageData queryStudentList(Page page){
        return studentService.queryStudentList(page);
    }

    @ApiOperation(value = "添加学生")
    @RequestMapping(value = "addStudent",method = RequestMethod.POST)
    public Message addStudent(@RequestBody Student student){

        return studentService.addStudent(student);

    }

    @ApiOperation(value = "查询学生")
    @RequestMapping(value = "getStudentList",method = RequestMethod.GET)
    public Message getStudentList(){
        List<Map> studentList = studentService.getStudentList();
        return Message.success(studentList);

    }

    @ApiOperation(value = "删除学生")
    @RequestMapping(value = "delStudent/{stu_number}",method = RequestMethod.DELETE)
    public Message delStudent(@PathVariable String stu_number){
        return studentService.delStudent(stu_number);
    }


}
