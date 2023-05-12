package com.zheng.spider.model.dto;

import java.io.Serializable;

public class SubmitSqlsDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3664067379021159857L;

	private String dbName;
	
	private String tableName;
	
	private String content;

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
