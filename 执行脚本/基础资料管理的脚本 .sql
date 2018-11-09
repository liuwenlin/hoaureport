场站
REPORT_STATIONS
//脚本开始
drop table REPORT_STATIONS cascade constraints;

/*==============================================================*/
/* Table: REPORT_STATIONS                                       */
/*==============================================================*/
create table REPORT_STATIONS  (
   STATIONS_ID          NUMBER(10)                      not null,
   STATIONS_SHORTNAME   VARCHAR2(40),
   STATIONS_NAME        VARCHAR2(50),
   THE_AREA             VARCHAR2(20),
   THE_BUSINESS_DEPARTMENT VARCHAR2(20),
   ACTIVE               CHAR(1),
   EFFECTED_TIME        DATE,
   INVALID_TIME         DATE,
   CREATE_TIME          DATE,
   CREATE_USER_CODE     VARCHAR2(20),
   MODIFY_TIME          DATE,
   MODIFY_USER_CODE     VARCHAR2(20),
   constraint PK_REPORT_STATIONS primary key (STATIONS_ID)
);

comment on column REPORT_STATIONS.STATIONS_SHORTNAME is
'场站简称';

comment on column REPORT_STATIONS.STATIONS_NAME is
'场站名称';

comment on column REPORT_STATIONS.THE_AREA is
'所属大区';

comment on column REPORT_STATIONS.THE_BUSINESS_DEPARTMENT is
'所属事业部';

comment on column REPORT_STATIONS.ACTIVE is
'是否有效';

comment on column REPORT_STATIONS.EFFECTED_TIME is
'生效时间';

comment on column REPORT_STATIONS.INVALID_TIME is
'失效时间';

comment on column REPORT_STATIONS.CREATE_TIME is
'创建时间';

comment on column REPORT_STATIONS.CREATE_USER_CODE is
'创建者编码';

comment on column REPORT_STATIONS.MODIFY_TIME is
'修改时间';

comment on column REPORT_STATIONS.MODIFY_USER_CODE is
'修改者编码';
脚本结束
场站序列
//脚本开始

CREATE SEQUENCE  SEQ_STATIONS_ID  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 10 NOORDER  NOCYCLE ;

脚本结束
场站辖区
REPORT_STATIONSAREA

//脚本开始
drop table REPORT_STATIONSAREA cascade constraints;

/*==============================================================*/
/* Table: REPORT_STATIONSAREA                                   */
/*==============================================================*/
create table REPORT_STATIONSAREA  (
   STATIONSAREA_ID      NUMBER(10)                      not null,
   OPERATING_UNIT_CODE  VARCHAR2(40),
   THE_STATIONS_SHORTNAME VARCHAR2(40),
   THE_AREA             VARCHAR2(40),
   THE_BUSINESS_DEPARTMENT VARCHAR2(40),
   ACTIVE               CHAR(1),
   EFFECTED_TIME        DATE,
   INVALID_TIME         DATE,
   CREATE_TIME          DATE,
   CREATE_USER_CODE     VARCHAR2(20),
   MODIFY_TIME          DATE,
   MODIFY_USER_CODE     VARCHAR2(20),
   constraint PK_REPORT_STATIONSAREA primary key (STATIONSAREA_ID)
);

comment on column REPORT_STATIONSAREA.OPERATING_UNIT_CODE is
'作业单位代码';

comment on column REPORT_STATIONSAREA.THE_STATIONS_SHORTNAME is
'归属场站简称';

comment on column REPORT_STATIONSAREA.THE_AREA is
'所属大区';

comment on column REPORT_STATIONSAREA.THE_BUSINESS_DEPARTMENT is
'所属事业部';

comment on column REPORT_STATIONSAREA.ACTIVE is
'是否有效';

comment on column REPORT_STATIONSAREA.EFFECTED_TIME is
'生效时间';

comment on column REPORT_STATIONSAREA.INVALID_TIME is
'失效时间';

comment on column REPORT_STATIONSAREA.CREATE_TIME is
'创建时间';

comment on column REPORT_STATIONSAREA.CREATE_USER_CODE is
'创建者编码';

comment on column REPORT_STATIONSAREA.MODIFY_TIME is
'修改时间';

comment on column REPORT_STATIONSAREA.MODIFY_USER_CODE is
'修改者编码';
脚本结束
场站辖区序列

//脚本开始

CREATE SEQUENCE  SEQ_STATIONSAREA_ID  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 10 NOORDER  NOCYCLE ;

