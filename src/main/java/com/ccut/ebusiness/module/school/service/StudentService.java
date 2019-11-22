package com.ccut.ebusiness.module.school.service;

import com.ccut.ebusiness.module.school.entity.Student;
import com.ccut.ebusiness.module.school.mapper.StudentMapper;
import com.ccut.ebusiness.module.tool.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

            message.setSuccess(true);
            message.setMessage("添加成功");

        }catch (Exception e){
            message.setSuccess(false);
            message.setMessage("添加失败");
            log.info("添加失败"+e.getMessage());
        }

        return message;

    }



    public List<Map> getStudentList(){
        List<Map>  studentList = null;
        try {
            studentList = studentMapper.getStudentList();
        }catch (Exception e){
            log.info("查询失败" +e.getMessage());
        }
        return studentList;
    }








}
