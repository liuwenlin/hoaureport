--�׵��Һ�������(����¼��ʱ���)
--�˵�¼��ʱ��, ǩ��ʱ��, ���˵�����,���˵���ҵ��, ���˵ش���, ���˵�·��, Ŀ�ĵ�����,Ŀ�ĵ���ҵ��, Ŀ�ĵش���, Ŀ�ĵ�·��, ����������, �Ƿ��Ѻ���, �˵����, ��Ʒ����, �˵���Դ, ��è������, ����������, ��������ʱ��, ԤԼʱ��,����ʱ��

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
  is '�˵�¼��ʱ��';
comment on column REPORT_YDJHX_TEMP.QSSJ
  is 'ǩ��ʱ��';
comment on column REPORT_YDJHX_TEMP.QYDMC
  is '���˵�����';
comment on column REPORT_YDJHX_TEMP.QYDSYB
  is '���˵���ҵ��';
comment on column REPORT_YDJHX_TEMP.QYDDQ
  is '���˵ش���';
comment on column REPORT_YDJHX_TEMP.QYDLQ
  is '���˵�·��';
comment on column REPORT_YDJHX_TEMP.MDDMC
  is 'Ŀ�ĵ�����';
comment on column REPORT_YDJHX_TEMP.MDDSYB
  is 'Ŀ�ĵ���ҵ��';
comment on column REPORT_YDJHX_TEMP.MDDDQ
  is 'Ŀ�ĵش���';
comment on column REPORT_YDJHX_TEMP.MDDLQ
  is 'Ŀ�ĵ�·��';
comment on column REPORT_YDJHX_TEMP.TYRMC
  is '����������';
comment on column REPORT_YDJHX_TEMP.SFYHX
  is '�Ƿ��Ѻ���';
comment on column REPORT_YDJHX_TEMP.YDBH
  is '�˵����';
comment on column REPORT_YDJHX_TEMP.CPLX
  is '��Ʒ����';
comment on column REPORT_YDJHX_TEMP.YDLY
  is '�˵���Դ';
comment on column REPORT_YDJHX_TEMP.TMDDH
  is '��è������';
comment on column REPORT_YDJHX_TEMP.HXCZR
  is '����������';
comment on column REPORT_YDJHX_TEMP.HXCZSJ
  is '��������ʱ��';
comment on column REPORT_YDJHX_TEMP.YYSJ
  is 'ԤԼʱ��';
comment on column REPORT_YDJHX_TEMP.DHSJ
  is '����ʱ��';

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
  is '�˵�¼��ʱ��';
comment on column REPORT_YDJHX.QSSJ
  is 'ǩ��ʱ��';
comment on column REPORT_YDJHX.QYDMC
  is '���˵�����';
comment on column REPORT_YDJHX.QYDSYB
  is '���˵���ҵ��';
comment on column REPORT_YDJHX.QYDDQ
  is '���˵ش���';
comment on column REPORT_YDJHX.QYDLQ
  is '���˵�·��';
comment on column REPORT_YDJHX.MDDMC
  is 'Ŀ�ĵ�����';
comment on column REPORT_YDJHX.MDDSYB
  is 'Ŀ�ĵ���ҵ��';
comment on column REPORT_YDJHX.MDDDQ
  is 'Ŀ�ĵش���';
comment on column REPORT_YDJHX.MDDLQ
  is 'Ŀ�ĵ�·��';
comment on column REPORT_YDJHX.TYRMC
  is '����������';
comment on column REPORT_YDJHX.SFYHX
  is '�Ƿ��Ѻ���';
comment on column REPORT_YDJHX.YDBH
  is '�˵����';
comment on column REPORT_YDJHX.CPLX
  is '��Ʒ����';
comment on column REPORT_YDJHX.YDLY
  is '�˵���Դ';
comment on column REPORT_YDJHX.TMDDH
  is '��è������';
comment on column REPORT_YDJHX.HXCZR
  is '����������';
comment on column REPORT_YDJHX.HXCZSJ
  is '��������ʱ��';
comment on column REPORT_YDJHX.YYSJ
  is 'ԤԼʱ��';
comment on column REPORT_YDJHX_TEMP.DHSJ
  is '����ʱ��';

--���洢����-----------
create or replace procedure REPORT_YDJHX_PROC is
--�׵��Һ�������洢����

begin
     
             --�����ʱ��ʵ���ϲ���Ҫ�ֶ����
             delete from REPORT_YDJHX_TEMP;
             
             --��ʱ�����������
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
                   DECODE(a.hxzt, 1, '�Ѻ���', 'δ����') as SFYHX,
                   d.ydbh,
                   DECODE(D.CYRQZ,
                          70000000000000000001,
                          '��-��װ',
                          30000000000000000001,
                          '���ÿ���',
                          10000000000000000001,
                          '���մ�',
                          20000000000000000001,
                          '����',
                          50000000000000000001,
                          'ƫ��',
                          60000000000000000001,
                          '��-�뻧',
                          90000000000000000001,
                          '����ƴ��',
                          80000000000000000001,
                          '��-����') as CPLX,
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
         
              
              --����ʱ������׵��ұ�������
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

--����job  
--����job��Ĭ����ִ�е�(��ע�͵���ʽ���Ƿ�ֹ���洢���̵�ʱ���)  
 
--declare  report_ydjhx_job number;  
--begin  
--dbms_job.submit(report_ydjhx_job,'REPORT_YDJHX_PROC;',sysdate,'sysdate+1/2');  
--commit;  
--end;

-------------����sql-----------------
--select LRSJ  ,  QSSJ  ,  QYDMC ,  QYDSYB,  QYDDQ ,  QYDLQ ,  MDDMC ,  MDDSYB,  
--       MDDDQ ,  MDDLQ ,  TYRMC ,  SFYHX ,  YDBH  ,  CPLX  ,  YDLY  ,  TMDDH ,  
--       HXCZR ,  HXCZSJ,  to_char(YYSJ,'yyyy-mm-dd hh24:mi:ss')  ,  to_char(DHSJ,'yyyy-mm-dd hh24:mi:ss')
--  from REPORT_YDJHX
-- where LRSJ between &BEGINDATE || ' 00:00:01' and
--     &ENDDATE || ' 23:59:59'
