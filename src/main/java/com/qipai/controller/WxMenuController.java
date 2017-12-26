package com.qipai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;

@RestController
@RequestMapping("/wechat/menu")
public class WxMenuController implements WxMpMenuService{

	@Autowired
	private WxMpService wxMpService;
	
	@Override
	@PostMapping(value = "/createMenu")
	public String menuCreate(@RequestBody WxMenu menu) throws WxErrorException {
		return wxMpService.getMenuService().menuCreate(menu);
	}

	@GetMapping(value = "/getMenuSample")
	public String menuCreateSample() throws WxErrorException{
		WxMenu menu = new WxMenu();
		WxMenuButton button1 = new WxMenuButton();
		
		button1.setName("陕西小吃");
		button1.setType("click");
		button1.setUrl("http://www.baidu.com");
		button1.setKey("V1001_GOOD");
		
		
		WxMenuButton button11 = new WxMenuButton();
		
		button11.setName("陕西凉皮");
		button11.setType("view");
		button11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx281669dc9164c874&redirect_uri=http://bb26a2cd.ngrok.io/wechat/index.html&response_type=code&scope=snsapi_userinfo&state=STAT#wechat_redirect");
		
		WxMenuButton button12 = new WxMenuButton();
		
		button12.setName("陕西肉夹馍");
		button12.setType("view");
		button12.setUrl("http://www.baidu.com");
		
		WxMenuButton button13 = new WxMenuButton();
		
		button13.setName("陕西饸饹");
		button13.setType("view");
		button13.setUrl("http://www.sina.com.cn");
		
		button1.getSubButtons().add(button11);
		button1.getSubButtons().add(button12);
		button1.getSubButtons().add(button13);
		
		WxMenuButton button2 = new WxMenuButton();
		
		button2.setName("古迹游览");
		button2.setType("click");
		button2.setUrl("http://www.soso.com/");
		button2.setKey("V1001_GOOD");
		
		WxMenuButton button21 = new WxMenuButton();
		
		button21.setName("乾陵");
		button21.setType("view");
		button21.setUrl("http://www.soso.com/");
		
		WxMenuButton button22 = new WxMenuButton();
		
		button22.setName("秦皇陵");
		button22.setType("view");
		button22.setUrl("http://www.soso.com/");
		
		WxMenuButton button23 = new WxMenuButton();
		
		button23.setName("法门寺");
		button23.setType("view");
		button23.setUrl("http://www.soso.com/");
		
		button2.getSubButtons().add(button21);
		button2.getSubButtons().add(button22);
		button2.getSubButtons().add(button23);
		
		WxMenuButton button3 = new WxMenuButton();
		button3.setName("风俗民情");
		button3.setType("click");
		button3.setUrl("http://mp.weixin.qq.com");
		button3.setKey("V1001_GOOD");
		WxMenuButton button31 = new WxMenuButton();
		
		button31.setName("秦腔");
		button31.setType("view");
		button31.setUrl("http://mp.weixin.qq.com");
		
		WxMenuButton button32 = new WxMenuButton();
		
		button32.setName("社火");
		button32.setType("view");
		button32.setUrl("http://mp.weixin.qq.com");
		
		WxMenuButton button33 = new WxMenuButton();
		
		button33.setName("皮影戏");
		button33.setType("view");
		button33.setUrl("http://mp.weixin.qq.com");
		
		button3.getSubButtons().add(button31);
		button3.getSubButtons().add(button32);
		button3.getSubButtons().add(button33);
		
		menu.getButtons().add(button1);
		menu.getButtons().add(button2);
		menu.getButtons().add(button3);
		return wxMpService.getMenuService().menuCreate(menu);
	}
	
	@Override
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public String menuCreate(@RequestBody String json) throws WxErrorException {
		return wxMpService.getMenuService().menuCreate(json);
	}

	@Override
	@DeleteMapping(value = "/delete")
	public void menuDelete() throws WxErrorException {
		wxMpService.getMenuService().menuDelete();
	}

	@Override
	@DeleteMapping(value= "/deleteMenuId")
	public void menuDelete(@RequestParam("menuId")String menuId) throws WxErrorException {
		wxMpService.getMenuService().menuDelete(menuId);
	}

	@Override
	@GetMapping(value = "/getMenu")
	public WxMpMenu menuGet() throws WxErrorException {
		return wxMpService.getMenuService().menuGet();
	}

	@Override
	@GetMapping(value = "/get")
	public WxMenu menuTryMatch(@RequestParam("userid")String userid) throws WxErrorException {
		return wxMpService.getMenuService().menuTryMatch(userid);
	}

	@Override
	@GetMapping(value = "/getSelfMenu")
	public WxMpGetSelfMenuInfoResult getSelfMenuInfo() throws WxErrorException {
		return wxMpService.getMenuService().getSelfMenuInfo();
	}
	
}
