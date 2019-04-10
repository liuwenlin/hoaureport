-- Create table
create table T_BSE_DATA_DICTIONARY
(
  ID                VARCHAR2(36) not null,
  TERMS_CODE        VARCHAR2(50) not null,
  TERMS_NAME        VARCHAR2(200) not null,
  IF_LEAF           CHAR(1),
  PARENT_TERMS_CODE VARCHAR2(50),
  NOTES             VARCHAR2(1000),
  ACTIVE            CHAR(1) not null,
  VERSION_NO        NUMBER not null,
  CREATE_USER_CODE  VARCHAR2(50) not null,
  MODIFY_TIME       DATE not null,
  MODIFY_USER_CODE  VARCHAR2(50) not null,
  CREATE_TIME       DATE not null
);
-- Add comments to the table 
comment on table T_BSE_DATA_DICTIONARY
  is '�����ֵ���Ϣ��';
-- Add comments to the columns 
comment on column T_BSE_DATA_DICTIONARY.ID
  is 'ID';
comment on column T_BSE_DATA_DICTIONARY.TERMS_CODE
  is '��������';
comment on column T_BSE_DATA_DICTIONARY.TERMS_NAME
  is '��������';
comment on column T_BSE_DATA_DICTIONARY.IF_LEAF
  is '�Ƿ�Ҷ�ӽڵ�';
comment on column T_BSE_DATA_DICTIONARY.PARENT_TERMS_CODE
  is '�ϼ�����';
comment on column T_BSE_DATA_DICTIONARY.NOTES
  is '��ע';
comment on column T_BSE_DATA_DICTIONARY.ACTIVE
  is '�Ƿ񼤻�';
comment on column T_BSE_DATA_DICTIONARY.VERSION_NO
  is '�汾��';
comment on column T_BSE_DATA_DICTIONARY.CREATE_USER_CODE
  is '������';
comment on column T_BSE_DATA_DICTIONARY.MODIFY_TIME
  is '����ʱ��';
comment on column T_BSE_DATA_DICTIONARY.MODIFY_USER_CODE
  is '������';
comment on column T_BSE_DATA_DICTIONARY.CREATE_TIME
  is '����ʱ��';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BSE_DATA_DICTIONARY
  add constraint PK_T_BSE_DATA_DICTIONARY primary key (ID);
create unique index IDX_BSE_DATA_D_CODE on T_BSE_DATA_DICTIONARY (TERMS_CODE);
create unique index IDX_BSE_DATA_D_NAME on T_BSE_DATA_DICTIONARY (TERMS_NAME);
