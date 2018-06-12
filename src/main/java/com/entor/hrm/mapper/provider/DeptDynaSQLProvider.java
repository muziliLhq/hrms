package com.entor.hrm.mapper.provider;

import com.entor.hrm.po.Dept;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.DEPT_TABLE;

public class DeptDynaSQLProvider {

    /**
     * 分页动态查询部门
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(DEPT_TABLE);
            // 处理检索条件
            if (params.get("dept") != null) {
                Dept dept = (Dept) params.get("dept");
                if (!StringUtils.isEmpty(dept.getName())) {
                    WHERE("name like concat('%',  #{dept.name},'%')");
                }
                if (!StringUtils.isEmpty(dept.getRemark())) {
                    WHERE("remark like concat('%',  #{dept.remark},'%')");
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
     * 动态查询部门总记录数
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(DEPT_TABLE);

            // 处理检索条件
            if (params.get("dept") != null) {
                Dept dept = (Dept) params.get("dept");
                if (!StringUtils.isEmpty(dept.getName())) {
                    WHERE("name like concat('%', #{dept.name},'%')");
                }
                if (!StringUtils.isEmpty(dept.getRemark())) {
                    WHERE("remark like concat('%', #{dept.remark},'%')");
                }
            }
        }}.toString();
        return sql;
    }

    /**
     * 插入
     * @param dept
     * @return
     */
    public String insertDept(Dept dept) {
        return new SQL() {{
            INSERT_INTO(DEPT_TABLE);
            if (!StringUtils.isEmpty(dept.getName())) {
                VALUES("name", "#{name}");
            }
            if (!StringUtils.isEmpty(dept.getRemark())) {
                VALUES("remark", "#{remark}");
            }
        }}.toString();
    }

    /**
     * 修改
     * @param dept
     * @return
     */
    public String updateDept(Dept dept) {
        return new SQL() {{
            UPDATE(DEPT_TABLE);
            if (!StringUtils.isEmpty(dept.getName())) {
                SET("name=#{name}");
            }
            if (!StringUtils.isEmpty(dept.getRemark())) {
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
    public String batchDeleteDept(Map<String, Object> params) {
       StringBuffer sqlBuffer = new StringBuffer("delete from ");
       sqlBuffer.append(DEPT_TABLE).append(" where id in(");
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
       return sqlBuffer.toString().replace(",)",")");
    }
}
