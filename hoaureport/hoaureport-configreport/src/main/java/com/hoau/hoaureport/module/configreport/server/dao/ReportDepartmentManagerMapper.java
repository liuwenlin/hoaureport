package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.ReportDepartmentManager;
import com.hoau.hoaureport.module.configreport.shared.vo.DepartmentManagerVo;
/**
 * 
 * @author 刘镇松
 *	基础资料 - 部门管理者
 */
@Repository
public interface ReportDepartmentManagerMapper {
	int deleteByPrimaryKey(String departmentName);

    int insert(ReportDepartmentManager record);

    int insertSelective(ReportDepartmentManager record);

    List<ReportDepartmentManager> selectByPrimaryKey(ReportDepartmentManager departmentName,RowBounds rowBounds);

    int updateByPrimaryKeySelective(ReportDepartmentManager record);

    int updateByPrimaryKey(ReportDepartmentManager record);
    
    Long countByCondition(ReportDepartmentManager record);
}