脚本结束

车队
REPORT_FLEET
//脚本开始
drop table REPORT_FLEET cascade constraints;

/*==============================================================*/
/* Table: REPORT_FLEET                                          */
/*==============================================================*/
create table REPORT_FLEET  (
   FLEET_ID             NUMBER(10)                      not null,
   FLEET_SHORTNAME      VARCHAR2(40),
   FLEET_NAME           VARCHAR2(50),
   THE_AREA             VARCHAR2(40),
   THE_BUSINESS_DEPARTMENT VARCHAR2(40),
   ACTIVE               CHAR(1),
   EFFECTED_TIME        DATE,
   INVALID_TIME         DATE,
   CREATE_TIME          DATE,
   CREATE_USER_CODE     VARCHAR2(20),
   MODIFY_TIME          DATE,
   MODIFY_USER_CODE     VARCHAR2(20),
   constraint PK_REPORT_FLEET primary key (FLEET_ID)
);

comment on column REPORT_FLEET.FLEET_SHORTNAME is
'车队简称';

comment on column REPORT_FLEET.FLEET_NAME is
'车队名称';

comment on column REPORT_FLEET.THE_AREA is
'所属大区';

comment on column REPORT_FLEET.THE_BUSINESS_DEPARTMENT is
'所属事业部';

comment on column REPORT_FLEET.ACTIVE is
'是否有效';

comment on column REPORT_FLEET.EFFECTED_TIME is
'生效时间';

comment on column REPORT_FLEET.INVALID_TIME is
'失效时间';

comment on column REPORT_FLEET.CREATE_TIME is
'创建时间';

comment on column REPORT_FLEET.CREATE_USER_CODE is
'创建者编码';

comment on column REPORT_FLEET.MODIFY_TIME is
'修改时间';

comment on column REPORT_FLEET.MODIFY_USER_CODE is
'修改者编码';
脚本结束

车队序列
//脚本开始

CREATE SEQUENCE  SEQ_FLEET_ID  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 10 NOORDER  NOCYCLE ;

脚本结束
车队辖区
REPORT_FLEETAREA
//脚本开始
drop table REPORT_FLEETAREA cascade constraints;

/*==============================================================*/
/* Table: REPORT_FLEETAREA                                      */
/*==============================================================*/
create table REPORT_FLEETAREA  (
   FLEETAREA_ID         NUMBER(10)                      not null,
   AREA_OPERATING_UNIT_SHORTNAME VARCHAR2(40),
   THE_FLEET_SHORTNAME  VARCHAR2(40),
   THE_AREA             VARCHAR2(40),
   THE_BUSINESS_DEPARTMENT VARCHAR2(40),
   ACTIVE               CHAR(1),
   EFFECTED_TIME        DATE,
   INVALID_TIME         DATE,
   CREATE_TIME          DATE,
   CREATE_USER_CODE     VARCHAR2(20),
   MODIFY_TIME          DATE,
   MODIFY_USER_CODE     VARCHAR2(20),
   constraint PK_REPORT_FLEETAREA primary key (FLEETAREA_ID)
);

comment on column REPORT_FLEETAREA.AREA_OPERATING_UNIT_SHORTNAME is
'辖区作业单位简称';

comment on column REPORT_FLEETAREA.THE_FLEET_SHORTNAME is
'归属车队简称';

comment on column REPORT_FLEETAREA.THE_AREA is
'所属大区';

comment on column REPORT_FLEETAREA.THE_BUSINESS_DEPARTMENT is
'所属事业部';

comment on column REPORT_FLEETAREA.ACTIVE is
'是否有效';

comment on column REPORT_FLEETAREA.EFFECTED_TIME is
'生效时间';

comment on column REPORT_FLEETAREA.INVALID_TIME is
'失效时间';

comment on column REPORT_FLEETAREA.CREATE_TIME is
'创建时间';

comment on column REPORT_FLEETAREA.CREATE_USER_CODE is
'创建者编码';

comment on column REPORT_FLEETAREA.MODIFY_TIME is
'修改时间';

comment on column REPORT_FLEETAREA.MODIFY_USER_CODE is
'修改者编码';
脚本结束

车队辖区序列
//脚本开始

CREATE SEQUENCE  SEQ_FLEETAREA_ID  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 10 NOORDER  NOCYCLE ;

脚本结束
平台
REPORT_PLATFORM
//脚本开始
drop table REPORT_PLATFORM cascade constraints;

