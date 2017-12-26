package com.qipai.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class NullHandler extends AbstractHandler{
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,Map<String, Object> context,WxMpService wxMpService,WxSessionManager wxSessionManager){
		//TODO 处理点击菜单连接事件
		return null;
	}
}
