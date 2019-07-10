package com.liuxin.ladder.logs.bean;

public enum GlobalExceptionEnum {
  
  
  /**
   * 错误信息编码与名称枚举类
   */
  PARAM_MISSING("101", "参数缺失"),
  PARAM_TYPE_NOT_MATCH("102", "参数类型不匹配"),
  PARAM_TYPE_NOT_INVALID("103", "参数格式不合法"), 
  PARAM_ALL_MISSING("104", "参数未命中"), 
  PARAM_JSON_CONNOT_SERIALIZABLE("105", "json类型参数不能序列化"), 
  REQUEST_METHOTH_NOT_SUPPORTED("106", "请求方法不支持"), 
  TOKEN_MISSING("201", "token缺失"), 
  TOKEN_TYPE_NOT_MATCH("202", "token格式不合法"), 
  TOKEN_INVALID("203", "token失效"), 
  APPID_MISSING("204", "appid缺失"), 
  USERID_NOT_MATCH_TOKEN("205", "userid和token不匹配"), 
  SIGN_MISSING("206", "验签sign缺失"), 
  SIGN_VERIFY_NOT_MATCH("207", "验签sign不匹配"), 
  AUTH_MISSGING("208", "没有权限"), 
  EXTERNAL_INTERFACE_TIMEOUT("301", "调用外部接口超时"), 
  EXTERNAL_INTERFACE_EXCEPTION("302", "外部接口异常"), 
  JOSN_RESULT_ANALYSIS("303", "json类型结果不能解析"), 
  REDIS_CONNOT_CONNECTION("304", "redis连接拿不到"), 
  REDIS_TIMEOUT("305", "redis超时"), 
  REDIS_SERVICE_UNAVAILABLE("306", "redis服务不可用"), 
  DB_CONNOT_CONNECTION("307", "数据库连接拿不到"), 
  SQL_EXECUTE_TIMEOUT("308", "sql执行超时"), 
  SQL_EXECUTE_EXCEPTION("309", "sql执行异常"),
  CLIENT_TERMINATION_OPERATION("401", "客户端终止操作");


  private String code;

  private String name;

  
  public String getName() {
    return name;
  }


  public String getCode() {
    return code;
  }

  private GlobalExceptionEnum(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
