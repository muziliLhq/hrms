package com.entor.hrm.controller;

import com.entor.hrm.po.Notice;
import com.entor.hrm.po.User;
import com.entor.hrm.service.NoticeService;
import com.entor.hrm.to.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;
import static com.entor.hrm.util.common.HrmConstants.USER_SESSION;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 自动转换日期类型的字段格式
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping("/notice/welcome")
    public String welcome(Model model) {
        Notice notice = noticeService.getNewest();
        notice.getUser().setCreateDate(new java.sql.Date(notice.getUser().getCreateDate().getTime()));
        model.addAttribute(notice);
        return "notice/hrms_welcome";
    }

    @GetMapping("/notice/recent")
    @ResponseBody
    public Object recent() {
        return noticeService.getRecent();
    }

    @GetMapping("/notice/list")
    @ResponseBody
    public Object list(@ModelAttribute Notice notice, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return noticeService.getByPage(notice, pageIndex, pageSize);
    }

    @GetMapping("/notice/del/{id}")
    @ResponseBody
    public Object del(@PathVariable("id") Integer id) {
        noticeService.removeById(id);
        return new CommonMessage("删除成功！");
    }

    @GetMapping("/notice/look/{id}")
    public String look(@PathVariable("id") Integer id, Model model) {
        Notice notice = noticeService.getById(id);
        notice.setCreateDate(new java.sql.Date(notice.getCreateDate().getTime()));
        model.addAttribute(notice);
        return "notice/hrms_notice_look";
    }

    @RequestMapping("/notice/update")
    public String update(@ModelAttribute Notice notice, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute(noticeService.getById(notice.getId()));
            return "notice/hrms_notice_update";
        }
        noticeService.modifyNotice(notice);
        model.addAttribute(new CommonMessage("修改成功！"));
        return "notice/hrms_notice";
    }

    @RequestMapping("/notice/add")
    public String add(@ModelAttribute Notice notice, Model model, HttpServletRequest request, HttpSession session) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "notice/hrms_notice_add";
        }
        // 设置关联的user对象
        notice.setUser((User) session.getAttribute(USER_SESSION));
        noticeService.save(notice);
        model.addAttribute(new CommonMessage("添加成功！"));
        return "notice/hrms_notice";
    }

    @RequestMapping("/notice/batchDelete")
    @ResponseBody
    public Object batchDelete(@RequestParam("ids[]") Integer[] ids) {
        noticeService.batchRemoveNotice(ids);
        return new CommonMessage("删除成功！");
    }




}
