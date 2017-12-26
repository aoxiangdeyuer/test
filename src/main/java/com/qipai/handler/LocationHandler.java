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
public class LocationHandler extends AbstractHandler{
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,Map<String, Object> context,WxMpService wxMpService,WxSessionManager wxSessionManager){
		if(wxMpXmlMessage.getMsgType().equals(WxConsts.XmlMsgType.LOCATION)){
			try {
				String content = "您好！感谢您的反馈。";
				return new TextBuilder().build(content,wxMpXmlMessage, null);
			} catch (Exception e) {
				logger.info("地理位置处理失败",e);
			}
		}
		logger.info("上报地理位置：");
		logger.info("纬度："+wxMpXmlMessage.getLatitude());
		logger.info("经度："+wxMpXmlMessage.getLongitude());
		logger.info("精度"+String.valueOf(wxMpXmlMessage.getPrecision()));
		return null;
	}
}
