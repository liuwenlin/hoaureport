--易到家核销报表(根据录入时间查)
--运单录入时间, 签收时间, 起运地名称,起运地事业部, 起运地大区, 起运地路区, 目的地名称,目的地事业部, 目的地大区, 目的地路区, 托运人名称, 是否已核销, 运单编号, 产品类型, 运单来源, 天猫订单号, 核销操作人, 核销操作时间, 预约时间,到货时间

-- Create table REPORT_YDJHX_TEMP
create table REPORT_YDJHX_TEMP
(
  LRSJ   VARCHAR2(19),
  QSSJ   VARCHAR2(19),
  QYDMC  CHAR(40),
  QYDSYB VARCHAR2(50),
  QYDDQ  VARCHAR2(50),
  QYDLQ  VARCHAR2(50),
  MDDMC  CHAR(40),
  MDDSYB VARCHAR2(50),
  MDDDQ  VARCHAR2(50),
  MDDLQ  VARCHAR2(50),
  TYRMC  VARCHAR2(50),
  SFYHX  VARCHAR2(6),
  YDBH   CHAR(8) not null,
  CPLX   VARCHAR2(8),
  YDLY   VARCHAR2(20),  
  TMDDH  VARCHAR2(50),
  HXCZR  VARCHAR2(50),
  HXCZSJ DATE,
  YYSJ   DATE,
  DHSJ   DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column REPORT_YDJHX_TEMP.LRSJ
  is '运单录入时间';
comment on column REPORT_YDJHX_TEMP.QSSJ
  is '签收时间';
comment on column REPORT_YDJHX_TEMP.QYDMC
  is '起运地名称';
comment on column REPORT_YDJHX_TEMP.QYDSYB
  is '起运地事业部';
comment on column REPORT_YDJHX_TEMP.QYDDQ
  is '起运地大区';
comment on column REPORT_YDJHX_TEMP.QYDLQ
  is '起运地路区';
comment on column REPORT_YDJHX_TEMP.MDDMC
  is '目的地名称';
comment on column REPORT_YDJHX_TEMP.MDDSYB
  is '目的地事业部';
comment on column REPORT_YDJHX_TEMP.MDDDQ
  is '目的地大区';
comment on column REPORT_YDJHX_TEMP.MDDLQ
  is '目的地路区';
comment on column REPORT_YDJHX_TEMP.TYRMC
  is '托运人名称';
comment on column REPORT_YDJHX_TEMP.SFYHX
  is '是否已核销';
comment on column REPORT_YDJHX_TEMP.YDBH
  is '运单编号';
comment on column REPORT_YDJHX_TEMP.CPLX
  is '产品类型';
comment on column REPORT_YDJHX_TEMP.YDLY
  is '运单来源';
comment on column REPORT_YDJHX_TEMP.TMDDH
  is '天猫订单号';
comment on column REPORT_YDJHX_TEMP.HXCZR
  is '核销操作人';
comment on column REPORT_YDJHX_TEMP.HXCZSJ
  is '核销操作时间';
comment on column REPORT_YDJHX_TEMP.YYSJ
  is '预约时间';
comment on column REPORT_YDJHX_TEMP.DHSJ
  is '到货时间';

-- Create table REPORT_YDJHX
create table REPORT_YDJHX
(
  LRSJ   VARCHAR2(19),
  QSSJ   VARCHAR2(19),
  QYDMC  CHAR(40),
  QYDSYB VARCHAR2(50),
  QYDDQ  VARCHAR2(50),
  QYDLQ  VARCHAR2(50),
  MDDMC  CHAR(40),
  MDDSYB VARCHAR2(50),
  MDDDQ  VARCHAR2(50),
  MDDLQ  VARCHAR2(50),
  TYRMC  VARCHAR2(50),
  SFYHX  VARCHAR2(6),
  YDBH   CHAR(8) not null,
  CPLX   VARCHAR2(8),
  YDLY   VARCHAR2(20),  
  TMDDH  VARCHAR2(50),
  HXCZR  VARCHAR2(50),
  HXCZSJ DATE,
  YYSJ   DATE,
  DHSJ   DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column REPORT_YDJHX.LRSJ
  is '运单录入时间';
comment on column REPORT_YDJHX.QSSJ
  is '签收时间';
comment on column REPORT_YDJHX.QYDMC
  is '起运地名称';
comment on column REPORT_YDJHX.QYDSYB
  is '起运地事业部';
comment on column REPORT_YDJHX.QYDDQ
  is '起运地大区';
comment on column REPORT_YDJHX.QYDLQ
  is '起运地路区';
comment on column REPORT_YDJHX.MDDMC
  is '目的地名称';
comment on column REPORT_YDJHX.MDDSYB
  is '目的地事业部';
comment on column REPORT_YDJHX.MDDDQ
  is '目的地大区';
comment on column REPORT_YDJHX.MDDLQ
  is '目的地路区';
comment on column REPORT_YDJHX.TYRMC
  is '托运人名称';
comment on column REPORT_YDJHX.SFYHX
  is '是否已核销';
comment on column REPORT_YDJHX.YDBH
  is '运单编号';
comment on column REPORT_YDJHX.CPLX
  is '产品类型';
comment on column REPORT_YDJHX.YDLY
  is '运单来源';
comment on column REPORT_YDJHX.TMDDH
  is '天猫订单号';
comment on column REPORT_YDJHX.HXCZR
  is '核销操作人';
comment on column REPORT_YDJHX.HXCZSJ
  is '核销操作时间';
comment on column REPORT_YDJHX.YYSJ
  is '预约时间';
comment on column REPORT_YDJHX_TEMP.DHSJ
  is '到货时间';

--建存储过程-----------
create or replace procedure REPORT_YDJHX_PROC is
--易到家核销报表存储过程

begin
     
             --清空临时表，实际上不需要手动清空
             delete from REPORT_YDJHX_TEMP;
             
             --临时表中添加数据
             insert into REPORT_YDJHX_TEMP
              select to_char(d.lrsj, 'yyyy-mm-dd hh24:mi:ss') as LRSJ,
                   to_char(d.qsrq, 'yyyy-mm-dd hh24:mi:ss') as QSSJ,
                   d.qydmc,
                   e.syb as QYDSYB,
                   e.jydq as QYDDQ,
                   e.luqu as QYDLQ,
                   d.mddmc,
                   f.syb as MDDSYB,
                   f.jydq as MDDDQ,
                   f.luqu as MDDLQ,
                   d.tyrmc,
                   DECODE(a.hxzt, 1, '已核销', '未核销') as SFYHX,
                   d.ydbh,
                   DECODE(D.CYRQZ,
                          70000000000000000001,
                          '易-安装',
                          30000000000000000001,
                          '经济快运',
                          10000000000000000001,
                          '定日达',
                          20000000000000000001,
                          '整车',
                          50000000000000000001,
                          '偏线',
                          60000000000000000001,
                          '易-入户',
                          90000000000000000001,
                          '经济拼车',
                          80000000000000000001,
                          '易-包裹') as CPLX,
                   c.DCO_SYS_ORIGN as YDLY,
                   nvl(c.dco_tm_source_code, j.tm_orderid) as TMDDH,
                   decode(a.HXZT, 1, a.modify_user_code, '') as HXCZR,
                   decode(a.HXZT, 1, a.MODIFY_TIME, '') as HXCZSJ,
                   a.yyshsj as YYSJ,
                   n.dhsj
              from hydata.ld_yd d
              left join hydata.OMS_DC_ORDER C ON C.DCO_BILL_NO = d.YDBH
              LEFT JOIN hydata.LD_YD_QTXX J ON J.YDBH = d.YDBH
              left join (SELECT * FROM(
SELECT z.ydbh ,z.hxzt, z.create_time,z.modify_user_code,z.MODIFY_TIME,z.yyshsj,ROW_NUMBER()
OVER(PARTITION BY z.ydbh ORDER BY z.create_time) AS code_id
FROM hydata.ld_yysh z
)
WHERE code_id =1) a on a.ydbh = d.ydbh
              left join HYREPORT_SSLUQU e on ltrim(rtrim(d.qydmc)) = e.qydjc
              left join HYREPORT_SSLUQU f on ltrim(rtrim(d.mddmc)) = f.qydjc
              left join (select a.ydbh,a.fhdbh,b.rkwcsj dhsj from 
(select ydbh,max(fhdbh) fhdbh from hydata.ld_fhdmx group by ydbh) a  --where a.ydbh='04735953'
left join hydata.ld_fhd b on a.fhdbh = b.fhdbh
)  n on d.ydbh = n.ydbh

             where (j.hx = 1 or
                   (c.dco_tm_source_code is not null and c.dco_sys_orign = 'TM'));
               /*and (d.lrsj between
                   to_date('2016-06-29' || '00:00:00', 'yyyy/MM/dd hh24:mi:ss') and
                   to_date('2016-06-29' || '23:59:59', 'yyyy/MM/dd hh24:mi:ss'))*/
         
              
              --用临时表更新易到家报表主表
              merge into
                  REPORT_YDJHX m using REPORT_YDJHX_TEMP t
                  on (m.YDBH = t.YDBH)
                  when matched then
                  update set
                    m.LRSJ  =t.LRSJ,m.QSSJ  =t.QSSJ,m.QYDMC =t.QYDMC,m.QYDSYB=t.QYDSYB,m.QYDDQ =t.QYDDQ,
                    m.QYDLQ =t.QYDLQ,m.MDDMC =t.MDDMC,m.MDDSYB=t.MDDSYB,m.MDDDQ =t.MDDDQ,m.MDDLQ =t.MDDLQ,
                    m.TYRMC =t.TYRMC,m.SFYHX =t.SFYHX,m.CPLX  =t.CPLX,m.YDLY  =t.YDLY,
                    m.TMDDH =t.TMDDH,m.HXCZR =t.HXCZR,m.HXCZSJ=t.HXCZSJ,m.YYSJ  =t.YYSJ,m.DHSJ  =t.DHSJ
                  when not matched then
                  insert (LRSJ,QSSJ,QYDMC,QYDSYB,QYDDQ,QYDLQ,MDDMC,MDDSYB,MDDDQ,MDDLQ,TYRMC, 
                          SFYHX,YDBH,CPLX,YDLY,TMDDH,HXCZR,HXCZSJ,YYSJ,DHSJ)
                  values(t.LRSJ,t.QSSJ,t.QYDMC,t.QYDSYB,t.QYDDQ,t.QYDLQ,t.MDDMC,t.MDDSYB,t.MDDDQ,t.MDDLQ,t.TYRMC, 
                         t.SFYHX,t.YDBH,t.CPLX,t.YDLY,t.TMDDH,t.HXCZR,t.HXCZSJ,t.YYSJ,t.DHSJ);              
               commit;             
end;

--创建job  
--建立job后默认是执行的(以注释的形式放是防止建存储过程的时候会)  
 
--declare  report_ydjhx_job number;  
--begin  
--dbms_job.submit(report_ydjhx_job,'REPORT_YDJHX_PROC;',sysdate,'sysdate+1/2');  
--commit;  
--end;

-------------报表sql-----------------
--select LRSJ  ,  QSSJ  ,  QYDMC ,  QYDSYB,  QYDDQ ,  QYDLQ ,  MDDMC ,  MDDSYB,  
--       MDDDQ ,  MDDLQ ,  TYRMC ,  SFYHX ,  YDBH  ,  CPLX  ,  YDLY  ,  TMDDH ,  
--       HXCZR ,  HXCZSJ,  to_char(YYSJ,'yyyy-mm-dd hh24:mi:ss')  ,  to_char(DHSJ,'yyyy-mm-dd hh24:mi:ss')
--  from REPORT_YDJHX
-- where LRSJ between &BEGINDATE || ' 00:00:01' and
--     &ENDDATE || ' 23:59:59'
