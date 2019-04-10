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

import com.hoau.hoaureport.module.job.server.dao.EczzsjMapper;
import com.hoau.hoaureport.module.job.server.dao.YthmdsjMapper;
import com.hoau.hoaureport.module.job.server.service.IYthmdsjService;
import com.hoau.hoaureport.module.job.shared.domain.EczzsjEntity;

@Service
public class YthmdsjService implements IYthmdsjService {
	private static final Log LOG = LogFactory.getLog(YthmdsjService.class);

	@Autowired
	private YthmdsjMapper ythmdsjMapper;
	@Autowired
	private EczzsjMapper eczzsjMapper;
	
	private String ls_szyqyd = "";
	
	@Override
	public void PutYthmdsjInfo() {
		//-----按照运单物流时间表的到货时间获取一天的定日达运单数据----------
		List<EczzsjEntity> eczzsjEntities = new ArrayList<EczzsjEntity>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
		Date enddate = new Date();
		String ld_endDate = df.format(enddate);
		Date begindate = getNextDay(enddate);
		String ld_beginDate = df.format(begindate);
		eczzsjEntities = ythmdsjMapper.GetYthmdsjInfo(ld_beginDate,ld_endDate);
		if(eczzsjEntities!=null && eczzsjEntities.size()>0){
			for(EczzsjEntity eczzsjEntity : eczzsjEntities){
				//-----按照运单判断起运地是否是一体化门店  并且第一个流程的一级公司在一体化特殊表内  算是发货----------
				List<EczzsjEntity> eczzsjEntities1 = new ArrayList<EczzsjEntity>();
					//-----按照运单判断如果是上转----------
					eczzsjEntities1 = new ArrayList<EczzsjEntity>();
					eczzsjEntities1 = ythmdsjMapper.GetSzyfhInfo(eczzsjEntity.getYdbh());
					if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
						if(eczzsjEntities1.get(0).getQyd().equals(eczzsjEntity.getQyd())){
							ls_szyqyd = eczzsjEntities1.get(0).getQyd();
							eczzsjEntity.setSfyth("szy");
							if(eczzsjEntities1.size()==1){		
								eczzsjEntity.setYcjszgs(eczzsjEntities1.get(0).getSzygs1());
								eczzsjEntity.setZhszydcqrsj(eczzsjEntities1.get(0).getZhszydcqrsj());
								eczzsjEntity.setYcjszsj(eczzsjEntities1.get(0).getRkwcsj1());
								eczzsjEntity.setYcszsj(eczzsjEntities1.get(0).getFcsj1());
							}else if(eczzsjEntities1.size()>=2){
								eczzsjEntity.setYcjszgs(eczzsjEntities1.get(0).getSzygs1());
								eczzsjEntity.setYcjszsj(eczzsjEntities1.get(0).getRkwcsj1());
								eczzsjEntity.setYcszsj(eczzsjEntities1.get(0).getFcsj1());	
								eczzsjEntity.setEcjszgs(eczzsjEntities1.get(1).getSzygs1());
								eczzsjEntity.setEcjszsj(eczzsjEntities1.get(1).getRkwcsj1());
								eczzsjEntity.setZhszydcqrsj(eczzsjEntities1.get(1).getZhszydcqrsj());
							}
						}
					}
					//-----按照运单判断如果是发货----------
					eczzsjEntities1 = new ArrayList<EczzsjEntity>();
					eczzsjEntities1 = ythmdsjMapper.GetFhsjInfo(eczzsjEntity.getYdbh(),eczzsjEntity.getMddssyjgs());
					if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
						if("szy".equals(eczzsjEntity.getSfyth())){
							EczzsjEntity eczzsjEntity1 = ythmdsjMapper.GetYthmdlist(eczzsjEntities1.get(0).getQyd(),eczzsjEntities1.get(0).getQkmddgs());
							if(eczzsjEntity1!=null && "1".equals(eczzsjEntity1.getBooleanyth())){
								eczzsjEntity.setSfyth("yth");
								eczzsjEntity.setEcjszgs(eczzsjEntities1.get(0).getQkmddgs());
								String temp_tjddcsj = null;
								if(eczzsjEntities1.get(0).getFdbgsbh()!=null && eczzsjEntities1.get(0).getFcbh()!=null){
									temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntities1.get(0).getFdbgsbh(),eczzsjEntities1.get(0).getFcbh());
								}else if(eczzsjEntities1.get(0).getPtdhgs()!=null){
									temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntities1.get(0).getPtdhgs(),eczzsjEntities1.get(0).getFcbh());
								}
								if(temp_tjddcsj!=null){
									eczzsjEntity.setZhszydcqrsj(temp_tjddcsj);
								}else{
									eczzsjEntity.setZhszydcqrsj(eczzsjEntities1.get(0).getEczzdcqrsj1());
								}
								eczzsjEntity.setEcjszsj(eczzsjEntities1.get(0).getYczzdhsj1());
								eczzsjEntity.setFhgs(eczzsjEntities1.get(0).getQkmddgs());
								if(eczzsjEntities1.size()==2){
									String temp_tjdfcsj = null;
									if(eczzsjEntities1.get(1).getScgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getScgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjdfcsj!=null){
										eczzsjEntity.setFhsj(temp_tjdfcsj);
									}else{
										eczzsjEntity.setFhsj(eczzsjEntities1.get(1).getYczzfhsj1());
									}									
									eczzsjEntity.setYczzgs(eczzsjEntities1.get(1).getQkmddgs());
									eczzsjEntity.setYczzdhsj(eczzsjEntities1.get(1).getYczzdhsj1());
									String temp_tjddcsj1 = null;
									if(eczzsjEntities1.get(1).getFdbgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getFdbgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjddcsj1!=null){
										eczzsjEntity.setYczzdcqrsj(temp_tjddcsj1);
									}else{
										eczzsjEntity.setYczzdcqrsj(eczzsjEntities1.get(1).getEczzdcqrsj1());
									}
								}else if(eczzsjEntities1.size()>=3){
									String temp_tjdfcsj = null;
									if(eczzsjEntities1.get(1).getScgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getScgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjdfcsj!=null){
										eczzsjEntity.setFhsj(temp_tjdfcsj);
									}else{
										eczzsjEntity.setFhsj(eczzsjEntities1.get(1).getYczzfhsj1());
									}									
									eczzsjEntity.setYczzgs(eczzsjEntities1.get(1).getQkmddgs());
									eczzsjEntity.setYczzdhsj(eczzsjEntities1.get(1).getYczzdhsj1());
									String temp_tjddcsj1 = null;
									if(eczzsjEntities1.get(1).getFdbgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getFdbgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjddcsj1!=null){
										eczzsjEntity.setYczzdcqrsj(temp_tjddcsj1);
									}else{
										eczzsjEntity.setYczzdcqrsj(eczzsjEntities1.get(1).getEczzdcqrsj1());
									}
									String temp_tjdfcsj1 = null;
									if(eczzsjEntities1.get(1).getScgsbh()!=null && eczzsjEntities1.get(2).getFcbh()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(2).getScgsbh(),eczzsjEntities1.get(2).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(2).getPtdhgs(),eczzsjEntities1.get(2).getFcbh());
									}
									if(temp_tjdfcsj1!=null){
										eczzsjEntity.setYczzfhsj(temp_tjdfcsj1);
									}else{
										eczzsjEntity.setYczzfhsj(eczzsjEntities1.get(2).getYczzfhsj1());
									}
									eczzsjEntity.setEczzgs(eczzsjEntities1.get(2).getQkmddgs());
									eczzsjEntity.setEczzdhsj(eczzsjEntities1.get(2).getYczzdhsj1());
									String temp_tjddcsj2 = null;
									if(eczzsjEntities1.get(2).getFdbgsbh()!=null && eczzsjEntities1.get(2).getFcbh()!=null){
										temp_tjddcsj2 = eczzsjMapper.getSftjd(eczzsjEntities1.get(2).getFdbgsbh(),eczzsjEntities1.get(2).getFcbh());
									}else if(eczzsjEntities1.get(2).getPtdhgs()!=null){
										temp_tjddcsj2 = eczzsjMapper.getSftjd(eczzsjEntities1.get(2).getPtdhgs(),eczzsjEntities1.get(2).getFcbh());
									}
									if(temp_tjddcsj2!=null){
										eczzsjEntity.setEczzdcqrsj(temp_tjddcsj2);
									}else{
										eczzsjEntity.setEczzdcqrsj(eczzsjEntities1.get(2).getEczzdcqrsj1());
									}
								}
								//判断到货情况是否一体化
								EczzsjEntity eczzsjEntity2 = new EczzsjEntity();
								eczzsjEntity2 = ythmdsjMapper.GetDhsjInfo(eczzsjEntity.getYdbh(),eczzsjEntity.getMddssyjgs());
								if(eczzsjEntity2!=null){
									eczzsjEntity1 = new EczzsjEntity();
									eczzsjEntity1 = ythmdsjMapper.GetYthmdlist(eczzsjEntity2.getQkmddgs(),eczzsjEntity2.getQyd());
									if(eczzsjEntity1!=null && "1".equals(eczzsjEntity1.getBooleanyth())){
										eczzsjEntity.setYcjxzgs(eczzsjEntity2.getQkmddgs());
										String temp_tjdfcsj2 = null;
										if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
											temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
										}else if(eczzsjEntity2.getPtdhgs()!=null){
											temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
										}
										if(temp_tjdfcsj2!=null){
											eczzsjEntity.setYcxzsj(temp_tjdfcsj2);
										}else{
											eczzsjEntity.setYcxzsj(eczzsjEntity2.getYczzfhsj1());
										}
										if(eczzsjEntities1.size()==1){
											eczzsjEntity.setFhgs("");
										}else if(eczzsjEntities1.size()==2){
											eczzsjEntity.setDhgs(eczzsjEntity.getYczzgs());
											eczzsjEntity.setYczzgs("");
											eczzsjEntity.setDcqrsj(eczzsjEntity.getYczzdcqrsj());
											eczzsjEntity.setYczzdcqrsj("");
											eczzsjEntity.setDhsj(eczzsjEntity.getYczzdhsj());
											eczzsjEntity.setYczzdhsj("");
											
										}else if(eczzsjEntities1.size()>=3){
											eczzsjEntity.setDhgs(eczzsjEntity.getEczzgs());
											eczzsjEntity.setEczzgs("");
											eczzsjEntity.setDcqrsj(eczzsjEntity.getEczzdcqrsj());
											eczzsjEntity.setEczzdcqrsj("");
											eczzsjEntity.setDhsj(eczzsjEntity.getEczzdhsj());
											eczzsjEntity.setEczzdhsj("");
										}
										eczzsjEntity.setYcjxzsj(eczzsjEntity2.getYczzdhsj1());
										eczzsjEntities1 = new ArrayList<EczzsjEntity>();
										eczzsjEntities1 = ythmdsjMapper.GetXzysjInfo(eczzsjEntity.getYdbh());
										if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
											eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setEcxzsj(eczzsjEntities1.get(0).getFcsj1());
											eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
										}else{
											eczzsjEntity.setZhjxzgs(eczzsjEntity2.getQkmddgs());
											eczzsjEntity.setZhjxzsj(eczzsjEntity2.getYczzdhsj1());
										}
									}else{
										if(eczzsjEntities1.size()==1){
											String temp_tjdfcsj2 = null;
											if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
												temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
											}else if(eczzsjEntity2.getPtdhgs()!=null){
												temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
											}
											if(temp_tjdfcsj2!=null){
												eczzsjEntity.setFhsj(temp_tjdfcsj2);
											}else{
												eczzsjEntity.setFhsj(eczzsjEntity2.getYczzfhsj1());
											}
										}else if(eczzsjEntities1.size()==2){
											String temp_tjdfcsj2 = null;
											if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
												temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
											}else if(eczzsjEntity2.getPtdhgs()!=null){
												temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
											}
											if(temp_tjdfcsj2!=null){
												eczzsjEntity.setYczzfhsj(temp_tjdfcsj2);
											}else{
												eczzsjEntity.setYczzfhsj(eczzsjEntity2.getYczzfhsj1());
											}	
										}else if(eczzsjEntities1.size()>=3){
											String temp_tjdfcsj2 = null;
											if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
												temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
											}else if(eczzsjEntity2.getPtdhgs()!=null){
												temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
											}
											if(temp_tjdfcsj2!=null){
												eczzsjEntity.setEczzfhsj(temp_tjdfcsj2);
											}else{
												eczzsjEntity.setEczzfhsj(eczzsjEntity2.getYczzfhsj1());
											}					
										}			
										eczzsjEntity.setDhgs(eczzsjEntity2.getQkmddgs());
										String temp_tjddcsj1 = null;
										if(eczzsjEntity2.getFdbgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
											temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntity2.getFdbgsbh(),eczzsjEntity2.getFcbh());
										}else if(eczzsjEntity2.getPtdhgs()!=null){
											temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
										}
										if(temp_tjddcsj1!=null){
											eczzsjEntity.setDcqrsj(temp_tjddcsj1);
										}else{
											eczzsjEntity.setDcqrsj(eczzsjEntity2.getEczzdcqrsj1());
										}
										eczzsjEntity.setDhsj(eczzsjEntity2.getYczzdhsj1());
										eczzsjEntities1 = new ArrayList<EczzsjEntity>();
										eczzsjEntities1 = ythmdsjMapper.GetXzysjInfo(eczzsjEntity.getYdbh());
										if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
											if(eczzsjEntities1.size()==1){
												eczzsjEntity.setYcjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setYcxzsj(eczzsjEntities1.get(0).getFcsj1());
												eczzsjEntity.setYcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
												eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											}else if(eczzsjEntities1.size()>=2){
												eczzsjEntity.setYcjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(1).getSzygs1());
												eczzsjEntity.setYcxzsj(eczzsjEntities1.get(0).getFcsj1());
												eczzsjEntity.setYcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
												eczzsjEntity.setEcxzsj(eczzsjEntities1.get(1).getFcsj1());
												eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
												
											}
										}
									}
								}else{
									if(eczzsjEntities1.size()==1){
										eczzsjEntity.setFhgs("");
									}else if(eczzsjEntities1.size()==2){
										eczzsjEntity.setYcjszgs("");
										eczzsjEntity.setYczzdhsj("");
										eczzsjEntity.setYczzfhsj("");
										eczzsjEntity.setYczzdcqrsj("");
										eczzsjEntity.setDhgs(eczzsjEntities1.get(1).getQkmddgs());
										String temp_tjddcsj1 = null;
										if(eczzsjEntities1.get(1).getFdbgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
											temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getFdbgsbh(),eczzsjEntities1.get(1).getFcbh());
										}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
											temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
										}
										if(temp_tjddcsj1!=null){
											eczzsjEntity.setDcqrsj(temp_tjddcsj1);
										}else{
											eczzsjEntity.setDcqrsj(eczzsjEntities1.get(1).getEczzdcqrsj1());
										}
										eczzsjEntity.setDhsj(eczzsjEntities1.get(1).getYczzdhsj1());										
									}else if(eczzsjEntities1.size()>=3){
										eczzsjEntity.setEcjszgs("");
										eczzsjEntity.setEczzdhsj("");
										eczzsjEntity.setEczzfhsj("");
										eczzsjEntity.setEczzdcqrsj("");
										eczzsjEntity.setDhgs(eczzsjEntities1.get(2).getQkmddgs());
										String temp_tjddcsj1 = null;
										if(eczzsjEntities1.get(2).getFdbgsbh()!=null && eczzsjEntities1.get(2).getFcbh()!=null){
											temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(2).getFdbgsbh(),eczzsjEntities1.get(2).getFcbh());
										}else if(eczzsjEntities1.get(2).getPtdhgs()!=null){
											temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(2).getPtdhgs(),eczzsjEntities1.get(2).getFcbh());
										}
										if(temp_tjddcsj1!=null){
											eczzsjEntity.setDcqrsj(temp_tjddcsj1);
										}else{
											eczzsjEntity.setDcqrsj(eczzsjEntities1.get(2).getEczzdcqrsj1());
										}
										eczzsjEntity.setDhsj(eczzsjEntities1.get(2).getYczzdhsj1());
									}			
									eczzsjEntities1 = new ArrayList<EczzsjEntity>();
									eczzsjEntities1 = ythmdsjMapper.GetXzysjInfo(eczzsjEntity.getYdbh());
									if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
										if(eczzsjEntities1.size()==1){
											eczzsjEntity.setYcjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setYcxzsj(eczzsjEntities1.get(0).getFcsj1());
											eczzsjEntity.setYcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
										}else if(eczzsjEntities1.size()>=2){
											eczzsjEntity.setYcjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(1).getSzygs1());
											eczzsjEntity.setYcxzsj(eczzsjEntities1.get(0).getFcsj1());
											eczzsjEntity.setYcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											eczzsjEntity.setEcxzsj(eczzsjEntities1.get(1).getFcsj1());
											eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
											
										}
									}
								}
							}else{
								//到货一体化
								eczzsjEntity.setFhgs(eczzsjEntities1.get(0).getQyd());
								String temp_tjdfcsj = null;
								if(eczzsjEntities1.get(0).getScgsbh()!=null && eczzsjEntities1.get(0).getFcbh()!=null){
									temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntities1.get(0).getScgsbh(),eczzsjEntities1.get(0).getFcbh());
								}else if(eczzsjEntities1.get(0).getPtdhgs()!=null){
									temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntities1.get(0).getPtdhgs(),eczzsjEntities1.get(0).getFcbh());
								}
								if(temp_tjdfcsj!=null){
									eczzsjEntity.setFhsj(temp_tjdfcsj);
								}else{
									eczzsjEntity.setFhsj(eczzsjEntities1.get(0).getYczzfhsj1());
								}
								eczzsjEntity.setYczzgs(eczzsjEntities1.get(0).getQkmddgs());
								eczzsjEntity.setYczzdhsj(eczzsjEntities1.get(0).getYczzdhsj1());
								String temp_tjddcsj2 = null;
								if(eczzsjEntities1.get(0).getFdbgsbh()!=null && eczzsjEntities1.get(0).getFcbh()!=null){
									temp_tjddcsj2 = eczzsjMapper.getSftjd(eczzsjEntities1.get(0).getFdbgsbh(),eczzsjEntities1.get(0).getFcbh());
								}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
									temp_tjddcsj2 = eczzsjMapper.getSftjd(eczzsjEntities1.get(0).getPtdhgs(),eczzsjEntities1.get(0).getFcbh());
								}
								if(temp_tjddcsj2!=null){
									eczzsjEntity.setYczzdcqrsj(temp_tjddcsj2);
								}else{
									eczzsjEntity.setYczzdcqrsj(eczzsjEntities1.get(0).getEczzdcqrsj1());
								}
								if(eczzsjEntities1.size()==2){
									String temp_tjdfcsj1 = null;
									if(eczzsjEntities1.get(1).getScgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getScgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjdfcsj1!=null){
										eczzsjEntity.setYczzfhsj(temp_tjdfcsj1);
									}else{
										eczzsjEntity.setYczzfhsj(eczzsjEntities1.get(1).getYczzfhsj1());
									}
									eczzsjEntity.setEczzgs(eczzsjEntities1.get(1).getQkmddgs());
									eczzsjEntity.setEczzdhsj(eczzsjEntities1.get(1).getYczzdhsj1());
									String temp_tjddcsj1 = null;
									if(eczzsjEntities1.get(1).getFdbgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getFdbgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjddcsj1!=null){
										eczzsjEntity.setEczzdcqrsj(temp_tjddcsj1);
									}else{
										eczzsjEntity.setEczzdcqrsj(eczzsjEntities1.get(1).getEczzdcqrsj1());
									}
								}else if(eczzsjEntities1.size()>=3){
									String temp_tjdfcsj1 = null;
									if(eczzsjEntities1.get(1).getScgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getScgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjdfcsj1!=null){
										eczzsjEntity.setYczzfhsj(temp_tjdfcsj1);
									}else{
										eczzsjEntity.setYczzfhsj(eczzsjEntities1.get(1).getYczzfhsj1());
									}
									eczzsjEntity.setEczzgs(eczzsjEntities1.get(1).getQkmddgs());
									eczzsjEntity.setEczzdhsj(eczzsjEntities1.get(1).getYczzdhsj1());
									String temp_tjddcsj1 = null;
									if(eczzsjEntities1.get(1).getFdbgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getFdbgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjddcsj1!=null){
										eczzsjEntity.setEczzdcqrsj(temp_tjddcsj1);
									}else{
										eczzsjEntity.setEczzdcqrsj(eczzsjEntities1.get(1).getEczzdcqrsj1());
									}
									String temp_tjdfcsj2 = null;
									if(eczzsjEntities1.get(2).getScgsbh()!=null && eczzsjEntities1.get(2).getFcbh()!=null){
										temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(2).getScgsbh(),eczzsjEntities1.get(2).getFcbh());
									}else if(eczzsjEntities1.get(2).getPtdhgs()!=null){
										temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(2).getPtdhgs(),eczzsjEntities1.get(2).getFcbh());
									}
									if(temp_tjdfcsj1!=null){
										eczzsjEntity.setEczzfhsj(temp_tjdfcsj2);
									}else{
										eczzsjEntity.setEczzfhsj(eczzsjEntities1.get(2).getYczzfhsj1());
									}
								}						
								//判断到货情况是否一体化
								EczzsjEntity eczzsjEntity2 = new EczzsjEntity();
								eczzsjEntity2 = ythmdsjMapper.GetDhsjInfo(eczzsjEntity.getYdbh(),eczzsjEntity.getMddssyjgs());
								if(eczzsjEntity2!=null){
									eczzsjEntity1 = new EczzsjEntity();
									eczzsjEntity1 = ythmdsjMapper.GetYthmdlist(eczzsjEntity2.getQkmddgs(),eczzsjEntity2.getQyd());
									if(eczzsjEntity1!=null && "1".equals(eczzsjEntity1.getBooleanyth())){
										eczzsjEntity.setSfyth("yth");
										String temp_tjdfcsj2 = null;
										if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
											temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
										}else if(eczzsjEntity2.getPtdhgs()!=null){
											temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
										}
										if(temp_tjdfcsj2!=null){
											eczzsjEntity.setYcxzsj(temp_tjdfcsj2);
										}else{
											eczzsjEntity.setYcxzsj(eczzsjEntity2.getYczzfhsj1());
										}
										if(eczzsjEntities1.size()==1){
											eczzsjEntity.setDhgs(eczzsjEntity.getYczzgs());
											eczzsjEntity.setYczzgs("");
											eczzsjEntity.setDcqrsj(eczzsjEntity.getYczzdcqrsj());
											eczzsjEntity.setYczzdcqrsj("");
											eczzsjEntity.setDhsj(eczzsjEntity.getYczzdhsj());
											eczzsjEntity.setYczzdhsj("");
										}else if(eczzsjEntities1.size()==2){
											eczzsjEntity.setDhgs(eczzsjEntity.getEczzgs());
											eczzsjEntity.setEczzgs("");
											eczzsjEntity.setDcqrsj(eczzsjEntity.getEczzdcqrsj());
											eczzsjEntity.setEczzdcqrsj("");
											eczzsjEntity.setDhsj(eczzsjEntity.getEczzdhsj());
											eczzsjEntity.setEczzdhsj("");
											
										}else if(eczzsjEntities1.size()>=3){
											eczzsjEntity.setDhgs(eczzsjEntity.getEczzgs());
											eczzsjEntity.setEczzgs("");
											eczzsjEntity.setDcqrsj(eczzsjEntity.getEczzdcqrsj());
											eczzsjEntity.setEczzdcqrsj("");
											eczzsjEntity.setDhsj(eczzsjEntity.getEczzdhsj());
											eczzsjEntity.setEczzdhsj("");
										}
										eczzsjEntity.setYcjxzgs(eczzsjEntity2.getQkmddgs());
										eczzsjEntity.setYcjxzsj(eczzsjEntity2.getYczzdhsj1());
										eczzsjEntities1 = new ArrayList<EczzsjEntity>();
										eczzsjEntities1 = ythmdsjMapper.GetXzysjInfo(eczzsjEntity.getYdbh());
										if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
											if(eczzsjEntities1.size()==1){
												eczzsjEntity.setEcjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setEcxzsj(eczzsjEntities1.get(0).getFcsj1());
												eczzsjEntity.setEcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
												eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											}else if(eczzsjEntities1.size()>=2){
												eczzsjEntity.setEcjxzgs(eczzsjEntities1.get(1).getSzygs1());
												eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(1).getSzygs1());
												eczzsjEntity.setEcxzsj(eczzsjEntities1.get(1).getFcsj1());
												eczzsjEntity.setEcjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
												eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
												
											}
										}else{
											eczzsjEntity.setZhjxzgs(eczzsjEntity2.getQkmddgs());
											eczzsjEntity.setZhjxzsj(eczzsjEntity2.getYczzdhsj1());
										}
								}
								}else{
									continue;
								}									
							}
						}else if(eczzsjEntities1.get(0).getQyd().equals(eczzsjEntity.getQyd())
								|| ls_szyqyd!="" && eczzsjEntity.getQyd().equals(ls_szyqyd)){
							EczzsjEntity eczzsjEntity1 = ythmdsjMapper.GetYthmdlist(eczzsjEntities1.get(0).getQyd(),eczzsjEntities1.get(0).getQkmddgs());
							if(eczzsjEntity1!=null && "1".equals(eczzsjEntity1.getBooleanyth())){
								eczzsjEntity.setSfyth("yth");
								eczzsjEntity.setYcjszgs(eczzsjEntities1.get(0).getQkmddgs());
								eczzsjEntity.setYcjszsj(eczzsjEntities1.get(0).getYczzdhsj1());
								String temp_tjdfcsj = null;
								if(eczzsjEntities1.get(0).getScgsbh()!=null && eczzsjEntities1.get(0).getFcbh()!=null){
									temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntities1.get(0).getScgsbh(),eczzsjEntities1.get(0).getFcbh());
								}else if(eczzsjEntities1.get(0).getPtdhgs()!=null){
									temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntities1.get(0).getPtdhgs(),eczzsjEntities1.get(0).getFcbh());
								}
								if(temp_tjdfcsj!=null){
									eczzsjEntity.setYcszsj(temp_tjdfcsj);
								}else{
									eczzsjEntity.setYcszsj(eczzsjEntities1.get(0).getYczzfhsj1());
								}	
								String temp_tjddcsj = null;
								if(eczzsjEntities1.get(0).getFdbgsbh()!=null && eczzsjEntities1.get(0).getFcbh()!=null){
									temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntities1.get(0).getFdbgsbh(),eczzsjEntities1.get(0).getFcbh());
								}else if(eczzsjEntities1.get(0).getPtdhgs()!=null){
									temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntities1.get(0).getPtdhgs(),eczzsjEntities1.get(0).getFcbh());
								}
								if(temp_tjddcsj!=null){
									eczzsjEntity.setZhszydcqrsj(temp_tjddcsj);
								}else{
									eczzsjEntity.setZhszydcqrsj(eczzsjEntities1.get(0).getEczzdcqrsj1());
								}
								eczzsjEntity.setFhgs(eczzsjEntities1.get(0).getQkmddgs());
								
								if(eczzsjEntities1.size()==2){
									eczzsjEntity.setYczzgs(eczzsjEntities1.get(1).getQkmddgs());
									eczzsjEntity.setYczzdhsj(eczzsjEntities1.get(1).getYczzdhsj1());
									String temp_tjddcsj1 = null;
									if(eczzsjEntities1.get(1).getFdbgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getFdbgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjddcsj1!=null){
										eczzsjEntity.setYczzdcqrsj(temp_tjddcsj1);
									}else{
										eczzsjEntity.setYczzdcqrsj(eczzsjEntities1.get(1).getEczzdcqrsj1());
									}
									String temp_tjdfcsj1 = null;
									if(eczzsjEntities1.get(1).getScgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getScgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjdfcsj1!=null){
										eczzsjEntity.setFhsj(temp_tjdfcsj1);
									}else{
										eczzsjEntity.setFhsj(eczzsjEntities1.get(1).getYczzfhsj1());
									}
								}else if(eczzsjEntities1.size()>=3){
									String temp_tjdfcsj2 = null;
									if(eczzsjEntities1.get(1).getScgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getScgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjdfcsj2!=null){
										eczzsjEntity.setFhsj(temp_tjdfcsj2);
									}else{
										eczzsjEntity.setFhsj(eczzsjEntities1.get(1).getYczzfhsj1());
									}
									eczzsjEntity.setYczzgs(eczzsjEntities1.get(1).getQkmddgs());
									eczzsjEntity.setYczzdhsj(eczzsjEntities1.get(1).getYczzdhsj1());
									String temp_tjddcsj1 = null;
									if(eczzsjEntities1.get(1).getFdbgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getFdbgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjddcsj1!=null){
										eczzsjEntity.setYczzdcqrsj(temp_tjddcsj1);
									}else{
										eczzsjEntity.setYczzdcqrsj(eczzsjEntities1.get(1).getEczzdcqrsj1());
									}
									String temp_tjdfcsj1 = null;
									if(eczzsjEntities1.get(2).getScgsbh()!=null && eczzsjEntities1.get(2).getFcbh()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(2).getScgsbh(),eczzsjEntities1.get(2).getFcbh());
									}else if(eczzsjEntities1.get(2).getPtdhgs()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(2).getPtdhgs(),eczzsjEntities1.get(2).getFcbh());
									}
									if(temp_tjdfcsj1!=null){
										eczzsjEntity.setYczzfhsj(temp_tjdfcsj1);
									}else{
										eczzsjEntity.setYczzfhsj(eczzsjEntities1.get(2).getYczzfhsj1());
									}
									eczzsjEntity.setEczzgs(eczzsjEntities1.get(2).getQkmddgs());
									eczzsjEntity.setEczzdhsj(eczzsjEntities1.get(2).getYczzdhsj1());
									String temp_tjddcsj2 = null;
									if(eczzsjEntities1.get(2).getFdbgsbh()!=null && eczzsjEntities1.get(2).getFcbh()!=null){
										temp_tjddcsj2 = eczzsjMapper.getSftjd(eczzsjEntities1.get(2).getFdbgsbh(),eczzsjEntities1.get(2).getFcbh());
									}else if(eczzsjEntities1.get(2).getPtdhgs()!=null){
										temp_tjddcsj2 = eczzsjMapper.getSftjd(eczzsjEntities1.get(2).getPtdhgs(),eczzsjEntities1.get(2).getFcbh());
									}
									if(temp_tjddcsj2!=null){
										eczzsjEntity.setEczzdcqrsj(temp_tjddcsj2);
									}else{
										eczzsjEntity.setEczzdcqrsj(eczzsjEntities1.get(2).getEczzdcqrsj1());
									}
								}								
								//判断到货情况是否一体化
								EczzsjEntity eczzsjEntity2 = new EczzsjEntity();
								eczzsjEntity2 = ythmdsjMapper.GetDhsjInfo(eczzsjEntity.getYdbh(),eczzsjEntity.getMddssyjgs());
								if(eczzsjEntity2!=null){
									eczzsjEntity1 = new EczzsjEntity();
									eczzsjEntity1 = ythmdsjMapper.GetYthmdlist(eczzsjEntity2.getQkmddgs(),eczzsjEntity2.getQyd());
									if(eczzsjEntity1!=null && "1".equals(eczzsjEntity1.getBooleanyth())){
										eczzsjEntity.setYcjxzgs(eczzsjEntity2.getQkmddgs());
										String temp_tjdfcsj2 = null;
										if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
											temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
										}else if(eczzsjEntity2.getPtdhgs()!=null){
											temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
										}
										if(temp_tjdfcsj2!=null){
											eczzsjEntity.setYcxzsj(temp_tjdfcsj2);
										}else{
											eczzsjEntity.setYcxzsj(eczzsjEntity2.getYczzfhsj1());
										}
										if(eczzsjEntities1.size()==2){
											eczzsjEntity.setDhgs(eczzsjEntity.getYczzgs());
											eczzsjEntity.setYczzgs("");
											eczzsjEntity.setDcqrsj(eczzsjEntity.getYczzdcqrsj());
											eczzsjEntity.setYczzdcqrsj("");
											eczzsjEntity.setDhsj(eczzsjEntity.getYczzdhsj());
											eczzsjEntity.setYczzdhsj("");
											
										}else if(eczzsjEntities1.size()>=3){
											eczzsjEntity.setDhgs(eczzsjEntity.getEczzgs());
											eczzsjEntity.setEczzgs("");
											eczzsjEntity.setDcqrsj(eczzsjEntity.getEczzdcqrsj());
											eczzsjEntity.setEczzdcqrsj("");
											eczzsjEntity.setDhsj(eczzsjEntity.getEczzdhsj());
											eczzsjEntity.setEczzdhsj("");
										}
										eczzsjEntity.setYcjxzsj(eczzsjEntity2.getYczzdhsj1());
										eczzsjEntities1 = new ArrayList<EczzsjEntity>();
										eczzsjEntities1 = ythmdsjMapper.GetXzysjInfo(eczzsjEntity.getYdbh());
										if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
											if(eczzsjEntities1.size()==1){
												eczzsjEntity.setEcjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setEcxzsj(eczzsjEntities1.get(0).getFcsj1());
												eczzsjEntity.setEcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
												eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											}else if(eczzsjEntities1.size()>=2){
												eczzsjEntity.setEcjxzgs(eczzsjEntities1.get(1).getSzygs1());
												eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(1).getSzygs1());
												eczzsjEntity.setEcxzsj(eczzsjEntities1.get(1).getFcsj1());
												eczzsjEntity.setEcjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
												eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
												
											}
										}else{
											eczzsjEntity.setZhjxzgs(eczzsjEntity2.getQkmddgs());
											eczzsjEntity.setZhjxzsj(eczzsjEntity2.getYczzdhsj1());
										}
									}else{
										if(eczzsjEntities1.size()==1){
											String temp_tjdfcsj2 = null;
											if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
												temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
											}else if(eczzsjEntity2.getPtdhgs()!=null){
												temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
											}
											if(temp_tjdfcsj2!=null){
												eczzsjEntity.setFhsj(temp_tjdfcsj2);
											}else{
												eczzsjEntity.setFhsj(eczzsjEntity2.getYczzfhsj1());
											}
										}
										if(eczzsjEntities1.size()==2){
											String temp_tjdfcsj2 = null;
											if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
												temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
											}else if(eczzsjEntity2.getPtdhgs()!=null){
												temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
											}
											if(temp_tjdfcsj2!=null){
												eczzsjEntity.setYczzfhsj(temp_tjdfcsj2);
											}else{
												eczzsjEntity.setYczzfhsj(eczzsjEntity2.getYczzfhsj1());
											}
										}else if(eczzsjEntities1.size()>=3){
											String temp_tjdfcsj3 = null;
											if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
												temp_tjdfcsj3 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
											}else if(eczzsjEntity2.getPtdhgs()!=null){
												temp_tjdfcsj3 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
											}
											if(temp_tjdfcsj3!=null){
												eczzsjEntity.setEczzfhsj(temp_tjdfcsj3);
											}else{
												eczzsjEntity.setEczzfhsj(eczzsjEntity2.getYczzfhsj1());
											}
										}
										eczzsjEntity.setDhgs(eczzsjEntity2.getQkmddgs());
										String temp_tjddcsj2 = null;
										if(eczzsjEntity2.getFdbgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
											temp_tjddcsj2 = eczzsjMapper.getSftjd(eczzsjEntity2.getFdbgsbh(),eczzsjEntity2.getFcbh());
										}else if(eczzsjEntity2.getPtdhgs()!=null){
											temp_tjddcsj2 = eczzsjMapper.getSftjd(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
										}
										if(temp_tjddcsj2!=null){
											eczzsjEntity.setDcqrsj(temp_tjddcsj2);
										}else{
											eczzsjEntity.setDcqrsj(eczzsjEntity2.getEczzdcqrsj1());
										}					
										eczzsjEntity.setDhsj(eczzsjEntity2.getYczzdhsj1());
										eczzsjEntities1 = new ArrayList<EczzsjEntity>();
										eczzsjEntities1 = ythmdsjMapper.GetXzysjInfo(eczzsjEntity.getYdbh());
										if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
											if(eczzsjEntities1.size()==1){
												eczzsjEntity.setYcjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setYcxzsj(eczzsjEntities1.get(0).getFcsj1());
												eczzsjEntity.setYcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
												eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											}else if(eczzsjEntities1.size()>=2){
												eczzsjEntity.setYcjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setEcjxzgs(eczzsjEntities1.get(1).getSzygs1());
												eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(1).getSzygs1());
												eczzsjEntity.setYcxzsj(eczzsjEntities1.get(0).getFcsj1());
												eczzsjEntity.setYcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
												eczzsjEntity.setEcxzsj(eczzsjEntities1.get(1).getFcsj1());
												eczzsjEntity.setEcjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
												eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
												
											}
										}
									}
								}else{
									if(eczzsjEntities1.size()==1){
										eczzsjEntity.setFhgs("");
									}else if(eczzsjEntities1.size()==2){
										eczzsjEntity.setYcjszgs("");
										eczzsjEntity.setYczzdhsj("");
										eczzsjEntity.setYczzfhsj("");
										eczzsjEntity.setYczzdcqrsj("");
										eczzsjEntity.setDhgs(eczzsjEntities1.get(1).getQkmddgs());
										String temp_tjddcsj1 = null;
										if(eczzsjEntities1.get(1).getFdbgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
											temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getFdbgsbh(),eczzsjEntities1.get(1).getFcbh());
										}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
											temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
										}
										if(temp_tjddcsj1!=null){
											eczzsjEntity.setDcqrsj(temp_tjddcsj1);
										}else{
											eczzsjEntity.setDcqrsj(eczzsjEntities1.get(1).getEczzdcqrsj1());
										}
										eczzsjEntity.setDhsj(eczzsjEntities1.get(1).getYczzdhsj1());										
									}else if(eczzsjEntities1.size()>=3){
										eczzsjEntity.setEcjszgs("");
										eczzsjEntity.setEczzdhsj("");
										eczzsjEntity.setEczzfhsj("");
										eczzsjEntity.setEczzdcqrsj("");
										eczzsjEntity.setDhgs(eczzsjEntities1.get(2).getQkmddgs());
										String temp_tjddcsj1 = null;
										if(eczzsjEntities1.get(2).getFdbgsbh()!=null && eczzsjEntities1.get(2).getFcbh()!=null){
											temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(2).getFdbgsbh(),eczzsjEntities1.get(2).getFcbh());
										}else if(eczzsjEntities1.get(2).getPtdhgs()!=null){
											temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(2).getPtdhgs(),eczzsjEntities1.get(2).getFcbh());
										}
										if(temp_tjddcsj1!=null){
											eczzsjEntity.setDcqrsj(temp_tjddcsj1);
										}else{
											eczzsjEntity.setDcqrsj(eczzsjEntities1.get(2).getEczzdcqrsj1());
										}
										eczzsjEntity.setDhsj(eczzsjEntities1.get(2).getYczzdhsj1());
									}			
									eczzsjEntities1 = new ArrayList<EczzsjEntity>();
									eczzsjEntities1 = ythmdsjMapper.GetXzysjInfo(eczzsjEntity.getYdbh());
									if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
										if(eczzsjEntities1.size()==1){
											eczzsjEntity.setYcjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setYcxzsj(eczzsjEntities1.get(0).getFcsj1());
											eczzsjEntity.setYcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
										}else if(eczzsjEntities1.size()>=2){
											eczzsjEntity.setYcjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(1).getSzygs1());
											eczzsjEntity.setYcxzsj(eczzsjEntities1.get(0).getFcsj1());
											eczzsjEntity.setYcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											eczzsjEntity.setEcxzsj(eczzsjEntities1.get(1).getFcsj1());
											eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
											
										}
									}
								}
							}else{
								//到货一体化
								eczzsjEntity.setFhgs(eczzsjEntities1.get(0).getQyd());
								String temp_tjdfcsj = null;
								if(eczzsjEntities1.get(0).getScgsbh()!=null && eczzsjEntities1.get(0).getFcbh()!=null){
									temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntities1.get(0).getScgsbh(),eczzsjEntities1.get(0).getFcbh());
								}else if(eczzsjEntities1.get(0).getPtdhgs()!=null){
									temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntities1.get(0).getPtdhgs(),eczzsjEntities1.get(0).getFcbh());
								}
								if(temp_tjdfcsj!=null){
									eczzsjEntity.setFhsj(temp_tjdfcsj);
								}else{
									eczzsjEntity.setFhsj(eczzsjEntities1.get(0).getYczzfhsj1());
								}
								eczzsjEntity.setYczzgs(eczzsjEntities1.get(0).getQkmddgs());
								eczzsjEntity.setYczzdhsj(eczzsjEntities1.get(0).getYczzdhsj1());
								String temp_tjddcsj2 = null;
								if(eczzsjEntities1.get(0).getFdbgsbh()!=null && eczzsjEntities1.get(0).getFcbh()!=null){
									temp_tjddcsj2 = eczzsjMapper.getSftjd(eczzsjEntities1.get(0).getFdbgsbh(),eczzsjEntities1.get(0).getFcbh());
								}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
									temp_tjddcsj2 = eczzsjMapper.getSftjd(eczzsjEntities1.get(0).getPtdhgs(),eczzsjEntities1.get(0).getFcbh());
								}
								if(temp_tjddcsj2!=null){
									eczzsjEntity.setYczzdcqrsj(temp_tjddcsj2);
								}else{
									eczzsjEntity.setYczzdcqrsj(eczzsjEntities1.get(0).getEczzdcqrsj1());
								}
								if(eczzsjEntities1.size()==2){
									String temp_tjdfcsj1 = null;
									if(eczzsjEntities1.get(1).getScgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getScgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjdfcsj1!=null){
										eczzsjEntity.setYczzfhsj(temp_tjdfcsj1);
									}else{
										eczzsjEntity.setYczzfhsj(eczzsjEntities1.get(1).getYczzfhsj1());
									}
									eczzsjEntity.setEczzgs(eczzsjEntities1.get(1).getQkmddgs());
									eczzsjEntity.setEczzdhsj(eczzsjEntities1.get(1).getYczzdhsj1());
									String temp_tjddcsj1 = null;
									if(eczzsjEntities1.get(1).getFdbgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getFdbgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjddcsj1!=null){
										eczzsjEntity.setEczzdcqrsj(temp_tjddcsj1);
									}else{
										eczzsjEntity.setEczzdcqrsj(eczzsjEntities1.get(1).getEczzdcqrsj1());
									}
								}else if(eczzsjEntities1.size()>=3){
									String temp_tjdfcsj1 = null;
									if(eczzsjEntities1.get(1).getScgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getScgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjdfcsj1!=null){
										eczzsjEntity.setYczzfhsj(temp_tjdfcsj1);
									}else{
										eczzsjEntity.setYczzfhsj(eczzsjEntities1.get(1).getYczzfhsj1());
									}
									eczzsjEntity.setEczzgs(eczzsjEntities1.get(1).getQkmddgs());
									eczzsjEntity.setEczzdhsj(eczzsjEntities1.get(1).getYczzdhsj1());
									String temp_tjddcsj1 = null;
									if(eczzsjEntities1.get(1).getFdbgsbh()!=null && eczzsjEntities1.get(1).getFcbh()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getFdbgsbh(),eczzsjEntities1.get(1).getFcbh());
									}else if(eczzsjEntities1.get(1).getPtdhgs()!=null){
										temp_tjddcsj1 = eczzsjMapper.getSftjd(eczzsjEntities1.get(1).getPtdhgs(),eczzsjEntities1.get(1).getFcbh());
									}
									if(temp_tjddcsj1!=null){
										eczzsjEntity.setEczzdcqrsj(temp_tjddcsj1);
									}else{
										eczzsjEntity.setEczzdcqrsj(eczzsjEntities1.get(1).getEczzdcqrsj1());
									}
									String temp_tjdfcsj2 = null;
									if(eczzsjEntities1.get(2).getScgsbh()!=null && eczzsjEntities1.get(2).getFcbh()!=null){
										temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(2).getScgsbh(),eczzsjEntities1.get(2).getFcbh());
									}else if(eczzsjEntities1.get(2).getPtdhgs()!=null){
										temp_tjdfcsj2 = eczzsjMapper.getTjdfc(eczzsjEntities1.get(2).getPtdhgs(),eczzsjEntities1.get(2).getFcbh());
									}
									if(temp_tjdfcsj1!=null){
										eczzsjEntity.setEczzfhsj(temp_tjdfcsj2);
									}else{
										eczzsjEntity.setEczzfhsj(eczzsjEntities1.get(2).getYczzfhsj1());
									}
								}
								//判断到货情况是否一体化
								EczzsjEntity eczzsjEntity2 = new EczzsjEntity();
								eczzsjEntity2 = ythmdsjMapper.GetDhsjInfo(eczzsjEntity.getYdbh(),eczzsjEntity.getMddssyjgs());
								if(eczzsjEntity2!=null){
									eczzsjEntity1 = new EczzsjEntity();
									eczzsjEntity1 = ythmdsjMapper.GetYthmdlist(eczzsjEntity2.getQkmddgs(),eczzsjEntity2.getQyd());
									if(eczzsjEntity1!=null && "1".equals(eczzsjEntity1.getBooleanyth())){
										eczzsjEntity.setSfyth("yth");
										if(eczzsjEntities1.size()==1){
//										eczzsjEntity.setFhgs("");
											String temp_tjdfcsj1 = null;
											if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
												temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
											}else if(eczzsjEntity2.getPtdhgs()!=null){
												temp_tjdfcsj1 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
											}
											if(temp_tjdfcsj1!=null){
												eczzsjEntity.setYcxzsj(temp_tjdfcsj1);
											}else{
												eczzsjEntity.setYcxzsj(eczzsjEntity2.getYczzfhsj1());
											}
											eczzsjEntity.setDhgs(eczzsjEntity.getYczzgs());
											eczzsjEntity.setYczzgs("");
											eczzsjEntity.setDcqrsj(eczzsjEntity.getYczzdcqrsj());
											eczzsjEntity.setYczzdcqrsj("");
											eczzsjEntity.setDhsj(eczzsjEntity.getYczzdhsj());
											eczzsjEntity.setYczzdhsj("");
											eczzsjEntity.setYcjxzgs(eczzsjEntity2.getQkmddgs());
										}else if(eczzsjEntities1.size()==2){
											String temp_tjdfcsj12 = null;
											if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
												temp_tjdfcsj12 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
											}else if(eczzsjEntity2.getPtdhgs()!=null){
												temp_tjdfcsj12 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
											}
											if(temp_tjdfcsj12!=null){
												eczzsjEntity.setYcxzsj(temp_tjdfcsj12);
											}else{
												eczzsjEntity.setYcxzsj(eczzsjEntity2.getYczzfhsj1());
											}
											eczzsjEntity.setDhgs(eczzsjEntity.getEczzgs());
											eczzsjEntity.setEczzgs("");
											eczzsjEntity.setDcqrsj(eczzsjEntity.getEczzdcqrsj());
											eczzsjEntity.setEczzdcqrsj("");
											eczzsjEntity.setDhsj(eczzsjEntity.getEczzdhsj());
											eczzsjEntity.setEczzdhsj("");
											eczzsjEntity.setYcjxzgs(eczzsjEntity2.getQkmddgs());
											
										}else if(eczzsjEntities1.size()>=3){
											String temp_tjdfcsj12 = null;
											if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
												temp_tjdfcsj12 = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
											}else if(eczzsjEntities1.get(2).getPtdhgs()!=null){
												temp_tjdfcsj12 = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
											}
											if(temp_tjdfcsj12!=null){
												eczzsjEntity.setYcxzsj(temp_tjdfcsj12);
											}else{
												eczzsjEntity.setYcxzsj(eczzsjEntity2.getYczzfhsj1());
											}
											eczzsjEntity.setDhgs(eczzsjEntity.getEczzgs());
											eczzsjEntity.setEczzgs("");
											eczzsjEntity.setDcqrsj(eczzsjEntity.getEczzdcqrsj());
											eczzsjEntity.setEczzdcqrsj("");
											eczzsjEntity.setDhsj(eczzsjEntity.getEczzdhsj());
											eczzsjEntity.setEczzdhsj("");
											eczzsjEntity.setEczzfhsj("");
											eczzsjEntity.setYcjxzgs(eczzsjEntity2.getQkmddgs());
										}
										eczzsjEntity.setYcjxzsj(eczzsjEntity2.getYczzdhsj1());
										eczzsjEntities1 = new ArrayList<EczzsjEntity>();
										eczzsjEntities1 = ythmdsjMapper.GetXzysjInfo(eczzsjEntity.getYdbh());
										if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
											if(eczzsjEntities1.size()==1){
												eczzsjEntity.setEcjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(0).getSzygs1());
												eczzsjEntity.setEcxzsj(eczzsjEntities1.get(0).getFcsj1());
												eczzsjEntity.setEcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
												eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											}else if(eczzsjEntities1.size()>=2){
												eczzsjEntity.setEcjxzgs(eczzsjEntities1.get(1).getSzygs1());
												eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(1).getSzygs1());
												eczzsjEntity.setEcxzsj(eczzsjEntities1.get(1).getFcsj1());
												eczzsjEntity.setEcjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
												eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
												
											}
										}else{
											eczzsjEntity.setZhjxzgs(eczzsjEntity2.getQkmddgs());
											eczzsjEntity.setZhjxzsj(eczzsjEntity2.getYczzdhsj1());
										}
									}else{
										continue;
									}									
								}
							}								
						}
					}else{
						EczzsjEntity eczzsjEntity2 = new EczzsjEntity();
						eczzsjEntity2 = ythmdsjMapper.GetDhsjInfo(eczzsjEntity.getYdbh(),eczzsjEntity.getMddssyjgs());
						if(eczzsjEntity2!=null){
							if(eczzsjEntity.getQyd().equals(eczzsjEntity2.getQyd()) || ls_szyqyd!="" && eczzsjEntity.getQyd().equals(ls_szyqyd)){
								EczzsjEntity eczzsjEntity1 = new EczzsjEntity();
								eczzsjEntity1 = ythmdsjMapper.GetYthmdlist(eczzsjEntity2.getQyd(),eczzsjEntity2.getQkmddgs());
								if(eczzsjEntity1!=null && "1".equals(eczzsjEntity1.getBooleanyth())){
									if("szy".equals(eczzsjEntity.getSfyth())){
										eczzsjEntity.setSfyth("yth");
										eczzsjEntity.setEcjszgs(eczzsjEntity2.getQkmddgs());
										String temp_tjddcsj = null;
										if(eczzsjEntity2.getFdbgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
											temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntity2.getFdbgsbh(),eczzsjEntity2.getFcbh());
										}else if(eczzsjEntity2.getPtdhgs()!=null){
											temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
										}
										if(temp_tjddcsj!=null){
											eczzsjEntity.setZhszydcqrsj(temp_tjddcsj);
										}else{
											eczzsjEntity.setZhszydcqrsj(eczzsjEntity2.getEczzdcqrsj1());
										}
										eczzsjEntity.setEcjszsj(eczzsjEntity2.getYczzdhsj1());
										
									}else{
										eczzsjEntity.setSfyth("yth");
										eczzsjEntity.setYcjszgs(eczzsjEntity2.getQkmddgs());
										eczzsjEntity.setYcjszsj(eczzsjEntity2.getYczzdhsj1());
										String temp_tjdfcsj = null;
										if(eczzsjEntity2.getScgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
											temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity2.getScgsbh(),eczzsjEntity2.getFcbh());
										}else if(eczzsjEntity2.getPtdhgs()!=null){
											temp_tjdfcsj = eczzsjMapper.getTjdfc(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
										}
										if(temp_tjdfcsj!=null){
											eczzsjEntity.setYcszsj(temp_tjdfcsj);
										}else{
											eczzsjEntity.setYcszsj(eczzsjEntity2.getYczzfhsj1());
										}	
										String temp_tjddcsj = null;
										if(eczzsjEntity2.getFdbgsbh()!=null && eczzsjEntity2.getFcbh()!=null){
											temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntity2.getFdbgsbh(),eczzsjEntity2.getFcbh());
										}else if(eczzsjEntity2.getPtdhgs()!=null){
											temp_tjddcsj = eczzsjMapper.getSftjd(eczzsjEntity2.getPtdhgs(),eczzsjEntity2.getFcbh());
										}
										if(temp_tjddcsj!=null){
											eczzsjEntity.setZhszydcqrsj(temp_tjddcsj);
										}else{
											eczzsjEntity.setZhszydcqrsj(eczzsjEntity2.getEczzdcqrsj1());
										}
									}
									eczzsjEntities1 = new ArrayList<EczzsjEntity>();
									eczzsjEntities1 = ythmdsjMapper.GetXzysjInfo(eczzsjEntity.getYdbh());
									if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
										if(eczzsjEntities1.size()==1){
											eczzsjEntity.setYcjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setYcxzsj(eczzsjEntities1.get(0).getFcsj1());
											eczzsjEntity.setYcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
										}else if(eczzsjEntities1.size()>=2){
											eczzsjEntity.setYcjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setEcjxzgs(eczzsjEntities1.get(1).getSzygs1());
											eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(1).getSzygs1());
											eczzsjEntity.setYcxzsj(eczzsjEntities1.get(0).getFcsj1());
											eczzsjEntity.setYcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											eczzsjEntity.setEcxzsj(eczzsjEntities1.get(1).getFcsj1());
											eczzsjEntity.setEcjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
											eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
											
										}
									}
								}else{
									continue;
								}
							}else if(eczzsjEntity.getMdd().equals(eczzsjEntity2.getQkmddgs())){
								EczzsjEntity eczzsjEntity1 = new EczzsjEntity();
								eczzsjEntity1 = ythmdsjMapper.GetYthmdlist(eczzsjEntity2.getQkmddgs(),eczzsjEntity2.getQyd());
								if(eczzsjEntity1!=null && "1".equals(eczzsjEntity1.getBooleanyth())){
									eczzsjEntity.setSfyth("yth");
									eczzsjEntity.setYcjxzgs(eczzsjEntity2.getQkmddgs());
									eczzsjEntity.setYcjxzsj(eczzsjEntity2.getYczzdhsj1());
									eczzsjEntities1 = new ArrayList<EczzsjEntity>();
									eczzsjEntities1 = ythmdsjMapper.GetXzysjInfo(eczzsjEntity.getYdbh());
									if(eczzsjEntities1!=null && eczzsjEntities1.size()>0){
										if(eczzsjEntities1.size()==1){
											eczzsjEntity.setEcjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(0).getSzygs1());
											eczzsjEntity.setEcxzsj(eczzsjEntities1.get(0).getFcsj1());
											eczzsjEntity.setEcjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
											eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(0).getRkwcsj1());
										}else if(eczzsjEntities1.size()>=2){
											eczzsjEntity.setEcjxzgs(eczzsjEntities1.get(1).getSzygs1());
											eczzsjEntity.setZhjxzgs(eczzsjEntities1.get(1).getSzygs1());
											eczzsjEntity.setEcxzsj(eczzsjEntities1.get(1).getFcsj1());
											eczzsjEntity.setEcjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
											eczzsjEntity.setZhjxzsj(eczzsjEntities1.get(1).getRkwcsj1());
											
										}
									}else{
										eczzsjEntity.setZhjxzgs(eczzsjEntity2.getQkmddgs());
										eczzsjEntity.setZhjxzsj(eczzsjEntity2.getYczzdhsj1());
									}
								}else{
									continue;
								}
							}
						}
					}
					
				//-----保存一体化数据
				if("yth".equals(eczzsjEntity.getSfyth())){
					int k = ythmdsjMapper.insertYthInfo(eczzsjEntity);
					if(k<=0){
						LOG.info("一体化数据写入失败---"+eczzsjEntity.getYdbh()+"---");
					}
				}else{
					continue;
				}
			}
			
		}else{
			LOG.info("一体化门店无数据");
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
