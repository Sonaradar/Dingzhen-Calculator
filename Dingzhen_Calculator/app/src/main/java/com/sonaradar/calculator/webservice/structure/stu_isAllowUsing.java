package com.sonaradar.calculator.webservice.structure;
//这个类用来存储检查是否允许使用的
public class stu_isAllowUsing {
    public boolean isAllowUsing = false;//是否允许登录
    public String reason = "";//禁止登录的原因
    public stu_isAllowUsing(boolean IAU,String R){//和上一个一样，不讲了
        isAllowUsing = IAU;
        reason = R;
    }
}

