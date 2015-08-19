package pers.magee.emap.core.util;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	
	private static final String EMAIL_PATTERN = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return 如果是空则返回false，如果不为空则返回true
	 */
	public static boolean isNotNull(String str) {
		if (str == null) {
			return false;
		}
		if (str.trim().length() < 1) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return 如果是空则返回true，如果不为空则返回false
	 */
	public static boolean isNull(String str) {
		return !isNotNull(str);
	}

	/**
	 * 判断字符串是否全部为数字组成
	 * 
	 * @param str
	 * @return 如果是则返回true，如果不是则返回false
	 */
	public static boolean isDigit(String str) {
		if (str == null) {
			return false;
		}
		char[] ca = str.toCharArray();
		for (int i = 0; i < ca.length; i++) {
			if (!Character.isDigit(ca[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否是日期格式，通过pattern进行日期格式的设置，默认是YYYYMMDD
	 * @param str  需要判断的字符串
	 * @param pattern  日期的转换格式
	 * @return 如果转换成功则是true，转换异常或者str为null则为false
	 */
	public static boolean isDate(String str, String pattern) {
		if (str == null) {
			return false;
		}
		if (pattern == null) {
			pattern = "YYYYMMDD";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try{
			sdf.parse(str);
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断字符串是否符合正则表达式的匹配要求
	 * @param str 需要判断的字符串
	 * @param pattern 正则表达式的字符串
	 * @return 匹配则是true，不匹配则是false
	 */
	public static boolean isMatcher(String str,String pattern){
		if(str==null){
			return false;
		}
		Pattern pt = Pattern.compile(pattern);
		Matcher mc = pt.matcher(str);
		return mc.matches();
	}
	
	

}
