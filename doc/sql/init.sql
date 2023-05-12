drop table if exists spider_fetch_page;

/*==============================================================*/
/* Table: spider_fetch_page                                     */
/*==============================================================*/
create table spider_fetch_page
(
   id                   varchar(128) not null,
   buss_type            varchar(20),
   title                varchar(1024),
   url                  varchar(1024),
   key1_name            varchar(256),
   key1_text            text,
   key2_name            varchar(256),
   key2_text            text,
   key3_name            varchar(256),
   key3_text            text,
   key4_name            varchar(256),
   key4_text            text,
   key5_name            varchar(256),
   key5_text            text,
   key6_name            varchar(256),
   key6_text            text,
   key7_name            varchar(256),
   key7_text            text,
   key8_name            varchar(256),
   key8_text            text,
   key9_name            varchar(256),
   key9_text            text,
   key10_name           varchar(256),
   key10_text           text,
   flag                 int comment '标记符',
   create_date          datetime comment '创建时间',
   primary key (id)
);

alter table spider_fetch_page comment '蜘蛛爬取的网站';



CREATE TABLE `sys_setting` (
  `mkey` varchar(50)   NOT NULL COMMENT '参数名',
  `text` varchar(100)   DEFAULT NULL COMMENT '参数值',
  `remarks` varchar(256)   DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`mkey`)
)  COMMENT='本系统设置';