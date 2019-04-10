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
  is '权限信息表';
-- Add comments to the columns 
comment on column T_BSE_RESOURCES.ID
  is 'ID';
comment on column T_BSE_RESOURCES.CODE
  is '权限编码';
comment on column T_BSE_RESOURCES.NAME
  is '权限名称';
comment on column T_BSE_RESOURCES.ENTRY_URI
  is '权限入口URI';
comment on column T_BSE_RESOURCES.RES_LEVEL
  is '功能层级';
comment on column T_BSE_RESOURCES.PARENT_RES
  is '上级权限';
comment on column T_BSE_RESOURCES.DISPLAY_ORDER
  is '显示顺序';
comment on column T_BSE_RESOURCES.CHECKED
  is '是否权限检查';
comment on column T_BSE_RESOURCES.RES_TYPE
  is '权限类型';
comment on column T_BSE_RESOURCES.LEAF_FLAG
  is '是否叶子节点';
comment on column T_BSE_RESOURCES.ICON_CLS
  is '图标的CSS样式';
comment on column T_BSE_RESOURCES.CLS
  is '节点的CSS样式';
comment on column T_BSE_RESOURCES.NOTES
  is '权限描述';
comment on column T_BSE_RESOURCES.BELONG_SYSTEM_TYPE
  is '所属系统类型';
comment on column T_BSE_RESOURCES.VERSION_NO
  is '数据版本号';
comment on column T_BSE_RESOURCES.CREATE_TIME
  is '创建时间';
comment on column T_BSE_RESOURCES.MODIFY_TIME
  is '更新时间';
comment on column T_BSE_RESOURCES.ACTIVE
  is '是否启用';
comment on column T_BSE_RESOURCES.CREATE_USER_CODE
  is '创建人';
comment on column T_BSE_RESOURCES.MODIFY_USER_CODE
  is '更新人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BSE_RESOURCES
  add constraint PK_T_BSE_RESOURCES primary key (ID);
