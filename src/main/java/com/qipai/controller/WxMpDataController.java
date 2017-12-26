package com.qipai.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpDataCubeService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeArticleResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeArticleTotal;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeInterfaceResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeMsgResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserCumulate;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserSummary;

@RequestMapping("/wechat/data")
@RestController
public class WxMpDataController implements WxMpDataCubeService{
	
	@Autowired
	private WxMpService wxMpService;
	@InitBinder  
    public void initBinder(WebDataBinder binder, WebRequest request) {    
        //转换日期  
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器  
    } 
	@Override
	@GetMapping(value = "/getUserSummary")
	public List<WxDataCubeUserSummary> getUserSummary(@RequestParam("beginDate")Date beginDate, @RequestParam("endDate")Date endDate) throws WxErrorException {
		
		return wxMpService.getDataCubeService().getUserSummary(beginDate, endDate);
	}

	@Override
	@GetMapping(value = "/getUserCumulate")
	public List<WxDataCubeUserCumulate> getUserCumulate(@RequestParam("beginDate")Date beginDate, @RequestParam("endDate")Date endDate) throws WxErrorException {
		return wxMpService.getDataCubeService().getUserCumulate(beginDate, endDate);
	}

	@Override
	public List<WxDataCubeArticleResult> getArticleSummary(Date beginDate, Date endDate) throws WxErrorException {
		return null;
	}

	@Override
	public List<WxDataCubeArticleTotal> getArticleTotal(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeArticleResult> getUserRead(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeArticleResult> getUserReadHour(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeArticleResult> getUserShare(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeArticleResult> getUserShareHour(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeMsgResult> getUpstreamMsg(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeMsgResult> getUpstreamMsgHour(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeMsgResult> getUpstreamMsgWeek(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeMsgResult> getUpstreamMsgMonth(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeMsgResult> getUpstreamMsgDist(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeMsgResult> getUpstreamMsgDistWeek(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeMsgResult> getUpstreamMsgDistMonth(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeInterfaceResult> getInterfaceSummary(Date beginDate, Date endDate) throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WxDataCubeInterfaceResult> getInterfaceSummaryHour(Date beginDate, Date endDate)
			throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}

}
