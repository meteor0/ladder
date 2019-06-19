package com.liuxin.ladder.logs.bean;

import java.io.Serializable;

/** log的输出日志 */
public class RespLogsVO  implements Serializable{

  private static final long serialVersionUID = -3935318746045374112L;

  // 日志产生的时间，格式为yyyy-mm-dd h:i:s，示例：2019-04-29 13:30:00
  private String time;

  // 系统对应的域名
  private String domain;

  // 接口的url地址，不包括域名和get参数
  private String url;

  // 包括get和post参数
  private String params;

  // 建议使用appId或者英文标识
  private String appId;

  // 错误日志提示码
  private String errorCode;

  // 错误日志的说明
  private String errorMsg;
  
  private String erroInfo;

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getParams() {
    return params;
  }

  public void setParams(String params) {
    this.params = params;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public RespLogsVO(String time, String domain, String url, String params, String appId,
      String errorCode, String errorMsg) {
    super();
    this.time = time;
    this.domain = domain;
    this.url = url;
    this.params = params;
    this.appId = appId;
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  public RespLogsVO() {
    super();
  }

  public String getErroInfo() {
    return erroInfo;
  }

  public void setErroInfo(GlobalExceptionEnum exception) {
    this.erroInfo = exception.getCode();
    this.errorMsg = exception.getName();
  }

  
}
