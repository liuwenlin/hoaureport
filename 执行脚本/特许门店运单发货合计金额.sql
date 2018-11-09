--创建表
-- Create table
create table REPORT_TXJYYDHJJSJE_TEMP
(
  YDBH CHAR(8) not null,
  SYB  VARCHAR2(50),
  DQ   VARCHAR2(50),
  GSJC VARCHAR2(40),
  HJJE NUMBER(12,2) not null,
  TYRQ DATE
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
comment on column REPORT_TXJYYDHJJSJE_TEMP.YDBH
  is '运单编号';
comment on column REPORT_TXJYYDHJJSJE_TEMP.SYB
  is '起运地所属事业部';
comment on column REPORT_TXJYYDHJJSJE_TEMP.DQ
  is '起运地所属大区';
comment on column REPORT_TXJYYDHJJSJE_TEMP.GSJC
  is '起运地简称';
comment on column REPORT_TXJYYDHJJSJE_TEMP.HJJE
  is '合计结算金额';
comment on column REPORT_TXJYYDHJJSJE_TEMP.TYRQ
  is '托运日期';

-- Create table
create table REPORT_TXJYYDHJJSJE
(
  YDBH CHAR(8) not null,
  SYB  VARCHAR2(50),
  DQ   VARCHAR2(50),
  GSJC VARCHAR2(40),
  HJJE NUMBER(12,2) not null,
  TYRQ DATE
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
comment on column REPORT_TXJYYDHJJSJE.YDBH
  is '运单编号';
comment on column REPORT_TXJYYDHJJSJE.SYB
  is '起运地所属事业部';
comment on column REPORT_TXJYYDHJJSJE.DQ
  is '起运地所属大区';
comment on column REPORT_TXJYYDHJJSJE.GSJC
  is '起运地简称';
comment on column REPORT_TXJYYDHJJSJE.HJJE
  is '合计结算金额';
comment on column REPORT_TXJYYDHJJSJE.TYRQ
  is '托运日期';

--建存储过程-----------
create or replace procedure REPORT_TXJYYDHJJSJE_PROC is
--特许经营运单合计结算金额存储过程

begin
     
             --清空临时表，实际上不需要手动清空(假临时表，后续可优化)
             delete from REPORT_TXJYYDHJJSJE_TEMP;
             
             --临时表中添加数据
             insert into REPORT_TXJYYDHJJSJE_TEMP
              select a.ydbh,f.syb,f.jydq dq,d.gsjc,a.hjje,b.tyrq
                from (SELECT * FROM(
SELECT z.*,ROW_NUMBER()
OVER(PARTITION BY z.ydbh ORDER BY z.record_date desc) AS code_id
FROM hydata.LD_TXMD_FHYFJSD z where z.sfyx=1)
WHERE code_id =1) a
                inner join HYDATA.LD_YD b on a.ydbh = b.ydbh and b.ydzt <> 5
                left join hydata.ld_gs d on b.qydzbh = d.gsbh
                left join HYREPORT_SSLUQU f
                on d.gsjc = f.qydjc
                where a.sfyx=1 
                and (b.tyrq between (sysdate-10) 
                            and sysdate);         
              
              --用临时表更新易到家报表主表
              merge into
                  REPORT_TXJYYDHJJSJE m using REPORT_TXJYYDHJJSJE_TEMP t
                  on (m.YDBH = t.YDBH)
                  when matched then
                  update set
                         m.syb = t.syb,m.dq = t.dq,m.gsjc = t.gsjc,m.hjje = t.hjje,m.tyrq = t.tyrq
                  when not matched then
                  insert (ydbh,syb,dq,gsjc,hjje,tyrq)
                  values(t.ydbh,t.syb,t.dq,t.gsjc,t.hjje,t.tyrq);              
               commit;             
end;

--创建job  
--建立job后默认是执行的(以注释的形式放是防止建存储过程的时候会)  
 
--declare  report_txjyydhjjsje_job number;  
--begin  
--dbms_job.submit(report_txjyydhjjsje_job,'REPORT_TXJYYDHJJSJE_PROC;',sysdate,'sysdate+1/2');  
--commit;  
--end;


--报表界面设计
--特许经营运单合计结算金额

--运单编号,起运地所属事业部,起运地所属大区,起运地简称,合计结算金额,托运日期
--select a.ydbh, a.syb, a.dq, a.gsjc, a.hjje, a.tyrq
  --from REPORT_TXJYYDHJJSJE a
 --where a.tyrq between
       --to_date(&BEGINDATE || '00:00:00', 'yyyy/MM/dd hh24:mi:ss') and
       --to_date(&ENDDATE || '23:59:59', 'yyyy/MM/dd hh24:mi:ss')

--[

--{"labelName":"查询日期","type":"D","id":"BEGINDATE"},

--{"labelName":"至","type":"D","id":"ENDDATE"}]