package com.hoau.hoaureport.module.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 用于处理字符串
 * @atuhor liuwenlin
 */
public class StringUtils {
    /**
     * 将传入的可变参数src加密处理
     * @param src 明文字符串参数
     * @return 加密后的字符串结果
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String src) {

        MessageDigest md;
        byte[] bytes;
        try {
            md = MessageDigest.getInstance("MD5");
            bytes = md.digest(src.getBytes("UTF-8"));
            return toHexString(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
            return "";
        }
    }

    /**
     * 将传入bytes转化为16进制的字符
     * @param bytes
     * @return 返回大写的16进制字符串
     */
    private static String toHexString(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString().toUpperCase();
    }

    /**
     * 将可变参数字符串数组转化为普通字符串
     * @param src
     * @return 返回处理后的字符串
     */
    public static String toStringSeq(String... src) {
        String[] str = src;
        //先按照首字母大小排序
        strOrderByCapitalLetter(str);
        //将传入的可变参数追加到StringBuilder中
        StringBuilder sb = new StringBuilder();
        for(String s : str){
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 将传入字符串数组按照首字母大小排序（区分大小写）
     * @param src
     * @return 返回排序后的字符串数据
     */
    private static String[] strOrderByCapitalLetter(String[] src) {
        String[] str = src;
        Arrays.sort(str, new Comparator<String>(){

            public int compare(String str1, String str2) {
                char c1 = str1.toCharArray()[0];
                char c2 = str2.toCharArray()[0];
                return c1 - c2;
            }

        });
        return str;
    }

    /**
     * 格式化json
     *
     * @param jsonStr
     * @return
     * @author liuwenlin
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr))
            return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '"':
                    if (last != '\\'){
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     *
     * @param sb
     * @param indent
     * @author liuwenlin
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
