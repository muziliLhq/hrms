package com.entor.hrm.mapper.provider;

import com.entor.hrm.po.Job;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.JOB_TABLE;

public class JobDynaSQLProvider {

    /**
     * 分页动态查询职位
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(JOB_TABLE);
            // 处理检索条件
            if (params.get("job") != null) {
                Job job = (Job) params.get("job");
                if (!StringUtils.isEmpty(job.getName())) {
                    WHERE("name like concat('%', #{job.name}, '%')");
                }
                if (!StringUtils.isEmpty(job.getRemark())) {
                    WHERE("remark like concat('%', #{job.remark}, '%')");
                }
            }
        }}.toString();

        // 处理分页参数
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
        }
        return sql;
    }

    /**
     * 动态查询职位总记录数
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        return new SQL() {{
            SELECT("count(*)");
            FROM(JOB_TABLE);
            // 处理检索条件
            if (params.get("job") != null) {
                Job job = (Job) params.get("job");
                if (!StringUtils.isEmpty(job.getName())) {
                    WHERE("name like concat('%', #{job.name}, '%')");
                }
                if (!StringUtils.isEmpty(job.getRemark())) {
                    WHERE("remark like concat('%', #{job.remark}, '%')");
                }
            }

        }}.toString();
    }


    /**
     * 动态插入
     * @param job
     * @return
             */
    public String insertJob(Job job) {
        return new SQL() {{
            INSERT_INTO(JOB_TABLE);
            if (!StringUtils.isEmpty(job.getName())) {
                VALUES("name", "#{name}");
            }
            if (!StringUtils.isEmpty(job.getRemark())) {
                VALUES("remark", "#{remark}");
            }
        }}.toString();
    }

    /**
     * 动态修改
     * @param job
     * @return
     */
    public String updateJob(Job job) {
        return new SQL() {{
            UPDATE(JOB_TABLE);
            if (!StringUtils.isEmpty(job.getName())) {
                SET("name=#{name}");
            }
            if (!StringUtils.isEmpty(job.getRemark())) {
                SET("remark=#{remark}");
            }
            WHERE("id=#{id}");
        }}.toString();
    }

    /**
     * 批量删除
     * @param params
     * @return
     */
    public String batchDeleteJob(Map<String, Object> params) {
        StringBuffer sqlBuffer = new StringBuffer("delete from ");
        sqlBuffer.append(JOB_TABLE).append(" where id in (");
        if (params.get("ids") != null) {
            Integer[] ids = (Integer[]) params.get("ids");
            if (ids.length > 0) {
                for (Integer id: ids) {
                    sqlBuffer.append(id).append(",");
                }
            }
            else {
                // 数组中不存在id
                sqlBuffer.append("null");
            }
        }
        else {
            // params中不存在id
            sqlBuffer.append("null");
        }
        sqlBuffer.append(")");
        return sqlBuffer.toString().replace(",)", ")");
    }
}
