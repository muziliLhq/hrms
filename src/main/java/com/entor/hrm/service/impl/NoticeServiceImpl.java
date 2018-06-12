package com.entor.hrm.service.impl;

import com.entor.hrm.mapper.NoticeMapper;
import com.entor.hrm.po.Notice;
import com.entor.hrm.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("noticeService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Transactional(readOnly = true)
    @Override
    public Notice getById(Integer id) {
        return noticeMapper.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Notice getNewest() {
        return noticeMapper.selectNewest();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Notice> getRecent() {
        return noticeMapper.selectRecent();
    }

    @Transactional(readOnly = true)
    @Override
    public PageModel<Notice> getByPage(Notice notice, Integer pageIndex, Integer pageSize) {
        // 整理参数
        Map<String, Object> params = new HashMap<>();
        params.put("notice", notice);
        // 根据检索条件查询记录总数
        int recordCount = noticeMapper.count(params);
        // 整理分页参数
        PageModel<Notice> pageModel = new PageModel<>();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            // 根据检索和分页条件查询用户记录，保存到分页对象中
            params.put("pageModel", pageModel);
            pageModel.setPageList(noticeMapper.selectByPage(params));
        }
        return pageModel;
    }

    @Override
    public void removeById(Integer id) {
        noticeMapper.deleteById(id);
    }

    @Override
    public void save(Notice notice) {
        noticeMapper.insert(notice);
    }

    @Override
    public void modifyNotice(Notice notice) {
        noticeMapper.update(notice);
    }

    @Override
    public void batchRemoveNotice(Integer[] ids) {
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        noticeMapper.batchDelete(params);
    }
}
