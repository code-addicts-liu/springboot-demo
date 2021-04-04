package com.ctg.test.utils;


import org.springframework.util.StringUtils;


public class MoneyFormatUitl {

    /**
     * 分转元<br>
     * 适用场景: <br>
     * 调用方式: <br>
     * 业务逻辑说明<br>
     */
    public static String changeF2Y(String fenStr) {
        String yuanStr = "";

        if (StringUtils.isEmpty(fenStr)) {
            return "";
        }

        if (!fenStr.matches("\\-?[0-9]+")) {
            return "0.00";
        }

        int flag = 0;
        String amString = fenStr.trim();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                // if ((i - 1) % 3 == 0 && i != 1) {
                // result.append(",");
                // }
                result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
            }
            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            yuanStr = "-" + result.toString();
        } else {
            yuanStr = result.toString();
        }

        return yuanStr;

    }

    /**
     * 分转元，带千分位表示 <br>
     * 适用场景: <br>
     * 调用方式: <br>
     * 业务逻辑说明<br>
     */
    public static String changeF2YT(String fenStr) {
        String yuanStr = "";

        if (!fenStr.matches("\\-?[0-9]+")) {
            return "0.00";
        }

        int flag = 0;
        String amString = fenStr.trim();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    result.append(",");
                }
                result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
            }
            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            yuanStr = "-" + result.toString();
        } else {
            yuanStr = result.toString();
        }

        return yuanStr;

    }

    /**
     * 元转分 <br>
     * 适用场景: <br>
     * 调用方式: <br>
     * 业务逻辑说明<br>
     */
    public static Long parseYuanToFen(String yunaStr) {
        if (StringUtils.isEmpty(yunaStr)) {
            return 0L;
        } else {
            String currency = yunaStr.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥ 或者$的金额
            int index = currency.indexOf(".");
            int length = currency.length();
            Long amLong = 0l;
            if (index == -1) {
                amLong = Long.valueOf(currency + "00");
            } else if (length - index >= 3) {
                amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
            } else if (length - index == 2) {
                amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
            } else {
                amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
            }
            return amLong;
        }
    }

    /**
     * @description:厘转分
     */
    public static String li2fen(String li) {
        if (StringUtils.isEmpty(li) || "0".equalsIgnoreCase(li) || li.length() <= 1) {
            return "0";
        } else {
            String fen = li.substring(0, li.length() - 1);
            return fen;
        }
    }

    /**
     * @description:分转厘
     */
    public static String fen2li(String fen) {
        if (StringUtils.isEmpty(fen) || "0".equalsIgnoreCase(fen)) {
            return "0";
        } else {
            String li = fen + "0";
            return li;
        }
    }


}
