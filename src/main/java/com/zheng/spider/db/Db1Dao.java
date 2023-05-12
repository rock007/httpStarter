package com.zheng.spider.db;

import java.util.List;

import org.jdbi.v3.sqlobject.SingleValue;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.zheng.spider.db.entity.SpiderFetchPage;
import com.zheng.spider.db.mapper.SpiderFetchPageMapper;

public interface Db1Dao {
	
	@SqlQuery("select * from spider_fetch_page where flag=0 and DATE_FORMAT( create_date,'%Y-%m-%d %H:%i:%S') >= :startDateStr order by create_date asc limit 0,:rowNum ")
	@RegisterRowMapper(SpiderFetchPageMapper.class)
	List<SpiderFetchPage> getFetchPageList(@Bind("startDateStr") String startDateStr,@Bind("rowNum") Integer rowNum);
	
	//@SqlUpdate("update spider_fetch_page set syncFlag=:syncFlag , syncResult=:syncResult where id=:id ")
	//void updateFetchPage(@Bind("id") Long id, @Bind("syncFlag") int syncFlag, @Bind("syncResult") String syncResult);

	@SqlUpdate("update spider_fetch_page set key1_text=:key1Text,key2_name=:key2Name,key2_text=:key2Text,key3_name=:key3Name,key3_text=:key3Text,key4_name=:key4Name,key4_text=:key4Text,key5_name=:key5Name,key5_text=:key5Text,key6_name=:key6Name,key6_text=:key6Text,key7_name=:key7Name,key7_text=:key7Text,key8_name=:key8Name,key8_text=:key8Text,key9_name=:key9Name,key9_text=:key9Text,key10_name=:key10Name,key10_text=:key10Text ,"
			+" key11_text=:key11Text,key12_name=:key12Name,key12_text=:key12Text,key13_name=:key13Name,key13_text=:key13Text,key14_name=:key14Name,key14_text=:key14Text,key15_name=:key15Name,key15_text=:key15Text,key16_name=:key16Name,key16_text=:key16Text,key17_name=:key17Name,key17_text=:key17Text,key18_name=:key18Name,key18_text=:key18Text,key19_name=:key19Name,key19_text=:key19Text,key20_name=:key20Name,key20_text=:key20Text ,"
			+ " flag=:flag ,update_date = now()  where id=:id ")
	void updateFetchPage(@BindBean SpiderFetchPage log);

	@SqlUpdate("insert into  spider_fetch_page (id,buss_type,title,url,key1_name,key1_text,key2_name,key2_text,key3_name,key3_text,key4_name,key4_text,key5_name,key5_text,key6_name,key6_text,key7_name,key7_text,key8_name,key8_text,key9_name,key9_text,key10_name,key10_text,create_date,flag) values (:id,:bussType,:title,:url,:key1Name,:key1Text,:key2Name,:key2Text,:key3Name,:key3Text,:key4Name,:key4Text,:key5Name,:key5Text,:key6Name,:key6Text,:key7Name,:key7Text,:key8Name,:key8Text,:key9Name,:key9Text,:key10Name,:key10Text,now(),:flag ) ")
	void insertFetchPage(@BindBean SpiderFetchPage log);
	
	@SqlQuery("select count(0) as num from spider_fetch_page  where buss_type=:bussType and url =:url ")
	Integer checkUrlExist(@Bind("bussType") String bussType,@Bind("url") String url);
	
	@SqlQuery("select * from spider_fetch_page where id = ?")
	@RegisterRowMapper(SpiderFetchPageMapper.class)
	@SingleValue
	SpiderFetchPage getFetchPageById(String id);
	
	//配置
	@SqlQuery("select `text` from sys_setting  where mkey = ?")
	String getSettingByKey(String key);

	@SqlUpdate("update sys_setting  set `text`=:value where mkey=:mkey ")
	void updateSetting(@Bind("mkey") String key, @Bind("value") String value);
}