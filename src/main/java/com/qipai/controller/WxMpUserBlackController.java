package com.qipai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserBlacklistService;
import me.chanjar.weixin.mp.bean.result.WxMpUserBlacklistGetResult;

@RestController
@RequestMapping("/portal")
public class WxMpUserBlackController implements WxMpUserBlacklistService {
	
	@Autowired
	private WxMpService wxMpService;
	
	@Override
	@GetMapping(value = "/getBlacklist")
	public WxMpUserBlacklistGetResult getBlacklist(@RequestParam("nextOpenid")String nextOpenid) throws WxErrorException {
		return wxMpService.getBlackListService().getBlacklist(nextOpenid);
	}

	@Override
	@PostMapping(value = "/pushToBlacklist")
	public void pushToBlacklist(List<String> openidList) throws WxErrorException {
		wxMpService.getBlackListService().pushToBlacklist(openidList);
	}

	@Override
	@GetMapping(value = "/pullFromBlacklist")
	public void pullFromBlacklist(List<String> openidList) throws WxErrorException {
		wxMpService.getBlackListService().pullFromBlacklist(openidList);
	}

}
