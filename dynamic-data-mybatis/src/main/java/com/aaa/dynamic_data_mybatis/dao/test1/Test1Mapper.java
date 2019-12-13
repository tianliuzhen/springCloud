package com.aaa.dynamic_data_mybatis.dao.test1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/12/13
 */
@Mapper
@Repository
public interface Test1Mapper {

    /**
     * 测试表一
     * @return java.util.List<java.util.Map>
     */
    List<Map> getAll();

    @Select("select * from  a")
    List<Map> getAll2();
}
