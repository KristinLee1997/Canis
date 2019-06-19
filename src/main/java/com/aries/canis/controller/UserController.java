package com.aries.canis.controller;

import com.aries.canis.constants.SystemStatus;
import com.aries.canis.model.HttpResponse;
import com.aries.canis.model.vo.AdminVo;
import com.aries.canis.service.UserService;
import com.aries.user.gaea.contact.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("admin-list");
        List<UserInfo> adminList = userService.getAdminList();
        if (CollectionUtils.isEmpty(adminList)) {
            log.warn("后台管理系统-查询管理员，返回数据为空");
            return modelAndView;
        }
        modelAndView.addObject("adminList", adminList);
        return modelAndView;
    }

    @RequestMapping("/add/view")
    public ModelAndView adminAdd() {
        ModelAndView modelAndView = new ModelAndView("admin-add");
        return modelAndView;
    }

    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody AdminVo adminVo) {
        if (adminVo.getNickname() == null || adminVo.getPhoneNumber() == null) {
            return HttpResponse.of(SystemStatus.PARAM_ERROR);
        }
        List<UserInfo> userInfoList = userService.checkAdminExist(adminVo.getNickname(), adminVo.getPhoneNumber());
        if (CollectionUtils.isEmpty(userInfoList)) {
            log.warn("添加管理员失败,不存在用户phoneNumber:{},nickName:{}", adminVo.getPhoneNumber(), adminVo.getNickname());
            return HttpResponse.of(SystemStatus.SYSTEM_ERROR);
        }
        List<UserInfo> adminList = userService.checkAdminIsAdmin(adminVo.getNickname(), adminVo.getPhoneNumber());
        if (CollectionUtils.isNotEmpty(adminList)) {
            log.warn("添加管理员失败,用户phoneNumber:{},nickName:{}已经是管理员了", adminVo.getPhoneNumber(), adminVo.getNickname());
            return HttpResponse.of(SystemStatus.SYSTEM_ERROR);
        }
        userService.addAdmin(userInfoList.get(0).getId());
        return HttpResponse.of(SystemStatus.SUCCESS);
    }

    @PostMapping("/reset")
    @ResponseBody
    public String reset(@PathVariable("id") Long id) {
        if (id == null) {
            return HttpResponse.of(SystemStatus.PARAM_ERROR);
        }
        userService.reset(id);
        return HttpResponse.of(SystemStatus.SUCCESS);
    }
}
