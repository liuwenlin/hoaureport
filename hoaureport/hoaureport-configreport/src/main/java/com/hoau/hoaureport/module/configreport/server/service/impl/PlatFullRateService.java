package com.hoau.hoaureport.module.configreport.server.service.impl;

import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.configreport.server.dao.PlatFullRateMapper;
import com.hoau.hoaureport.module.configreport.server.service.IPlatFullRateService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.PlatFullRate;
import com.hoau.hoaureport.module.configreport.shared.domain.PlatNextdayReachRate;
import com.hoau.hoaureport.module.util.DateUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Pattern;

/**满载率目标值
 * @author by 宋京涛
 * @Description
 * @Date 2018/3/12
 */
@Service
public class PlatFullRateService implements IPlatFullRateService {

    private static final Logger log = LoggerFactory.getLogger(PlatFullRate.class);
    @Resource
    PlatFullRateMapper platFullRateMapper;

    /**
     * 查询满载率
     * @param platFullRate
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<PlatFullRate> queryPlatFullRateList(PlatFullRate platFullRate, int start, int limit) {
        RowBounds rowBounds = new RowBounds(start, limit);
        List<PlatFullRate> platFullRateList = platFullRateMapper.queryPlatFullRate(platFullRate, rowBounds);
        return platFullRateList;
    }

    /**
     * 查询记录总数
     */
    @Override
    public Long queryPlatNextdayReachRateCount(PlatFullRate param) {
        return platFullRateMapper.queryPlatFullRateCount(param);
    }

    /**
     * 导入excel
     * @param path
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> bathImplPlatNextdayReachRate(String path) throws Exception {
        //导入结果
        Map<String,Object> retuMap =new HashMap<String, Object>();
        //解析Excel
        List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 32, 2);
        //把键值对 转换成 对象集合
        List<PlatFullRate> platFullRates = new ArrayList<PlatFullRate>();
        //“目标值”必须为数字类型字符，精确到小数点后两位（不可以用%表示）。
        // “上月完成值”必须是数字类型的字符，精确到小数点后两位(不可以用%表示)。
        // “所属月份”必须是“2016-08”这种格式
        //匹配目标值 和 上月完成值
//        Pattern pValue = Pattern.compile("^\\d{1,9}(\\.\\d{1,41})?$");
        //匹配所属月份
        Pattern pMonth = Pattern.compile("^\\d{4}(\\-\\d{2})?$");
        if(list.size() > 0){
            Map<String, String> map = null;
            for(int i = 0;i<list.size();i++){
                map = list.get(i);
                //如果部门为空就说明读到空行了
                if(StringUtil.isEmpty(StringUtil.trim(map.get(0+"")))){
                    break;
                }
                if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(0+"")))
                        ||!StringUtil.isNotEmpty(StringUtil.trim(map.get(1+"")))
                        ||!StringUtil.isNotEmpty(StringUtil.trim(map.get(2+"")))
                        ||!StringUtil.isNotEmpty(StringUtil.trim(map.get(3+"")))){
                    retuMap.put("error", "第"+(++i)+"行,为空项请用0替换!");
                    return retuMap;
                }
                if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(3+"")))){
                    retuMap.put("error", "第"+(++i)+"行,所属月份不能为空!");
                    return retuMap;
                }
                if(!pMonth.matcher(StringUtil.trim(map.get(3+""))).matches()){
                    retuMap.put("error", "第"+(++i)+"行,所属月份格式有错误,请修改后重新导入!");
                    return retuMap;
                }
            }
        }
        for (Map<String, String> map : list) {
            PlatFullRate bean = new PlatFullRate();
            try {
                if(StringUtil.isEmpty(StringUtil.trim(map.get(0+"")))){
                    continue;
                }
                //部门
                bean.setDepartment(StringUtil.trim(map.get(0+"")));
                //目标值
                bean.setTargetValue(StringUtil.trim(map.get(1+"")));
                //上个月目标值
                bean.setLastMonthFinishValue(StringUtil.trim(map.get(2+"")));
                //所属月份
                bean.setTargetValueMonth(StringUtil.trim(map.get(3+"")));
            } catch (Exception e) {
                bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
                log.error("批量导入模版数据异常，业务需要仅作提示", e);
            } finally {
                platFullRates.add(bean);
            }
        }
        //设置增加条数,覆盖条数
        Map<String, Long> countMap = new HashMap<String, Long>();
        countMap.put("addSize", new Long(0));// 增加条数
        countMap.put("coverSize", new Long(0));// 覆盖条数
        Long beforAddSize=null;
        Long beforCoverSize=null;
        for (int i = 0; i < platFullRates.size(); i++) {
            PlatFullRate info = platFullRates.get(i);
            try {
                if (info == null) {// 有异常
                    continue;
                } else {
                    beforAddSize = countMap.get("addSize");
                    beforCoverSize = countMap.get("coverSize");
                    this.addOrUpdatePlatNextdayReachRate(info, countMap);
                }
            } catch (Exception e) {
                log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
                countMap.put("addSize", beforAddSize);
                countMap.put("coverSize", beforCoverSize);
            }
        }

        retuMap.put("addSize", countMap.get("addSize"));
        retuMap.put("coverSize", countMap.get("coverSize"));
        retuMap.put("sumSize", platFullRates.size());
        retuMap.put("error", "");
        return retuMap;
    }

    /**
     * 导入满载率目标值
     * @param info
     * @param countMap
     * @throws Exception
     */
    private void addOrUpdatePlatNextdayReachRate(PlatFullRate info, Map<String, Long> countMap) throws Exception {
        UserEntity user = (UserEntity) (UserContext.getCurrentUser());
        long addSize= countMap.get("addSize").longValue();
        long coverSize= countMap.get("coverSize").longValue();
        //查询历史有效信息
        PlatFullRate queryParam = new PlatFullRate();
        queryParam.setDepartment(info.getDepartment());
        queryParam.setTargetValueMonth(info.getTargetValueMonth());
        List<PlatFullRate> infoList = this.queryPlatFullRateList(queryParam, 0, 1);
        if(infoList.size() > 0){//已存在 就直接更新
            //更新数据
            PlatFullRate updateParam = new PlatFullRate();
            updateParam.setImportTime(new Date());
            updateParam.setTargetValue(info.getTargetValue());
            updateParam.setLastMonthFinishValue(info.getLastMonthFinishValue());
            updateParam.setImportUser(user.getEmpCode().substring(2));
            this.updatePlatNextdayReachRate(updateParam);
            //覆盖+1
            coverSize++;
        }else{
            //插入数据
            info.setImportTime(new Date());
            this.addPlatNextdayReachRate(info);
            //新增+1
            addSize++;
        }
        countMap.put("addSize", addSize);
        countMap.put("coverSize", coverSize);
    }

    @Transactional
    public void updatePlatNextdayReachRate(PlatFullRate record) {
        platFullRateMapper.update(record);
    }

    /**
     * 增加满载率目标值
     */
    @Transactional
    private void addPlatNextdayReachRate(PlatFullRate record) {
        UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
        //工号
        String currUserCode = currUser.getEmpCode().substring(2);
        record.setImportUser(currUserCode);
        platFullRateMapper.insert(record);
    }
}
