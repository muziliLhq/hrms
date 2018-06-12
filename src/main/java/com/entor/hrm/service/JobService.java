package com.entor.hrm.service;

import com.entor.hrm.po.Job;
import com.entor.hrm.service.impl.PageModel;

public interface JobService {

    Job getById(Integer id);

    PageModel<Job> getByPage(Job job, Integer pageIndex, Integer pageSize);

    void modifyJob(Job job);

    void saveJob(Job job);

    void removeJob(Integer id);

    void batchRemoveJob(Integer[] ids);

}
