package com.hand.commonKey;

/**
 * Created by tuberose on 16-9-2.
 */
public class CommonKey {

    // url 地址

    public static  String URL_PERFIX ="http://localhost:8080/";
    public static  int     PAGESIZE  = 5;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////sys、创建、删除、修改——码///////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 深层调用失败
    public  static  String  FAILCAUSEDEEP  = "0000";
    // 参数缺失
    public  static  String  PARAMETERDEFICIENCY = "0001";
    //  创建
    public  static  String  CREATESUCCESS = "0101";
    public  static  String  CREATFAIL     = "0102";
    //  删除
    public  static  String  DELETESUCCESS = "0103";
    public  static  String  DELETEFAIL    = "0004";
    //  修改
    public  static  String  UPDATESUCCESS = "0105";
    public  static  String  UPDATEFAIL    = "0106";



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////用户层/////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 普通用户
    public static int USER = 1;
    public static String USERSTRING = "USER";

    // 管理员
    public static int ADMIN = 2;
    public static String ADMINSTRING = "ADMIN";



    // 用户名重复
    public static String ACCOUNTNAMEDUPLICATE ="1101";

    // 登陆
    public static String LOGINSUCCESS = "1103";
    public static String LOGINFAIL    = "1104";

    // 登出
    public static String LOGOUTSUCCESS = "1105";
    public static String LOGOUTFAIL    = "1106";

    //  用户没有登陆
    public static String NOTLOGIN = "1107";

}
