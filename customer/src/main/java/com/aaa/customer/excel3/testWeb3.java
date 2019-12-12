package com.aaa.customer.excel3;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.aaa.customer.excel.EasyPoiUtils;
import com.aaa.customer.excel.FullDataExportDTO;
import com.aaa.customer.excel2.DeptUtil;
import com.aaa.customer.excel2.EmpUtil;
import com.aaa.customer.excel2.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/12/12
 */
@RestController
public class testWeb3 {

    /**
     * 多sheet 测试导出
     *
     * @param response
     * @return void
     */
    @GetMapping("/exportAct3")
    public void exportAct(HttpServletResponse response) throws IOException {
        List<CourseEntity> list = addList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2412312", "测试", "测试"),
                CourseEntity.class, list);
        EasyPoiUtils.download(workbook, response);


    }

    public List<CourseEntity> addList() {
        List<CourseEntity> list = new ArrayList<>();
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId("1");
        courseEntity.setName("课程");
        TeacherEntity mathTeacher = new TeacherEntity();
        mathTeacher.setId("1");
        mathTeacher.setName("老王");
        List<StudentEntity> studentEntities = new ArrayList<>();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId("1");
        studentEntity.setName("tom");
        courseEntity.setMathTeacher(mathTeacher);
        studentEntities.add(studentEntity);
        courseEntity.setStudents(studentEntities);
        list.add(courseEntity);
        return list;
    }

}
