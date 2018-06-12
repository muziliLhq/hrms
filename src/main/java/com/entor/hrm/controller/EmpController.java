package com.entor.hrm.controller;

import com.entor.hrm.po.Employee;
import com.entor.hrm.service.EmpService;
import com.entor.hrm.to.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;

@Controller
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/emp/list")
    @ResponseBody
    public Object list(@ModelAttribute Employee employee, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return empService.getByPage(employee, pageIndex, pageSize);
    }

    @GetMapping("/emp/look/{id}")
    public String look(@PathVariable("id") Integer id, Model model) {
        Employee employee = empService.getById(id);
        // java.util.Date转换成java.sql.Date，主要是为了满足在framemarker页面能正常显示日期，并使用相应的语法
        employee.setCreateDate(new java.sql.Date(employee.getCreateDate().getTime()));
        // 把数据装进model，数据返回页面
        model.addAttribute(employee);
        return "emp/hrms_emp_look";
    }

    @RequestMapping("/emp/update")
    public String update(@ModelAttribute Employee employee, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute(empService.getById(employee.getId()));
            return "emp/hrms_emp_update";
        }
        empService.modifyEmp(employee);
        model.addAttribute(new CommonMessage("修改成功！"));
        return "emp/hrms_emp";
    }

    @GetMapping("/emp/del/{id}")
    @ResponseBody
    public Object del(@PathVariable("id") Integer id) {
        empService.remove(id);
        return new CommonMessage("删除成功！");
    }

    @RequestMapping("/emp/add")
    public String add(@ModelAttribute Employee employee, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "emp/hrms_emp_add";
        }
        empService.save(employee);
        model.addAttribute(new CommonMessage("添加成功！"));
        return "emp/hrms_emp";
    }

    @RequestMapping("/emp/batchDel")
    @ResponseBody
    public Object batchDel(@RequestParam("ids[]") Integer[] ids) {
        empService.batchRemove(ids);
        return new CommonMessage("删除成功！");
    }
}
