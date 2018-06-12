package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.DeptDynaSQLProvider;
import com.entor.hrm.mapper.provider.EmpDynaSQLProvider;
import com.entor.hrm.po.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.mapping.FetchType.EAGER;

public interface EmpMapper {

    @SelectProvider(type = EmpDynaSQLProvider.class, method = "selectWithParams")
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "dept_id", property = "dept", javaType = com.entor.hrm.po.Dept.class,
            one = @One(select = "com.entor.hrm.mapper.DeptMapper.getById", fetchType = EAGER)),
            @Result(column = "job_id", property = "job", javaType = com.entor.hrm.po.Job.class,
            one = @One(select = "com.entor.hrm.mapper.JobMapper.getById", fetchType = EAGER))
    })
    List<Employee> getByPage(Map<String, Object> params);

    @SelectProvider(type = DeptDynaSQLProvider.class, method = "count")
    int count(Map<String, Object> params);

    @Select("select * from employee_inf where id=#{id}")
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "card_id", property = "cardId", javaType = java.lang.String.class),
            @Result(column = "post_code", property = "postCode", javaType = java.lang.String.class),
            @Result(column = "qq_num", property = "qqNum", javaType = java.lang.String.class),
            @Result(column = "dept_id", property = "dept", javaType = com.entor.hrm.po.Dept.class,
                    one = @One(select = "com.entor.hrm.mapper.DeptMapper.getById", fetchType = EAGER)),
            @Result(column = "job_id", property = "job", javaType = com.entor.hrm.po.Job.class,
                    one = @One(select = "com.entor.hrm.mapper.JobMapper.getById", fetchType = EAGER))
    })
    Employee selectById(Integer id);

    @Delete("delete from employee_inf where id=#{id}")
    void delete(Integer id);

    @InsertProvider(type = EmpDynaSQLProvider.class, method = "insertEmp")
    void insert(Employee employee);

    @UpdateProvider(type = EmpDynaSQLProvider.class, method = "updateEmp")
    void update(Employee employee);

    @DeleteProvider(type = EmpDynaSQLProvider.class, method = "batchDeleteEmp")
    void batchDelete(Map<String, Object> params);
}
