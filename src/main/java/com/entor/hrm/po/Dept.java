package com.entor.hrm.po;

import java.io.Serializable;

/**
 * 部门持久类，与dept_inf表对应
 */
public class Dept implements Serializable{
    private Integer id;
    private String name;
    private String remark;

    public Dept() {
    }

    public Dept(Integer id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
