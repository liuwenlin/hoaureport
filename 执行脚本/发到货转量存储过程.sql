CREATE OR REPLACE PROCEDURE "HYREPORT_FDHZL" (BEGIN_DATE IN varchar2,END_DATE IN varchar2) is
--变量定义
  v_begin_date date := to_date(BEGIN_DATE,'YYYY/MM/DD');
  v_end_date date := to_date(END_DATE,'YYYY/MM/DD');
  v_month char(8) := to_char(sysdate,'YYYY/MM');
begin
	  --检查是否存在
	  DELETE FROM HYREPORT_fdhzl_HZ WHERE SSR=v_begin_date;
	  --插入发到货转量汇总表
	  INSERT INTO HYREPORT_fdhzl_HZ(SSR,QY,YJGS,FHZL,DHZL,ZZZL,SZZL,XZZL,ZZDH,SZDHZL,XZDHZL,create_time,modify_time,create_user)
	  select v_begin_date AS SSR,A.QY ,A.GS AS YJGS ,SUM(A.FHZL) AS FHZL,SUM(A.DHZL) AS DHZL,SUM(A.ZZZL) AS ZZZL,SUM(nvl(A.SZZL,0)) AS SZZL,SUM(nvl(A.XZZL,0)) AS XZZL,SUM(A.ZZDH) AS ZZDH,SUM(A.SZDH) AS SZDHZL,SUM(A.XZDH) AS XZDHZL,min(sysdate),min(sysdate),min('SYSTEM')
		 from (SELECT qy.QYMC as qy ,yjgs.gsjc as gs ,SUM(YD.hsbl) FHZL,0 DHZL,0 ZZZL,0 SZZL,0 XZZL,0 ZZDH,0 SZDH,0 XZDH
		  FROM hydata.ld_gs GS
		  JOIN hydata.LD_YD YD ON YD.qydzbh = GS.gsbh
		  JOIN hydata.LD_FHDMX FMX ON FMX.ydbh = YD.ydbh
		  JOIN hydata.LD_FHD FHD ON FHD.fhdbh = FMX.fhdbh
		  JOIN hydata.ld_gs FCGS ON FCGS.gsbh = FHD.scgsbh AND FCGS.ssyjgs = GS.ssyjgs
		  JOIN hydata.LD_FHCLQK CL ON CL.fcbh = FHD.fcbh
		  join hydata.ld_gs yjgs on yjgs.gsbh=GS.ssyjgs
		 join hydata.LD_QY qy on gs.QYBH=qy.QYBH
		 WHERE CL.fcsj >= v_begin_date
		   AND CL.fcsj < v_end_date
		   AND YD.CYRQZ <> '20000000000000000001'
		 GROUP BY qy.QYMC, yjgs.gsjc
		UNION ALL
		SELECT  qy.QYMC as qy,yjgs.gsjc as gs,0,SUM(YD.hsbl) , 0,0,0, 0,0, 0
		  FROM hydata.ld_gs MDGS
		  JOIN hydata.LD_YD YD ON YD.mddzbh = MDGS.gsbh
		  JOIN hydata.LD_YDWLSJ_NEW WL ON WL.ydbh =YD.ydbh
		  join hydata.LD_QY qy on MDGS.QYBH=qy.QYBH
		  join hydata.ld_gs yjgs on yjgs.gsbh=MDGS.ssyjgs
		 WHERE WL.dhsj >= v_begin_date
		   AND WL.dhsj < v_end_date
		   AND YD.CYRQZ <> '20000000000000000001'
		 GROUP BY qy.QYMC, yjgs.gsjc
		UNION ALL
		SELECT qy.QYMC as qy,yjgs.gsjc as gs, 0,0,SUM(YD.hsbl),0,0,0,0,0
		  FROM hydata.LD_FHD FHD
		  JOIN hydata.LD_FHDMX FMX ON FHD.fhdbh = FMX.fhdbh
		  JOIN hydata.ld_gs DCGS ON DCGS.gsbh = FHD.gsbh
		  JOIN hydata.ld_gs FCGS ON FCGS.gsbh = FHD.scgsbh
		  JOIN hydata.LD_YD YD ON FMX.ydbh = YD.ydbh
		  JOIN hydata.ld_gs QYGS ON YD.qydzbh = QYGS.gsbh
		  JOIN hydata.ld_gs MDGS ON YD.mddzbh = MDGS.gsbh AND QYGS.ssyjgs <> MDGS.ssyjgs AND FCGS.ssyjgs <> QYGS.ssyjgs AND FCGS.ssyjgs <> MDGS.ssyjgs
		  JOIN hydata.LD_FHCLQK CL ON FHD.fcbh = CL.fcbh
		  join hydata.LD_QY qy on FCGS.QYBH=qy.QYBH
		  join hydata.ld_gs yjgs on yjgs.gsbh=FCGS.ssyjgs
		 WHERE CL.zt <> 4
		   AND CL.fcsj >= v_begin_date
		   AND CL.fcsj < v_end_date
		   AND YD.cyrqz <> '20000000000000000001'
		 GROUP BY qy.QYMC, yjgs.gsjc
		UNION ALL
		SELECT qy.QYMC as qy, yjgs.gsjc as gs, 0, 0, 0, SUM(YD.hsbl), 0, 0, 0, 0
		  FROM hydata.LD_SZYFH FHD
		  JOIN hydata.LD_SZYMX FMX ON FHD.fcbh = FMX.fcbh
		  JOIN hydata.ld_gs GS ON FHD.gsbh = GS.gsbh
		  JOIN hydata.LD_YD YD ON FMX.ydbh = YD.ydbh
		  join hydata.LD_QY qy on GS.QYBH=qy.QYBH
		  join hydata.ld_gs yjgs on yjgs.gsbh=GS.ssyjgs
		 WHERE FHD.fcsj >= v_begin_date
		   AND FHD.fcsj < v_end_date
		   AND YD.cyrqz <> '20000000000000000001'
		 GROUP BY qy.QYMC, yjgs.gsjc
		UNION ALL
		SELECT qy.QYMC as qy, yjgs.gsjc as gs, 0, 0, 0, 0, SUM(YD.hsbl), 0, 0, 0
		  FROM hydata.LD_XZYFH FHD
		  JOIN hydata.LD_XZYMX FMX ON FHD.fcbh = FMX.fcbh
		  JOIN hydata.ld_gs GS ON FHD.gsbh = GS.gsbh
		  JOIN hydata.LD_YD YD ON FMX.ydbh = YD.ydbh
		   join hydata.LD_QY qy on GS.QYBH=qy.QYBH
		  join hydata.ld_gs yjgs on yjgs.gsbh=GS.ssyjgs
		 WHERE FHD.fcsj >= v_begin_date
		   AND FHD.fcsj < v_end_date
		   AND YD.cyrqz <> '20000000000000000001'
		 GROUP BY qy.QYMC, yjgs.gsjc
		UNION ALL
		SELECT qy.QYMC as qy, yjgs.gsjc as gs,  0, 0, 0, 0, 0, SUM(YD.hsbl), 0, 0
		  FROM hydata.LD_YDWLSJ_NEW WL
		  JOIN hydata.ld_gs GS ON wl.yczzgs = GS.gsbh
		  JOIN hydata.LD_YD YD ON WL.ydbh = YD.ydbh
		   join hydata.LD_QY qy on GS.QYBH=qy.QYBH
		  join hydata.ld_gs yjgs on yjgs.gsbh=GS.ssyjgs  
		 WHERE WL.yczzdhsj >= v_begin_date
		   AND WL.yczzdhsj < v_end_date
		   AND YD.cyrqz <> '20000000000000000001'
		 GROUP BY qy.QYMC, yjgs.gsjc
		UNION ALL 
		SELECT qy.QYMC as qy, yjgs.gsjc as gs,  0, 0, 0, 0,  0, SUM(YD.hsbl), 0, 0
		  FROM hydata.LD_YDWLSJ_NEW WL
		  JOIN hydata.ld_gs GS ON wl.eczzgs = GS.gsbh
		  JOIN hydata.LD_YD YD ON WL.ydbh = YD.ydbh
		  join hydata.LD_QY qy on GS.QYBH=qy.QYBH
		  join hydata.ld_gs yjgs on yjgs.gsbh=GS.ssyjgs  
		 WHERE WL.eczzdhsj >=  v_begin_date
		   AND WL.eczzdhsj < v_end_date
		   AND YD.cyrqz <> '20000000000000000001'
		 GROUP BY qy.QYMC, yjgs.gsjc
		UNION ALL
		SELECT qy.QYMC as qy, yjgs.gsjc as gs, 0, 0, 0, 0, 0, 0, SUM(hsbl), 0
		  FROM hydata.LD_SZYFH FHD
		  JOIN hydata.LD_SZYMX FMX ON FHD.fcbh = FMX.fcbh
		  JOIN hydata.ld_gs GS ON FHD.mddgs = GS.gsbh
		  JOIN hydata.LD_YD YD ON FMX.ydbh = YD.ydbh
		   join hydata.LD_QY qy on GS.QYBH=qy.QYBH
		  join hydata.ld_gs yjgs on yjgs.gsbh=GS.ssyjgs   
		 WHERE FHD.rkwcsj >= v_begin_date
		   AND FHD.rkwcsj < v_end_date
		   AND YD.cyrqz <> '20000000000000000001'
		 GROUP BY qy.QYMC, yjgs.gsjc
		UNION ALL
		SELECT qy.QYMC as qy, yjgs.gsjc as gs, 0, 0, 0, 0, 0, 0, 0, SUM(yd.hsbl)
		  FROM hydata.LD_XZYFH FHD
		  JOIN hydata.LD_XZYMX FMX ON FHD.fcbh = FMX.fcbh
		  JOIN hydata.ld_gs GS ON FHD.mddgs = GS.gsbh
		  JOIN hydata.LD_YD YD ON FMX.ydbh = YD.ydbh
		   join hydata.LD_QY qy on GS.QYBH=qy.QYBH
		  join hydata.ld_gs yjgs on yjgs.gsbh=GS.ssyjgs    
		 WHERE FHD.zhddsj >= v_begin_date
		   AND FHD.zhddsj < v_end_date
		   AND YD.cyrqz <> '20000000000000000001'
		 GROUP BY qy.QYMC, yjgs.gsjc
		) A GROUP BY A.QY,A.GS;
		--检查是否存在
		DELETE FROM HYREPORT_fdhzl_mx WHERE SSR=v_begin_date;
		--插入发到货转量明细表
		INSERT into HYREPORT_fdhzl_mx (SSY,SSR, QY, YJGS, FHZL,DHZL,ZZZL,SZZL,XZZL,ZZDH,SZDHZL,XZDHZL,create_time,modify_time,create_user)
		SELECT v_month,v_begin_date AS SSR,QY, YJGS, FHZL,DHZL,ZZZL,SZZL,XZZL,ZZDH,SZDHZL,XZDHZL,create_time,modify_time,create_user  FROM HYREPORT_fdhzl_HZ WHERE SSR=v_begin_date;
		--更新月统计量字段
		MERGE INTO HYREPORT_fdhzl_HZ A
		USING (SELECT YJGS,SUM(FHZL+DHZL+ZZZL+SZZL+XZZL+ZZDH+SZDHZL+XZDHZL) AS ZL  FROM HYREPORT_fdhzl_mx WHERE SSY=v_month GROUP BY YJGS) V
		ON (V.YJGS = A.YJGS)
		WHEN MATCHED THEN UPDATE SET
		A.dyljzl = V.ZL;
END;

