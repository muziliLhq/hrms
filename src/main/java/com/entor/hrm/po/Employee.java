package com.entor.hrm.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 持久类Employee映射employee_inf表
 */
public class Employee implements Serializable{
    private Integer id;// 员工id
    // 实体类Dept
    private Dept dept;
    // 实体类Job
    private Job job;
    private Integer age;
    private String name;
    private String cardId;// 身份证
    private String address;// 地址
    private String postCode;// 邮编
    private String tel;
    private String phone;
    private String qqNum;
    private String email;
    private Integer sex;
    private String party; // 党派
    // 后台从数据库中查询结果格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    // 前端传参格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    // 后台从数据库中查询结果格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    // 前端传参格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private String race;// 民族
    private String education;// 教育程度
    private String speciality;// 特长
    private String hobby;// 爱好
    private String remark;

    public Employee() {
    }

    public Employee(Integer id, Dept dept, Job job, Integer age,
                    String name, String cardId, String address, String postCode, String tel, String phone,
                    String qqNum, String email, Integer sex, String party, Date createDate, Date birthday,
                    String race, String education, String speciality, String hobby, String remark) {
        this.id = id;
        this.dept = dept;
        this.job = job;
        this.age = age;
        this.name = name;
        this.cardId = cardId;
        this.address = address;
        this.postCode= postCode;
        this.tel = tel;
        this.phone = phone;
        this.qqNum = qqNum;
        this.email = email;
        this.sex = sex;
        this.party = party;
        this.createDate = createDate;
        this.birthday = birthday;
        this.race = race;
        this.education = education;
        this.speciality = speciality;
        this.hobby = hobby;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", dept=" + dept +
                ", job=" + job +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", tel='" + tel + '\'' +
                ", phone='" + phone + '\'' +
                ", qqNum='" + qqNum + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", party='" + party + '\'' +
                ", createDate=" + createDate +
                ", birthday=" + birthday +
                ", race='" + race + '\'' +
                ", education='" + education + '\'' +
                ", speciality='" + speciality + '\'' +
                ", hobby='" + hobby + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
