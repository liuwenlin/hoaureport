package com.hoau.hoaureport.module.configreport.shared.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 员工当月出勤记录信息
 * ClassName: MonthAttendanceInfo 
 * @author 文洁
 * @date 2016年9月23日
 * @version V1.0
 */
public class MonthAttendanceInfo implements Serializable{
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8483555431892995205L;
	//工号
    private String userId;
	//姓名
    private String userName;
    //月份 (如：'201609')
    private String workMonth;
    //操作公司
    private String companyName;    
    //当月第1天工作量
    private BigDecimal day1LoadQuantity;
    //当月第2天工作量
    private BigDecimal day2LoadQuantity;
    //当月第3天工作量
    private BigDecimal day3LoadQuantity;
    //当月第4天工作量
    private BigDecimal day4LoadQuantity;
    //当月第5天工作量
    private BigDecimal day5LoadQuantity;
    //当月第6天工作量
    private BigDecimal day6LoadQuantity;
    //当月第7天工作量
    private BigDecimal day7LoadQuantity;
    //当月第8天工作量
    private BigDecimal day8LoadQuantity;
    //当月第9天工作量
    private BigDecimal day9LoadQuantity;
    //当月第10天工作量
    private BigDecimal day10LoadQuantity;
    //当月第11天工作量
    private BigDecimal day11LoadQuantity;
    //当月第12天工作量
    private BigDecimal day12LoadQuantity;
    //当月第13天工作量
    private BigDecimal day13LoadQuantity;
    //当月第14天工作量
    private BigDecimal day14LoadQuantity;
    //当月第15天工作量
    private BigDecimal day15LoadQuantity;
    //当月第16天工作量
    private BigDecimal day16LoadQuantity;
    //当月第17天工作量
    private BigDecimal day17LoadQuantity;
    //当月第18天工作量
    private BigDecimal day18LoadQuantity;
    //当月第19天工作量
    private BigDecimal day19LoadQuantity;
    //当月第20天工作量
    private BigDecimal day20LoadQuantity;
    //当月第21天工作量
    private BigDecimal day21LoadQuantity;
    //当月第22天工作量  
    private BigDecimal day22LoadQuantity;
    //当月第23天工作量  
    private BigDecimal day23LoadQuantity;
    //当月第24天工作量  
    private BigDecimal day24LoadQuantity;
    //当月第25天工作量  
    private BigDecimal day25LoadQuantity;
    //当月第26天工作量  
    private BigDecimal day26LoadQuantity;
    //当月第27天工作量  
    private BigDecimal day27LoadQuantity;
    //当月第28天工作量  
    private BigDecimal day28LoadQuantity;
    //当月第29天工作量  
    private BigDecimal day29LoadQuantity;
    //当月第30天工作量  
    private BigDecimal day30LoadQuantity;
    //当月第31天工作量  
    private BigDecimal day31LoadQuantity;
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getWorkMonth() {
		return workMonth;
	}
	public void setWorkMonth(String workMonth) {
		this.workMonth = workMonth;
	}
	public BigDecimal getDay1LoadQuantity() {
		return day1LoadQuantity;
	}
	public void setDay1LoadQuantity(BigDecimal day1LoadQuantity) {
		this.day1LoadQuantity = day1LoadQuantity;
	}
	public BigDecimal getDay2LoadQuantity() {
		return day2LoadQuantity;
	}
	public void setDay2LoadQuantity(BigDecimal day2LoadQuantity) {
		this.day2LoadQuantity = day2LoadQuantity;
	}
	public BigDecimal getDay3LoadQuantity() {
		return day3LoadQuantity;
	}
	public void setDay3LoadQuantity(BigDecimal day3LoadQuantity) {
		this.day3LoadQuantity = day3LoadQuantity;
	}
	public BigDecimal getDay4LoadQuantity() {
		return day4LoadQuantity;
	}
	public void setDay4LoadQuantity(BigDecimal day4LoadQuantity) {
		this.day4LoadQuantity = day4LoadQuantity;
	}
	public BigDecimal getDay5LoadQuantity() {
		return day5LoadQuantity;
	}
	public void setDay5LoadQuantity(BigDecimal day5LoadQuantity) {
		this.day5LoadQuantity = day5LoadQuantity;
	}
	public BigDecimal getDay6LoadQuantity() {
		return day6LoadQuantity;
	}
	public void setDay6LoadQuantity(BigDecimal day6LoadQuantity) {
		this.day6LoadQuantity = day6LoadQuantity;
	}
	public BigDecimal getDay7LoadQuantity() {
		return day7LoadQuantity;
	}
	public void setDay7LoadQuantity(BigDecimal day7LoadQuantity) {
		this.day7LoadQuantity = day7LoadQuantity;
	}
	public BigDecimal getDay8LoadQuantity() {
		return day8LoadQuantity;
	}
	public void setDay8LoadQuantity(BigDecimal day8LoadQuantity) {
		this.day8LoadQuantity = day8LoadQuantity;
	}
	public BigDecimal getDay9LoadQuantity() {
		return day9LoadQuantity;
	}
	public void setDay9LoadQuantity(BigDecimal day9LoadQuantity) {
		this.day9LoadQuantity = day9LoadQuantity;
	}
	public BigDecimal getDay10LoadQuantity() {
		return day10LoadQuantity;
	}
	public void setDay10LoadQuantity(BigDecimal day10LoadQuantity) {
		this.day10LoadQuantity = day10LoadQuantity;
	}
	public BigDecimal getDay11LoadQuantity() {
		return day11LoadQuantity;
	}
	public void setDay11LoadQuantity(BigDecimal day11LoadQuantity) {
		this.day11LoadQuantity = day11LoadQuantity;
	}
	public BigDecimal getDay12LoadQuantity() {
		return day12LoadQuantity;
	}
	public void setDay12LoadQuantity(BigDecimal day12LoadQuantity) {
		this.day12LoadQuantity = day12LoadQuantity;
	}
	public BigDecimal getDay13LoadQuantity() {
		return day13LoadQuantity;
	}
	public void setDay13LoadQuantity(BigDecimal day13LoadQuantity) {
		this.day13LoadQuantity = day13LoadQuantity;
	}
	public BigDecimal getDay14LoadQuantity() {
		return day14LoadQuantity;
	}
	public void setDay14LoadQuantity(BigDecimal day14LoadQuantity) {
		this.day14LoadQuantity = day14LoadQuantity;
	}
	public BigDecimal getDay15LoadQuantity() {
		return day15LoadQuantity;
	}
	public void setDay15LoadQuantity(BigDecimal day15LoadQuantity) {
		this.day15LoadQuantity = day15LoadQuantity;
	}
	public BigDecimal getDay16LoadQuantity() {
		return day16LoadQuantity;
	}
	public void setDay16LoadQuantity(BigDecimal day16LoadQuantity) {
		this.day16LoadQuantity = day16LoadQuantity;
	}
	public BigDecimal getDay17LoadQuantity() {
		return day17LoadQuantity;
	}
	public void setDay17LoadQuantity(BigDecimal day17LoadQuantity) {
		this.day17LoadQuantity = day17LoadQuantity;
	}
	public BigDecimal getDay18LoadQuantity() {
		return day18LoadQuantity;
	}
	public void setDay18LoadQuantity(BigDecimal day18LoadQuantity) {
		this.day18LoadQuantity = day18LoadQuantity;
	}
	public BigDecimal getDay19LoadQuantity() {
		return day19LoadQuantity;
	}
	public void setDay19LoadQuantity(BigDecimal day19LoadQuantity) {
		this.day19LoadQuantity = day19LoadQuantity;
	}
	public BigDecimal getDay20LoadQuantity() {
		return day20LoadQuantity;
	}
	public void setDay20LoadQuantity(BigDecimal day20LoadQuantity) {
		this.day20LoadQuantity = day20LoadQuantity;
	}
	public BigDecimal getDay21LoadQuantity() {
		return day21LoadQuantity;
	}
	public void setDay21LoadQuantity(BigDecimal day21LoadQuantity) {
		this.day21LoadQuantity = day21LoadQuantity;
	}
	public BigDecimal getDay22LoadQuantity() {
		return day22LoadQuantity;
	}
	public void setDay22LoadQuantity(BigDecimal day22LoadQuantity) {
		this.day22LoadQuantity = day22LoadQuantity;
	}
	public BigDecimal getDay23LoadQuantity() {
		return day23LoadQuantity;
	}
	public void setDay23LoadQuantity(BigDecimal day23LoadQuantity) {
		this.day23LoadQuantity = day23LoadQuantity;
	}
	public BigDecimal getDay24LoadQuantity() {
		return day24LoadQuantity;
	}
	public void setDay24LoadQuantity(BigDecimal day24LoadQuantity) {
		this.day24LoadQuantity = day24LoadQuantity;
	}
	public BigDecimal getDay25LoadQuantity() {
		return day25LoadQuantity;
	}
	public void setDay25LoadQuantity(BigDecimal day25LoadQuantity) {
		this.day25LoadQuantity = day25LoadQuantity;
	}
	public BigDecimal getDay26LoadQuantity() {
		return day26LoadQuantity;
	}
	public void setDay26LoadQuantity(BigDecimal day26LoadQuantity) {
		this.day26LoadQuantity = day26LoadQuantity;
	}
	public BigDecimal getDay27LoadQuantity() {
		return day27LoadQuantity;
	}
	public void setDay27LoadQuantity(BigDecimal day27LoadQuantity) {
		this.day27LoadQuantity = day27LoadQuantity;
	}
	public BigDecimal getDay28LoadQuantity() {
		return day28LoadQuantity;
	}
	public void setDay28LoadQuantity(BigDecimal day28LoadQuantity) {
		this.day28LoadQuantity = day28LoadQuantity;
	}
	public BigDecimal getDay29LoadQuantity() {
		return day29LoadQuantity;
	}
	public void setDay29LoadQuantity(BigDecimal day29LoadQuantity) {
		this.day29LoadQuantity = day29LoadQuantity;
	}
	public BigDecimal getDay30LoadQuantity() {
		return day30LoadQuantity;
	}
	public void setDay30LoadQuantity(BigDecimal day30LoadQuantity) {
		this.day30LoadQuantity = day30LoadQuantity;
	}
	public BigDecimal getDay31LoadQuantity() {
		return day31LoadQuantity;
	}
	public void setDay31LoadQuantity(BigDecimal day31LoadQuantity) {
		this.day31LoadQuantity = day31LoadQuantity;
	}
	@Override
	public String toString() {
		return "MonthAttendanceInfo [userId=" + userId + ", userName="
				+ userName + ", workMonth=" + workMonth + ", companyName="
				+ companyName + ", day1LoadQuantity=" + day1LoadQuantity
				+ ", day2LoadQuantity=" + day2LoadQuantity
				+ ", day3LoadQuantity=" + day3LoadQuantity
				+ ", day4LoadQuantity=" + day4LoadQuantity
				+ ", day5LoadQuantity=" + day5LoadQuantity
				+ ", day6LoadQuantity=" + day6LoadQuantity
				+ ", day7LoadQuantity=" + day7LoadQuantity
				+ ", day8LoadQuantity=" + day8LoadQuantity
				+ ", day9LoadQuantity=" + day9LoadQuantity
				+ ", day10LoadQuantity=" + day10LoadQuantity
				+ ", day11LoadQuantity=" + day11LoadQuantity
				+ ", day12LoadQuantity=" + day12LoadQuantity
				+ ", day13LoadQuantity=" + day13LoadQuantity
				+ ", day14LoadQuantity=" + day14LoadQuantity
				+ ", day15LoadQuantity=" + day15LoadQuantity
				+ ", day16LoadQuantity=" + day16LoadQuantity
				+ ", day17LoadQuantity=" + day17LoadQuantity
				+ ", day18LoadQuantity=" + day18LoadQuantity
				+ ", day19LoadQuantity=" + day19LoadQuantity
				+ ", day20LoadQuantity=" + day20LoadQuantity
				+ ", day21LoadQuantity=" + day21LoadQuantity
				+ ", day22LoadQuantity=" + day22LoadQuantity
				+ ", day23LoadQuantity=" + day23LoadQuantity
				+ ", day24LoadQuantity=" + day24LoadQuantity
				+ ", day25LoadQuantity=" + day25LoadQuantity
				+ ", day26LoadQuantity=" + day26LoadQuantity
				+ ", day27LoadQuantity=" + day27LoadQuantity
				+ ", day28LoadQuantity=" + day28LoadQuantity
				+ ", day29LoadQuantity=" + day29LoadQuantity
				+ ", day30LoadQuantity=" + day30LoadQuantity
				+ ", day31LoadQuantity=" + day31LoadQuantity + "]";
	}

    
}
