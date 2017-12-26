package com.qipai.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "wx")
public class WxMpProperties {
	/**
	 * 设置微信公众号的appId
	 */
	private String appId;
	/**
	 * 设置微信公众号的secret
	 */
	private String secret;
	/**
	 * 设置微信公众号的token
	 */
	private String token;
	/**
	 * 设置微信公众号的aesKey
	 */
	private String aesKey;
	/**
	 * 设置微信公众号的wxId
	 */
	private String wxId;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
