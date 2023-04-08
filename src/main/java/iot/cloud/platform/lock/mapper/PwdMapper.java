package iot.cloud.platform.lock.mapper;

import iot.cloud.platform.lock.entity.PwdEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 密码验证
 */
@Mapper
public interface PwdMapper {

    /**
     * 根据密码查询所有数据库匹配密码
     *
     * @param pwd
     * @return
     */
    List<PwdEntity> getPwd(String pwd);

    //TODO:修改密码
    String updatePwd(@Param("nPwd") String nPwd, @Param("oPwd") String oPwd);

    //TODO:新增临时密码
//    String addTempPwd(@Param("pwd") String pwd, @Param("expiredTime") Date expiredTime);
}

