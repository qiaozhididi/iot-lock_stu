package iot.cloud.platform.lock.controller;

import iot.cloud.platform.lock.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @Autowired
    private ConfigService configService;

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
//    model.addAttribute("deviceId","Lock01");
        //从数据库中查询设备ID
        model.addAttribute("deviceId", configService.getDeviceId());
        return "index";
    }
}
