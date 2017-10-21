package com.edwin.spring.web.jvm.remote;

/**
 * 修改Class文件，暂时只提供修改常量池的功能
 * 
 * @author caojunming
 * @data 2017年10月22日 上午12:57:24
 */
public class ClassModifier {

	/**
	 * Class文件中常量池的起始偏移
	 */
	private static final int CONSTANT_POOL_COUNT_INDEX = 8;

	/**
	 * CONSTANT_Utf8_info常量的tag标志
	 */
	private static final int CONSTANT_UTF8_INFO = 1;

	/**
	 * 常量池中11种常量所占的长度，CONSTANT_Utf8_info型常量除外，因为它不是定长的
	 */
	private static final int[] CONSTANT_ITEM_LENGTH = { -1, -1, -1, 5, 5, 9, 9,
			3, 3, 5, 5, 5, 5 };

	private static final int U1 = 1;
	private static final int U2 = 2;

	private byte[] classByte;

	public ClassModifier(byte[] classByte) {
		this.classByte = classByte;
	}

	/**
	 * 修改常量池中CONSTANT_Utf8_info常量的内容
	 * 
	 * @param oldStr 修改前的字符串
	 * @param newStr 修改后的字符串
	 * @return 修改结果
	 */
	public byte[] modifyUTF8Constant(String oldStr, String newStr) {
		int cpc = getConstantPoolCount();
		int offset = CONSTANT_POOL_COUNT_INDEX + U2;
		for (int i = 0; i < cpc; i++) {
			int tag = ByteUtils.bytes2Int(classByte, offset, U1);
			if (tag == CONSTANT_UTF8_INFO) {
				int len = ByteUtils.bytes2Int(classByte, offset + U1, U2);
				offset += (U1 + U2);
				String str = ByteUtils.bytes2String(classByte, offset, len);
				if (str.equalsIgnoreCase(oldStr)) {
					byte[] strBytes = ByteUtils.string2Bytes(newStr);
					byte[] strLen = ByteUtils.int2Bytes(newStr.length(), U2);
					classByte = ByteUtils.bytesReplace(classByte, offset - U2,
							U2, strLen);
					classByte = ByteUtils.bytesReplace(classByte, offset, len,
							strBytes);
					return classByte;
				} else {
					offset += len;
				}
			} else {
				offset += CONSTANT_ITEM_LENGTH[tag];
			}
		}
		return classByte;
	}

	/**
	 * 获取常量池种常量的数量
	 * 
	 * @return
	 */
	public int getConstantPoolCount() {
		return ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, U2);
	}
}
