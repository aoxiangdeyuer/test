package com.qipai.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.qipai.builder.TextBuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class SubscribleHandler extends AbstractHandler{
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,Map<String, Object> context,WxMpService wxMpService,WxSessionManager wxSessionManager){
		//关注公众号事件
		if(wxMpXmlMessage.getMsgType().equals(WxConsts.EventType.SUBSCRIBE)){
			
		}
		String content = "关注公众号成功";
		return new TextBuilder().build(content, wxMpXmlMessage, wxMpService);
	}
}
