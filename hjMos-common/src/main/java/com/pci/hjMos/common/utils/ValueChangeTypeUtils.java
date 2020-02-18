package com.pci.hjMos.common.utils;


import cn.hutool.core.comparator.CompareUtil;
import com.pci.hjMos.common.constants.ValueChangeTypeConstants;

public class ValueChangeTypeUtils {

	/**
	 * 比较两个参数的大小并返回变动的类型, 需要类型实现Comparable接口
	 * 第一个参数是目标值, 第二个参数是基准值, 当第一个参数大于第二个参数时返回上升或增长
	 * @param i1 待比较的目标值
	 * @param i2 被比较的基准值
	 * @return i1 与 i2 比较结果的变化方式
	 */
	public static <T extends Comparable<? super T>> String compare(T i1, T i2) {
		int rst = CompareUtil.compare(i1, i2);
		if (rst == 0) {
			return ValueChangeTypeConstants.EQ;
		} else if (rst > 0) {
			return ValueChangeTypeConstants.UP;
		} else {
			return ValueChangeTypeConstants.DOWN;
		}
	}
}
