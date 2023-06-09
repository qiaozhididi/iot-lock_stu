package iot.cloud.platform.lock.service.impl;

import iot.cloud.platform.lock.entity.PwdEntity;
import iot.cloud.platform.lock.mapper.PwdMapper;
import iot.cloud.platform.lock.service.PwdService;
import iot.cloud.platform.lock.vo.ResMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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

    //密码正则验证
    @Override
    public boolean isValidPwd(String pwd) {
        String tpwd = StringUtils.trim(pwd);
        boolean result = false;
        Pattern pattern = Pattern.compile("[0-9]{6,18}");//正则表达式校验
        if (pattern.matcher(tpwd).matches()) {
            result = true;
        }
        return result;
    }

    //TODO: 新增修改固定密码方法resetFixedPwd
    @Override
    public PwdEntity resetFixedPwd(String oPwd, String nPwd) {
        PwdEntity pwd = new PwdEntity();
        pwd.setPwd(oPwd);
        if (isValidPwd(oPwd)) {
            pwdMapper.updatePwd(nPwd, oPwd);
        }
        return pwd;
    }

    //TODO: 新增临时密码方法addTempPwd
//    @Override
//    public ResMsg addTempPwd(String pwd, Date expiredTime) {
//        ResMsg resMsg = new ResMsg();
//        if (isValidPwd(pwd)) {
//            PwdEntity pwdEntity = new PwdEntity();
//            pwdEntity.setPwd(pwd);
//            pwdEntity.setExpired_time(expiredTime);
//            pwdEntity.setType(PWD_TYPE_TEMP);
//            pwdEntity.setStatus("enabled");
//            pwdMapper.addTempPwd(pwd, expiredTime);
//        } else {
//            return resMsg;
//        }
//        return resMsg;
//    }
}
