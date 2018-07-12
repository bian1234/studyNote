package com.byk.Amap.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:处理字符串中包含的特殊符号
 * @author:ykbian
 * @date:2018/7/12 23:55
 */


@Component
public class StringUtil {

    /**
     * 提取字符串中的数字
     *
     * @param number
     * @return
     * @throws Exception
     */
    public static  String numberIntercept(String number) throws Exception {

        return Pattern.compile("[^0-9]").matcher(number).replaceAll("");

    }

    /**
     * 提取字符串中所有的汉字
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static  String intercept(String str) throws Exception {
        String regex = "[\u4E00-\u9FA5]";//汉字
        Matcher matcher = Pattern.compile(regex).matcher(str);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            sb.append(matcher.group());
        }

        return sb.toString();
    }

    /**
     * 过滤设置的特殊符号
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static  String filtration(String str) throws Exception {
        String regEx = "[`~!@#$%^&*()+=|{}:;\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        return Pattern.compile(regEx).matcher(str).replaceAll("").trim();
    }


}
