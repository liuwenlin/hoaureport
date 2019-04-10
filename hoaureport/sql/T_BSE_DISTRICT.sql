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
  is '���������';
-- Add comments to the columns 
comment on column T_BSE_DISTRICT.ID
  is 'ID';
comment on column T_BSE_DISTRICT.DISTRICT_CODE
  is '�����������';
comment on column T_BSE_DISTRICT.DISTRICT_NAME
  is '������������';
comment on column T_BSE_DISTRICT.DISTRICT_TYPE
  is '������������';
comment on column T_BSE_DISTRICT.PARENT_DISTRICT_CODE
  is '�ϼ������������';
comment on column T_BSE_DISTRICT.PINYIN
  is 'ƴ��';
comment on column T_BSE_DISTRICT.VERSION_NO
  is '�汾��';
comment on column T_BSE_DISTRICT.CREATE_TIME
  is '����ʱ��';
comment on column T_BSE_DISTRICT.CREATE_USER_CODE
  is '������';
comment on column T_BSE_DISTRICT.MODIFY_TIME
  is '����ʱ��';
comment on column T_BSE_DISTRICT.MODIFY_USER_CODE
  is '������';
comment on column T_BSE_DISTRICT.ACTIVE
  is '�Ƿ����';
comment on column T_BSE_DISTRICT.DISTRICT_SHORT_NAME
  is '�����������Ƽ��';
comment on column T_BSE_DISTRICT.PINYIN_SHORT
  is 'ƴ�����';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BSE_DISTRICT
  add constraint PK_T_BSE_DISTRICT primary key (ID);
-- Create/Recreate indexes 
create index DX_BSE_DISTRICT_PARENT_CODE on T_BSE_DISTRICT (PARENT_DISTRICT_CODE);
create unique index IDX_BSE_DISTRICT_CODE on T_BSE_DISTRICT (DISTRICT_CODE);
