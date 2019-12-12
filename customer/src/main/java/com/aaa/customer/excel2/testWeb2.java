package com.aaa.customer.excel2;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.aaa.customer.excel.EasyPoiUtils;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
public class testWeb2 {

    /**
     * 测试导出
     * @param response
     * @return void
     */
    @GetMapping("/exportAct2")
    public void exportAct( HttpServletResponse response){
        export(response);

    }

    public String export(HttpServletResponse response){

        Workbook workBook = null;
        try {
            // 有一个问题就是如果sheet填充的数据源是一样的，那么第二个sheet的内容就会为空
            // 这里模拟两个数据源
            List<DeptUtil> exportList =addList();
            List<DeptUtil> exportList2 =addList2();
            System.err.println(JSONArray.toJSONString(exportList));

            // 创建参数对象（用来设定excel得sheet得内容等信息）
            ExportParams deptExportParams = new ExportParams();
            // 设置sheet得名称
            deptExportParams.setSheetName("员工报表1");
            // 创建sheet1使用得map
            Map<String, Object> deptExportMap = new HashMap<>();
            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
            deptExportMap.put("title", deptExportParams);
            // 模版导出对应得实体类型
            deptExportMap.put("entity", DeptUtil.class);
            // sheet中要填充得数据
            deptExportMap.put("data", exportList);

            ExportParams empExportParams = new ExportParams();
            empExportParams.setSheetName("员工报表2");
            // 创建sheet2使用得map
            Map<String, Object> empExportMap = new HashMap<>();
            empExportMap.put("title", empExportParams);
            empExportMap.put("entity", DeptUtil.class);
            empExportMap.put("data", exportList2);

            // 将sheet1、sheet2、sheet3使用得map进行包装
            List<Map<String, Object>> sheetsList = new ArrayList<>();
            sheetsList.add(deptExportMap);
            sheetsList.add(empExportMap);
            // 执行方法
            workBook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
            EasyPoiUtils.download(workBook, response);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(workBook != null) {
                try {
                    workBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }

    public List<DeptUtil>  addList(){
        List<DeptUtil> list=new ArrayList<>();
        List<EmpUtil> empUtilList = getEmpUtils();

        DeptUtil deptUtil=new DeptUtil();
        deptUtil.setDeptName("开发部");
        deptUtil.setId(1);
        deptUtil.setEmps(empUtilList);
        list.add(deptUtil);

        return list;
    }
    public List<DeptUtil>  addList2(){
        List<DeptUtil> list=new ArrayList<>();
        List<EmpUtil> empUtilList = getEmpUtils();

        DeptUtil deptUtil=new DeptUtil();
        deptUtil.setDeptName("开发部2");
        deptUtil.setId(2);
        deptUtil.setEmps(empUtilList);
        list.add(deptUtil);

        return list;
    }
    private List<EmpUtil> getEmpUtils() {
        List<EmpUtil> empUtilList= Lists.newArrayList();

        EmpUtil empUtil= new EmpUtil();
        empUtil.setAge(1);
        empUtil.setEmpName("tom");
        empUtilList.add(empUtil);
        EmpUtil empUtil2= new EmpUtil();
        empUtil2.setAge(2);
        empUtil2.setEmpName("tom2");
        empUtilList.add(empUtil2);
        return empUtilList;
    }
}
