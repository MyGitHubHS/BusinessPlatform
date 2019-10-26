package com.yaorange.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yaorange.entity.Base.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 模块类
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 14-10-17 14:27:32
 */
@Entity
@Table(name = "jk_model")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Module extends BaseEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "MODULE_ID")
    private String id;
    @Column(name = "PARENT_ID")
    private String parentId;//父模块id
    @Column(name = "PARENT_NAME")
    private String parentName;//父模块名称

    @Override
    public String toString() {
        return "Module{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", parentName='" + parentName + '\'' +
                ", name='" + name + '\'' +
                ", layerNum=" + layerNum +
                ", isLeaf=" + isLeaf +
                ", ico='" + ico + '\'' +
                ", cpermission='" + cpermission + '\'' +
                ", curl='" + curl + '\'' +
                ", ctype=" + ctype +
                ", state=" + state +
                ", belong='" + belong + '\'' +
                ", cwhich='" + cwhich + '\'' +
                ", quoteNum=" + quoteNum +
                ", remark='" + remark + '\'' +
                ", orderNo=" + orderNo +
                ", roleSet=" + roleSet +
                ", childModule=" + childModule +
                '}';
    }

    @Column(name = "NAME")
    private String name;//模块名称
    @Column(name = "LAYER_NUM")
    private Long layerNum;//层数
    @Column(name = "IS_LEAF")
    private Long isLeaf;//标识节点
    @Column(name = "ICO")
    private String ico;//图标
    @Column(name = "CPERMISSION")
    private String cpermission;//权限说明
    @Column(name = "CURL")
    private String curl;//关联地址
    @Column(name = "CTYPE")
    private Long ctype;//模块类型：0父模块，1子模块
    @Column(name = "STATE")
    private Long state;//状态：0不可用，1可用
    @Column(name = "BELONG")
    private String belong;//从属
    @Column(name = "CWHICH")
    private String cwhich;//调用
    @Column(name = "QUOTE_NUM")
    private Long quoteNum;//引用编号
    @Column(name = "REMARK")
    private String remark;//备注
    @Column(name = "ORDER_NO")
    private Long orderNo;//订单号
    @JsonIgnore
    @ManyToMany(targetEntity = Role.class,mappedBy = "moduleSet",fetch = FetchType.EAGER)
    private Set<Role> roleSet = new HashSet<Role>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID")
    private Set<Module> childModule = new HashSet<Module>();
    public String getId() {
        return id;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<Module> getChildModule() {
        return childModule;
    }

    public void setChildModule(Set<Module> childModule) {
        this.childModule = childModule;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLayerNum() {
        return layerNum;
    }

    public void setLayerNum(Long layerNum) {
        this.layerNum = layerNum;
    }

    public Long getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Long isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getCpermission() {
        return cpermission;
    }

    public void setCpermission(String cpermission) {
        this.cpermission = cpermission;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }

    public Long getCtype() {
        return ctype;
    }

    public void setCtype(Long ctype) {
        this.ctype = ctype;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getCwhich() {
        return cwhich;
    }

    public void setCwhich(String cwhich) {
        this.cwhich = cwhich;
    }

    public Long getQuoteNum() {
        return quoteNum;
    }

    public void setQuoteNum(Long quoteNum) {
        this.quoteNum = quoteNum;
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
}
