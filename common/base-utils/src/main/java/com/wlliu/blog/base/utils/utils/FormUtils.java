package com.wlliu.blog.base.utils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 字符串操作工具类
 *
 * @author qy
 * @since 1.0
 */
public class FormUtils {

    /**
     * 手机号验证
     */
    public static boolean isMobile(String str) {
        // 验证手机号
        Pattern p = compile("^[1][3,4,5,7,8,9][0-9]{9}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
