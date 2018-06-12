package com.entor.hrm.service;

import com.entor.hrm.mapper.EmpMapper;
import com.entor.hrm.po.Employee;
import com.entor.hrm.service.impl.PageModel;

import java.util.Map;

public interface EmpService {

    Employee getById(Integer id);

    PageModel<Employee> getByPage(Employee employee, Integer pageIndex, Integer pageSize);

    void modifyEmp(Employee employee);

    void save(Employee employee);

    void remove(Integer id);

    void batchRemove(Integer[] ids);

}
