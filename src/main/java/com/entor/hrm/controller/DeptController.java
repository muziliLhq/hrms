package com.entor.hrm.controller;

import com.entor.hrm.po.Dept;
import com.entor.hrm.service.DeptService;
import com.entor.hrm.to.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;
/*
@PathVaribale 获取url中的数据

@RequestParam 获取请求参数的值

@GetMapping 组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写*/
@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/list")
    @ResponseBody // 返回JSON数据
    public Object list(@ModelAttribute Dept dept, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return deptService.getByPage(dept, pageIndex, pageSize);
    }

    @GetMapping("/dept/del/{id}")
    @ResponseBody
    public Object delete(@PathVariable("id") Integer id) {
        deptService.removeDept(id);
        return new CommonMessage("删除成功！");
    }

    @RequestMapping("/dept/update")
    public String update(@ModelAttribute Dept dept, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute(deptService.getById(dept.getId()));
            return "dept/hrms_dept_update";
        }
        deptService.modifyDept(dept);
        model.addAttribute(new CommonMessage("修改成功！"));
        return "dept/hrms_dept";
    }

    @GetMapping("/dept/look/{id}")
    public String look(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(deptService.getById(id));
        return "dept/hrms_dept_look";
    }

    @RequestMapping("/dept/add")
    public String add(@ModelAttribute Dept dept, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "dept/hrms_dept_add";
        }
        deptService.saveDept(dept);
        model.addAttribute(new CommonMessage("添加成功！"));
        return "dept/hrms_dept";
    }

    @RequestMapping("/dept/batchDelete")
    @ResponseBody
    public Object batchDelete(@RequestParam("ids[]") Integer[] ids) {
        deptService.batchRemoveDept(ids);
        return new CommonMessage("删除成功！");
    }
}
