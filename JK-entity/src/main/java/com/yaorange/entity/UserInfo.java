package com.yaorange.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yaorange.entity.Base.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
/**
 * @description: 用户信息实体
 * @company: yaorange
 * @author: Mr.Huang
 * @version: 1.0
 * @create: 2019/10/18-10-20 10:49:53
 */
@Entity
@Table(name = "JK_USERINFO")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo extends BaseEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "USER_INFO_ID")
    private String id;
    @Column(name = "NAME")
    private String name;//姓名

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "JOIN_DATE")
    private Date joinDate;//入职日期
    @Column(name = "SALARY")
    private Long salary;//工资

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "BIRTHDAY")
    private Date birthday;//生日
    @Column(name = "GENDER")
    private String gender;//性别
    @Column(name = "STATION")
    private String station;//岗位
    @Column(name = "TELEPHONE")
    private String telephone;//电话
    @Column(name = "DEGREE")
    private Long degree;//等级:0-超级管理员,1-跨部门跨人员,2-管理所有下属部门和人员,3-管理本部门,4-普通员工
    @Column(name = "REMARK")
    private String remark;//备注
    @Column(name = "ORDER_NO")
    private Long orderNo;//订单号
    @Column(name = "EMAIL")
    private String email;//邮箱
    @OneToOne
    @JoinColumn(name = "MANAGER_ID")
    @JsonIgnoreProperties("userInfo")
    private User manager;
 /*   @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn//如果不加此注解，hibernate会在数据库默认生成一条article_id属性
    private User user;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getDegree() {
        return degree;
    }

    public void setDegree(Long degree) {
        this.degree = degree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", station='" + station + '\'' +
                ", telephone='" + telephone + '\'' +
                ", degree=" + degree +
                ", remark='" + remark + '\'' +
                ", orderNo=" + orderNo +
                ", email='" + email + '\'' +
                ", manager=" + manager +
                '}';
    }
}