/*==============================================================*/
/* Table: REPORT_PLATFORM                                       */
/*==============================================================*/
create table REPORT_PLATFORM  (
   PLATFORM_ID          NUMBER(10)                      not null,
   PLATFORM_SHORTNAME   VARCHAR2(40),
   PLATFORM_NAME        VARCHAR2(40),
   IS_HEADQUARTERS_PLATFORM CHAR(1),
   THE_FLEET            VARCHAR2(40),
   THE_ROAD_AREA        VARCHAR2(50),
   THE_AREA             VARCHAR2(40),
   THE_BUSINESS_DEPARTMENT VARCHAR2(40),
   ACTIVE               CHAR(1),
   EFFECTED_TIME        DATE,
   INVALID_TIME         DATE,
   CREATE_TIME          DATE,
   CREATE_USER_CODE     VARCHAR2(20),
   MODIFY_TIME          DATE,
   MODIFY_USER_CODE     VARCHAR2(20),
   constraint PK_REPORT_PLATFORM primary key (PLATFORM_ID)
);

comment on column REPORT_PLATFORM.PLATFORM_SHORTNAME is
'平台简称';

comment on column REPORT_PLATFORM.PLATFORM_NAME is
'平台名称';

comment on column REPORT_PLATFORM.IS_HEADQUARTERS_PLATFORM is
'是否总部平台';

comment on column REPORT_PLATFORM.THE_FLEET is
'所属车队';

comment on column REPORT_PLATFORM.THE_ROAD_AREA is
'所属路区';

comment on column REPORT_PLATFORM.THE_AREA is
'所属大区';

comment on column REPORT_PLATFORM.THE_BUSINESS_DEPARTMENT is
'所属事业部';

comment on column REPORT_PLATFORM.ACTIVE is
'是否有效';

comment on column REPORT_PLATFORM.EFFECTED_TIME is
'生效时间';

comment on column REPORT_PLATFORM.INVALID_TIME is
'失效时间';

comment on column REPORT_PLATFORM.CREATE_TIME is
'创建时间';

comment on column REPORT_PLATFORM.CREATE_USER_CODE is
'创建者编码';

comment on column REPORT_PLATFORM.MODIFY_TIME is
'修改时间';

comment on column REPORT_PLATFORM.MODIFY_USER_CODE is
'修改者编码';
脚本结束

平台序列
//脚本开始

CREATE SEQUENCE  SEQ_PLATFORM_ID  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 10 NOORDER  NOCYCLE ;

脚本结束
平台辖区
REPORT_PLATFORMAREA
//脚本开始
drop table REPORT_PLATFORMAREA cascade constraints;

/*==============================================================*/
/* Table: REPORT_PLATFORMAREA                                   */
/*==============================================================*/
create table REPORT_PLATFORMAREA  (
   PLATFORMAREA_ID      NUMBER(10)                      not null,
   AREA_OPERATING_UNIT_SHORTNAME VARCHAR2(40),
   THE_PLATFORMAREA_SHORTNAME VARCHAR2(40),
   THE_AREA             VARCHAR2(40),
   THE_BUSINESS_DEPARTMENT VARCHAR2(40),
   ACTIVE               CHAR(1),
   EFFECTED_TIME        DATE,
   INVALID_TIME         DATE,
   CREATE_TIME          DATE,
   CREATE_USER_CODE     VARCHAR2(20),
   MODIFY_TIME          DATE,
   MODIFY_USER_CODE     VARCHAR2(20),
   constraint PK_REPORT_PLATFORMAREA primary key (PLATFORMAREA_ID)
);

comment on column REPORT_PLATFORMAREA.AREA_OPERATING_UNIT_SHORTNAME is
'辖区作业单位简称';

comment on column REPORT_PLATFORMAREA.THE_PLATFORMAREA_SHORTNAME is
'归属平台简称';

comment on column REPORT_PLATFORMAREA.THE_AREA is
'所属大区';

comment on column REPORT_PLATFORMAREA.THE_BUSINESS_DEPARTMENT is
'所属事业部';

comment on column REPORT_PLATFORMAREA.ACTIVE is
'是否有效';

comment on column REPORT_PLATFORMAREA.EFFECTED_TIME is
'生效时间';

comment on column REPORT_PLATFORMAREA.INVALID_TIME is
'失效时间';

comment on column REPORT_PLATFORMAREA.CREATE_TIME is
'创建时间';

