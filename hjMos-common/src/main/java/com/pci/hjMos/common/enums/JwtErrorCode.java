package com.pci.hjMos.common.enums;


/**
 * Jwt 错误信息枚举类
 *
 * @class_name JwtErrorCode
 * @author lsk
 * @date 2019-05-30
 */
public enum JwtErrorCode{
	/**
     * 成功
     */
    SUCCESS(0, "执行成功"),
	/**
     * 令牌失效
     */
    EXPIRED(-1, "令牌失效"),
	/**
     * 令牌解析失败
     */
    PARSE_FAILED(-2, "令牌解析失败");

    private final long code;
    private final String msg;

    JwtErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static JwtErrorCode fromCode(long code) {
    	JwtErrorCode[] ecs = JwtErrorCode.values();
        for (JwtErrorCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

	public long getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	@Override
    public String toString() {
        return String.format(" JwtErrorCode:{code=%s, msg=%s} ", code, msg);
    }

}
