package com.qipai.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qipai.builder.TextBuilder;
import com.qipai.util.JsonUtils;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class MsgHandler extends AbstractHandler{
	private static final Logger logger = LoggerFactory.getLogger(MsgHandler.class);
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,Map<String, Object> context,WxMpService wxMpService,WxSessionManager wxSessionManager){
		//组装回复消息
		String content = "收到信息内容：" + JsonUtils.toJson(wxMpXmlMessage);
		return new TextBuilder().build(content, wxMpXmlMessage, wxMpService);
	}
}
