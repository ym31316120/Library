package pers.magee.emap.core.util;

public class ValidateUtil {

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

}
