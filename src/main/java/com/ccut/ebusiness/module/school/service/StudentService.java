package com.ccut.ebusiness.module.school.service;

import com.ccut.ebusiness.module.school.entity.Student;
import com.ccut.ebusiness.module.school.mapper.StudentMapper;
import com.ccut.ebusiness.module.tool.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *   @author Meng.yang
 *   @ProjectName ebusiness
 *   @title: StudentService
 *   @Description: 业务处理层
 *   @date 2019/11/21
*/
@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public Message addStudent(Student student){
        Message message = Message.N();

        try {
            student.setC_time(new Date());
            studentMapper.insert(student);

        }catch (Exception e){
            log.info("添加失败"+e.getMessage());
        }
        return message;
    }












}
