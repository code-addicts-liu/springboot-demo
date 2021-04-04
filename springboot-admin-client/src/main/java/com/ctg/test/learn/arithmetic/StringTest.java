package com.ctg.test.learn.arithmetic;

import java.util.regex.Pattern;

/**
 * @author liuyue
 * @date 2021/3/30 13:37
 * @Description:
 */
public class StringTest {
    /**
     * 第一个斜杠是转义符，第二个斜杠是斜杠本身，第三个斜杠又是转义符，第四个斜杠是斜杠本身。
     * 在 Java 中，输出 "\n" 字符串需要两个反斜杠和一个 'n'，在 Java 的正则表达式中，要给这两个反斜杠分别再分配一个反斜杠进行转义，才能生效。
     * 总而言之，记住一句话：Java 正则表达式中，匹配一个反斜杠要用四个反斜杠！
     *
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println("\");
        System.out.println("\\");
        System.out.println("\n");//换行了
        System.out.println("-----");
        System.out.println("\\\\n");
        replaceStr();
    }

    /**
     * 第一个斜杠是转义符，第二个斜杠是斜杠本身，第三个斜杠又是转义符，第四个斜杠是斜杠本身。
     * 替换字符\n  用正则表达式需要四个下划线
     */
    public static void replaceStr() {
        String regex = "\\\\n";
        String inputStr = "select * \\n from a";
        String replacement = "";
        String resultStr = Pattern.compile(regex).matcher(inputStr).replaceAll(replacement);
        System.out.println(resultStr);
    }
}

