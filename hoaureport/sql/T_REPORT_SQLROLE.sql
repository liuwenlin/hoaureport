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
  is '����';
comment on column T_REPORT_SQLROLE.SQLID
  is '����ID';
comment on column T_REPORT_SQLROLE.ROLEID
  is '��ɫID';
comment on column T_REPORT_SQLROLE.VERSION_NO
  is '�汾��';
comment on column T_REPORT_SQLROLE.CREATE_TIME
  is '����ʱ��';
comment on column T_REPORT_SQLROLE.CREATE_USER_CODE
  is '������';
comment on column T_REPORT_SQLROLE.MODIFY_TIME
  is '�޸�ʱ��';
comment on column T_REPORT_SQLROLE.MODIFY_USER_CODE
  is '�޸���';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_REPORT_SQLROLE
  add constraint PK_REPORT_SQLROLE primary key (ID);
