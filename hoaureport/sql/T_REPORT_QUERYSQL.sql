-- Create table
create table T_REPORT_QUERYSQL
(
  ID          VARCHAR2(44) not null,
  NUM         VARCHAR2(50),
  SQL1        BLOB,
  TABLEHEAD   VARCHAR2(2000),
  PARAM       VARCHAR2(2000),
  REMARK      VARCHAR2(2000),
  ACTIVE      CHAR(1),
  CREATOR     VARCHAR2(50),
  CREATE_DATE TIMESTAMP(6),
  MODIFIER    VARCHAR2(50),
  MODIFY_DATE TIMESTAMP(6)
);
-- Add comments to the table 
comment on table T_REPORT_QUERYSQL
  is '自定义报表SQL配置表';
-- Add comments to the columns 
comment on column T_REPORT_QUERYSQL.ID
  is '主键';
comment on column T_REPORT_QUERYSQL.NUM
  is '报表编号';
comment on column T_REPORT_QUERYSQL.SQL1
  is '报表SQL内容';
comment on column T_REPORT_QUERYSQL.TABLEHEAD
  is '报表返回值列名';
comment on column T_REPORT_QUERYSQL.PARAM
  is '报表参数';
comment on column T_REPORT_QUERYSQL.REMARK
  is '报表描述';
comment on column T_REPORT_QUERYSQL.ACTIVE
  is '是否有效';
comment on column T_REPORT_QUERYSQL.CREATOR
  is '创建人';
comment on column T_REPORT_QUERYSQL.CREATE_DATE
  is '创建时间';
comment on column T_REPORT_QUERYSQL.MODIFIER
  is '修改人';
comment on column T_REPORT_QUERYSQL.MODIFY_DATE
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_REPORT_QUERYSQL
  add constraint PK_REPORT_QUERYSQL primary key (ID);
