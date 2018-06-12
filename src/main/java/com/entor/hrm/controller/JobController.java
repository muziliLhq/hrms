package com.entor.hrm.controller;

import com.entor.hrm.po.Job;
import com.entor.hrm.service.JobService;
import com.entor.hrm.to.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;

@Controller
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/job/list")
    @ResponseBody
    public Object list(@ModelAttribute Job job, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return jobService.getByPage(job, pageIndex, pageSize);
    }

    @GetMapping("/job/del/{id}")
    @ResponseBody
    public Object del(@PathVariable("id") Integer id) {
        jobService.removeJob(id);
        return new CommonMessage("删除成功！");
    }

    @GetMapping("/job/look/{id}")
    public String look(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(jobService.getById(id));
        return "job/hrms_job_look";
    }

    @RequestMapping("/job/update")
    public String update(@ModelAttribute Job job, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute(jobService.getById(job.getId()));
            return "job/hrms_job_update";
        }
        jobService.modifyJob(job);
        model.addAttribute(new CommonMessage("修改成功！"));
        return "job/hrms_job";
    }

    @RequestMapping("/job/add")
    public String add(@ModelAttribute Job job, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "job/hrms_job_add";
        }
        jobService.saveJob(job);
        model.addAttribute(new CommonMessage("添加成功！"));
        return "job/hrms_job";
    }

    @RequestMapping("/job/batchDelete")
    @ResponseBody
    public Object batchDelete(@RequestParam("ids[]") Integer[] ids) {
        jobService.batchRemoveJob(ids);
        return new CommonMessage("删除成功！");
    }
}
