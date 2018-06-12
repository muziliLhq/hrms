package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.DeptDynaSQLProvider;
import com.entor.hrm.po.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface DeptMapper {

    /**
     * 根据id获取记录
     * @param id
     * @return
     */
    @Select("select * from dept_inf where id = #{id}")
    Dept getById(Integer id);

    /**
     * 动态查询部门
     * @param params
     * @return
     */
    @SelectProvider(type = DeptDynaSQLProvider.class, method = "selectWithParams")
    List<Dept> selectByPage(Map<String, Object> params);

    /**
     * 动态查询部门总记录数
     * @param params
     * @return
     */
    @SelectProvider(type = DeptDynaSQLProvider.class, method = "count")
    int count(Map<String, Object> params);

    /**
     * 动态插入部门
     * @param dept
     */
    @InsertProvider(type = DeptDynaSQLProvider.class, method = "insertDept")
    void insert(Dept dept);

    /**
     * 动态修改部门
     * @param dept
     */
    @UpdateProvider(type = DeptDynaSQLProvider.class, method = "updateDept")
    void update(Dept dept);

    /**
     * 根据id删除部门记录
     * @param id
     */
    @Delete("delete from dept_inf where id = #{id}")
    void delete(Integer id);

    /**
     * 根据id批量删除部门记录
     * @param params
     */
    @DeleteProvider(type = DeptDynaSQLProvider.class, method = "batchDeleteDept")
    void batchDelete(Map<String, Object> params);
}
