-- Create table
create table T_BSE_ORG
(
  ID                    VARCHAR2(36) not null,
  CODE                  VARCHAR2(50) not null,
  NAME                  VARCHAR2(200) not null,
  PARENT_CODE           VARCHAR2(50),
  PARENT_NAME           VARCHAR2(200),
  NATURE                NUMBER(32),
  LOGIST_CODE           VARCHAR2(200),
  MANAGER_CODE          VARCHAR2(50),
  MANAGER_NAME          VARCHAR2(200),
  PROVINCE              VARCHAR2(200),
  PROVINCE_CODE         VARCHAR2(50),
  CITY                  VARCHAR2(200),
  CITY_CODE             VARCHAR2(50),
  COUNTY                VARCHAR2(200),
  COUNTY_CODE           VARCHAR2(50),
  AREA_CODE             VARCHAR2(50),
  PHONE                 VARCHAR2(200),
  FAX                   VARCHAR2(200),
  ADDRESS_DETAIL        VARCHAR2(1000),
  LNG                   NUMBER,
  LAT                   NUMBER,
  IS_DIVISION           CHAR(1),
  DIVISION_CODE         VARCHAR2(50),
  IS_BIG_REGION         CHAR(1),
  BIG_REGION_CODE       VARCHAR2(50),
  IS_TRANSFER_CENTER    CHAR(1),
  IS_ROAD_AREA          CHAR(1),
  IS_FLEET              CHAR(1),
  IS_PLATFORM           CHAR(1),
  IS_SALES_DEPARTMENT   CHAR(1),
  ACTIVE                CHAR(1),
  VERSION_NO            NUMBER(18),
  CREATE_TIME           DATE,
  CREATE_USER_CODE      VARCHAR2(50),
  MODIFY_TIME           DATE,
  MODIFY_USER_CODE      VARCHAR2(50),
  BEGIN_TIME            DATE,
  END_TIME              DATE,
  PINYIN                VARCHAR2(50),
  SIMPLE_PINYIN         VARCHAR2(50),
  NOTES                 VARCHAR2(4000),
  IS_BIG_REGION_FINANCE CHAR(1),
  IS_DIVISION_FINANCE   CHAR(1),
  IS_FINANCE            CHAR(1),
  IS_FRANCHISE          CHAR(1)
);
-- Add comments to the table 
comment on table T_BSE_ORG
  is '组织信息表';
-- Add comments to the columns 
comment on column T_BSE_ORG.ID
  is 'ID';
comment on column T_BSE_ORG.CODE
  is '组织编码';
comment on column T_BSE_ORG.NAME
  is '组织名称';
comment on column T_BSE_ORG.PARENT_CODE
  is '上级组织编码';
comment on column T_BSE_ORG.PARENT_NAME
  is '上级组织名称';
comment on column T_BSE_ORG.NATURE
  is '组织性质';
comment on column T_BSE_ORG.LOGIST_CODE
  is '物流代码';
comment on column T_BSE_ORG.MANAGER_CODE
  is '组织负责人';
comment on column T_BSE_ORG.MANAGER_NAME
  is '组织负责人姓名';
comment on column T_BSE_ORG.PROVINCE
  is '省份';
comment on column T_BSE_ORG.PROVINCE_CODE
  is '省份编码';
comment on column T_BSE_ORG.CITY
  is '城市';
comment on column T_BSE_ORG.CITY_CODE
  is '城市编码';
comment on column T_BSE_ORG.COUNTY
  is '区县';
comment on column T_BSE_ORG.COUNTY_CODE
  is '区县编码';
comment on column T_BSE_ORG.AREA_CODE
  is '区号';
comment on column T_BSE_ORG.PHONE
  is '电话';
comment on column T_BSE_ORG.FAX
  is '传真';
comment on column T_BSE_ORG.ADDRESS_DETAIL
  is '详细地址';
comment on column T_BSE_ORG.LNG
  is '经度';
comment on column T_BSE_ORG.LAT
  is '纬度';
comment on column T_BSE_ORG.IS_DIVISION
  is '是否事业部';
comment on column T_BSE_ORG.DIVISION_CODE
  is '事业部编码';
comment on column T_BSE_ORG.IS_BIG_REGION
  is '是否大区';
comment on column T_BSE_ORG.BIG_REGION_CODE
  is '大区编码';
comment on column T_BSE_ORG.IS_TRANSFER_CENTER
  is '是否场站';
comment on column T_BSE_ORG.IS_ROAD_AREA
  is '是否路区';
comment on column T_BSE_ORG.IS_FLEET
  is '是否车队';
comment on column T_BSE_ORG.IS_PLATFORM
  is '是否平台';
comment on column T_BSE_ORG.IS_SALES_DEPARTMENT
  is '是否门店';
comment on column T_BSE_ORG.ACTIVE
  is '是否激活';
comment on column T_BSE_ORG.VERSION_NO
  is '版本号';
comment on column T_BSE_ORG.CREATE_TIME
  is '创建时间';
comment on column T_BSE_ORG.CREATE_USER_CODE
  is '创建人';
comment on column T_BSE_ORG.MODIFY_TIME
  is '修改时间';
comment on column T_BSE_ORG.MODIFY_USER_CODE
  is '修改人';
comment on column T_BSE_ORG.BEGIN_TIME
  is '启用日期';
comment on column T_BSE_ORG.END_TIME
  is '结束使用日期';
comment on column T_BSE_ORG.PINYIN
  is '组织名称拼音全拼';
comment on column T_BSE_ORG.SIMPLE_PINYIN
  is '组织名称拼音首字母';
comment on column T_BSE_ORG.NOTES
  is '备注';
comment on column T_BSE_ORG.IS_BIG_REGION_FINANCE
  is '是否大区财务部';
comment on column T_BSE_ORG.IS_DIVISION_FINANCE
  is '是否事业部财务部';
comment on column T_BSE_ORG.IS_FINANCE
  is '是否一级公司财务部';
comment on column T_BSE_ORG.IS_FRANCHISE
  is '是否特许经营';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BSE_ORG
  add constraint PK_T_BSE_ORG primary key (ID);
create index IDX_BSE_ORG_CODE on T_BSE_ORG (CODE);
create index IDX_BSE_ORG_LOGIST_CODE on T_BSE_ORG (LOGIST_CODE);
create index IDX_BSE_ORG_PARENT_CODE on T_BSE_ORG (PARENT_CODE);
