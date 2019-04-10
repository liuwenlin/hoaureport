-- Create table
create table T_REPORT_SQLROLE
(
  ID               VARCHAR2(50) not null,
  SQLID            VARCHAR2(50),
  ROLEID           VARCHAR2(50),
  VERSION_NO       NUMBER(18),
  CREATE_TIME      DATE,
  CREATE_USER_CODE VARCHAR2(50),
  MODIFY_TIME      DATE,
  MODIFY_USER_CODE VARCHAR2(50),
  ACTIVE           CHAR(1)
);
-- Add comments to the columns 
comment on column T_REPORT_SQLROLE.ID
  is '主键';
comment on column T_REPORT_SQLROLE.SQLID
  is '报表ID';
comment on column T_REPORT_SQLROLE.ROLEID
  is '角色ID';
comment on column T_REPORT_SQLROLE.VERSION_NO
  is '版本号';
comment on column T_REPORT_SQLROLE.CREATE_TIME
  is '创建时间';
comment on column T_REPORT_SQLROLE.CREATE_USER_CODE
  is '创建人';
comment on column T_REPORT_SQLROLE.MODIFY_TIME
  is '修改时间';
comment on column T_REPORT_SQLROLE.MODIFY_USER_CODE
  is '修改人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_REPORT_SQLROLE
  add constraint PK_REPORT_SQLROLE primary key (ID);
