package com.ccut.ebusiness.module.school.controller;

import com.ccut.ebusiness.module.school.entity.Student;
import com.ccut.ebusiness.module.school.service.StudentService;
import com.ccut.ebusiness.module.tool.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "学生接口")
@RestController
@RequestMapping(value = "student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "添加学生")
    @RequestMapping(value = "addStuent",method = RequestMethod.POST)
    public Message addStuent(Student student){

        return studentService.addStudent(student);

    }






}