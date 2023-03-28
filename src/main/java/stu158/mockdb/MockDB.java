package stu158.mockdb;

import stu158.entity.PwdEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 模拟数据库
 */
public class MockDB {
    /**
     * 密码列表
     */
    public static List<PwdEntity> PASSWORD_LIST = new ArrayList<>();

    static {
        //一个不会过期的固定密码
        PwdEntity pe1 = new PwdEntity();
        pe1.setPwd("123456");
        pe1.setTyp("fixed");
        pe1.setExpired_time(new Date(new Date().getTime() + 1000 * 60 * 30));
        pe1.setStatus("enabled");

        //一个禁用了的临时密码
        PwdEntity pe2 = new PwdEntity();
        pe2.setPwd("000000");
        pe2.setTyp("temp");
        pe2.setExpired_time(new Date(new Date().getTime() + 1000 * 60 * 30));
        pe2.setStatus("disabled");

        //一个启用，但是过期了的临时密码
        PwdEntity pe3 = new PwdEntity();
        pe3.setPwd("666666");
        pe3.setTyp("temp");
        pe3.setExpired_time(new Date(new Date().getTime() - 1000 * 60 * 30));
        pe3.setStatus("enabled");

        PASSWORD_LIST.add(pe1);
        PASSWORD_LIST.add(pe2);
        PASSWORD_LIST.add(pe3);
    }
}

