package com.k2data.kbc.announce.model;

import java.util.Date;

public class Announce {

    private Integer id;
    private String content;
    private String remark;
    private Date startTime;
    private Date endTime;
    private String creator;
    private Date createDate;

    /**
     * 获取物理主键
     *
     * @return id - 物理主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置物理主键
     *
     * @param id 物理主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取消息正文内容
     *
     * @return content - 消息正文内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置消息正文内容
     *
     * @param content 消息正文内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取消息展示启动时间
     *
     * @return start_time - 消息展示启动时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置消息展示启动时间
     *
     * @param startTime 消息展示启动时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取消息展示结束时间
     *
     * @return end_time - 消息展示结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置消息展示结束时间
     *
     * @param endTime 消息展示结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取创建者id
     *
     * @return creator - 创建者id
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者id
     *
     * @param creator 创建者id
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}