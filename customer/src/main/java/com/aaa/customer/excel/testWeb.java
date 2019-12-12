package com.aaa.customer.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
public class testWeb {
    @GetMapping("/exportAct")
    public void exportAct( HttpServletResponse response){
        try {
            // 这里模拟测试数据
            List<FullData> fullData=addList();

            List<FullDataExportDTO> list= Lists.newArrayList();
            for (FullData fullDatum : fullData) {
                FullDataExportDTO fullDataExportDTO=new FullDataExportDTO();
                BeanCopyUtil.copyProperties(fullDatum,fullDataExportDTO,true);
                list.add(fullDataExportDTO);
            }

            Workbook workbook = null;
            ExportParams exportParams = new ExportParams();
            if(!list.isEmpty()){
                workbook = ExcelExportUtil.exportBigExcel(exportParams, FullDataExportDTO.class, list);
                ExcelExportUtil.closeExportBigExcel();
                EasyPoiUtils.download(workbook, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<FullData>  addList(){
        List<FullData> list=new ArrayList<>();
        FullData f1=new FullData();
        f1.setPayNum("111");
        f1.setOrderUserNum("111");
        list.add(f1);
        FullData f2=new FullData();
        f2.setPayNum("222");
        f2.setOrderUserNum("222");
        list.add(f2);
        FullData f3=new FullData();
        f3.setPayNum("333");
        f3.setOrderUserNum("333");
        list.add(f3);

        return list;
    }
}
