package com.qipai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserTagService;
import me.chanjar.weixin.mp.bean.tag.WxTagListUser;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;

@RestController
@RequestMapping("/wechat/user/tag")
public class UserController implements WxMpUserTagService{
	@Autowired
	private WxMpService wxMpService;
	
	@Override
	@GetMapping(value = "/tagCreate")
	public WxUserTag tagCreate(String name) throws WxErrorException {
		return wxMpService.getUserTagService().tagCreate(name);
	}

	@Override
	@GetMapping(value = "/tagGet")
	public List<WxUserTag> tagGet() throws WxErrorException {	
		return wxMpService.getUserTagService().tagGet();
	}

	@Override
	public Boolean tagUpdate(Long tagId, String name) throws WxErrorException {
		return null;
	}

	@Override
	public Boolean tagDelete(Long tagId) throws WxErrorException {
		return null;
	}

	@Override
	public WxTagListUser tagListUser(Long tagId, String nextOpenid) throws WxErrorException {
		return null;
	}

	@Override
	@GetMapping(value = "/batchTagging")
	public boolean batchTagging(Long tagId, String[] openids) throws WxErrorException {
		return wxMpService.getUserTagService().batchTagging(tagId, openids);
	}

	@Override
	@PostMapping(value = "/batchUntagging")
	public boolean batchUntagging(Long tagId, String[] openids) throws WxErrorException {
		return wxMpService.getUserTagService().batchUntagging(tagId, openids);
	}

	@Override
	public List<Long> userTagList(String openid) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

}
