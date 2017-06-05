package com.ynyes.lyz.controller.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.lyz.service.TdManagerLogService;
import com.ynyes.lyz.service.TdManagerService;


/**
 * 品牌管理控制器
 * 
 * @author Sharon
 * 
 */
@Controller
@RequestMapping(value="/Verwalter")
public class TdManagerLoginController {
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdManagerService tdManagerService;

    @RequestMapping(value="/login")
    public String login(String username, String password, ModelMap map, HttpServletRequest request){
    	
    	return "redirect:http://manage.leyizhuang.com.cn/Verwalter/login";
    }
    
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request){

        tdManagerLogService.addLog("logout", "用户退出登录", request);
        
        request.getSession().invalidate();
        
        return "redirect:/Verwalter/login";
    }
}
