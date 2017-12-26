package com.qipai.config;

import static org.junit.Assert.assertTrue;

import java.awt.Menu;

import org.apache.poi.ss.formula.functions.Even;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qipai.handler.KfSessionHandler;
import com.qipai.handler.LocationHandler;
import com.qipai.handler.LogHandler;
import com.qipai.handler.MenuHandler;
import com.qipai.handler.MsgHandler;
import com.qipai.handler.NullHandler;
import com.qipai.handler.Scanhandler;
import com.qipai.handler.StoreCheckNotifyHandler;
import com.qipai.handler.SubscribleHandler;
import com.qipai.handler.UnSubscribeHandler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.api.WxConsts.MassMsgType;
import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;


@Configuration
@ConditionalOnClass(WxMpService.class)
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WxMpProperties properties;
	
	@Autowired
	private LogHandler logHandler;
	
	@Autowired
	protected MsgHandler msgHandler;
	
	@Autowired
	private SubscribleHandler subscribleHandler;
	
	@Autowired
	private UnSubscribeHandler unSubscribleHandler;
	
	@Autowired
	private LocationHandler locationHandler;
	
	@Autowired
	private KfSessionHandler kfSessionHandler;
	
	@Autowired
	private NullHandler nullHandler;
	
	@Autowired
	private Scanhandler scanhandler;
	
	@Autowired
	private MenuHandler menuHandler;
	
	@Autowired
	private StoreCheckNotifyHandler storeCheckNotifyHandler;
	
	public KfSessionHandler getKfSessionHandler() {
		return kfSessionHandler;
	}

	public NullHandler getNullHandler() {
		return nullHandler;
	}

	public Scanhandler getScanhandler() {
		return scanhandler;
	}

	public MenuHandler getMenuHandler() {
		return menuHandler;
	}

	public StoreCheckNotifyHandler getStoreCheckNotifyHandler() {
		return storeCheckNotifyHandler;
	}
	
	public LocationHandler getLocationHandler() {
		return locationHandler;
	}

	public LogHandler getLogHandler() {
		return logHandler;
	}

	public MsgHandler getMsgHandler() {
		return msgHandler;
	}

	public SubscribleHandler getSubscribleHandler() {
		return subscribleHandler;
	}

	public UnSubscribeHandler getUnSubscribleHandler() {
		return unSubscribleHandler;
	}

	@Bean
	@ConditionalOnMissingBean
	public WxMpConfigStorage configStorage(){
		WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
		configStorage.setAppId(properties.getAppId());
		configStorage.setToken(properties.getToken());
		configStorage.setSecret(properties.getSecret());
		configStorage.setAesKey(properties.getAesKey());
		return configStorage;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public WxMpService wxMpService(WxMpConfigStorage configStorage){
		WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(configStorage);
		return wxMpService;
	}
	@Bean
	public WxMpMessageRouter router(WxMpService wxMpService){
		final WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
		//记录所有事件的日志(异步执行)
		router.rule().handler(this.logHandler).next();
		//关注事件
		router.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.SUBSCRIBE).handler(this.subscribleHandler).end();
		//取消关注事件
		router.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.UNSUBSCRIBE).handler(this.unSubscribleHandler).end();
		//地理位置上传事件
		router.rule().async(false).msgType(XmlMsgType.LOCATION).event(EventType.LOCATION).handler(this.locationHandler).end();
		//地理位置接收事件
		router.rule().async(false).msgType(XmlMsgType.LOCATION).event(EventType.LOCATION).handler(this.locationHandler).end();
		//客服处理事件
		router.rule().async(false).msgType(XmlMsgType.EVENT).event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION).handler(this.kfSessionHandler).end();
		router.rule().async(false).msgType(XmlMsgType.EVENT).event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION).handler(this.kfSessionHandler).end();
		router.rule().async(false).msgType(XmlMsgType.EVENT).event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION).handler(this.kfSessionHandler).end();
		//扫码事件
		router.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.SCAN).handler(this.scanhandler).end();
		//点击菜单连接事件
		router.rule().async(false).msgType(XmlMsgType.EVENT).event(MenuButtonType.VIEW).handler(this.nullHandler).end();
		//门店审核事件
		router.rule().async(false).msgType(XmlMsgType.EVENT).event(WxMpEventConstants.POI_CHECK_NOTIFY).handler(this.storeCheckNotifyHandler).end();
		//自定义菜单事件
		router.rule().async(false).msgType(XmlMsgType.EVENT).event(MenuButtonType.CLICK).handler(this.menuHandler).end();
		//默认事件
		router.rule().async(false).handler(this.msgHandler).end();
		//返回路由
		return router;
	}
}
