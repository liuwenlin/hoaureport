package com.hoau.hoaureport.module.job.server.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.job.server.dao.TxYmYckMapper;
import com.hoau.hoaureport.module.job.server.service.ITxYmYckService;
import com.hoau.hoaureport.module.job.shared.domain.TxYmYckEntity;


@Service
public class TxYmYckService implements ITxYmYckService {
	private static final Log LOG = LogFactory.getLog(TxYmYckService.class);
	@Autowired
	private TxYmYckMapper txYmYckMapper;
	
	@Override
	public void TxYmYckInfo() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		//获取上个月
		Calendar begin_calendar = Calendar.getInstance();
		begin_calendar.setTime(new Date()); 
		begin_calendar.add(Calendar.MONTH, -1);		
		String temp_ssy = df.format(begin_calendar.getTime());		
		List<TxYmYckEntity> txYmYckEntities = new ArrayList<TxYmYckEntity>();
		// 1--先将所有的预存款公司获取出来
		txYmYckEntities = txYmYckMapper.YckGsCollected();
		if(txYmYckEntities!=null && txYmYckEntities.size()>0){
			for(TxYmYckEntity txYmYckEntity:txYmYckEntities){
				String ls_gsbh = txYmYckEntity.getSsgsbh();
				// 2--通过降序获取期末
				if(ls_gsbh!=null && !("".equals(ls_gsbh))){
					List<TxYmYckEntity> txYmYckEntities2 = new ArrayList<TxYmYckEntity>();
					txYmYckEntities2 = txYmYckMapper.GetBeforeYckQmje(ls_gsbh, temp_ssy);
					//--获取到期末金额，这个月有数据
					if(txYmYckEntities2 !=null && txYmYckEntities2.size()>0){
						//新开的门店
						if(txYmYckEntities2.size()==1){
							List<TxYmYckEntity> txYmYckEntities3 = new ArrayList<TxYmYckEntity>();
							txYmYckEntities3 = txYmYckMapper.GetPastYck(ls_gsbh, temp_ssy);
							if(txYmYckEntities3.size()==1){
								//审核完成的保存
								if("2".equals(txYmYckEntities2.get(0).getMxzt())){
									if(txYmYckEntities2.get(0).getShsj()!=null 
											&& temp_ssy.equals(txYmYckEntities2.get(0).getShsj())){
										txYmYckEntity.setDjje(BigDecimal.ZERO);
										txYmYckEntity.setKsyye(txYmYckEntities2.get(0).getCzje());
										txYmYckEntity.setYckqcje(BigDecimal.ZERO);
										txYmYckEntity.setYckqmje(txYmYckEntities2.get(0).getCzje());
										txYmYckEntity.setYckye(txYmYckEntities2.get(0).getCzje());
										txYmYckEntity.setCzsj(txYmYckEntities2.get(0).getCzsj());
									}else{
										continue;
									}
								}else{
									continue;
								}
							}else{
								if("2".equals(txYmYckEntities2.get(0).getMxzt())){
									TxYmYckEntity txYmYckEntity3 = new TxYmYckEntity();
									//获取冻结金额
									txYmYckEntity3 = txYmYckMapper.GetBeforeDjje(ls_gsbh, temp_ssy);
									if(txYmYckEntity3 != null){
										if(txYmYckEntity3.getDjje()!=null){
											txYmYckEntity.setDjje(txYmYckEntity3.getDjje());	
										}else{
											txYmYckEntity.setDjje(BigDecimal.ZERO);
										}
									}else{
										txYmYckEntity.setDjje(BigDecimal.ZERO);
									}
									//获取最大审核时间的期初
									txYmYckEntities3 = new ArrayList<TxYmYckEntity>();
									txYmYckEntities3 = txYmYckMapper.GetMaxShsj(ls_gsbh, temp_ssy);
							        //获取Calendar实例
									if(txYmYckEntities3!=null && txYmYckEntities3.size()>0 &&  txYmYckEntities3.get(0).getShsj()!=null){
										SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
										Date date1 = null;
										Date date2 = null;
										try {
											
											date1 = sdf.parse(txYmYckEntities3.get(0).getShsj());
											date2 = sdf.parse(txYmYckEntities2.get(0).getCzsj());
										} catch (ParseException e) {
											e.printStackTrace();
										}
										Calendar compareTime1 = Calendar.getInstance();
										Calendar compareTime2 = Calendar.getInstance();
										compareTime1.setTime(date1);
										compareTime2.setTime(date2);
										if(compareTime1.compareTo(compareTime2) < 0){
											txYmYckEntity.setCzsj(txYmYckEntities2.get(0).getCzsj());
											txYmYckEntity.setKsyye(txYmYckEntities2.get(0).getYckqmje());
											txYmYckEntity.setYckqmje(txYmYckEntities2.get(0).getYckqmje());
											txYmYckEntity.setYckye(txYmYckEntity.getDjje().add(txYmYckEntities2.get(0).getYckqmje()));
										}else{
											txYmYckEntity.setCzsj(txYmYckEntities3.get(0).getShsj());
											txYmYckEntity.setKsyye(txYmYckEntities3.get(0).getYckqmje());
											txYmYckEntity.setYckqmje(txYmYckEntities3.get(0).getYckqmje());
											txYmYckEntity.setYckye(txYmYckEntity.getDjje().add(txYmYckEntities3.get(0).getYckqmje()));							        	
										}
									}else{
										txYmYckEntity.setCzsj(txYmYckEntities2.get(0).getCzsj());
										txYmYckEntity.setKsyye(txYmYckEntities2.get(0).getYckqmje());
										txYmYckEntity.setYckqmje(txYmYckEntities2.get(0).getYckqmje());
										txYmYckEntity.setYckye(txYmYckEntity.getDjje().add(txYmYckEntities2.get(0).getYckqmje()));									
									}
									// 2--通过升序获取期初
									txYmYckEntity3 = new TxYmYckEntity();
									//获取期初冻结金额
									txYmYckEntity3 = txYmYckMapper.GetBeforeQcDjje(ls_gsbh);
									BigDecimal lb_djje4qc = null;
									if(txYmYckEntity3 != null){
										if(txYmYckEntity3.getDjje()!=null){
											lb_djje4qc = txYmYckEntity3.getDjje();	
										}else{
											lb_djje4qc = BigDecimal.ZERO;
										}
									}else{
										lb_djje4qc = BigDecimal.ZERO;
									}									
									List<TxYmYckEntity> txYmYckEntities31 = new ArrayList<TxYmYckEntity>();
									txYmYckEntities31 = txYmYckMapper.GetBeforeYckQcje(ls_gsbh, temp_ssy);
									if("5".equals(txYmYckEntities31.get(0).getCzlx())){
										txYmYckEntity.setYckqcje(lb_djje4qc.add(txYmYckEntities31.get(0).getCzje()));
									}else{
										txYmYckEntity.setYckqcje(lb_djje4qc.add(txYmYckEntities31.get(0).getYckqcje()));
									}
																	
								}else{
									continue;
								}
							}
						}else{
							if("2".equals(txYmYckEntities2.get(0).getMxzt())){
								//按照公司编号，操作时间获取同一时间的多条数据，按照直到找出期末没有的那条为止
								TxYmYckEntity txYmYckEntity3 = new TxYmYckEntity();
								//获取冻结金额
								txYmYckEntity3 = txYmYckMapper.GetBeforeDjje(ls_gsbh, temp_ssy);
								if(txYmYckEntity3 != null){
									if(txYmYckEntity3.getDjje()!=null){
										txYmYckEntity.setDjje(txYmYckEntity3.getDjje());	
									}else{
										txYmYckEntity.setDjje(BigDecimal.ZERO);
									}
								}else{
									txYmYckEntity.setDjje(BigDecimal.ZERO);
								}
								//获取最大审核时间的期初
								List<TxYmYckEntity> txYmYckEntities3 = new ArrayList<TxYmYckEntity>();
								txYmYckEntities3 = txYmYckMapper.GetMaxShsj(ls_gsbh, temp_ssy);
						        //获取Calendar实例
								if(txYmYckEntities3!=null && txYmYckEntities3.size()>0  && txYmYckEntities3.get(0).getShsj()!=null){
									SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
									Date date1 = null;
									Date date2 = null;
									try {
										
										date1 = sdf.parse(txYmYckEntities3.get(0).getShsj());
										date2 = sdf.parse(txYmYckEntities2.get(0).getCzsj());
									} catch (ParseException e) {
										e.printStackTrace();
									}
									Calendar compareTime1 = Calendar.getInstance();
									Calendar compareTime2 = Calendar.getInstance();
									compareTime1.setTime(date1);
									compareTime2.setTime(date2);
									if(compareTime1.compareTo(compareTime2) < 0){
										txYmYckEntity.setCzsj(txYmYckEntities2.get(0).getCzsj());
										txYmYckEntity.setKsyye(txYmYckEntities2.get(0).getYckqmje());
										txYmYckEntity.setYckqmje(txYmYckEntities2.get(0).getYckqmje());
										txYmYckEntity.setYckye(txYmYckEntity.getDjje().add(txYmYckEntities2.get(0).getYckqmje()));
									}else{
										txYmYckEntity.setCzsj(txYmYckEntities3.get(0).getShsj());
										txYmYckEntity.setKsyye(txYmYckEntities3.get(0).getYckqmje());
										txYmYckEntity.setYckqmje(txYmYckEntities3.get(0).getYckqmje());
										txYmYckEntity.setYckye(txYmYckEntity.getDjje().add(txYmYckEntities3.get(0).getYckqmje()));							        	
									}
								}else{
									txYmYckEntity.setCzsj(txYmYckEntities2.get(0).getCzsj());
									txYmYckEntity.setKsyye(txYmYckEntities2.get(0).getYckqmje());
									txYmYckEntity.setYckqmje(txYmYckEntities2.get(0).getYckqmje());
									txYmYckEntity.setYckye(txYmYckEntity.getDjje().add(txYmYckEntities2.get(0).getYckqmje()));									
								}
								// 2--通过升序获取期初
								txYmYckEntity3 = new TxYmYckEntity();
								//获取期初冻结金额
								txYmYckEntity3 = txYmYckMapper.GetBeforeQcDjje(ls_gsbh);
								BigDecimal lb_djje4qc = null;
								if(txYmYckEntity3 != null){
									if(txYmYckEntity3.getDjje()!=null){
										lb_djje4qc = txYmYckEntity3.getDjje();	
									}else{
										lb_djje4qc = BigDecimal.ZERO;
									}
								}else{
									lb_djje4qc = BigDecimal.ZERO;
								}								
								List<TxYmYckEntity> txYmYckEntities4 = new ArrayList<TxYmYckEntity>();
								txYmYckEntities4 = txYmYckMapper.GetBeforeYckQcje(ls_gsbh, temp_ssy);
								if("5".equals(txYmYckEntities4.get(0).getCzlx())){
									txYmYckEntity.setYckqcje(lb_djje4qc.add(txYmYckEntities4.get(0).getCzje()));
								}else{
									txYmYckEntity.setYckqcje(lb_djje4qc.add(txYmYckEntities4.get(0).getYckqcje()));
								}								
							}else{
								continue;
							}
						}
						//--获取到期末金额，这个月无数据	
					}else{
						txYmYckEntities2 = new ArrayList<TxYmYckEntity>();
						txYmYckEntities2 = txYmYckMapper.GetYckQmje(ls_gsbh, temp_ssy);
						//新开的门店
						if(txYmYckEntities2 !=null && txYmYckEntities2.size()>0){
							if(txYmYckEntities2.size()==1){
								if("2".equals(txYmYckEntities2.get(0).getMxzt())){
									if(txYmYckEntities2.get(0).getShsj()!=null){
										
										SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM" );
										Date date1 = null;
										Date date2 = null;
										try {
											
											date1 = sdf.parse(txYmYckEntities2.get(0).getShsj());
											date2 = sdf.parse(temp_ssy);
										} catch (ParseException e) {
											e.printStackTrace();
										}
										Calendar compareTime1 = Calendar.getInstance();
										Calendar compareTime2 = Calendar.getInstance();
										compareTime1.setTime(date1);
										compareTime2.setTime(date2);
										if(compareTime1.compareTo(compareTime2) > 0){
											continue;
										}else{
											txYmYckEntity.setDjje(BigDecimal.ZERO);
											txYmYckEntity.setKsyye(txYmYckEntities2.get(0).getCzje());
											txYmYckEntity.setYckqcje(BigDecimal.ZERO);
											txYmYckEntity.setYckqmje(txYmYckEntities2.get(0).getCzje());
											txYmYckEntity.setYckye(txYmYckEntities2.get(0).getCzje());
											txYmYckEntity.setCzsj(txYmYckEntities2.get(0).getCzsj());
										}
									}
								}else{
									continue;
								}
							}else{
								if("2".equals(txYmYckEntities2.get(0).getMxzt())){
									TxYmYckEntity txYmYckEntity3 = new TxYmYckEntity();
									//获取冻结金额
									txYmYckEntity3 = txYmYckMapper.GetBeforeDjje(ls_gsbh, temp_ssy);
									if(txYmYckEntity3 != null){
										if(txYmYckEntity3.getDjje()!=null){
											txYmYckEntity.setDjje(txYmYckEntity3.getDjje());	
										}else{
											txYmYckEntity.setDjje(BigDecimal.ZERO);
										}
									}else{
										txYmYckEntity.setDjje(BigDecimal.ZERO);
									}
									//获取最大审核时间的期初
									List<TxYmYckEntity> txYmYckEntities3 = new ArrayList<TxYmYckEntity>();
									txYmYckEntities3 = txYmYckMapper.GetMaxShsj(ls_gsbh, temp_ssy);
							        //获取Calendar实例
									if(txYmYckEntities3!=null  && txYmYckEntities3.size()>0 && txYmYckEntities3.get(0).getShsj()!=null){
										SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
										Date date1 = null;
										Date date2 = null;
										try {
											
											date1 = sdf.parse(txYmYckEntities3.get(0).getShsj());
											date2 = sdf.parse(txYmYckEntities2.get(0).getCzsj());
										} catch (ParseException e) {
											e.printStackTrace();
										}
										Calendar compareTime1 = Calendar.getInstance();
										Calendar compareTime2 = Calendar.getInstance();
										compareTime1.setTime(date1);
										compareTime2.setTime(date2);
										if(compareTime1.compareTo(compareTime2) < 0){
											txYmYckEntity.setCzsj(txYmYckEntities2.get(0).getCzsj());
											txYmYckEntity.setKsyye(txYmYckEntities2.get(0).getYckqmje());
											txYmYckEntity.setYckqmje(txYmYckEntities2.get(0).getYckqmje());
											txYmYckEntity.setYckye(txYmYckEntity.getDjje().add(txYmYckEntities2.get(0).getYckqmje()));
											txYmYckEntity.setYckqcje(txYmYckEntities2.get(0).getYckqmje());
										}else{
											txYmYckEntity.setCzsj(txYmYckEntities3.get(0).getShsj());
											txYmYckEntity.setKsyye(txYmYckEntities3.get(0).getYckqmje());
											txYmYckEntity.setYckqmje(txYmYckEntities3.get(0).getYckqmje());
											txYmYckEntity.setYckye(txYmYckEntity.getDjje().add(txYmYckEntities3.get(0).getYckqmje()));
											txYmYckEntity.setYckqcje(txYmYckEntities3.get(0).getYckqmje());
										}
									}else{
										txYmYckEntity.setCzsj(txYmYckEntities2.get(0).getCzsj());
										txYmYckEntity.setKsyye(txYmYckEntities2.get(0).getYckqmje());
										txYmYckEntity.setYckqmje(txYmYckEntities2.get(0).getYckqmje());
										txYmYckEntity.setYckye(txYmYckEntity.getDjje().add(txYmYckEntities2.get(0).getYckqmje()));
										txYmYckEntity.setYckqcje(txYmYckEntities2.get(0).getYckqmje());
									}
										
								}else{
									continue;
								}
							}						
						}else{
							continue;
						}
						
					}
					txYmYckEntity.setSsy(temp_ssy);
				}
				
				// 4--保存取到的期初 期末 
				int temp_re = txYmYckMapper.InsertYmYck(txYmYckEntity);
				if(temp_re<=0){
					LOG.info("月末预存款门店"+txYmYckEntity.getSsgsjc()+"没有记录插入到表'HYREPORT_TXMD_YMYCK'中");
				}
			}
		}else{
			LOG.info("月末预存款未找到特许门店");
		}
	}

}
