package com.yaorange.entity;

import javax.persistence.*;

/**
 * @description: 实体类
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 15-10-12 15:43:00
 */
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String psaaword;


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

    public String getPsaaword() {
        return psaaword;
    }

    public void setPsaaword(String psaaword) {
        this.psaaword = psaaword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", psaaword='" + psaaword + '\'' +
                '}';
    }
}
