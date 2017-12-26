package com.qipai.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class UnSubscribeHandler extends AbstractHandler{
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,Map<String, Object> context,WxMpService wxMpService,WxSessionManager sessionManager){		
		return null;
	}
}
