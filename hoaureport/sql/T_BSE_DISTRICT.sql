-- Create table
create table T_BSE_DISTRICT
(
  ID                   VARCHAR2(36) not null,
  DISTRICT_CODE        VARCHAR2(50) not null,
  DISTRICT_NAME        VARCHAR2(200) not null,
  DISTRICT_TYPE        VARCHAR2(50) not null,
  PARENT_DISTRICT_CODE VARCHAR2(50),
  PINYIN               VARCHAR2(400),
  VERSION_NO           NUMBER(18) not null,
  CREATE_TIME          DATE,
  CREATE_USER_CODE     VARCHAR2(50),
  MODIFY_TIME          DATE,
  MODIFY_USER_CODE     VARCHAR2(50),
  ACTIVE               CHAR(1) not null,
  DISTRICT_SHORT_NAME  VARCHAR2(200),
  PINYIN_SHORT         VARCHAR2(400)
);
-- Add comments to the table 
comment on table T_BSE_DISTRICT
  is '行政区域表';
-- Add comments to the columns 
comment on column T_BSE_DISTRICT.ID
  is 'ID';
comment on column T_BSE_DISTRICT.DISTRICT_CODE
  is '行政区域编码';
comment on column T_BSE_DISTRICT.DISTRICT_NAME
  is '行政区域名称';
comment on column T_BSE_DISTRICT.DISTRICT_TYPE
  is '行政区域类型';
comment on column T_BSE_DISTRICT.PARENT_DISTRICT_CODE
  is '上级行政区域编码';
comment on column T_BSE_DISTRICT.PINYIN
  is '拼音';
comment on column T_BSE_DISTRICT.VERSION_NO
  is '版本号';
comment on column T_BSE_DISTRICT.CREATE_TIME
  is '创建时间';
comment on column T_BSE_DISTRICT.CREATE_USER_CODE
  is '创建人';
comment on column T_BSE_DISTRICT.MODIFY_TIME
  is '更新时间';
comment on column T_BSE_DISTRICT.MODIFY_USER_CODE
  is '更新人';
comment on column T_BSE_DISTRICT.ACTIVE
  is '是否可用';
comment on column T_BSE_DISTRICT.DISTRICT_SHORT_NAME
  is '行政区域名称简称';
comment on column T_BSE_DISTRICT.PINYIN_SHORT
  is '拼音简称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BSE_DISTRICT
  add constraint PK_T_BSE_DISTRICT primary key (ID);
-- Create/Recreate indexes 
create index DX_BSE_DISTRICT_PARENT_CODE on T_BSE_DISTRICT (PARENT_DISTRICT_CODE);
create unique index IDX_BSE_DISTRICT_CODE on T_BSE_DISTRICT (DISTRICT_CODE);
