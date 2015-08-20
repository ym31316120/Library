package pers.magee.emap.core.util;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

	private static final String EMAIL_PATTERN = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	private static final String IPADDRESS_PATTERN = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
	private static final String PHONE_NUMBER = "/^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\\d{8}$)/";
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
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @param pattern
	 *            日期的转换格式
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
		try {
			sdf.parse(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串是否符合正则表达式的匹配要求
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @param pattern
	 *            正则表达式的字符串
	 * @return 匹配则是true，不匹配则是false
	 */
	public static boolean isMatcher(String str, String pattern) {
		if (str == null) {
			return false;
		}
		if (pattern == null) {
			throw new IllegalArgumentException();
		}
		Pattern pt = Pattern.compile(pattern);
		Matcher mc = pt.matcher(str);
		return mc.matches();
	}

	/**
	 * 判断字符串是否是邮箱地址的格式
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 如果是则返回true，如果不是则返回false
	 */
	public static boolean isEmail(String str) {
		return isMatcher(str, EMAIL_PATTERN);
	}
	/**
	 * 判断字符串是否是IP地址的格式
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 如果是则返回true，如果不是则返回false
	 */
	public static boolean isIpAddress(String str) {
		return isMatcher(str, IPADDRESS_PATTERN);
	}
	/**
	 * 判断字符串是否是电话号码或者手机号码
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 如果是则返回true，如果不是则返回false
	 */
	public static boolean isPhoneNumber(String str) {
		return isMatcher(str, PHONE_NUMBER);
	}
	/**
	 * 判断字符串数组strs中是否包含字符串str
	 * @param strs 字符串数组
	 * @param str 字符串
	 * @param isCase 是否进行大小写区分，true为区分，false为不区分
	 * @return 如果包含则返回true，如果不包含则返回false
	 */
	public static boolean isContains(String[] strs, String str, boolean isCase) {
		if (str != null && str.length() > 0) {
			if (strs != null && strs.length > 0) {
				for (String s : strs) {
					if (isCase) {
						if (str.equals(s)) {
							return true;
						}
					} else {
						if (str.equalsIgnoreCase(s)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * 判断字符串的长度是否在设置的参数范围内，>=和<=的关系
	 * @param str 需要判断的字符串
	 * @param min 字符串的最小长度
	 * @param max 字符串的最大长度
	 * @return 如果符合则返回true，如果不符合则返回false
	 */
	public static boolean isLengthBetwwen(String str,int min,int max){
		if(str == null){
			return false;
		}
		if(min>max){
			throw new IllegalArgumentException();
		}
		return (str.length()>=min)&&(str.length()<=max);
	}

}
