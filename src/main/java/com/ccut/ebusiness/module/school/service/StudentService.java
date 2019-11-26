package com.ccut.ebusiness.module.school.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ccut.ebusiness.module.school.entity.Student;
import com.ccut.ebusiness.module.school.mapper.StudentMapper;
import com.ccut.ebusiness.module.tool.Message;
import com.ccut.ebusiness.module.tool.Page;
import com.ccut.ebusiness.module.tool.PageData;
import com.ccut.ebusiness.module.tool.QueryUntil;
import com.ccut.ebusiness.module.tool.toolentity.Filed;
import com.ccut.ebusiness.module.tool.toolentity.TextFiled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public PageData queryStudentList(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            queryFileds.add(new TextFiled("stu_number", params));
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("()");
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, " and deltag = '0' order by c_time desc", "student");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(studentMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(studentMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }

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

    public Message delStudent(String stu_number){
        Message message = Message.N();
        try {
            Wrapper<Student> studentWrapper = new EntityWrapper<>();
            studentWrapper.eq("stu_number",stu_number);
            Student student = new Student();
            student.setDeltag("1");
            studentMapper.update(student,studentWrapper);

            message.setSuccess(true);
            message.setMessage("删除成功");
        }catch (Exception e){
            log.info("删除失败"+e.getMessage());
            message.setSuccess(false);
            message.setMessage("删除失败");
        }

        return message;

    }


}
