package com.hoau.hoaureport.module.job.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hoaureport.module.job.server.dao.EczzsjMapper;
import com.hoau.hoaureport.module.job.server.service.IEczzsjService;
import com.hoau.hoaureport.module.job.shared.domain.EczzsjEntity;
@Service
public class EczzsjService implements IEczzsjService {
	private static final Log LOG = LogFactory.getLog(EczzsjService.class);
	@Autowired
	private EczzsjMapper eczzsjMapper;
	
	private List<EczzsjEntity> eczzsjEntities;
	//一次中转到车确认时间  第一条的zhddsj
	public String ld_yczzdcqrsj = "";
	//一次中转到货时间       第一条的RKSJ
	public String ld_yczzdhsj = "";
	//一次中转发货时间       第二条的FCSJ
	public String ld_yczzfhsj = "";
	//二次中转到车确认时间   第二条的zhddsj
	public String ld_eczzdcqrsj = "";
	//二次中转到货时间       第二条的RKSJ
	public String ld_eczzdhsj = "";
	//二次中转发货时间       第三条的FCSJ
	public String ld_eczzfhsj = "";
	//到车确认时间           第三条的zhddsj
	public String ld_dcqrsj = "";
	
	public String ld_yczzgs = "";
	public String ld_eczzgs = "";
	@Transactional
	@Override
	public void PutEczzsjInfo() {
		//存放二次中转数据
		eczzsjEntities = new ArrayList<EczzsjEntity>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
		Date enddate = new Date();
		String ld_endDate = df.format(enddate);
		Date begindate = getNextDay(enddate);
		String ld_beginDate = df.format(begindate);
		eczzsjEntities = eczzsjMapper.getEczzsjInfo(ld_beginDate,ld_endDate);
		if(eczzsjEntities!=null && eczzsjEntities.size()>0){
			int tempCountMdd = 0;// 用于计数
			int tempCountMdd1 = 0;// 用于中转计数
			int tempCountMdd2 = 0;// 用于到货计数
			for(int i=0;i<eczzsjEntities.size();i++){
				EczzsjEntity eczzsjEntity = eczzsjEntities.get(i);
				if(i<eczzsjEntities.size()-1){
					EczzsjEntity eczzsjEntityAf = eczzsjEntities.get(i+1);
					if(eczzsjEntity.getYdbh().equals(eczzsjEntityAf.getYdbh())){
						if(tempCountMdd==0){
							//此记录为中转
							if(!(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()))
									&& eczzsjEntity.getPtdhgs()==null ){
								String temp_tjddcsj = null;
								if(eczzsjEntity.getFdbgsbh()!=null && eczzsjEntity.getFcbh()!=null){
									temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntity.getFdbgsbh(),eczzsjEntity.getFcbh());
								}
								if(temp_tjddcsj!=null){
									ld_yczzdcqrsj = temp_tjddcsj;
									ld_yczzdhsj = eczzsjEntity.getYczzdhsj1();
									ld_yczzgs = eczzsjEntity.getEczzgs1();
								}else{
									ld_yczzdcqrsj = eczzsjEntity.getEczzdcqrsj1();
									ld_yczzdhsj = eczzsjEntity.getYczzdhsj1();
									ld_yczzgs = eczzsjEntity.getEczzgs1();
								}
								tempCountMdd1++;
							}else if(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()) || eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs())){
								if(tempCountMdd1==0){
									continue;
								}else{
									String temp_tjdfcsj = null;
									if(eczzsjEntity.getScgsbh()!=null && eczzsjEntity.getFcbh()!=null){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getScgsbh(),eczzsjEntity.getFcbh());
									}
									if(temp_tjdfcsj!=null){
										ld_yczzfhsj = temp_tjdfcsj;
									}else{
										ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
									}
									String temp_tjddcsj = null;
									if(eczzsjEntity.getFdbgsbh()!=null && eczzsjEntity.getFcbh()!=null){
										temp_tjddcsj = eczzsjMapper.getTjddc(eczzsjEntity.getFdbgsbh(),eczzsjEntity.getFcbh());
									}
									if(temp_tjddcsj!=null){
										ld_dcqrsj = temp_tjddcsj;
									}else{
										ld_dcqrsj = eczzsjEntity.getEczzdcqrsj1();
									}								
									tempCountMdd2++;
								}
							}						
						}else if(tempCountMdd==1){
							if(!(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()))
									|| ( eczzsjEntity.getPtdhgs()!=null && !(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs())))){
								String temp_tjddcsj = null;
								if(eczzsjEntity.getFdbgsbh()!=null && eczzsjEntity.getFcbh()!=null){
									temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntity.getFdbgsbh(),eczzsjEntity.getFcbh());
								}
								if(temp_tjddcsj!=null){
									String temp_tjdfcsj = null;
									if(eczzsjEntity.getScgsbh()!=null && eczzsjEntity.getFcbh()!=null){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getScgsbh(),eczzsjEntity.getFcbh());
									}
									if(temp_tjdfcsj!=null){
										ld_yczzfhsj = temp_tjdfcsj;
									}else{
										ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
									}
									ld_eczzdcqrsj = temp_tjddcsj;
									ld_eczzdhsj = eczzsjEntity.getYczzdhsj1();
									ld_eczzgs = eczzsjEntity.getEczzgs1();
								}else{
									String temp_tjdfcsj = null;
									if(eczzsjEntity.getScgsbh()!=null && eczzsjEntity.getFcbh()!=null){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getScgsbh(),eczzsjEntity.getFcbh());
									}
									if(temp_tjdfcsj!=null){
										ld_yczzfhsj = temp_tjdfcsj;
									}else{
										ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
									}
									ld_eczzdcqrsj = eczzsjEntity.getEczzdcqrsj1();
									ld_eczzdhsj = eczzsjEntity.getYczzdhsj1();
									ld_eczzgs = eczzsjEntity.getEczzgs1();
								}
								tempCountMdd1++;
							}else if(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()) || eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs())){
								String temp_tjddcsj = null;
								if(( eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs()))){
									temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntity.getPtdhgs(),eczzsjEntity.getFcbh());
								}else if(eczzsjEntity.getFdbgsbh()!=null && eczzsjEntity.getFcbh()!=null){
									temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntity.getFdbgsbh(),eczzsjEntity.getFcbh());
								}
								
								if(tempCountMdd2==1){
									String temp_tjdfcsj = null;
									if(( eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs()))){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getPtdhgs(),eczzsjEntity.getFcbh());
									}else if(eczzsjEntity.getScgsbh()!=null && eczzsjEntity.getFcbh()!=null){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getScgsbh(),eczzsjEntity.getFcbh());
									}
									if(temp_tjdfcsj!=null){
										ld_eczzfhsj = temp_tjdfcsj;
									}else{
										ld_eczzfhsj = eczzsjEntity.getYczzfhsj1();
									}
									if(temp_tjddcsj!=null){
										ld_eczzdcqrsj = temp_tjddcsj;
										ld_eczzdhsj = eczzsjEntity.getYczzdhsj1();									
									}else{
										ld_eczzdcqrsj = eczzsjEntity.getEczzdcqrsj1();
										ld_eczzdhsj = eczzsjEntity.getYczzdhsj1();
									}									
									
								}else if(tempCountMdd2==0){
									String temp_tjdfcsj = null;
									if(( eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs()))){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getPtdhgs(),eczzsjEntity.getFcbh());
									}else if(eczzsjEntity.getScgsbh()!=null && eczzsjEntity.getFcbh()!=null){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getScgsbh(),eczzsjEntity.getFcbh());
									}
									if(temp_tjdfcsj!=null){
										ld_yczzfhsj = temp_tjdfcsj;
									}else{
										ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
									}
									if(temp_tjddcsj!=null && "".equals(ld_yczzdcqrsj)){
										ld_yczzdcqrsj = temp_tjddcsj;
										ld_yczzdhsj = eczzsjEntity.getYczzdhsj1();								
									}else if("".equals(ld_yczzdcqrsj)){
										ld_yczzdcqrsj = eczzsjEntity.getEczzdcqrsj1();
										ld_yczzdhsj = eczzsjEntity.getYczzdhsj1();								
									}									
								}
								String temp_tjddcsj1 = null;
								if(( eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs()))){
									temp_tjddcsj1 = eczzsjMapper.getTjddc(eczzsjEntity.getPtdhgs(),eczzsjEntity.getFcbh());
								}else if(eczzsjEntity.getFdbgsbh()!=null && eczzsjEntity.getFcbh()!=null)
								temp_tjddcsj1 = eczzsjMapper.getTjddc(eczzsjEntity.getFdbgsbh(),eczzsjEntity.getFcbh());
								if(temp_tjddcsj1!=null){
									ld_dcqrsj = temp_tjddcsj1;
								}else{
									ld_dcqrsj = eczzsjEntity.getEczzdcqrsj1();
								}
								tempCountMdd2++;
							}
						}else{
							if(!(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()))
									&&  eczzsjEntity.getPtdhgs()==null ){
								String temp_tjdscsj = null;
								if(eczzsjEntity.getFcbh()!=null && eczzsjEntity.getScgsbh()!=null){
									temp_tjdscsj = eczzsjMapper.getTjdScsj(eczzsjEntity.getFcbh(),eczzsjEntity.getScgsbh());
								}
								if(temp_tjdscsj!=null){
									ld_eczzfhsj = temp_tjdscsj;
								}else{
									ld_eczzfhsj = eczzsjEntity.getYczzfhsj1();
								}
								tempCountMdd1++;
							}else if(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()) || eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs())){
								ld_eczzfhsj = eczzsjEntity.getYczzfhsj1();
							}
							
						}

						tempCountMdd++;
						continue;
					}else{
						if(eczzsjEntity.getFhgs()==null  && eczzsjEntity.getFdbgsbh()==null){
							EczzsjEntity eczzsjEntity2 = new EczzsjEntity();
							eczzsjEntity2 = eczzsjMapper.getycjxzsj(eczzsjEntity.getYdbh());
							eczzsjEntity.setYcjxzgs(eczzsjEntity2.getYcjxzgs());
							eczzsjEntity.setYcjxzsj(eczzsjEntity2.getYcjxzsj());
						}
						if(eczzsjEntity.getFdbgsbh()!=null && !(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()))
								&&  eczzsjEntity.getPtdhgs()==null ){
							String temp_tjddcsj = null;
							if(eczzsjEntity.getFdbgsbh()!=null&&eczzsjEntity.getFcbh()!=null){
								temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntity.getFdbgsbh(),eczzsjEntity.getFcbh());
							}
							if(tempCountMdd>1 && "".equals(ld_eczzdcqrsj)){
								if(temp_tjddcsj!=null){
									ld_eczzdcqrsj = temp_tjddcsj;
									ld_eczzdhsj = eczzsjEntity.getYczzdhsj1();
									ld_eczzgs = eczzsjEntity.getEczzgs1();
								}else{
									ld_eczzdcqrsj = eczzsjEntity.getEczzdcqrsj1();
									ld_eczzdhsj = eczzsjEntity.getYczzdhsj1();
									ld_eczzgs = eczzsjEntity.getEczzgs1();
								}
							}else if("".equals(ld_yczzdcqrsj)){
								if(tempCountMdd!=0){
									if(temp_tjddcsj!=null){
										ld_yczzdcqrsj = temp_tjddcsj;
										ld_yczzdhsj = eczzsjEntity.getYczzdhsj1();	
										ld_yczzgs = eczzsjEntity.getEczzgs1();
									}else{
										ld_yczzdcqrsj = eczzsjEntity.getEczzdcqrsj1();
										ld_yczzdhsj = eczzsjEntity.getYczzdhsj1();
										ld_yczzgs = eczzsjEntity.getEczzgs1();
									}
								}
							}
						}else if(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()) || eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs())){
							String temp_tjdfcsj = null;
							if(eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs())){
								temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getPtdhgs(),eczzsjEntity.getFcbh());
							}else if(eczzsjEntity.getScgsbh()!=null && eczzsjEntity.getFcbh()!=null){
								temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getScgsbh(),eczzsjEntity.getFcbh());
							}
							if(tempCountMdd>1 ){
								if(temp_tjdfcsj!=null){
									ld_eczzfhsj = temp_tjdfcsj;
								}else{
									ld_eczzfhsj = eczzsjEntity.getYczzfhsj1();
								}	
								
							}else {
								if(temp_tjdfcsj!=null){
									ld_yczzfhsj = temp_tjdfcsj;
								}else{
									ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
								}							
							}
							ld_dcqrsj = eczzsjEntity.getEczzdcqrsj1();
						}
						if(tempCountMdd==0 && (eczzsjEntity.getFdbgsbh()!=null && !(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()))
								|| ( eczzsjEntity.getPtdhgs()!=null && !(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs()))))){
							eczzsjEntity.setYczzdcqrsj(ld_yczzdcqrsj);
							eczzsjEntity.setYczzdhsj(ld_yczzdhsj);
							eczzsjEntity.setYczzfhsj(ld_yczzfhsj);	
							eczzsjEntity.setYczzgs(ld_yczzgs);	
						}						
						if(tempCountMdd1==0 && tempCountMdd>0){
							eczzsjEntity.setYczzdcqrsj(ld_yczzdcqrsj);
							eczzsjEntity.setYczzdhsj(ld_yczzdhsj);	
							eczzsjEntity.setYczzgs(ld_yczzgs);	
							if(eczzsjEntity.getMdd()!=null 
									&& (eczzsjEntity.getMdd().indexOf("金华")>-1)){
								eczzsjEntity.setYczzgs("杭州"); 
							}else if(eczzsjEntity.getMdd()!=null 
									&& (eczzsjEntity.getMdd().indexOf("中山")>-1)){
								eczzsjEntity.setYczzgs("广州"); 
							}
							if(eczzsjEntity.getEczzgs()==null){
								eczzsjEntity.setEczzdcqrsj("");
								eczzsjEntity.setEczzdhsj("");
								eczzsjEntity.setEczzfhsj("");
							}								
							if(ld_yczzgs!="" && eczzsjEntity.getDhgs()!=null && eczzsjEntity.getDhgs().equals(ld_yczzgs)){
								eczzsjEntity.setYczzgs("");
								eczzsjEntity.setYczzdcqrsj("");
								eczzsjEntity.setYczzdhsj("");
								eczzsjEntity.setYczzfhsj("");
							}							
						}else if(tempCountMdd1==1){
								eczzsjEntity.setYczzdcqrsj(ld_yczzdcqrsj);
								eczzsjEntity.setYczzdhsj(ld_yczzdhsj);
								eczzsjEntity.setYczzgs(ld_yczzgs);	
								if("".equals(ld_yczzfhsj)){
									ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
								}
								if(eczzsjEntity.getEczzgs()==null){
									eczzsjEntity.setEczzdcqrsj("");
									eczzsjEntity.setEczzdhsj("");
									eczzsjEntity.setEczzfhsj("");
								}								
								eczzsjEntity.setYczzfhsj(ld_yczzfhsj);
									if(eczzsjEntity.getMdd()!=null 
											&& (eczzsjEntity.getMdd().indexOf("金华")>-1)){
										eczzsjEntity.setYczzgs("杭州"); 
									}else if(eczzsjEntity.getMdd()!=null 
											&& (eczzsjEntity.getMdd().indexOf("中山")>-1)){
										eczzsjEntity.setYczzgs("广州"); 
									}
									if(ld_yczzgs!="" && eczzsjEntity.getDhgs()!=null && eczzsjEntity.getDhgs().equals(ld_yczzgs)){
										eczzsjEntity.setYczzgs("");
										eczzsjEntity.setYczzdcqrsj("");
										eczzsjEntity.setYczzdhsj("");
										eczzsjEntity.setYczzfhsj("");
									}									
							}else if(tempCountMdd1==2){
								eczzsjEntity.setYczzdcqrsj(ld_yczzdcqrsj);
								eczzsjEntity.setYczzdhsj(ld_yczzdhsj);
								eczzsjEntity.setYczzgs(ld_yczzgs);	
//								ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
								eczzsjEntity.setYczzfhsj(ld_yczzfhsj);
								eczzsjEntity.setEczzdcqrsj(ld_eczzdcqrsj);
								eczzsjEntity.setEczzdhsj(ld_eczzdhsj);
								eczzsjEntity.setEczzgs(ld_eczzgs);
								if("".equals(ld_eczzfhsj)){
									ld_eczzfhsj = eczzsjEntity.getYczzfhsj1();
								}
								eczzsjEntity.setEczzfhsj(ld_eczzfhsj);
								if(eczzsjEntity.getMdd()!=null 
										&& (eczzsjEntity.getMdd().indexOf("金华")>-1)){
									eczzsjEntity.setEczzgs("杭州"); 
								}else if(eczzsjEntity.getMdd()!=null 
										&& (eczzsjEntity.getMdd().indexOf("中山")>-1)){
									eczzsjEntity.setEczzgs("广州"); 
								}
								if(ld_eczzgs!="" && eczzsjEntity.getDhgs()!=null&&eczzsjEntity.getDhgs().equals(ld_eczzgs)){
									eczzsjEntity.setEczzdcqrsj("");
									eczzsjEntity.setEczzdhsj("");
									eczzsjEntity.setEczzfhsj("");
									eczzsjEntity.setEczzgs("");
								}								
							}
							String temp_tjddcsj1 = null;
							if(eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs())){
								temp_tjddcsj1 = eczzsjMapper.getTjddc(eczzsjEntity.getPtdhgs(),eczzsjEntity.getFcbh());
							}else if(eczzsjEntity.getFdbgsbh()!=null && eczzsjEntity.getFcbh()!=null){
								
								temp_tjddcsj1 = eczzsjMapper.getTjddc(eczzsjEntity.getFdbgsbh(),eczzsjEntity.getFcbh());
							}
							if(temp_tjddcsj1!=null){
								ld_dcqrsj = temp_tjddcsj1;
							}else{
								if("".equals(ld_dcqrsj)){
									ld_dcqrsj = eczzsjEntity.getDcqrsj();
								}
							}						
							eczzsjEntity.setDcqrsj(ld_dcqrsj);
							int k = eczzsjMapper.insertEczzData(eczzsjEntity);
							tempCountMdd = 0;
							tempCountMdd1 = 0;
							tempCountMdd2 = 0;
							ld_yczzdcqrsj = "";
							ld_yczzdhsj = "";
							ld_yczzfhsj = "";
							ld_eczzdcqrsj = "";
							ld_eczzdhsj = "";
							ld_eczzfhsj = "";
							ld_dcqrsj = "";
							ld_yczzgs = "";
							ld_eczzgs = "";
							if(k<=0){
								LOG.info("二次中转数据运单号"+eczzsjEntity.getYdbh()+"时没有对应明细记录插入到表'HYREPORT_ECZZ'中");
							}								
							continue;					
					}					
				}else if(i==eczzsjEntities.size()-1){
					if(eczzsjEntity.getFhgs()==null  && eczzsjEntity.getFdbgsbh()==null){
						EczzsjEntity eczzsjEntity2 = new EczzsjEntity();
						eczzsjEntity2 = eczzsjMapper.getycjxzsj(eczzsjEntity.getYdbh());
						eczzsjEntity.setYcjxzgs(eczzsjEntity2.getYcjxzgs());
						eczzsjEntity.setYcjxzsj(eczzsjEntity2.getYcjxzsj());
					}
					if(eczzsjEntity.getFdbgsbh()!=null && !(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()))
							&& eczzsjEntity.getPtdhgs()==null ){
						String temp_tjddcsj=null;
						if(eczzsjEntity.getFdbgsbh()!=null && eczzsjEntity.getFcbh()!=null){
							temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntity.getFdbgsbh(),eczzsjEntity.getFcbh());
						}
						if(tempCountMdd>1 && "".equals(ld_eczzdcqrsj)){
							if(temp_tjddcsj!=null){
								ld_eczzdcqrsj = temp_tjddcsj;
								ld_eczzdhsj = eczzsjEntity.getYczzdhsj1();
								ld_eczzgs = eczzsjEntity.getEczzgs1();
							}else{
								ld_eczzdcqrsj = eczzsjEntity.getEczzdcqrsj1();
								ld_eczzdhsj = eczzsjEntity.getYczzdhsj1();
								ld_eczzgs = eczzsjEntity.getEczzgs1();
							}
						}else if("".equals(ld_yczzdcqrsj)){
							if(tempCountMdd!=0){
								if(temp_tjddcsj!=null){
									ld_yczzdcqrsj = temp_tjddcsj;
									ld_yczzdhsj = eczzsjEntity.getYczzdhsj1();
									ld_yczzgs = eczzsjEntity.getEczzgs1();
								}else{
									ld_yczzdcqrsj = eczzsjEntity.getEczzdcqrsj1();
									ld_yczzdhsj = eczzsjEntity.getYczzdhsj1();
									ld_yczzgs = eczzsjEntity.getEczzgs1();
								}
							}
						}						
					}else if(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()) || eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs())){
						String temp_tjdfcsj = null;
						if(eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs())){
							temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getPtdhgs(),eczzsjEntity.getFcbh());
						}else if(eczzsjEntity.getScgsbh()!=null && eczzsjEntity.getFcbh()!=null){
							temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity.getScgsbh(),eczzsjEntity.getFcbh());
						}
						if(tempCountMdd>1 ){
							if(temp_tjdfcsj!=null){
								ld_eczzfhsj = temp_tjdfcsj;
							}else{
								ld_eczzfhsj = eczzsjEntity.getYczzfhsj1();
							}							
						}else {
							if(temp_tjdfcsj!=null){
								ld_yczzfhsj = temp_tjdfcsj;
							}else{
								ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
							}							
						}
						ld_dcqrsj = eczzsjEntity.getEczzdcqrsj1();
					}
					if(tempCountMdd==0 && (eczzsjEntity.getFdbgsbh()!=null && !(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getFdbgsbh()))
							|| ( eczzsjEntity.getPtdhgs()!=null && !(eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs()))))){
						eczzsjEntity.setYczzdcqrsj(ld_yczzdcqrsj);
						eczzsjEntity.setYczzdhsj(ld_yczzdhsj);
						eczzsjEntity.setYczzfhsj(ld_yczzfhsj);	
						eczzsjEntity.setYczzgs(ld_yczzgs);
					}
					if(tempCountMdd1==0 && tempCountMdd>0){
						eczzsjEntity.setYczzdcqrsj(ld_yczzdcqrsj);
						eczzsjEntity.setYczzdhsj(ld_yczzdhsj);
						eczzsjEntity.setYczzgs(ld_yczzgs);
						if("".equals(ld_yczzfhsj)){
							ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
						}
						eczzsjEntity.setYczzfhsj(ld_yczzfhsj);						
						if(eczzsjEntity.getMdd()!=null 
								&& (eczzsjEntity.getMdd().indexOf("金华")>-1)){
							eczzsjEntity.setYczzgs("杭州"); 
						}else if(eczzsjEntity.getMdd()!=null 
								&& (eczzsjEntity.getMdd().indexOf("中山")>-1)){
							eczzsjEntity.setYczzgs("广州"); 
						}
						if(eczzsjEntity.getEczzgs()==null){
							eczzsjEntity.setEczzdcqrsj("");
							eczzsjEntity.setEczzdhsj("");
							eczzsjEntity.setEczzfhsj("");
						}								
						if(ld_yczzgs!="" && eczzsjEntity.getDhgs()!=null && eczzsjEntity.getDhgs().equals(ld_yczzgs)){
							eczzsjEntity.setYczzdcqrsj("");
							eczzsjEntity.setYczzdhsj("");
							eczzsjEntity.setYczzfhsj("");
							eczzsjEntity.setYczzgs("");
						}						
					}else if(tempCountMdd1==1){
						eczzsjEntity.setYczzdcqrsj(ld_yczzdcqrsj);
						eczzsjEntity.setYczzdhsj(ld_yczzdhsj);
						eczzsjEntity.setYczzgs(ld_yczzgs);
						if("".equals(ld_yczzfhsj)){
							ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
						}
//						ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
						eczzsjEntity.setYczzfhsj(ld_yczzfhsj);
						if(eczzsjEntity.getMdd()!=null 
								&& (eczzsjEntity.getMdd().indexOf("金华")>-1)){
							eczzsjEntity.setYczzgs("杭州"); 
						}else if(eczzsjEntity.getMdd()!=null 
								&& (eczzsjEntity.getMdd().indexOf("中山")>-1)){
							eczzsjEntity.setYczzgs("广州"); 
						}	
						if(eczzsjEntity.getEczzgs()==null){
							eczzsjEntity.setEczzdcqrsj("");
							eczzsjEntity.setEczzdhsj("");
							eczzsjEntity.setEczzfhsj("");
						}								
						if(ld_yczzgs!="" && eczzsjEntity.getDhgs()!=null && eczzsjEntity.getDhgs().equals(ld_yczzgs)){
							eczzsjEntity.setYczzdcqrsj("");
							eczzsjEntity.setYczzdhsj("");
							eczzsjEntity.setYczzfhsj("");
							eczzsjEntity.setYczzgs("");
						}						
					}else if(tempCountMdd1==2){
						eczzsjEntity.setYczzdcqrsj(ld_yczzdcqrsj);
						eczzsjEntity.setYczzdhsj(ld_yczzdhsj);
//						ld_yczzfhsj = eczzsjEntity.getYczzfhsj1();
						eczzsjEntity.setYczzfhsj(ld_yczzfhsj);
						eczzsjEntity.setYczzgs(ld_yczzgs);
						eczzsjEntity.setEczzdcqrsj(ld_eczzdcqrsj);
						eczzsjEntity.setEczzdhsj(ld_eczzdhsj);
						eczzsjEntity.setEczzgs(ld_eczzgs);
						if("".equals(ld_eczzfhsj)){
							ld_eczzfhsj = eczzsjEntity.getYczzfhsj1();
						}
						eczzsjEntity.setEczzfhsj(ld_eczzfhsj);	
						if(eczzsjEntity.getMdd()!=null 
								&& (eczzsjEntity.getMdd().indexOf("金华")>-1)){
							eczzsjEntity.setEczzgs("杭州"); 
						}else if(eczzsjEntity.getMdd()!=null 
								&& (eczzsjEntity.getMdd().indexOf("中山")>-1)){
							eczzsjEntity.setEczzgs("广州"); 
						}								
						if(ld_eczzgs!="" && eczzsjEntity.getDhgs()!=null &&eczzsjEntity.getDhgs().equals(ld_eczzgs)){
							eczzsjEntity.setEczzdcqrsj("");
							eczzsjEntity.setEczzdhsj("");
							eczzsjEntity.setEczzfhsj("");
							eczzsjEntity.setEczzgs("");
						}						
					}
					String temp_tjddcsj1 = null;
					if(eczzsjEntity.getPtdhgs()!=null && eczzsjEntity.getMddssyjgs().equals(eczzsjEntity.getPtdhgs())){
						temp_tjddcsj1 = eczzsjMapper.getTjddc(eczzsjEntity.getPtdhgs(),eczzsjEntity.getFcbh());
					}else if(eczzsjEntity.getFdbgsbh()!=null && eczzsjEntity.getFcbh()!=null){
						temp_tjddcsj1 = eczzsjMapper.getTjddc(eczzsjEntity.getFdbgsbh(),eczzsjEntity.getFcbh());
					}
					if(temp_tjddcsj1!=null){
						ld_dcqrsj = temp_tjddcsj1;
					}else{
						if("".equals(ld_dcqrsj)){
							ld_dcqrsj = eczzsjEntity.getDcqrsj();
						}
					}					
					eczzsjEntity.setDcqrsj(ld_dcqrsj);
					int k = eczzsjMapper.insertEczzData(eczzsjEntity);
					tempCountMdd = 0;
					tempCountMdd1 = 0;
					tempCountMdd2 = 0;
					ld_yczzdcqrsj = "";
					ld_yczzdhsj = "";
					ld_yczzfhsj = "";
					ld_eczzdcqrsj = "";
					ld_eczzdhsj = "";
					ld_eczzfhsj = "";
					ld_dcqrsj = "";
					ld_yczzgs = "";
					ld_eczzgs = "";
					if(k<=0){
						LOG.info("二次中转数据运单号"+eczzsjEntity.getYdbh()+"时没有对应明细记录插入到表'HYREPORT_ECZZ'中");
					}					
				}				
			}
		}
	}
	public Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);//+1今天的时间加一天
		date = calendar.getTime();
		return date;
	}

}
