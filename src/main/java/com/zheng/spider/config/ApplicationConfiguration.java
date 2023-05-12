package com.zheng.spider.config;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationConfiguration {
	
	/**
	 * 是否在外网运行
	 */
	@JsonProperty
	private Integer isOutSide;
	

	public Integer getIsOutSide() {
		return isOutSide;
	}

	public void setIsOutSide(Integer isOutSide) {
		this.isOutSide = isOutSide;
	}

}
