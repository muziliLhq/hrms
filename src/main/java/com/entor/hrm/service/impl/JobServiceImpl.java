package com.entor.hrm.service.impl;

import com.entor.hrm.mapper.JobMapper;
import com.entor.hrm.po.Job;
import com.entor.hrm.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Transactional(readOnly = true)
    @Override
    public Job getById(Integer id) {
        return jobMapper.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageModel<Job> getByPage(Job job, Integer pageIndex, Integer pageSize) {
        // 整理参数
        Map<String, Object> params = new HashMap<>();
        params.put("job", job);
        // 根据检索条件查询记录
        int recordCount = jobMapper.count(params);
        // 整理分页参数
        PageModel<Job> pageModel = new PageModel<>();
        pageModel.setRecordCount(recordCount);
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        if (recordCount > 0) {
            // 根据检索和分页条件查询职位记录，并保存带到新的分页对象中
            params.put("pageModel", pageModel);
            pageModel.setPageList(jobMapper.selectByPage(params));
        }
        return pageModel;
    }

    @Override
    public void modifyJob(Job job) {
        jobMapper.update(job);
    }

    @Override
    public void saveJob(Job job) {
        jobMapper.insert(job);
    }

    @Override
    public void removeJob(Integer id) {
        jobMapper.delete(id);
    }

    @Override
    public void batchRemoveJob(Integer[] ids) {
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);

        jobMapper.batchDelete(params);
    }
}
