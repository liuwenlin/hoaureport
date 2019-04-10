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
  is '�Զ��屨��SQL���ñ�';
-- Add comments to the columns 
comment on column T_REPORT_QUERYSQL.ID
  is '����';
comment on column T_REPORT_QUERYSQL.NUM
  is '������';
comment on column T_REPORT_QUERYSQL.SQL1
  is '����SQL����';
comment on column T_REPORT_QUERYSQL.TABLEHEAD
  is '������ֵ����';
comment on column T_REPORT_QUERYSQL.PARAM
  is '�������';
comment on column T_REPORT_QUERYSQL.REMARK
  is '��������';
comment on column T_REPORT_QUERYSQL.ACTIVE
  is '�Ƿ���Ч';
comment on column T_REPORT_QUERYSQL.CREATOR
  is '������';
comment on column T_REPORT_QUERYSQL.CREATE_DATE
  is '����ʱ��';
comment on column T_REPORT_QUERYSQL.MODIFIER
  is '�޸���';
comment on column T_REPORT_QUERYSQL.MODIFY_DATE
  is '�޸�ʱ��';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_REPORT_QUERYSQL
  add constraint PK_REPORT_QUERYSQL primary key (ID);
