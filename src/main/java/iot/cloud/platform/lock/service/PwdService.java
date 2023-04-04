package iot.cloud.platform.lock.service;

import iot.cloud.platform.lock.entity.PwdEntity;

public interface PwdService {

    /**
     * 检查密码是否正确
     *
     * @param pwd
     * @return
     */
    boolean verifyPwd(String pwd);

    boolean isValidPwd(String pwd);

    //TODO: 新增修改固定密码方法resetFixedPwd
    PwdEntity resetFixedPwd(String oPwd,String nPwd);
}
