package iot.cloud.platform.lock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  /**
   * 首页
   * @param model
   * @return
   */
  @RequestMapping("/")
  public String index(Model model){
    model.addAttribute("deviceId","Lock01");
    return "index";
  }
}
