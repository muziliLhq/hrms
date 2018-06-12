package com.entor.hrm.service;

import com.entor.hrm.po.Dept;
import com.entor.hrm.service.impl.PageModel;

import java.util.Map;

public interface DeptService {

    Dept getById(Integer id);

    PageModel<Dept> getByPage(Dept dept, Integer pageIndex, Integer pageSize);

    void saveDept(Dept dept);

    void modifyDept(Dept dept);

    void removeDept(Integer id);

    void batchRemoveDept(Integer[] ids);
}
