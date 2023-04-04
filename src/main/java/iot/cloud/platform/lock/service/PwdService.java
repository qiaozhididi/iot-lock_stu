package iot.cloud.platform.lock.service;

import iot.cloud.platform.lock.vo.ResMsg;

import java.util.Date;

public interface PwdService {

    /**
     * 检查密码是否正确
     * @param pwd
     * @return
     */
    boolean verifyPwd(String pwd);

    //TODO: 新增修改固定密码方法resetFixedPwd
    boolean resetFixedPwd(String pwd);
}
