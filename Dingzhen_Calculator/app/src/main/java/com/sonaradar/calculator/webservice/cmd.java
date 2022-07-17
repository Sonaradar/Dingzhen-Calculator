package com.sonaradar.calculator.webservice;

import com.sonaradar.calculator.algorithm.autoCAL;
import com.sonaradar.calculator.webservice.structure.stu_checkVersion;
import com.sonaradar.calculator.webservice.structure.stu_isAllowUsing;

public class cmd {
    public static int myVersion = 110;//软件现行版本
    public static String da = "";//禁止登录的原因
    //发送检测更新命令，获取到最新版本号、可允许使用的最老版本号和新版本下载地址
    ///[checkversion]
    ///[checkversion]<latestversion:100><requiredoldestversion:100><downaddress:****>
    public static stu_checkVersion checkVersion(){
        try{

            String str_return = socket.Sender("[checkversion]");
            String lv = autoCAL.getSubString(str_return,"<latestversion:",">");
            String rov = autoCAL.getSubString(str_return,"<requiredoldestversion:",">");
            String da = autoCAL.getSubString(str_return,"<downaddress:",">");
            return new stu_checkVersion(Integer.valueOf(lv),Integer.valueOf(rov),da);
        }catch (Exception e){e.printStackTrace();}
        return new stu_checkVersion(0,0,"");
    }
    //向服务器发送命令，请求是否可以使用软件，返回是否允许使用的布尔值和禁止登录的原因(如果禁止登录的话)
    ///[isallowusing]<version:100>
    ///[isallowusing]<status:accept/deny><reason:******>
    public static stu_isAllowUsing isAllowUsing(int versionCode){
        try{
            String str_return = socket.Sender("[isallowusing]<version:" + versionCode + ">");
            String status = autoCAL.getSubString(str_return,"<status:",">");
            String reason = autoCAL.getSubString(str_return,"<reason:",">");
            if(str_return.contains("accept")){
                return new stu_isAllowUsing(true,reason);
            }else{
                return new stu_isAllowUsing(false,reason);
            }
        } catch (Exception e) {}
        return new stu_isAllowUsing(false,"");
    }
}
