package iot.cloud.platform.lock.entity;

import java.util.Date;

public class PwdEntity {
    private Long id;
    //其他属性、getter、setter请自行完成
    private String pwd;
    private String typ;
    private String status;
    private Date upt_time;
    private Date expired_time;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setType(String type) {
        this.typ = typ;
    }

    public String getType() {
        return typ;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpt_time(Date upt_time) {
        this.upt_time = upt_time;
    }

    public Date getUpt_time() {
        return upt_time;
    }

    public void setExpired_time(Date expired_time) {
        this.expired_time = expired_time;
    }

    public Date getExpired_time() {
        return expired_time;
    }
}
