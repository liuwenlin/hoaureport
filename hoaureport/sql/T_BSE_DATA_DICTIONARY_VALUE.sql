-- Create table
create table T_BSE_DATA_DICTIONARY_VALUE
(
  ID               VARCHAR2(36) not null,
  VALUE_CODE       VARCHAR2(50) not null,
  VALUE_NAME       VARCHAR2(200) not null,
  VALUE_SEQ        NUMBER not null,
  LANGUAGE         VARCHAR2(50),
  NOTES            VARCHAR2(1000),
  ACTIVE           CHAR(1) not null,
  VERSION_NO       NUMBER not null,
  CREATE_USER_CODE VARCHAR2(50) not null,
  MODIFY_TIME      DATE not null,
  MODIFY_USER_CODE VARCHAR2(50) not null,
  CREATE_TIME      DATE not null,
  TERMS_CODE       VARCHAR2(50) not null,
  IS_APP_USE       CHAR(1)
);
-- Add comments to the table 
comment on table T_BSE_DATA_DICTIONARY_VALUE
  is '�����ֵ�ֵ��Ϣ��';
-- Add comments to the columns 
comment on column T_BSE_DATA_DICTIONARY_VALUE.ID
  is 'ID';
comment on column T_BSE_DATA_DICTIONARY_VALUE.VALUE_CODE
  is '����';
comment on column T_BSE_DATA_DICTIONARY_VALUE.VALUE_NAME
  is 'ֵ����';
comment on column T_BSE_DATA_DICTIONARY_VALUE.VALUE_SEQ
  is '˳��';
comment on column T_BSE_DATA_DICTIONARY_VALUE.LANGUAGE
  is '����';
comment on column T_BSE_DATA_DICTIONARY_VALUE.NOTES
  is '��ע';
comment on column T_BSE_DATA_DICTIONARY_VALUE.ACTIVE
  is '�Ƿ񼤻�';
comment on column T_BSE_DATA_DICTIONARY_VALUE.VERSION_NO
  is '�汾��';
comment on column T_BSE_DATA_DICTIONARY_VALUE.CREATE_USER_CODE
  is '������';
comment on column T_BSE_DATA_DICTIONARY_VALUE.MODIFY_TIME
  is '����ʱ��';
comment on column T_BSE_DATA_DICTIONARY_VALUE.MODIFY_USER_CODE
  is '������';
comment on column T_BSE_DATA_DICTIONARY_VALUE.CREATE_TIME
  is '����ʱ��';
comment on column T_BSE_DATA_DICTIONARY_VALUE.TERMS_CODE
  is '��������';
comment on column T_BSE_DATA_DICTIONARY_VALUE.IS_APP_USE
  is '�Ƿ�appʹ��';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BSE_DATA_DICTIONARY_VALUE
  add constraint PK_T_BSE_DATA_DICTIONARY_VALUE primary key (ID);
-- Create/Recreate indexes 
create index IDX_BSE_DATA_DV_CODE on T_BSE_DATA_DICTIONARY_VALUE (VALUE_CODE, TERMS_CODE);
