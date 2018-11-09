package com.hoau.hoaureport.module.frameworkimpl.shared.domain;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hoau.hbdp.framework.server.sso.domain.Token;

public class CookieToken extends Token
{
  private static final Logger LOGGER = LoggerFactory.getLogger(CookieToken.class);
  private String empCode;
  private String deptCode;
  private Long expireTime = Long.valueOf(System.currentTimeMillis());

  public String getEmpCode() {
    return this.empCode;
  }

  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }

  public String getDeptCode() {
    return this.deptCode;
  }

  public void setDeptCode(String deptCode) {
    this.deptCode = deptCode;
  }

  public Long getExpireTime() {
    return this.expireTime;
  }

  public void setExpireTime(Long millisecond) {
    this.expireTime = millisecond;
  }

  public CookieToken(String sessionId, String userId, String empCode, String currentDeptCode, int expireSecond)
  {
    setUserId(userId);
    setSessionId(sessionId);
    this.empCode = empCode;
    this.deptCode = currentDeptCode;

    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(System.currentTimeMillis());
    int millisecond = expireSecond * 1000;
    c.add(14, millisecond);
    this.expireTime = Long.valueOf(c.getTimeInMillis());
  }

  public CookieToken(byte[] tokenBytes)
  {
    try
    {
      String token = new String(tokenBytes, "UTF-8");
      String[] keys = token.split(",");
      setSessionId(keys[0]);
      setUserId(keys[1]);
      this.empCode = keys[2];
      this.deptCode = keys[3];
      this.expireTime = Long.valueOf(Long.parseLong(keys[4]));
    } catch (UnsupportedEncodingException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  public byte[] toBytes()
  {
    try
    {
      return toString().getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  public String toString()
  {
    super.toString();
    StringBuffer sb = new StringBuffer(8);
    sb.append(getSessionId()).append(",");
    sb.append(getUserId()).append(",");
    sb.append(getEmpCode()).append(",");
    sb.append(getDeptCode()).append(",");
    sb.append(getExpireTime());
    return sb.toString();
  }

  public boolean expired()
  {
    long millisecond = getExpireTime().longValue();
    long currentMs = System.currentTimeMillis();
    return millisecond < currentMs;
  }
}