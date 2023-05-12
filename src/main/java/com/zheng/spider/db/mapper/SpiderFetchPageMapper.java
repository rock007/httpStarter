package com.zheng.spider.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import com.zheng.spider.db.entity.SpiderFetchPage;

public class SpiderFetchPageMapper implements RowMapper<SpiderFetchPage>{

	public SpiderFetchPageMapper() {
		
	}
	
	@Override
	public SpiderFetchPage map(ResultSet rs, StatementContext ctx) throws SQLException {
		
		SpiderFetchPage one=new SpiderFetchPage();
		
		one.setId(rs.getString("id"));
		
		one.setTitle(rs.getString("title"));
		one.setUrl(rs.getString("url"));
		one.setBussType(rs.getString("buss_type"));
		
		one.setKey1Name(rs.getString("key1_name"));
		one.setKey1Text(rs.getString("key1_text"));
		
		one.setKey2Name(rs.getString("key2_name"));
		one.setKey2Text(rs.getString("key2_text"));
		
		one.setKey3Name(rs.getString("key3_name"));
		one.setKey3Text(rs.getString("key3_text"));
		
		one.setKey4Name(rs.getString("key4_name"));
		one.setKey4Text(rs.getString("key4_text"));
		
		one.setKey5Name(rs.getString("key5_name"));
		one.setKey5Text(rs.getString("key5_text"));
		
		one.setKey6Name(rs.getString("key6_name"));
		one.setKey6Text(rs.getString("key6_text"));
		
		one.setKey7Name(rs.getString("key7_name"));
		one.setKey7Text(rs.getString("key7_text"));
		
		one.setKey8Name(rs.getString("key8_name"));
		one.setKey8Text(rs.getString("key8_text"));
		
		one.setKey9Name(rs.getString("key9_name"));
		one.setKey9Text(rs.getString("key9_text"));
		
		one.setKey10Name(rs.getString("key10_name"));
		one.setKey10Text(rs.getString("key10_text"));
		
		//
		one.setKey11Name(rs.getString("key11_name"));
		one.setKey11Text(rs.getString("key11_text"));
		
		one.setKey12Name(rs.getString("key12_name"));
		one.setKey12Text(rs.getString("key12_text"));
		
		one.setKey13Name(rs.getString("key13_name"));
		one.setKey13Text(rs.getString("key13_text"));
		
		one.setKey14Name(rs.getString("key14_name"));
		one.setKey14Text(rs.getString("key14_text"));
		
		one.setKey15Name(rs.getString("key15_name"));
		one.setKey15Text(rs.getString("key15_text"));
		
		one.setKey16Name(rs.getString("key16_name"));
		one.setKey16Text(rs.getString("key16_text"));
		
		one.setKey17Name(rs.getString("key17_name"));
		one.setKey17Text(rs.getString("key17_text"));
		
		one.setKey18Name(rs.getString("key18_name"));
		one.setKey18Text(rs.getString("key18_text"));
		
		one.setKey19Name(rs.getString("key19_name"));
		one.setKey19Text(rs.getString("key19_text"));
		
		one.setKey20Name(rs.getString("key20_name"));
		one.setKey20Text(rs.getString("key20_text"));
		
		one.setCreateDate(rs.getTimestamp("create_date"));
		one.setFlag(rs.getInt("flag"));
		
		one.setUpdateDate(rs.getTimestamp("update_date"));
		
		return one;
	}

	
}
