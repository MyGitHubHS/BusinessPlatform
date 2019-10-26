package com.yaorange.entity;

import com.yaorange.entity.Base.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @description: 部门类
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 14-10-17 14:27:32
 */
@Entity
@Table(name = "jk_dept")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Dept extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "DEPT_ID")
    private String deptId;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "STATE")
    private Long state;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", state=" + state +
                '}';
    }
}
