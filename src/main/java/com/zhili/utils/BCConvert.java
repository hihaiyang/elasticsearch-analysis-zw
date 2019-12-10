package com.zhili.utils;

public class BCConvert {

//    private static final char QJ_CHAR_LOWER_A = 'ａ';
//    private static final char QJ_CHAR_LOWER_Z = 'ｚ';
//    private static final char QJ_CHAR_UPPER_A = 'Ａ';
//    private static final char QJ_CHAR_UPPER_Z = 'Ｚ';
//    private static final char QJ_CHAR_UPPER_0 = '０';
//    private static final char QJ_CHAR_UPPER_9 = '９';

    //全角转半角
    public static char quanjiaoToBanjiao(char c) {
        if (((65345 <= c) && (c <= 65370)) ||
                ((65313 <= c) && (c <= 65338)) || (
                (65296 <= c) && (c <= 65305))) {
            return (char) (c - 65248);
        }
        return c;
    }

    /**
     * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
     */
    static final char SBC_SPACE = 12288; // 全角空格

    /**
     * 半角空格的值，在ASCII中为32(Decimal)
     */
    static final char DBC_SPACE = ' '; // 半角空格

    /**
     * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
     */
    static final int CONVERT_STEP = 65248; // 全角半角转换间隔

    /**
     * 半角转全角
     *
     * @param input
     *            String.
     * @return {String} 全角字符串.
     */
    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for ( int i = 0; i < c. length; i++) {
            if (c[i] == DBC_SPACE) {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = ( char) (c[i] + CONVERT_STEP);
            }
        }
        return new String(c);
    }

    /**
     * 全角转半角
     *
     * @param input
     *            String.
     * @return {String} 半角字符串
     */
    public static String ToDBC(String input) {
        char c[] = input.toCharArray();
        for ( int i = 0; i < c. length; i++) {
            if (c[i] == '\u3000') {
                c[i] = DBC_SPACE;
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = ( char) (c[i] - CONVERT_STEP);
            }
        }
        return new String(c);
    }

    /**
     * 全角字符转半角字符
     * @param c
     * @return {Char}
     */
    public static char ToDBC(char c) {
        if (c == '\u3000') {
            return DBC_SPACE;
        } else if (c > '\uFF00' && c < '\uFF5F') {
            return (char)(c - CONVERT_STEP);
        }
        return c;
    }

    /**
     * 转标准字符
     * @param str
     * @return {String} 中文\大小写字母\数字
     */
    public static String toStandard(String str) {
        if (null == str || str.trim().length()==0 || str.trim().toLowerCase().equals("null")) {
            return "";
        }
        String regex = "[^\\u4e00-\\u9fa50-9a-zA-Z]";
        return ToDBC(str).replaceAll(regex, "");
    }
}
