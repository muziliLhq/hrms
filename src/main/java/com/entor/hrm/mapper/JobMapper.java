package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.JobDynaSQLProvider;
import com.entor.hrm.po.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface JobMapper {

    /**
     * 根据id获取记录
     * @param id
     * @return
     */
    @Select("select * from job_inf where id=#{id}")
    Job getById(Integer id);

    /**
     * 动态查询职位
     * @param params
     * @return
     */
    @SelectProvider(type = JobDynaSQLProvider.class, method = "selectWithParams")
    List<Job> selectByPage(Map<String, Object> params);

    /**
     * 动态查询职位总记录数
     * @param params
     * @return
     */
    @SelectProvider(type = JobDynaSQLProvider.class, method = "count")
    int count(Map<String, Object> params);

    /**
     * 根据id删除
     * @param id
     */
    @Delete("delete from job_inf where id=#{id}")
    void delete(Integer id);

    /**
     * 增加
     * @param job
     */
    @InsertProvider(type = JobDynaSQLProvider.class, method = "insertJob")
    void insert(Job job);

    /**
     * 修改
     * @param job
     */
    @UpdateProvider(type = JobDynaSQLProvider.class, method = "updateJob")
    void update(Job job);

    /**
     * 根据id批量删除
     * @param params
     */
    @DeleteProvider(type = JobDynaSQLProvider.class, method = "batchDeleteJob")
    void batchDelete(Map<String, Object> params);
}
