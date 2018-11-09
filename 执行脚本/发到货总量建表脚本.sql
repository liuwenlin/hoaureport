-- Create table
create table HYREPORT_fdhzl_HZ
(
  SSR                 DATE,
  QY                  VARCHAR2(50),
  YJGS                VARCHAR2(100),
  FHZL                number (24,2),
  DHZL                number (24,2),
  ZZZL                number (24,2),
  SZZL                number (24,2),
  XZZL                number (24,2),
  ZZDH                number (24,2),
  SZDHZL              number (24,2),
  XZDHZL              number (24,2),
  dyljzl              number (24,2),
  create_time         DATE default SYSDATE not null,
  CREATE_USER         VARCHAR2(50),
  MODIFY_TIME         DATE default SYSDATE not null,
  MODIFY_USER         VARCHAR2(50)
);
-- Add comments to the columns 
comment on column HYREPORT_fdhzl_HZ.SSR
  is '所属日';
comment on column HYREPORT_fdhzl_HZ.QY
  is '区域';
comment on column HYREPORT_fdhzl_HZ.YJGS
  is '一级公司';
comment on column HYREPORT_fdhzl_HZ.FHZL
  is '发货总量';
comment on column HYREPORT_fdhzl_HZ.DHZL
  is '到货总量';
comment on column HYREPORT_fdhzl_HZ.ZZZL
  is '中转总量';
comment on column HYREPORT_fdhzl_HZ.SZZL
  is '上转总量';
comment on column HYREPORT_fdhzl_HZ.XZZL
  is '下转总量';
comment on column HYREPORT_fdhzl_HZ.ZZDH
  is '总转到货总量';
comment on column HYREPORT_fdhzl_HZ.SZDHZL
  is '上转到货总量';
  comment on column HYREPORT_fdhzl_HZ.XZDHZL
  is '下转到货总量';
  comment on column HYREPORT_fdhzl_HZ.dyljzl
  is '当月累计重量';  
comment on column HYREPORT_fdhzl_HZ.CREATE_TIME
  is '记录创建时间';
comment on column HYREPORT_fdhzl_HZ.CREATE_USER
  is '记录创建人';
comment on column HYREPORT_fdhzl_HZ.MODIFY_TIME
  is '记录修改/作废时间';
comment on column HYREPORT_fdhzl_HZ.MODIFY_USER
  is '记录修改/作废人';
  
create table HYREPORT_fdhzl_mx
(
  SSR                 DATE,
  SSY                 char(8),
  QY                  VARCHAR2(50),
  YJGS                VARCHAR2(100),
  FHZL                number (24,2),
  DHZL                number (24,2),
  ZZZL                number (24,2),
  SZZL                number (24,2),
  XZZL                number (24,2),
  ZZDH                number (24,2),
  SZDHZL              number (24,2),
  XZDHZL              number (24,2),
  create_time         DATE default SYSDATE not null,
  CREATE_USER         VARCHAR2(50),
  MODIFY_TIME         DATE default SYSDATE not null,
  MODIFY_USER         VARCHAR2(50)
);
-- Add comments to the columns 
comment on column HYREPORT_fdhzl_mx.SSR
  is '所属日';
comment on column HYREPORT_fdhzl_mx.SSY
  is '所属月';  
comment on column HYREPORT_fdhzl_mx.QY
  is '区域';
comment on column HYREPORT_fdhzl_mx.YJGS
  is '一级公司';
comment on column HYREPORT_fdhzl_mx.FHZL
  is '发货总量';
comment on column HYREPORT_fdhzl_mx.DHZL
  is '到货总量';
comment on column HYREPORT_fdhzl_mx.ZZZL
  is '中转总量';
comment on column HYREPORT_fdhzl_mx.SZZL
  is '上转总量';
comment on column HYREPORT_fdhzl_mx.XZZL
  is '下转总量';
comment on column HYREPORT_fdhzl_mx.ZZDH
  is '总转到货总量';
comment on column HYREPORT_fdhzl_mx.SZDHZL
  is '上转到货总量';
  comment on column HYREPORT_fdhzl_mx.XZDHZL
  is '下转到货总量'; 
comment on column HYREPORT_fdhzl_mx.CREATE_TIME
  is '记录创建时间';
comment on column HYREPORT_fdhzl_mx.CREATE_USER
  is '记录创建人';
comment on column HYREPORT_fdhzl_mx.MODIFY_TIME
  is '记录修改/作废时间';
comment on column HYREPORT_fdhzl_mx.MODIFY_USER
  is '记录修改/作废人';

insert into qrtz_job_schedules (ID, TRIGGER_GROUP, TRIGGER_NAME, JOB_GROUP, JOB_NAME, DESCRIPTION, TRIGGER_TYPE, TRIGGER_EXPRESSION, JOB_CLASS, JOB_DATA)
values ('2', 'G1', 'J2', 'HARJOB', 'FDHZLJob', '发到货转量', 1, '0 0 1 * * ?', 'com.hoau.hoaureport.module.job.server.job.FDHZLJob', '');


create table HYREPORT_SSLUQU
(
  QYDJC               VARCHAR2(50),
  SYB                  VARCHAR2(50),
  JYDQ                VARCHAR2(50),
  LUQU                VARCHAR2(50),
  QYDSSYJGS                VARCHAR2(50),
  create_time         DATE default SYSDATE not null,
  CREATE_USER         VARCHAR2(50),
  MODIFY_TIME         DATE default SYSDATE not null,
  MODIFY_USER         VARCHAR2(50)
);
-- Add comments to the columns 
comment on column HYREPORT_SSLUQU.QYDJC
  is '起运地简称';
comment on column HYREPORT_SSLUQU.SYB
  is '事业部';
comment on column HYREPORT_SSLUQU.JYDQ
  is '经营大区';
comment on column HYREPORT_SSLUQU.LUQU
  is '路区';
comment on column HYREPORT_SSLUQU.QYDSSYJGS
  is '起运地所属一级公司';
comment on column HYREPORT_SSLUQU.CREATE_TIME
  is '记录创建时间';
comment on column HYREPORT_SSLUQU.CREATE_USER
  is '记录创建人';
comment on column HYREPORT_SSLUQU.MODIFY_TIME
  is '记录修改/作废时间';
comment on column HYREPORT_SSLUQU.MODIFY_USER
  is '记录修改/作废人';