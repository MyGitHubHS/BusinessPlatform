package com.yaorange.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yaorange.entity.Base.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 实体类
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 15-10-12 15:43:00
 */
@Entity
@Table(name = "jk_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid" )
public class User extends BaseEntity {
    @Id
//    @GeneratedValue(generator = "jpa-uuid")
    @GenericGenerator(name = "foreignKey" ,//生成器名称
            strategy = "foreign",//使用hibernate的外键策略
            parameters = @org.hibernate.annotations.Parameter(value = "userInfo",name = "property"))//指定成员属性中的article所在类的主键为本类的主键,这里的参数属性name必须为"property"
    @GeneratedValue(generator = "foreignKey")//使用上述定义的id生成器
    @Column(name = "USER_ID")
    private String id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "STATE")
    private Long state;
    //基于外键的多对一关联
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "DEPT_ID")
    private Dept dept;
    //用户和用户信息是一对一关系
    //基于主键关联
    //单向关联
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserInfo userInfo;
    //基于中间表的多对多关系
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_USER_P",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
//    @JsonIgnore//在返回JSON前端时不序列化当前对象
    private Set<Role> roleSet = new HashSet<Role>();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", dept=" + dept +
                ", userInfo=" + userInfo +
                ", roleSet=" + roleSet +
                '}';
    }
}
