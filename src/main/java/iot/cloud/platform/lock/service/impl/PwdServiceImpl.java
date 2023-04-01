package iot.cloud.platform.lock.service.impl;

import iot.cloud.platform.lock.entity.PwdEntity;
import iot.cloud.platform.lock.mapper.PwdMapper;
import iot.cloud.platform.lock.service.PwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PwdServiceImpl implements PwdService {

    private final static String PWD_TYPE_FIXED = "fixed";
    private final static String PWD_TYPE_TEMP = "temp";

    @Autowired
    private PwdMapper pwdMapper;

    @Override
    public boolean verifyPwd(String pwd) {
        boolean result = false;
        List<PwdEntity> peList = pwdMapper.getPwd(pwd);
        for (PwdEntity pe : peList) {
            if (pe != null) {
                if (pe.getStatus().equals("enabled")) {
                    if (pe.getType().equals(PWD_TYPE_FIXED)) {//自行完善代码，如果是固定密码，而且状态是enable。
                        result = true;
                    } else if (pe.getType().equals(PWD_TYPE_TEMP) && pe.getExpired_time().getTime() > new Date().getTime()) {//自行完善代码，如果是临时密码，而且未过期，状态是enable
                        result = true;
                    }
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }
}
