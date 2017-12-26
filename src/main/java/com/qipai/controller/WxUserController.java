package com.qipai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.WxMpUserQuery;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

@RestController
@RequestMapping("/wechat/user")
public class WxUserController implements WxMpUserService{
	
	@Autowired
	private WxMpService wxMpService;
	@GetMapping(value = "/createUser")
	@Override
	public void userUpdateRemark(@RequestParam("openid")String openid, @RequestParam("remark")String remark) throws WxErrorException {
		wxMpService.getUserService().userUpdateRemark(openid, remark);
	}

	@Override
	@GetMapping("/getUserInfo")
	public WxMpUser userInfo(@RequestParam("openid")String openid) throws WxErrorException {
		return wxMpService.getUserService().userInfo(openid);
	}

	@Override
	public WxMpUser userInfo(String openid, String lang) throws WxErrorException {
		return null;
	}

	@Override
	public List<WxMpUser> userInfoList(List<String> openids) throws WxErrorException {
		return null;
	}

	@Override
	public List<WxMpUser> userInfoList(WxMpUserQuery userQuery) throws WxErrorException {
		return null;
	}

	@Override
	public WxMpUserList userList(String nextOpenid) throws WxErrorException {
		return null;
	}

}
