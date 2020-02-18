package com.pci.hjMos.common.utils;

import java.text.NumberFormat;

/**
 * double转百分数
 * @author 曾乐平
 *
 */
public class DoubleToPercentformat {
	/**
	 * 
	 * @param d
	 * @param IntegerDigits
	 * @param FractionDigits
	 * @return String
	 */
	public static String getPercentFormat(double d, int FractionDigits) {
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(FractionDigits);// 小数点后保留几位
		String str = nf.format(d);
		return str;
	}

	public static void main(String[] args) {
		System.out.println(getPercentFormat(0.99999, 2));
	}
	
}
