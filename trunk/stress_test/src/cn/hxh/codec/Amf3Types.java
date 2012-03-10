package cn.hxh.codec;

public abstract interface Amf3Types {
	public static final int kUndefinedType = 0;
	public static final int kNullType = 1;
	public static final int kFalseType = 2;
	public static final int kTrueType = 3;
	public static final int kIntegerType = 4;
	public static final int kDoubleType = 5;
	public static final int kStringType = 6;
	public static final int kXMLType = 7;
	public static final int kDateType = 8;
	public static final int kArrayType = 9;
	public static final int kObjectType = 10;
	public static final int kAvmPlusXmlType = 11;
	public static final int kByteArrayType = 12;
	public static final String EMPTY_STRING = "";
	public static final int UINT29_MASK = 536870911;
	public static final int INT28_MAX_VALUE = 268435455;
	public static final int INT28_MIN_VALUE = -268435456;
}