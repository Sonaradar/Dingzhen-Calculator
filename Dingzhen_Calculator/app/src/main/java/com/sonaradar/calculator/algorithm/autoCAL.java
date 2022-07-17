package com.sonaradar.calculator.algorithm;

import java.util.Collections;
import java.util.Stack;

public class autoCAL {
    public static String getResult(String formula){
        return String.valueOf(conversion(triangle_cal(format_changer(formula))));
    }
    //通过使用栈来实现
    private Stack<String> postfixStack = new Stack<String>();//存储后缀
    private Stack<Character> opStack = new Stack<Character>();//存储运算符
    //外部调用这个方法
    public static double conversion(String expression) {
        double result = 0;
        autoCAL cal = new autoCAL();
        try {
            expression = transform(expression);
            result = cal.calculate(expression);
        }
        catch (Exception e) {
            //报NAN错误，算不出来
            return 0.0 / 0.0;
        }
        return result;
    }

    //负数符号翻转为~，防止和减号弄混了
    private static String transform(String expression) {
        char[] arr = expression.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {
                if (i == 0) {
                    arr[i] = '~';
                } else {
                    char c = arr[i - 1];
                    if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == 'E' || c == 'e'||c == '%'|| c == '^'|| c == '!' || c == '$') {
                        arr[i] = '~';
                    }
                }
            }
        }
        if(arr[0]=='~'||arr[1]=='('){
            arr[0]='-';
            return "0"+new String(arr);
        } else{
            return new String(arr);
        }
    }

    //计算表达式
    private double calculate(String expression) {
        Stack<String> resultStack = new Stack<String>();
        prepare(expression);
        Collections.reverse(postfixStack);
        //将后缀式栈反转
        String firstValue, secondValue, currentValue;
        //参与计算的第一个值，第二个值和算术运算符
        while (!postfixStack.isEmpty()) {
            currentValue = postfixStack.pop();
            if (!isOperator(currentValue.charAt(0))) {
                //如果不是运算符则存入操作数栈中
                currentValue = currentValue.replace("~", "-");
                resultStack.push(currentValue);
            } else {
                //如果是运算符则从操作数栈中取两个值和该数值一起参与运算
                secondValue = resultStack.pop();
                firstValue = resultStack.pop();
                //将负数标记符改为负号
                firstValue = firstValue.replace("~", "-");
                secondValue = secondValue.replace("~", "-");
                String tempResult = calculate(firstValue, secondValue, currentValue.charAt(0));
                resultStack.push(tempResult);
            }
        }
        return Double.valueOf(resultStack.pop());
    }

    //数据准备阶段将表达式转换成为后缀式栈
    private void prepare(String expression) {
        opStack.push(',');
        // 运算符放入栈底元素逗号，此符号优先级最低
        char[] arr = expression.toCharArray();
        int currentIndex = 0;
        // 当前字符的位置
        int count = 0;
        // 上次算术运算符到本次算术运算符的字符的长度便于或者之间的数值
        char currentOp, peekOp;
        // 当前操作符和栈顶操作符
        for (int i = 0; i < arr.length; i++) {
            currentOp = arr[i];
            if (isOperator(currentOp)) {
                // 如果当前字符是运算符
                if (count > 0) {
                    postfixStack.push(new String(arr, currentIndex, count));
                    // 取两个运算符之间的数字
                }
                peekOp = opStack.peek();
                if (currentOp == ')') {
                    // 遇到反括号则将运算符栈中的元素移除到后缀式栈中直到遇到左括号
                    while (opStack.peek() != '(') {
                        postfixStack.push(String.valueOf(opStack.pop()));
                    }
                    opStack.pop();
                } else {
                    while (currentOp != '(' && peekOp != ',' && compare(currentOp, peekOp)) {
                        postfixStack.push(String.valueOf(opStack.pop()));
                        peekOp = opStack.peek();
                    }
                    opStack.push(currentOp);
                }
                count = 0;
                currentIndex = i + 1;
            } else {
                count++;
            }
        }
        if (count > 1 || (count == 1 && !isOperator(arr[currentIndex]))) {
            // 最后一个字符不是括号或者其他运算符的则加入后缀式栈中
            postfixStack.push(new String(arr, currentIndex, count));
        }
        while (opStack.peek() != ',') {
            postfixStack.push(String.valueOf(opStack.pop()));
            // 将操作符栈中的剩余的元素添加到后缀式栈中
        }
    }

    //运算符号判断
    private Boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'|| c == '%'|| c == '^'|| c == '!' || c == '$';
    }

    //优先级的注册与判断
    private Boolean compare(char cur, char peek) {
        // 如果是peek优先级高于cur，返回true，默认都是peek优先级要低
        switch (cur){
            case '+':
            case '-':cur=1;break;
            case '*':
            case '/':
            case '%':cur=2;break;
            case '^':
            case '$':
            case '!':cur=3;break;
            case '(':
            case ')':cur=0;break;
        }
        switch (peek){
            case '+':
            case '-':peek=1;break;
            case '*':
            case '/':
            case '%':peek=2;break;
            case '^':
            case '$':
            case '!':peek=3;break;
            case '(':
            case ')':peek=0;break;
        }
        Boolean result = false;
        if (peek >= cur) {
            result = true;
        }
        return result;
    }

    //简单运算处理
    private String calculate(String firstValue, String secondValue, char currentOp) {
        String result = "";
        switch (currentOp) {
            case '+':
                result = String.valueOf(Double.valueOf(firstValue) + Double.valueOf(secondValue));
                break;
            case '-':
                result = String.valueOf(Double.valueOf(firstValue) - Double.valueOf(secondValue));
                break;
            case '*':
                result = String.valueOf(Double.valueOf(firstValue) * Double.valueOf(secondValue));
                break;
            case '/':
                result = String.valueOf(Double.valueOf(firstValue) / Double.valueOf(secondValue));
                break;
            case '$'://开方
                result = String.valueOf(Math.pow(Double.valueOf(secondValue), (double)1/Double.valueOf(firstValue)));
                break;
            case '^':
                result = String.valueOf(Math.pow(Double.valueOf(firstValue),Double.valueOf(secondValue)));
                break;
            case '!':
                result = String.valueOf(factorial(Integer.valueOf(firstValue)));
                break;
            case '%':
                result = String.valueOf(Double.valueOf(firstValue) % Double.valueOf(secondValue));
                break;
        }
        return result;
    }
    //阶乘计算
    private static long factorial(long number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }
    //对于一些符号的转换
    private static String format_changer(String formula){
        return formula.replace("√","$").replace("!","!0").replace("×","*").replace("π",String.valueOf(Math.PI));
    }
    //三角函数计算
    private static String triangle_cal(String formula){
        if(formula.contains("sin")){
           return formula.replace("sin(" + getSubString(formula,"sin(",")") + ")",String.valueOf(Math.sin(Double.valueOf(conversion(getSubString(formula,"sin(",")"))))));
        }
        if(formula.contains("cos")){
            return formula.replace("cos(" + getSubString(formula,"cos(",")") + ")",String.valueOf(Math.cos(Double.valueOf(conversion(getSubString(formula,"cos(",")"))))));
        }
        if(formula.contains("tan")){
            return formula.replace("tan(" + getSubString(formula,"tan(",")") + ")",String.valueOf(Math.tan(Double.valueOf(conversion(getSubString(formula,"tan(",")"))))));
        }
        return formula;
    }
    //取中间文本
    public static String getSubString(String text, String left, String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }
    //转二进制
    public static String toBin(String num){
        try{
            return Integer.toBinaryString((int) Math.round(Double.valueOf(num)));
        }catch (Exception e){
            return "NaN";
        }
    }
    //转八进制
    public static String toOct(String num){
        try{
            return Integer.toOctalString((int) Math.round(Double.valueOf(num)));
        }catch (Exception e){
            return "NaN";
        }
    }
    //转16进制
    public static String toHex(String num){
        try{
            return Integer.toHexString((int) Math.round(Double.valueOf(num)));
        }catch (Exception e){
            return "NaN";
        }
    }
}
