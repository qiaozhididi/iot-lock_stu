package stu158.entity;

import java.util.Date;

public class PwdEntity {
    //    PwdEntity用于装载密码信息。根据数据表编写对应的属性和 Getter 和 Setter
//    id、typ、pwd、status、update_time,expired_time
    private int id;
    private String typ;
    private String pwd;
    private String status;
    private Date update_time;
    private Date expired_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getExpired_time() {
        return expired_time;
    }

    public void setExpired_time(Date expired_time) {
        this.expired_time = expired_time;
    }
}
