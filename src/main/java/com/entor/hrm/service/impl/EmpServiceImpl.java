package com.entor.hrm.service.impl;

import com.entor.hrm.mapper.EmpMapper;
import com.entor.hrm.po.Employee;
import com.entor.hrm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service("empService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Transactional(readOnly = true)
    @Override
    public Employee getById(Integer id) {
        return empMapper.selectById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageModel<Employee> getByPage(Employee employee, Integer pageIndex, Integer pageSize) {
        // 整理参数
        Map<String, Object> params = new HashMap<>();
        params.put("employee", employee);
        // 根据检索条件查询记录总数
        int recordCount = empMapper.count(params);
        // 整理分页参数
        PageModel<Employee> pageModel = new PageModel<>();
        pageModel.setRecordCount(recordCount);
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        if (recordCount > 0) {
            // 根据检索和分页条件查询用户记录，保存到分页对象中去
            params.put("pageModel", pageModel);
            pageModel.setPageList(empMapper.getByPage(params));
        }
        return pageModel;
    }

    @Override
    public void modifyEmp(Employee employee) {
        empMapper.update(employee);
    }

    @Override
    public void save(Employee employee) {
        empMapper.insert(employee);
    }

    @Override
    public void remove(Integer id) {
        empMapper.delete(id);
    }

    @Override
    public void batchRemove(Integer[] ids) {
        // 整理参数
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        empMapper.batchDelete(params);
    }
}
