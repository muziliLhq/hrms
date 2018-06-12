package com.entor.hrm.service;

import com.entor.hrm.po.Notice;
import com.entor.hrm.service.impl.PageModel;

import java.util.List;

public interface NoticeService {


    /**
     * 根据id查询通知
     *
     * @param id
     * @return
     */
    Notice getById(Integer id);

    /**
     * 获取最新通知
     *
     * @return
     */
    Notice getNewest();

    /**
     * 获取近期通知
     *
     * @return
     */
    List<Notice> getRecent();

    /**
     * 分页查询通知
     *
     * @param notice
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageModel<Notice> getByPage(Notice notice, Integer pageIndex, Integer pageSize);

    /**
     * 根据id移除通知
     *
     * @param id
     */
    void removeById(Integer id);

    /**
     * 添加通知
     *
     * @param notice
     */
    void save(Notice notice);

    /**
     * 修改通知
     *
     * @param notice
     */
    void modifyNotice(Notice notice);

    /**
     * 根据id批量移除通知
     *
     * @param ids
     */
    void batchRemoveNotice(Integer[] ids);
}