comment on column REPORT_PLATFORMAREA.CREATE_USER_CODE is
'创建者编号';

comment on column REPORT_PLATFORMAREA.MODIFY_TIME is
'修改时间';

comment on column REPORT_PLATFORMAREA.MODIFY_USER_CODE is
'修改者编号';
脚本结束

平台辖区序列
//脚本开始

CREATE SEQUENCE  SEQ_PLATFORMAREA_ID  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 10 NOORDER  NOCYCLE ;

脚本结束
                    
吨成本
REPORT_TONCOST
//脚本开始
drop table REPORT_TONCOST cascade constraints;

/*==============================================================*/
/* Table: REPORT_TONCOST                                        */
/*==============================================================*/
create table REPORT_TONCOST  (
   TON_COST_ID          NUMBER(10)                      not null,
   DEPARTMENT           VARCHAR2(40),
   TARGET_VALUE         VARCHAR2(40),
   TARGET_VALUE_MONTH   VARCHAR2(40),
   IMPORT_TIME          DATE,
   MANAGER              VARCHAR2(20),
   ACTIVE               CHAR(1),
   EFFECTED_TIME        DATE,
   INVALID_TIME         DATE,
   CREATE_TIME          DATE,
   CREATE_USER_CODE     VARCHAR2(20),
   MODIFY_TIME          DATE,
   MODIFY_USER_CODE     VARCHAR2(20),
LASTMONTH_FINISHVALUE  VARCHAR2(20), 
   constraint PK_REPORT_TONCOST primary key (TON_COST_ID)
);

comment on table REPORT_TONCOST is
'吨成本';

comment on column REPORT_TONCOST.TON_COST_ID is
'吨成本ID';

comment on column REPORT_TONCOST.DEPARTMENT is
'部门';

comment on column REPORT_TONCOST.TARGET_VALUE is
'目标值';

comment on column REPORT_TONCOST.TARGET_VALUE_MONTH is
'目标值所属月份';

comment on column REPORT_TONCOST.IMPORT_TIME is
'导入时间';

comment on column REPORT_TONCOST.MANAGER is
'负责人';

comment on column REPORT_TONCOST.ACTIVE is
'是否有效';

comment on column REPORT_TONCOST.EFFECTED_TIME is
'生效时间';

comment on column REPORT_TONCOST.INVALID_TIME is
'失效时间';

comment on column REPORT_TONCOST.CREATE_TIME is
'创建时间';

comment on column REPORT_TONCOST.CREATE_USER_CODE is
'创建者编码';

comment on column REPORT_TONCOST.MODIFY_TIME is
'修改时间';

comment on column REPORT_TONCOST.MODIFY_USER_CODE is
'修改者编码';
comment on column REPORT_TONCOST.LASTMONTH_FINISHVALUE is
'上月完成值';
脚本结束

吨成本序列
//脚本开始

CREATE SEQUENCE SEQ_TONCOST_ID  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 10 NOORDER  NOCYCLE ;

脚本结束


次日送达率
REPORT_NEXTDAY_REACH_RATE
//脚本开始

drop table REPORT_NEXTDAY_REACH_RATE cascade constraints;

/*==============================================================*/
/* Table: REPORT_NEXTDAY_REACH_RATE                             */
/*==============================================================*/
create table REPORT_NEXTDAY_REACH_RATE  (
   REACHRATE_ID         NUMBER(10)                      not null,
   DEPARTMENT           VARCHAR2(40),
   TARGET_VALUE         VARCHAR2(40),
   TARGET_VALUE_MONTH   VARCHAR2(40),
   IMPORT_TIME          DATE,
   MANAGER              VARCHAR2(20),
   ACTIVE               CHAR(1),
   EFFECTED_TIME        DATE,
   INVALID_TIME         DATE,
   CREATE_TIME          DATE,
   CREATE_USER_CODE     VARCHAR2(20),
   MODIFY_TIME          DATE,
   MODIFY_USER_CODE     VARCHAR2(20),
   LASTMONTH_FINISHVALUE  VARCHAR2(20), 
   constraint PK_REPORT_NEXTDAY_REACH_RATE primary key (REACHRATE_ID)
);

comment on table REPORT_NEXTDAY_REACH_RATE is
'次日送达率';

comment on column REPORT_NEXTDAY_REACH_RATE.REACHRATE_ID is
'次日送达率ID';

comment on column REPORT_NEXTDAY_REACH_RATE.DEPARTMENT is
'部门';

