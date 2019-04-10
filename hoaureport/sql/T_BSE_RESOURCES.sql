-- Create table
create table T_BSE_RESOURCES
(
  ID                 VARCHAR2(36) not null,
  CODE               VARCHAR2(50) not null,
  NAME               VARCHAR2(200) not null,
  ENTRY_URI          VARCHAR2(1000),
  RES_LEVEL          VARCHAR2(20) not null,
  PARENT_RES         VARCHAR2(50),
  DISPLAY_ORDER      VARCHAR2(20),
  CHECKED            CHAR(1) not null,
  RES_TYPE           VARCHAR2(20) not null,
  LEAF_FLAG          CHAR(1),
  ICON_CLS           VARCHAR2(200),
  CLS                VARCHAR2(200),
  NOTES              VARCHAR2(1000),
  BELONG_SYSTEM_TYPE VARCHAR2(200),
  VERSION_NO         NUMBER(18) not null,
  CREATE_TIME        DATE not null,
  MODIFY_TIME        DATE not null,
  ACTIVE             CHAR(1) not null,
  CREATE_USER_CODE   VARCHAR2(50) not null,
  MODIFY_USER_CODE   VARCHAR2(50) not null
);
-- Add comments to the table 
comment on table T_BSE_RESOURCES
  is 'Ȩ����Ϣ��';
-- Add comments to the columns 
comment on column T_BSE_RESOURCES.ID
  is 'ID';
comment on column T_BSE_RESOURCES.CODE
  is 'Ȩ�ޱ���';
comment on column T_BSE_RESOURCES.NAME
  is 'Ȩ������';
comment on column T_BSE_RESOURCES.ENTRY_URI
  is 'Ȩ�����URI';
comment on column T_BSE_RESOURCES.RES_LEVEL
  is '���ܲ㼶';
comment on column T_BSE_RESOURCES.PARENT_RES
  is '�ϼ�Ȩ��';
comment on column T_BSE_RESOURCES.DISPLAY_ORDER
  is '��ʾ˳��';
comment on column T_BSE_RESOURCES.CHECKED
  is '�Ƿ�Ȩ�޼��';
comment on column T_BSE_RESOURCES.RES_TYPE
  is 'Ȩ������';
comment on column T_BSE_RESOURCES.LEAF_FLAG
  is '�Ƿ�Ҷ�ӽڵ�';
comment on column T_BSE_RESOURCES.ICON_CLS
  is 'ͼ���CSS��ʽ';
comment on column T_BSE_RESOURCES.CLS
  is '�ڵ��CSS��ʽ';
comment on column T_BSE_RESOURCES.NOTES
  is 'Ȩ������';
comment on column T_BSE_RESOURCES.BELONG_SYSTEM_TYPE
  is '����ϵͳ����';
comment on column T_BSE_RESOURCES.VERSION_NO
  is '���ݰ汾��';
comment on column T_BSE_RESOURCES.CREATE_TIME
  is '����ʱ��';
comment on column T_BSE_RESOURCES.MODIFY_TIME
  is '����ʱ��';
comment on column T_BSE_RESOURCES.ACTIVE
  is '�Ƿ�����';
comment on column T_BSE_RESOURCES.CREATE_USER_CODE
  is '������';
comment on column T_BSE_RESOURCES.MODIFY_USER_CODE
  is '������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BSE_RESOURCES
  add constraint PK_T_BSE_RESOURCES primary key (ID);
