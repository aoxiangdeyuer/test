package com.qipai.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class MenuHandler extends AbstractHandler{
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,Map<String, Object> context,WxMpService wxMpService,WxSessionManager wxSessionManager){
		String msg = String.format("type:%s,event:%s,key:%s", wxMpXmlMessage.getMsgType(),wxMpXmlMessage.getEvent(),wxMpXmlMessage.getEventKey());
		if(MenuButtonType.VIEW.equals(wxMpXmlMessage.getEvent())){
			return null;
		}
		return WxMpXmlOutMessage.TEXT().content(msg).fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser()).build();
	}
}
