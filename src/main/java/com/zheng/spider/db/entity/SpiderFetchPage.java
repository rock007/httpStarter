package com.zheng.spider.db.entity;

import java.io.Serializable;
import java.util.Date;

public class SpiderFetchPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1206319546776662530L;

	private String id;
	
	private String bussType="okcis";
	
	private String title;
	
	private String url;
	
	//数据
	private String key1Name;
	private String key1Text;
	
	private String key2Name;
	private String key2Text;
	
	private String key3Name;
	private String key3Text;
	
	private String key4Name;
	private String key4Text;
	
	private String key5Name;
	private String key5Text;
	
	private String key6Name;
	private String key6Text;
	
	private String key7Name;
	private String key7Text;
	
	private String key8Name;
	private String key8Text;
	
	private String key9Name;
	private String key9Text;
	
	private String key10Name;
	private String key10Text;
	
	//
	private String key11Name;
	private String key11Text;
	
	private String key12Name;
	private String key12Text;
	
	private String key13Name;
	private String key13Text;
	
	private String key14Name;
	private String key14Text;
	
	private String key15Name;
	private String key15Text;
	
	private String key16Name;
	private String key16Text;
	
	private String key17Name;
	private String key17Text;
	
	private String key18Name;
	private String key18Text;
	
	private String key19Name;
	private String key19Text;
	
	private String key20Name;
	private String key20Text;
	
	private Integer flag;
	
	private Date createDate;
	
	private Date updateDate;
	
	public SpiderFetchPage() {
		super();
		
	}
	
	public SpiderFetchPage(String buss, String name, String url) {
		super();
		this.bussType = buss;
		this.title = name;
		this.url = url;
	}

	public SpiderFetchPage(String name, String url) {
		super();
		this.title = name;
		this.url = url;
	}


	public String getBussType() {
		return bussType;
	}

	public void setBussType(String bussType) {
		this.bussType = bussType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey1Name() {
		return key1Name;
	}

	public void setKey1Name(String key1Name) {
		this.key1Name = key1Name;
	}

	public String getKey1Text() {
		return key1Text;
	}

	public void setKey1Text(String key1Text) {
		this.key1Text = key1Text;
	}

	public String getKey2Name() {
		return key2Name;
	}

	public void setKey2Name(String key2Name) {
		this.key2Name = key2Name;
	}

	public String getKey2Text() {
		return key2Text;
	}

	public void setKey2Text(String key2Text) {
		this.key2Text = key2Text;
	}

	public String getKey3Name() {
		return key3Name;
	}

	public void setKey3Name(String key3Name) {
		this.key3Name = key3Name;
	}

	public String getKey3Text() {
		return key3Text;
	}

	public void setKey3Text(String key3Text) {
		this.key3Text = key3Text;
	}

	public String getKey4Name() {
		return key4Name;
	}

	public void setKey4Name(String key4Name) {
		this.key4Name = key4Name;
	}

	public String getKey4Text() {
		return key4Text;
	}

	public void setKey4Text(String key4Text) {
		this.key4Text = key4Text;
	}

	public String getKey5Name() {
		return key5Name;
	}

	public void setKey5Name(String key5Name) {
		this.key5Name = key5Name;
	}

	public String getKey5Text() {
		return key5Text;
	}

	public void setKey5Text(String key5Text) {
		this.key5Text = key5Text;
	}

	public String getKey6Name() {
		return key6Name;
	}

	public void setKey6Name(String key6Name) {
		this.key6Name = key6Name;
	}

	public String getKey6Text() {
		return key6Text;
	}

	public void setKey6Text(String key6Text) {
		this.key6Text = key6Text;
	}

	public String getKey7Name() {
		return key7Name;
	}

	public void setKey7Name(String key7Name) {
		this.key7Name = key7Name;
	}

	public String getKey7Text() {
		return key7Text;
	}

	public void setKey7Text(String key7Text) {
		this.key7Text = key7Text;
	}

	public String getKey8Name() {
		return key8Name;
	}

	public void setKey8Name(String key8Name) {
		this.key8Name = key8Name;
	}

	public String getKey8Text() {
		return key8Text;
	}

	public void setKey8Text(String key8Text) {
		this.key8Text = key8Text;
	}

	public String getKey9Name() {
		return key9Name;
	}

	public void setKey9Name(String key9Name) {
		this.key9Name = key9Name;
	}

	public String getKey9Text() {
		return key9Text;
	}

	public void setKey9Text(String key9Text) {
		this.key9Text = key9Text;
	}

	public String getKey10Name() {
		return key10Name;
	}

	public void setKey10Name(String key10Name) {
		this.key10Name = key10Name;
	}

	public String getKey10Text() {
		return key10Text;
	}

	public void setKey10Text(String key10Text) {
		this.key10Text = key10Text;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getKey11Name() {
		return key11Name;
	}

	public void setKey11Name(String key11Name) {
		this.key11Name = key11Name;
	}

	public String getKey11Text() {
		return key11Text;
	}

	public void setKey11Text(String key11Text) {
		this.key11Text = key11Text;
	}

	public String getKey12Name() {
		return key12Name;
	}

	public void setKey12Name(String key12Name) {
		this.key12Name = key12Name;
	}

	public String getKey12Text() {
		return key12Text;
	}

	public void setKey12Text(String key12Text) {
		this.key12Text = key12Text;
	}

	public String getKey13Name() {
		return key13Name;
	}

	public void setKey13Name(String key13Name) {
		this.key13Name = key13Name;
	}

	public String getKey13Text() {
		return key13Text;
	}

	public void setKey13Text(String key13Text) {
		this.key13Text = key13Text;
	}

	public String getKey14Name() {
		return key14Name;
	}

	public void setKey14Name(String key14Name) {
		this.key14Name = key14Name;
	}

	public String getKey14Text() {
		return key14Text;
	}

	public void setKey14Text(String key14Text) {
		this.key14Text = key14Text;
	}

	public String getKey15Name() {
		return key15Name;
	}

	public void setKey15Name(String key15Name) {
		this.key15Name = key15Name;
	}

	public String getKey15Text() {
		return key15Text;
	}

	public void setKey15Text(String key15Text) {
		this.key15Text = key15Text;
	}

	public String getKey16Name() {
		return key16Name;
	}

	public void setKey16Name(String key16Name) {
		this.key16Name = key16Name;
	}

	public String getKey16Text() {
		return key16Text;
	}

	public void setKey16Text(String key16Text) {
		this.key16Text = key16Text;
	}

	public String getKey17Name() {
		return key17Name;
	}

	public void setKey17Name(String key17Name) {
		this.key17Name = key17Name;
	}

	public String getKey17Text() {
		return key17Text;
	}

	public void setKey17Text(String key17Text) {
		this.key17Text = key17Text;
	}

	public String getKey18Name() {
		return key18Name;
	}

	public void setKey18Name(String key18Name) {
		this.key18Name = key18Name;
	}

	public String getKey18Text() {
		return key18Text;
	}

	public void setKey18Text(String key18Text) {
		this.key18Text = key18Text;
	}

	public String getKey19Name() {
		return key19Name;
	}

	public void setKey19Name(String key19Name) {
		this.key19Name = key19Name;
	}

	public String getKey19Text() {
		return key19Text;
	}

	public void setKey19Text(String key19Text) {
		this.key19Text = key19Text;
	}

	public String getKey20Name() {
		return key20Name;
	}

	public void setKey20Name(String key20Name) {
		this.key20Name = key20Name;
	}

	public String getKey20Text() {
		return key20Text;
	}

	public void setKey20Text(String key20Text) {
		this.key20Text = key20Text;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
