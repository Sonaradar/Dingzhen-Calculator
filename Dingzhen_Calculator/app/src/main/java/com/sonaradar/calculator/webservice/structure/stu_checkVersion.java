package com.sonaradar.calculator.webservice.structure;

public class stu_checkVersion {
    //本类是用于存储检查最新版本时保存数据用途
    public int latestVersionCode = 0;//最新的版本代码
    public int requiredOldestVersionCode = 0;//最老允许使用的版本代码
    public String downloadAddress = "";//新版本下载地址
    public stu_checkVersion(int LVC,int ROVC,String DA){//这个功能是用来设置数据的，能方便点
        latestVersionCode = LVC;
        requiredOldestVersionCode = ROVC;
        downloadAddress = DA;
    }
}
