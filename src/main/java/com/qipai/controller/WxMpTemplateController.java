package com.qipai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

@RestController
@RequestMapping("/portal")
public class WxMpTemplateController implements WxMpTemplateMsgService{

	@Autowired
	private WxMpService wxMpService;
	@Override
	@PostMapping(value = "/setIndustry")
	public boolean setIndustry(WxMpTemplateIndustry wxMpIndustry) throws WxErrorException {
		return wxMpService.getTemplateMsgService().setIndustry(wxMpIndustry);
	}

	@Override
	@GetMapping(value = "/getIndustry")
	public WxMpTemplateIndustry getIndustry() throws WxErrorException {
		return wxMpService.getTemplateMsgService().getIndustry();
	}

	@Override
	@PostMapping(value = "/sendTemplate")
	public String sendTemplateMsg(WxMpTemplateMessage templateMessage) throws WxErrorException {
		return wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
	}

	@Override
	@PostMapping(value = "/addTemplate")
	public String addTemplate(String shortTemplateId) throws WxErrorException {
		return wxMpService.getTemplateMsgService().addTemplate(shortTemplateId);
	}

	@Override
	@GetMapping(value = "/getAll")
	public List<WxMpTemplate> getAllPrivateTemplate() throws WxErrorException {
		return wxMpService.getTemplateMsgService().getAllPrivateTemplate();
	}

	@Override
	@GetMapping(value = "/del")
	public boolean delPrivateTemplate(String templateId) throws WxErrorException {
		return wxMpService.getTemplateMsgService().delPrivateTemplate(templateId);
	}

}
