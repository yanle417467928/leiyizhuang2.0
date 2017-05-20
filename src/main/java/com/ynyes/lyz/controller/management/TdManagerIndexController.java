package com.ynyes.lyz.controller.management;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.lyz.entity.TdManager;
import com.ynyes.lyz.entity.TdNavigationMenu;
import com.ynyes.lyz.entity.TdSetting;
import com.ynyes.lyz.service.TdManagerRoleService;
import com.ynyes.lyz.service.TdManagerService;
import com.ynyes.lyz.service.TdNavigationMenuService;
import com.ynyes.lyz.service.TdSettingService;


/**
 * 后台首页控制器
 * 
 * @author Sharon
 */
@Controller
public class TdManagerIndexController {

    @Autowired
    TdNavigationMenuService tdNavigationMenuService;

    @Autowired
    TdManagerService tdManagerService;

    @Autowired
    TdSettingService tdSettingService;
       
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    //增加返回值 manager_role:管理员角色 zp
    @RequestMapping(value = "/Verwalter")
    public String index(ModelMap map, HttpServletRequest req) {
    	
    	return "redirect:http://manage.leyizhuang.com.cn";
    }

    /**
	 * @author lc
	 * @注释：
	 */
    public List<TdNavigationMenu> change(List<TdNavigationMenu> list, TdNavigationMenu shu[]){
    	list.clear();
    	for(int i = 0; i < shu.length; i++){
    		if (null != shu[i]) {
				list.add(shu[i]);
			}
    	}
    	
    	return list;
    } 
    
    @RequestMapping(value = "/Verwalter/center")
    public String center(ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        Properties props = System.getProperties();

        map.addAttribute(
                "os_name",
                props.getProperty("os.name") + " "
                        + props.getProperty("os.version"));
        map.addAttribute("java_home", props.getProperty("java.home"));
        map.addAttribute("java_version", props.getProperty("java.version"));
        map.addAttribute("remote_ip", req.getRemoteAddr());
        map.addAttribute("server_name", req.getServerName());
        map.addAttribute("server_ip", req.getLocalAddr());
        map.addAttribute("server_port", req.getServerPort());

        TdSetting setting = tdSettingService.findTopBy();

        if (null != setting) 
        {
            map.addAttribute("site_name", setting.getTitle());
            map.addAttribute("company_name", setting.getCompany());
        }

        if (!username.equalsIgnoreCase("admin")) {
            TdManager manager = tdManagerService
                    .findByUsernameAndIsEnableTrue(username);
            map.addAttribute("last_ip", manager.getLastLoginIp());
            map.addAttribute("last_login_time", manager.getLastLoginTime());
        }

        String ip = req.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }

        map.addAttribute("client_ip", ip);

        return "/site_mag/center";
    }

  
}