comment on column REPORT_NEXTDAY_REACH_RATE.TARGET_VALUE is
'目标值';

comment on column REPORT_NEXTDAY_REACH_RATE.TARGET_VALUE_MONTH is
'目标值所属月份';

comment on column REPORT_NEXTDAY_REACH_RATE.IMPORT_TIME is
'导入时间';

comment on column REPORT_NEXTDAY_REACH_RATE.MANAGER is
'负责人';

comment on column REPORT_NEXTDAY_REACH_RATE.ACTIVE is
'是否有效';

comment on column REPORT_NEXTDAY_REACH_RATE.EFFECTED_TIME is
'生效时间';

comment on column REPORT_NEXTDAY_REACH_RATE.INVALID_TIME is
'失效时间';

comment on column REPORT_NEXTDAY_REACH_RATE.CREATE_TIME is
'创建时间';

comment on column REPORT_NEXTDAY_REACH_RATE.CREATE_USER_CODE is
'创建者编码';

comment on column REPORT_NEXTDAY_REACH_RATE.MODIFY_TIME is
'修改时间';

comment on column REPORT_NEXTDAY_REACH_RATE.MODIFY_USER_CODE is
'修改者编码';
comment on column REPORT_NEXTDAY_REACH_RATE.LASTMONTH_FINISHVALUE is
'上月完成值';


脚本结束

次日送达率序列
//脚本开始

CREATE SEQUENCE  SEQ_REACHRATE_ID  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 10 NOORDER  NOCYCLE ;


脚本结束


送货及时率
REPORT_DELIVER_INTIME_RATE
//脚本开始

drop table REPORT_DELIVER_INTIME_RATE cascade constraints;

/*==============================================================*/
/* Table: REPORT_DELIVER_INTIME_RATE                            */
/*==============================================================*/
create table REPORT_DELIVER_INTIME_RATE  (
INTIMERATE_ID        NUMBER(10)                      not null,
   DEPARTMENT           VARCHAR2(40),
   TARGET_VALUE         VARCHAR2(40),
   TARGET_VALUE_MONTH   VARCHAR2(40),
   IMPORT_TIME          DATE,
   MANAGER              VARCHAR2(20),
   ACTIVE               CHAR(1),
   EFFECTED_TIME        DATE,
   INVALID_TIME         DATE,
   CREATE_TIME          DATE,
   CREATE_USER_CODE     VARCHAR2(20),
   MODIFY_TIME          DATE,
   MODIFY_USER_CODE     VARCHAR2(20),
   LASTMONTH_FINISHVALUE  VARCHAR2(20), 
   constraint PK_REPORT_DELIVER_INTIME_RATE primary key (INTIMERATE_ID)
);

comment on table REPORT_DELIVER_INTIME_RATE is
'送货及时率';

comment on column REPORT_DELIVER_INTIME_RATE.INTIMERATE_ID is
'送货及时率ID';

comment on column REPORT_DELIVER_INTIME_RATE.DEPARTMENT is
'部门';

comment on column REPORT_DELIVER_INTIME_RATE.TARGET_VALUE is
'目标值';

comment on column REPORT_DELIVER_INTIME_RATE.TARGET_VALUE_MONTH is
'目标值所属月份';

comment on column REPORT_DELIVER_INTIME_RATE.IMPORT_TIME is
'导入时间';

comment on column REPORT_DELIVER_INTIME_RATE.MANAGER is
'负责人';

comment on column REPORT_DELIVER_INTIME_RATE.ACTIVE is
'是否有效';

comment on column REPORT_DELIVER_INTIME_RATE.EFFECTED_TIME is
'生效时间';

comment on column REPORT_DELIVER_INTIME_RATE.INVALID_TIME is
'失效时间';

comment on column REPORT_DELIVER_INTIME_RATE.CREATE_TIME is
'创建时间';

comment on column REPORT_DELIVER_INTIME_RATE.CREATE_USER_CODE is
'创建者编码';

comment on column REPORT_DELIVER_INTIME_RATE.MODIFY_TIME is
'修改时间';

comment on column REPORT_DELIVER_INTIME_RATE.MODIFY_USER_CODE is
'修改者编码';
comment on column REPORT_DELIVER_INTIME_RATE.LASTMONTH_FINISHVALUE is
'上月完成值';


脚本结束

送货及时率序列
//脚本开始

CREATE SEQUENCE  SEQ_INTIMERATE_ID  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 10 NOORDER  NOCYCLE ;

脚本结束


