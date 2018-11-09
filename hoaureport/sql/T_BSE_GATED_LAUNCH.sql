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
  is '灰度发布表';
-- Add comments to the columns 
comment on column T_BSE_GATED_LAUNCH.ID
  is 'ID';
comment on column T_BSE_GATED_LAUNCH.USER_NAME
  is '用户名';
comment on column T_BSE_GATED_LAUNCH.ORG_CODE
  is '组织编号';
comment on column T_BSE_GATED_LAUNCH.VERSION
  is '版本';
comment on column T_BSE_GATED_LAUNCH.CREATE_TIME
  is '创建时间';
comment on column T_BSE_GATED_LAUNCH.CREATE_USER_CODE
  is '创建人';
comment on column T_BSE_GATED_LAUNCH.MODIFY_TIME
  is '更新时间';
comment on column T_BSE_GATED_LAUNCH.MODIFY_USER_CODE
  is '更新人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BSE_GATED_LAUNCH
  add constraint PK_BSE_GATED_LAUNCH primary key (ID);
-- Create/Recreate indexes 
create index IDX_BSE_GATED_LAUNCH_USER_NAME on T_BSE_GATED_LAUNCH (USER_NAME);
