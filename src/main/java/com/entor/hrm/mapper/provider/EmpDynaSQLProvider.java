package com.entor.hrm.mapper.provider;

import com.entor.hrm.po.Employee;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.EMPLOYEE_TABLE;

public class EmpDynaSQLProvider {

    /**
     * 动态分页查询员工记录
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(EMPLOYEE_TABLE);

            // 处理检索条件
            if (params.get("employee") != null) {
                Employee employee = (Employee) params.get("employee");
                if (!StringUtils.isEmpty(employee.getName())) {
                    WHERE("name like concat('%', #{employee.name}, '%')");
                }
                if (employee.getSex() != null) {
                    WHERE("sex = #{employee.sex}");
                }
                if (!StringUtils.isEmpty(employee.getRace())) {
                    WHERE("race like concat('%', #{employee.race}, '%')");
                }
            }
        }}.toString();
        // 处理分页参数
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        }
        return sql;
    }

    /**
     * 动态分页查询员工总记录数
     *
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(EMPLOYEE_TABLE);

            // 处理检索条件
            if (params.get("employee") != null) {
                Employee employee = (Employee) params.get("employee");
                if (!StringUtils.isEmpty(employee.getName())) {
                    WHERE("name like concat('%', #{employee.name}, '%')");
                }
                if (employee.getSex() != null) {
                    WHERE("sex = #{employee.sex}");
                }
                if (!StringUtils.isEmpty(employee.getRace())) {
                    WHERE("race like concat('%', #{employee.race}, '%')");
                }
            }
        }}.toString();
        return sql;
    }

    /**
     * 更新
     * @param employee
     * @return
     */
    public String updateEmp(Employee employee) {
        return new SQL() {{
            UPDATE(EMPLOYEE_TABLE);
            if (!StringUtils.isEmpty(employee.getRace())) {
                SET("race=#{race}");
            }
            if (!StringUtils.isEmpty(employee.getName())) {
                SET("name=#{name}");
            }
            if (!StringUtils.isEmpty(employee.getAddress())) {
                SET("address=#{address}");
            }
            if (!StringUtils.isEmpty(employee.getAge())) {
                SET("age=#{age}");
            }
            if (!StringUtils.isEmpty(employee.getBirthday())) {
                SET("birthday=#{birthday}");
            }
            if (!StringUtils.isEmpty(employee.getEducation())) {
                SET("education=#{education}");
            }
            if (!StringUtils.isEmpty(employee.getEmail())) {
                SET("email=#{email}");
            }
            if (!StringUtils.isEmpty(employee.getHobby())) {
                SET("hobby=#{hobby}");
            }
            if (!StringUtils.isEmpty(employee.getParty())) {
                SET("party=#{party}");
            }
            if (!StringUtils.isEmpty(employee.getTel())) {
                SET("tel=#{tel}");
            }
            if (!StringUtils.isEmpty(employee.getPhone())) {
                SET("phone=#{phone}");
            }
            if (!StringUtils.isEmpty(employee.getPostCode())) {
                SET("post_code=#{postCode}");
            }
            if (!StringUtils.isEmpty(employee.getQqNum())) {
                SET("qq_num=#{qqNum}");
            }
            if (!StringUtils.isEmpty(employee.getRemark())) {
                SET("remark=#{remark}");
            }
            if (!StringUtils.isEmpty(employee.getSpeciality())) {
                SET("speciality=#{speciality}");
            }
            if (employee.getSex() != null) {
                SET("sex=#{sex}");
            }
            if (employee.getDept() != null && employee.getDept().getId() != null) {
                SET("dept_id={dept.id}");
            }
            if (employee.getJob() != null && employee.getJob().getId() != null) {
                SET("job_id=#{job.id}");
            }
            WHERE("id=#{id}");
        }}.toString();
    }


    /**
     * 增加
     * @param employee
     * @return
     */
    public String insertEmp(Employee employee) {
        return new SQL() {{
            INSERT_INTO(EMPLOYEE_TABLE);
            if (!StringUtils.isEmpty(employee.getRace())) {
                VALUES("race", "#{race}");
            }
            if (!StringUtils.isEmpty(employee.getName())) {
                VALUES("name", "#{name}");
            }
            if (!StringUtils.isEmpty(employee.getCardId())) {
                VALUES("card_id", "#{cardId}");
            }
            if (!StringUtils.isEmpty(employee.getAddress())) {
                VALUES("address", "#{address}");
            }
            if (!StringUtils.isEmpty(employee.getAge())) {
                VALUES("age", "#{age}");
            }
            if (!StringUtils.isEmpty(employee.getBirthday())) {
                VALUES("birthday", "#{birthday}");
            }
            if (!StringUtils.isEmpty(employee.getEducation())) {
                VALUES("education", "#{education}");
            }
            if (!StringUtils.isEmpty(employee.getEmail())) {
                VALUES("email", "#{email}");
            }
            if (!StringUtils.isEmpty(employee.getHobby())) {
                VALUES("hobby", "#{hobby}");
            }
            if (!StringUtils.isEmpty(employee.getParty())) {
                VALUES("party", "#{party}");
            }
            if (!StringUtils.isEmpty(employee.getTel())) {
                VALUES("tel", "#{tel}");
            }
            if (!StringUtils.isEmpty(employee.getPhone())) {
                VALUES("phone", "#{phone}");
            }
            if (!StringUtils.isEmpty(employee.getPostCode())) {
                VALUES("post_code", "#{postCode}");
            }
            if (!StringUtils.isEmpty(employee.getQqNum())) {
                VALUES("qq_num", "#{qqNum}");
            }
            if (!StringUtils.isEmpty(employee.getRemark())) {
                VALUES("remark", "#{remark}");
            }
            if (!StringUtils.isEmpty(employee.getSpeciality())) {
                VALUES("speciality", "#{speciality}");
            }
            if (employee.getSex() != null) {
                VALUES("sex", "#{sex}");
            }
            if (employee.getDept() != null && employee.getDept().getId() != null) {
                VALUES("dept_id", "#{dept.id}");
            }
            if (employee.getJob() != null && employee.getJob().getId() != null) {
                VALUES("job_id", "#{job.id}");
            }
        }}.toString();
    }

    /**
     * 批量删除
     * @param params
     * @return
     */
    public String batchDeleteEmp(Map<String, Object> params) {
        StringBuffer sqlBuffer = new StringBuffer("delete from ");
        sqlBuffer.append(EMPLOYEE_TABLE).append(" where id in (");
        if (params.get("ids") != null) {
            Integer[] ids = (Integer[]) params.get("ids");
            if (ids.length > 0 ) {
                for (Integer id: ids) {
                    sqlBuffer.append(id).append(",");
                }
            }
            else {
                sqlBuffer.append("null");
            }
        }
        else {
            sqlBuffer.append("null");
        }
        sqlBuffer.append(")");
        return sqlBuffer.toString().replace(",)", ")");
    }


}
