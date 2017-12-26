package com.qipai.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qipai.WechatApplication;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@RestController
@RequestMapping("/portal")
public class WxController {
	
	private static final Logger logger = LoggerFactory.getLogger(WechatApplication.class);
	
	@Autowired
	private WxMpService wxMpService;
	
	@Autowired
	private WxMpMessageRouter router;
	
	@GetMapping(produces = "text/plain;charset=utf-8")
	public String authGet(
		@RequestParam(name = "signature",required = false) String signature,//微信加密签名
        @RequestParam(name = "timestamp",required = false) String timestamp,//时间戳
        @RequestParam(name = "nonce", required = false) String nonce,//随机数
        @RequestParam(name = "echostr", required = false) String echostr) {//随机字符串

    this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {},{}]", signature,
            timestamp, nonce, echostr);

    if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
        throw new IllegalArgumentException("请求参数非法，请核实!");
    }

    if (this.wxMpService.checkSignature(timestamp, nonce, signature)) {
//    wxUtils.updateAccessTokenByToken(wxService.getWxMpConfigStorage().getToken(),wxService);
        return echostr;
    }
    return "非法请求";
	}
	  @PostMapping(produces = "application/xml; charset=UTF-8")
	   public String post(
			   			   @RequestBody String requestBody,
	                       @RequestParam("signature") String signature,
	                       @RequestParam("timestamp") String timestamp,
	                       @RequestParam("nonce") String nonce,
	                       @RequestParam(name = "encrypt_type",required = false) String encType,
	                       @RequestParam(name = "msg_signature",required = false) String msgSignature) {
	        this.logger.info(
	                "\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
	                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
	                signature, encType, msgSignature, timestamp, nonce);
	 
	        if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {//验证消息的确来自微信服务器
	            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
	        }
	        String out = null;
	        if (encType == null) {
	            // 明文传输的消息
	            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
	            WxMpXmlOutMessage outMessage = this.route(inMessage);
	            if (outMessage == null) {
	                return "";
	            }

	            out = outMessage.toXml();
	        } else if ("aes".equals(encType)) {  // aes加密的消息
	            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
	                    requestBody, this.wxMpService.getWxMpConfigStorage(), timestamp,
	                    nonce, msgSignature);
	            this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
	            WxMpXmlOutMessage outMessage = this.route(inMessage);
	            if (outMessage == null) {
	                return "";
	            }

	            out = outMessage
	                    .toEncryptedXml(this.wxMpService.getWxMpConfigStorage());
	        }
	        
	        this.logger.debug("\n组装回复信息：{}", out);
	        return out;
	    }

	    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
	        try {
	            return this.router.route(message);
	        } catch (Exception e) {
	            this.logger.error(e.getMessage(), e);
	        }

	        return null;
	    }
}
