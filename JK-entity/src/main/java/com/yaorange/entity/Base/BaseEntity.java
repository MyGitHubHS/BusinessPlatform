package com.yaorange.entity.Base;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;
/**
 * @description: 实体父类
 * @company: yaorange
 * @author: Mr.Huang
 * @version: 1.0
 * @create: 2019/10/18-10-20 10:45:36
 */
@MappedSuperclass
public class BaseEntity {
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "CREATE_DEPT")
    private String createDept;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Column(name = "UPDATE_BY")
    private String updateBy;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
