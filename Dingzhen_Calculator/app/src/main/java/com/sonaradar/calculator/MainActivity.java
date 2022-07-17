package com.sonaradar.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sonaradar.calculator.algorithm.autoCAL;

public class MainActivity extends AppCompatActivity {

    public static boolean isGetresult = false;
    public static String formula = "";
    public static String M = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///控件注册
        Button num_0 =  (Button) findViewById(R.id.btn_0);
        Button num_1 =  (Button) findViewById(R.id.btn_1);
        Button num_2 =  (Button) findViewById(R.id.btn_2);
        Button num_3 =  (Button) findViewById(R.id.btn_3);
        Button num_4 =  (Button) findViewById(R.id.btn_4);
        Button num_5 =  (Button) findViewById(R.id.btn_5);
        Button num_6 =  (Button) findViewById(R.id.btn_6);
        Button num_7 =  (Button) findViewById(R.id.btn_7);
        Button num_8 =  (Button) findViewById(R.id.btn_8);
        Button num_9 =  (Button) findViewById(R.id.btn_9);
        Button char_percentage = (Button) findViewById(R.id.btn_percentage);
        Button char_dot = (Button) findViewById(R.id.btn_dot);
        Button char_plus = (Button) findViewById(R.id.btn_plus);
        Button char_reduce = (Button) findViewById(R.id.btn_reduce);
        Button char_multply = (Button) findViewById(R.id.btn_muliply);
        Button char_devide = (Button) findViewById(R.id.btn_devide);
        Button char_clear = (Button) findViewById(R.id.btn_clear);
        Button char_remove = (Button) findViewById(R.id.btn_remove);
        Button char_getresult = (Button) findViewById(R.id.btn_getresult);
        Button char_sqrt = (Button) findViewById(R.id.btn_sqrt);
        Button char_square = (Button) findViewById(R.id.btn_square);
        Button char_sin = (Button) findViewById(R.id.btn_sin);
        Button char_cos = (Button) findViewById(R.id.btn_cos);
        Button char_tan = (Button) findViewById(R.id.btn_tan);
        Button char_bin = (Button) findViewById(R.id.btn_bin);
        Button char_oct = (Button) findViewById(R.id.btn_oct);
        Button char_hex = (Button) findViewById(R.id.btn_hex);
        Button char_set = (Button) findViewById(R.id.btn_setm);
        Button char_m = (Button) findViewById(R.id.btn_m);
        Button char_factorial = (Button) findViewById(R.id.btn_factorial);
        Button char_pi = (Button) findViewById(R.id.btn_pi);
        Button char_setM = (Button) findViewById(R.id.btn_set);
        Button char_leftbracket = (Button) findViewById(R.id.btn_leftbracket);
        Button char_rightbracket = (Button) findViewById(R.id.btn_rightbracket);
        TextView txt_mainbar = (TextView)findViewById(R.id.txt_mainbars);
        TextView txt_maindescribe = (TextView)findViewById(R.id.txt_maindescribe);
        TextView txt_subbar = (TextView)findViewById(R.id.txt_subbar);
        TextView txt_subdescribe = (TextView)findViewById(R.id.txt_subdescribe);

        ///置低
        ScrollView sv = (ScrollView)findViewById(R.id.scrollView2);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        ///数字输入
        num_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("0");
            }
        });

        num_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("1");
            }
        });

        num_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("2");
            }
        });

        num_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("3");
            }
        });

        num_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("4");
            }
        });

        num_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("5");
            }
        });

        num_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("6");
            }
        });

        num_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("7");
            }
        });

        num_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("8");
            }
        });

        num_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("9");
            }
        });
        char_percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("%");
            }
        });
        char_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula(".");
            }
        });
        char_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("+");
            }
        });
        char_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("-");
            }
        });
        char_multply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("×");
            }
        });
        char_devide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("/");
            }
        });
        char_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula = "0";
                showFormula();
            }
        });
        char_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("sin(");
            }
        });
        char_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("cos(");
            }
        });
        char_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("tan(");
            }
        });
        char_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("√");
            }
        });
        char_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("^");
            }
        });
        char_factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("!");
            }
        });
        char_pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("π");
            }
        });
        char_leftbracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula("(");
            }
        });
        char_rightbracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendFormula(")");
            }
        });
        //退格功能，遇到sin cos tan nan时自动全退
        char_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formula.length()>0){
                    try{
                        if(formula.substring(formula.length()-4,formula.length()).contains("sin(")||
                                formula.substring(formula.length()-4,formula.length()).contains("cos(")||
                                formula.substring(formula.length()-4,formula.length()).contains("tan(")
                        ){
                            formula = formula.substring(0,formula.length()-4);
                        }else{
                            if(formula.contains("NaN")){
                                formula = "0";
                            }else{
                                formula = formula.substring(0,formula.length()-1);
                            }
                        }
                    }catch (Exception e){
                        if(formula.contains("NaN")){
                            formula = "0";
                        }else{
                            formula = formula.substring(0,formula.length()-1);
                        }
                    }
                }
                showFormula();
            }
        });
        //计算表达式，就是等号的功能，通过编的autocal算法实现
        char_getresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula = autoCAL.getResult(formula).replace("NaN","假");
                showFormula();
            }
        });
        //二进制转换
        char_bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula = autoCAL.toBin(autoCAL.getResult(formula+"+0").replace("NaN","假"));
                showFormula();
            }
        });
        //八进制转换
        char_oct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula = autoCAL.toOct(autoCAL.getResult(formula+"+0").replace("NaN","假"));
                showFormula();
            }
        });
        //十六进制转换
        char_hex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula = autoCAL.toHex(autoCAL.getResult(formula+"+0").replace("NaN","假"));
                showFormula();
            }
        });
        //计算机记忆功能，把数据记忆起来
        char_setM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M = formula;
                Toast.makeText(getApplicationContext(), "保存数据成功:" + formula, Toast.LENGTH_LONG).show();
            }
        });
        //记忆输出功能
        char_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula += M;
                showFormula();
            }
        });
        //更新检测按钮
        char_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), elsePage.class);//启动MainActivity
                startActivity(it);
            }
        });


    }
    //显示计算后得到的表达式
    public void showFormula(){
        try{
            TextView txt_mainbar = (TextView)findViewById(R.id.txt_mainbars);
            TextView txt_subbar = (TextView)findViewById(R.id.txt_subbar);
            if(formula.length()<=0){
                txt_mainbar.setText("0");
            }else{
                String tmp_result1 = formula;
                try{
                    if(tmp_result1.substring(tmp_result1.length()-2).equals(".0")){
                        txt_mainbar.setText(tmp_result1.substring(0,tmp_result1.length()-2));
                    }else{
                        txt_mainbar.setText(tmp_result1);
                    }
                }catch(Exception e){txt_mainbar.setText(tmp_result1);}
            }
            String tmp_result = autoCAL.getResult(formula).replace("NaN","假");
            try{
            if(tmp_result.substring(tmp_result.length()-2).equals(".0")){
                txt_subbar.setText(tmp_result.substring(0,tmp_result.length()-2));
            }else{
                txt_subbar.setText(tmp_result);
            }
            }catch(Exception e){txt_subbar.setText(tmp_result);}
        }catch (Exception e){}
    }
    //将数字或者运算符接到表达式后面
    public void appendFormula(String append_str) {
        if(formula.replace("0","") == ""){
            formula = append_str;
        }else{
            formula += append_str;
        }
        showFormula();
    }
}