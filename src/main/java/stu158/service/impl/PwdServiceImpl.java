package stu158.service.impl;

import stu158.entity.PwdEntity;
import stu158.mockdb.MockDB;


import java.util.Date;
import java.util.List;

public class PwdServiceImpl implements stu158.service.PwdService {
    /**
     * 如果密码正确，则返回true，否则返回false
     *
     * @param password 密码
     * @return boolean
     */
    @Override
    public boolean isValidPassword(String password) {
        boolean result = false;
        List<PwdEntity> pwdList = MockDB.PASSWORD_LIST;
        for (PwdEntity pe : pwdList) {
            if (pe.getStatus().equals("enabled")) {//如果密码是启用了
                if (pe.getExpired_time() != null) {
                    //有过期期限的密码，临时密码
                    if (pe.getExpired_time().getTime() > new Date().getTime()) {
                        System.out.println(pe.getExpired_time().getTime() + "----" + new Date().getTime());
                        //如果未超过当前时间，即未过期
                        if (pe.getPwd().equals(password)) {
                            result = true;
                        }
                    }
                } else {
                    //不会过期的密码，固定密码
                    if (pe.getPwd().equals(password)) {
                        result = true;
                    }
                }
            }
            if (result) {
                return true;
            }
        }
        return false;
    }
}
