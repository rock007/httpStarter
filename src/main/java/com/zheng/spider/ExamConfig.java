package com.zheng.spider;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zheng.spider.config.ApplicationConfiguration;
import com.zheng.spider.core.Template;

import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.db.DataSourceFactory;

public class ExamConfig extends Configuration {
	
	@NotEmpty
	private String defaultName = "ABC";
	 
	@NotEmpty
	private String template;
	  
    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
    
    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }
    
    public Template buildTemplate() {
        return new Template(template, defaultName);
    }
    
	@Valid
	@NotNull
	@JsonProperty("application")
	private ApplicationConfiguration appCfg;
	
	public ApplicationConfiguration getAppCfg() {
		return appCfg;
	}	

	@Valid
	@NotNull
	private HttpClientConfiguration httpClient = new HttpClientConfiguration();

	@JsonProperty("httpClient")
	public HttpClientConfiguration getHttpClientConfiguration() {
		return httpClient;
	}

	@JsonProperty("httpClient")
	public void setHttpClientConfiguration(HttpClientConfiguration httpClient) {
		this.httpClient = httpClient;
	}
	
	//db1
	@Valid
	@NotNull
	private DataSourceFactory database1 = new DataSourceFactory();

	@JsonProperty("database1")
	public void setDataSource1Factory(DataSourceFactory factory) {
		this.database1 = factory;
	}

	@JsonProperty("database1")
	public DataSourceFactory getDataSource1Factory() {
		return database1;
	}
	
	
}
