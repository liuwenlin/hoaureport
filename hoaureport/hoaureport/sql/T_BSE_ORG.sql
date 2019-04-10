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
  is '��֯��Ϣ��';
-- Add comments to the columns 
comment on column T_BSE_ORG.ID
  is 'ID';
comment on column T_BSE_ORG.CODE
  is '��֯����';
comment on column T_BSE_ORG.NAME
  is '��֯����';
comment on column T_BSE_ORG.PARENT_CODE
  is '�ϼ���֯����';
comment on column T_BSE_ORG.PARENT_NAME
  is '�ϼ���֯����';
comment on column T_BSE_ORG.NATURE
  is '��֯����';
comment on column T_BSE_ORG.LOGIST_CODE
  is '��������';
comment on column T_BSE_ORG.MANAGER_CODE
  is '��֯������';
comment on column T_BSE_ORG.MANAGER_NAME
  is '��֯����������';
comment on column T_BSE_ORG.PROVINCE
  is 'ʡ��';
comment on column T_BSE_ORG.PROVINCE_CODE
  is 'ʡ�ݱ���';
comment on column T_BSE_ORG.CITY
  is '����';
comment on column T_BSE_ORG.CITY_CODE
  is '���б���';
comment on column T_BSE_ORG.COUNTY
  is '����';
comment on column T_BSE_ORG.COUNTY_CODE
  is '���ر���';
comment on column T_BSE_ORG.AREA_CODE
  is '����';
comment on column T_BSE_ORG.PHONE
  is '�绰';
comment on column T_BSE_ORG.FAX
  is '����';
comment on column T_BSE_ORG.ADDRESS_DETAIL
  is '��ϸ��ַ';
comment on column T_BSE_ORG.LNG
  is '����';
comment on column T_BSE_ORG.LAT
  is 'γ��';
comment on column T_BSE_ORG.IS_DIVISION
  is '�Ƿ���ҵ��';
comment on column T_BSE_ORG.DIVISION_CODE
  is '��ҵ������';
comment on column T_BSE_ORG.IS_BIG_REGION
  is '�Ƿ����';
comment on column T_BSE_ORG.BIG_REGION_CODE
  is '��������';
comment on column T_BSE_ORG.IS_TRANSFER_CENTER
  is '�Ƿ�վ';
comment on column T_BSE_ORG.IS_ROAD_AREA
  is '�Ƿ�·��';
comment on column T_BSE_ORG.IS_FLEET
  is '�Ƿ񳵶�';
comment on column T_BSE_ORG.IS_PLATFORM
  is '�Ƿ�ƽ̨';
comment on column T_BSE_ORG.IS_SALES_DEPARTMENT
  is '�Ƿ��ŵ�';
comment on column T_BSE_ORG.ACTIVE
  is '�Ƿ񼤻�';
comment on column T_BSE_ORG.VERSION_NO
  is '�汾��';
comment on column T_BSE_ORG.CREATE_TIME
  is '����ʱ��';
comment on column T_BSE_ORG.CREATE_USER_CODE
  is '������';
comment on column T_BSE_ORG.MODIFY_TIME
  is '�޸�ʱ��';
comment on column T_BSE_ORG.MODIFY_USER_CODE
  is '�޸���';
comment on column T_BSE_ORG.BEGIN_TIME
  is '��������';
comment on column T_BSE_ORG.END_TIME
  is '����ʹ������';
comment on column T_BSE_ORG.PINYIN
  is '��֯����ƴ��ȫƴ';
comment on column T_BSE_ORG.SIMPLE_PINYIN
  is '��֯����ƴ������ĸ';
comment on column T_BSE_ORG.NOTES
  is '��ע';
comment on column T_BSE_ORG.IS_BIG_REGION_FINANCE
  is '�Ƿ��������';
comment on column T_BSE_ORG.IS_DIVISION_FINANCE
  is '�Ƿ���ҵ������';
comment on column T_BSE_ORG.IS_FINANCE
  is '�Ƿ�һ����˾����';
comment on column T_BSE_ORG.IS_FRANCHISE
  is '�Ƿ�����Ӫ';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BSE_ORG
  add constraint PK_T_BSE_ORG primary key (ID);
create index IDX_BSE_ORG_CODE on T_BSE_ORG (CODE);
create index IDX_BSE_ORG_LOGIST_CODE on T_BSE_ORG (LOGIST_CODE);
create index IDX_BSE_ORG_PARENT_CODE on T_BSE_ORG (PARENT_CODE);
