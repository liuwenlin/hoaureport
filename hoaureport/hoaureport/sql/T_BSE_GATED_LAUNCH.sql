-- Create table
create table T_BSE_GATED_LAUNCH
(
  ID               VARCHAR2(36) not null,
  USER_NAME        VARCHAR2(50) not null,
  ORG_CODE         VARCHAR2(50),
  VERSION          VARCHAR2(50),
  CREATE_TIME      DATE,
  CREATE_USER_CODE VARCHAR2(50),
  MODIFY_TIME      DATE,
  MODIFY_USER_CODE VARCHAR2(50)
);
-- Add comments to the table 
comment on table T_BSE_GATED_LAUNCH
  is '�Ҷȷ�����';
-- Add comments to the columns 
comment on column T_BSE_GATED_LAUNCH.ID
  is 'ID';
comment on column T_BSE_GATED_LAUNCH.USER_NAME
  is '�û���';
comment on column T_BSE_GATED_LAUNCH.ORG_CODE
  is '��֯���';
comment on column T_BSE_GATED_LAUNCH.VERSION
  is '�汾';
comment on column T_BSE_GATED_LAUNCH.CREATE_TIME
  is '����ʱ��';
comment on column T_BSE_GATED_LAUNCH.CREATE_USER_CODE
  is '������';
comment on column T_BSE_GATED_LAUNCH.MODIFY_TIME
  is '����ʱ��';
comment on column T_BSE_GATED_LAUNCH.MODIFY_USER_CODE
  is '������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BSE_GATED_LAUNCH
  add constraint PK_BSE_GATED_LAUNCH primary key (ID);
-- Create/Recreate indexes 
create index IDX_BSE_GATED_LAUNCH_USER_NAME on T_BSE_GATED_LAUNCH (USER_NAME);
