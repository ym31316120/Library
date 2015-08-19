package pers.magee.emap.core.util;

import java.util.StringTokenizer;

/**
 * 提供字符串操作的公共方法
 * 
 * @author Magee_yang
 *
 */

public class StringUtil {

	/**
	 * 把Object对象转换成Int整型并返回
	 * 
	 * @param obj
	 *            用于转换的对象
	 * @param def
	 *            如果转换过程异常，则默认返回def值
	 * @return 返回Int整型
	 */
	public static int getInt(Object obj, int def) {
		try {
			return Integer.parseInt(obj.toString().trim());
		} catch (Exception e) {
		}
		return def;
	}

	/**
	 * 把Object对象转换成Int整型并返回
	 * 
	 * @param obj
	 *            用于转换的对象
	 * @return 返回Int整型；如果obj为null或者转换异常则默认返回-1
	 */
	public static int getInt(Object obj) {
		if (obj == null) {
			return -1;
		}
		return getInt(obj.toString(), -1);
	}

	/**
	 * 把Object对象转换成long长整型并返回
	 * 
	 * @param obj
	 *            用于转换的对象
	 * @param def
	 *            如果转换过程异常，则默认返回def值
	 * @return 返回long长整型
	 */
	public static long getLong(Object obj, long def) {
		try {
			return Long.parseLong(obj.toString().trim());
		} catch (Exception e) {
		}
		return def;
	}

	/**
	 * 把Object对象转换成long长整型并返回
	 * 
	 * @param obj
	 *            用于转换的对象
	 * @return 返回long长整型；如果obj为null或者转换异常则默认返回-1L
	 */
	public static long getLong(Object obj) {
		if (obj == null) {
			return -1L;
		}
		return getLong(obj.toString(), -1L);
	}

	/**
	 * 把Object对象转换成boolean布尔型并返回
	 * 
	 * @param obj
	 *            用于转换的对象
	 * @param def
	 *            如果转换过程异常，则默认返回def值
	 * @return 返回boolean布尔型
	 */
	public static boolean getBoolean(Object obj, boolean def) {
		try {
			return Boolean.parseBoolean(obj.toString().trim());
		} catch (Exception e) {
		}
		return def;
	}

	/**
	 * 把Object对象转换成boolean布尔型并返回
	 * 
	 * @param obj
	 *            用于转换的对象
	 * @return 返回boolean布尔型；如果obj为null或者转换异常则默认返回false
	 */
	public static boolean getBoolean(Object obj) {
		if (obj == null) {
			return false;
		}
		return getBoolean(obj.toString(), false);
	}

	/**
	 * 把Object对象转换成double双精度浮点型并返回
	 * 
	 * @param obj
	 *            用于转换的对象
	 * @param def
	 *            如果转换过程异常，则默认返回def值
	 * @return 返回double双精度浮点型
	 */
	public static double getDouble(Object obj, double def) {
		try {
			return Double.parseDouble(obj.toString().trim());
		} catch (Exception e) {
		}
		return def;
	}

	/**
	 * 把Object对象转换成double双精度浮点型并返回
	 * 
	 * @param obj
	 *            用于转换的对象
	 * @return 返回double双精度浮点型；如果obj为null或者转换异常则默认返回0.0D
	 */
	public static double getDouble(Object obj) {
		if (obj == null) {
			return 0.0D;
		}
		return getDouble(obj.toString(), 0.0D);
	}

	/**
	 * 通过判断传入参数是否是YES或者NO进行boolean布尔型返回
	 * 
	 * @param str
	 *            用于判断的字符串
	 * @return yes true / no false
	 */
	public static boolean getBooleanFromYesNo(String str) {
		if (str != null && str.equalsIgnoreCase(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 提供字符串转换成字符串数组的方法,以sStr作为分隔符进行切分; 如果sStr传递的null或者""则系统默认用空格进行分隔处理
	 * 
	 * @param tStr
	 *            原字符串
	 * @param sStr
	 *            作为分隔符的字符串
	 * @return 返回切分好的字符串数组
	 */
	public static String[] StrToStrs(String tStr, String sStr) {
		String[] reStrs = null;
		StringTokenizer st = null;
		if (tStr != null) {
			if (sStr == null || "".equals(sStr)) {
				st = new StringTokenizer(tStr);
			} else {
				st = new StringTokenizer(tStr, sStr);
			}
			reStrs = new String[st.countTokens()];
			int n = 0;
			while (st.hasMoreTokens()) {
				reStrs[n] = st.nextToken();
				n++;

			}
			return reStrs;
		} else {
			return new String[0];
		}
	}

	/**
	 * 提供字符串数组转换成字符串的方法,以sStr作为分隔符进行合并; 如果sStr传递的null则系统默认用空格进行合并
	 * 
	 * @param tStrs
	 *            原字符串数组
	 * @param sStr
	 *            作为分隔符的字符串
	 * @return 返回合并好的字符串
	 */
	public static String StrsToStr(String[] tStrs, String sStr) {
		String reStr = "";
		if (sStr == null) {
			sStr = " ";
		}
		if (tStrs != null) {
			int len = tStrs.length;
			if (len > 0) {
				if (tStrs[0] != null) {
					reStr = tStrs[0];
				}
			}
			for (int i = 1; i < len; i++) {
				if (tStrs[i] != null) {
					reStr += sStr + tStrs[i];
				}
			}
		}
		return reStr;
	}

	/**
	 * 提供把数字转换成中文大写方式的字符串
	 * 
	 * @param a
	 *            代表要转换的数字
	 * @return 返回中文大写的字符串
	 * 
	 */
	public static String TranslateToChinese(int a) {
		// String[] units = { "", "十", "百", "千", "万", "十", "百", "千", "亿", "十",
		// "百", "千", "兆" };
		// String[] nums = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
		String[] c_units = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" };
		String[] c_nums = { "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String result = "";
		if (a < 0) {
			result = "负";
			a = Math.abs(a);
		}
		String t = String.valueOf(a);
		for (int i = t.length() - 1; i >= 0; i--) {
			int r = (int) (a / Math.pow(10, i));
			if (r % 10 != 0) {
				String s = String.valueOf(r);
				String l = s.substring(s.length() - 1, s.length());
				result += c_nums[Integer.valueOf(l) - 1];
				result += c_units[i];
			} else {
				if (i == 0) {
					if (result.endsWith("零")) {
						result = result.substring(0, result.length() - 1);
					}
					result += c_units[i];
				} else {
					if (!result.endsWith("零")) {
						result += "零";
					} else {
						if ((i) % 4 == 0) {
							result = result.substring(0, result.length() - 1);
							result += c_units[i];
						}
					}
				}
			}
		}
		return result;
	}

}
