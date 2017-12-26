package com.qipai;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WechatApplication {
	
	// 1.获取code的请求地址
	public static String Get_Code = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STAT#wechat_redirect";
	
	// 替换字符串
	public static String getCode(String APPID, String REDIRECT_URI,String SCOPE){
		return String.format(Get_Code,APPID,REDIRECT_URI,SCOPE);
	}
	
	 // 2.获取Web_access_tokenhttps的请求地址
    public static String Web_access_tokenhttps = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    // 替换字符串
    public static String getWebAccess(String APPID, String SECRET,String CODE) {
        return String.format(Web_access_tokenhttps, APPID, SECRET,CODE);
    }

    // 3.拉取用户信息的请求地址
    public static String User_Message = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
    // 替换字符串
    public static String getUserMessage(String access_token, String openid) {
        return String.format(User_Message, access_token,openid);
    }
	
	public static void main(String[] args) {
		String  REDIRECT_URI = "http://bb26a2cd.ngrok.io/wechat/index.html";
        String SCOPE = "snsapi_userinfo";
        //appId
        String APPID = "wx281669dc9164c874";
        String getCodeUrl = getCode(APPID, REDIRECT_URI, SCOPE);
        System.out.println("getCodeUrl:"+getCodeUrl);
//        String[] shortCodeArray = ShortUrlUtil.shortUrl(getCodeUrl);
//        
//        for (int i = 0; i < shortCodeArray.length; i++) {  
//            System.out.println(shortCodeArray[i]);// 任意一个都可以作为短链接码  
//        }  
        
		SpringApplication.run(WechatApplication.class, args);
	}
	
	@Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(8080);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(443);
        return connector;
    }

}
