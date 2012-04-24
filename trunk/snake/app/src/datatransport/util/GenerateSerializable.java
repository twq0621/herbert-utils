package datatransport.util;

import java.util.Date;


/**
 * 生成跨服对象的序列化代码.
 * 
 * @@author serv_dev.
 * @version: 1.0
 * @Create at: 2011-7-4 下午09:52:44
 */
public class GenerateSerializable {

//	/**
//	 * @param args
//	 */
//	@SuppressWarnings("rawtypes")
//	public static void main(String[] args) {
//		Class[] cls = { AccountTransportData.class, AcrossIncomeTransportData.class, ChannelZhenlongTransportData.class, CharacterTransportData.class, BowTransportData.class,
//				CharacterBuffTransportData.class, GoodsTransportData.class, HiddenWeaponTransportData.class, CharacterHorseTransportData.class, CharacterLiantiTransportData.class,
//				CharacterOnHoorConfigTransportData.class, CharacterSkillTransportData.class, CharacterTeamfightingTransportData.class, CharacterWeddingringTransportData.class,
//				CharacterDanTianTransportData.class };
//		for (Class cl : cls) {
//			for (Method md : cl.getMethods()) {
//				if (md.getName().split("get").length > 1) {
//					String s = md.getName().split("get")[1];
//					String ss = s.substring(0, 1).toLowerCase() + s.substring(1);
//					if (!ss.equals("class")) {
//						System.out.println("out.write" + str1(md.getReturnType()) + "(this." + ss + isString(md.getReturnType(), s.substring(0, 1).toLowerCase() + s.substring(1))
//								+ ");");
//					}
//				}
//			}
//			for (Method md : cl.getMethods()) {
//				if (md.getName().split("get").length > 1) {
//					String s = md.getName().split("get")[1];
//					if (!s.equals("Class")) {
//						String ss = s.substring(0, 1).toLowerCase() + s.substring(1);
//						System.out.println("this." + ss + " = " + str2(md.getReturnType()) + "in.read" + str1(md.getReturnType()) + "()" + isBoolean(md.getReturnType(), ss) + ";");
//						if (md.getReturnType().equals(Integer.class)) {
//							System.out.println("this." + ss + " = this." + ss + " == (-2^31) ? null : this." + ss + ";");
//						}
//						if (md.getReturnType().equals(String.class)) {
//							System.out.println("this." + ss + " = this." + ss + ".equals(\"NaN\") ? null : this." + ss + ";");
//						}
//					}
//				}
//			}
//		}
//	}

	private static String isBoolean(Class<?> t, String ss) {
		if (t.equals(Boolean.class))
			return ")";
		return "";
	}

	@SuppressWarnings("unused")
	private Boolean cv(byte b) {
		if (b == 0) {
			return false;
		} else if (b == 1) {
			return true;
		}
		return null;
	}

	private static String isString(Class<?> t, String s) {
		if (t.equals(String.class))
			return " == null ? \"NaN\" : this." + s;
		if (t.equals(Integer.class))
			return " == null ? (-2^31) : this." + s;
		if (t.equals(Boolean.class))
			return " == null ? 2 : this." + s + "== true ? 1 : 0";
		return "";
	}

	private static String str2(Class<?> t) {
		if (t.equals(Date.class)) {
			return "(Date)";
		}
		if (t.equals(Boolean.class)) {
			return "cv(";
		}
		return "";
	}

	@SuppressWarnings("rawtypes")
	private static String str1(Class t) {
		if (t.equals(Integer.class)) {
			return "Int";
		}
		if (t.equals(String.class)) {
			return "UTF";
		}
		if (t.equals(Date.class)) {
			return "Object";
		}
		if (t.equals(Boolean.class)) {
			return "Byte";
		}

		return t.getSimpleName();
	}

}
