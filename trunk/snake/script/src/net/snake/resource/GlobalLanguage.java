package net.snake.resource;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.script.IGlobalLanguage;





/**
 * 脚本中文国际化类
 * <p>
 * 国际化类使用注意事项
 * 对应gamedata数据库中t_language_script表，变量名称为key，对应数据为value，读取valuei18n列
 * 例：
 * public static String instanceBangbailiOpenStr = "通往下一营的传送点已经开启";
 * 修改为：
 * 声明变量
 * public static String instanceBangbailiOpenStr;
 * 填充数据库数据
 * f_key : instanceBangbailiOpenStr(与变量名保持一致)
 * f_value ： 通往下一营的传送点已经开启
 * f_valuei18n  ： 通往下一营的传送点已经开启(程序加载列)
 * </p>
 * @author serv_dev
 */
public class GlobalLanguage implements IGlobalLanguage{
	
private static Logger logger = Logger.getLogger(GlobalLanguage.class);

	public static String instanceBangbailiOpenStr;
	public static String instanceLianzhanStr;
	// $符号表示参数
	public static String instanceLianzhanKillBossStr;
	public static String instanceTianguanEnterNextStr;
	public static String instanceTianguanGoodStr;
	public static String instanceTianguanGood1Str;
	public static String instanceTianguanEnterStr;

	public static String Baiyuanwang_zhuxuanStr;

	public static String goodUse3400Str;
	public static String goodUse7033Str;
	public static String goodUse7043Str;
	public static String goodUse3401Str;
	public static String FuZiZhenEnterTip;
	public static String FuZiZhenIdiomsTip;
	public static String FuZiZhenBossTip;
	public static String FuZiZhenBoss;
	public static String FuZiZhenTip;
	public static String FuZiZhenBoosCrt;
	public static String FuZiZhenNpcTip;

	public static String LiangYiEnterTip;
	public static String LiangYiEnterTip1;
	public static String LiangYiEnterTip3;
	public static String MiZongEnterTip;
	public static String MiZongEnterTip1;
	public static String SiShengShouEnterTip;
	
	public static String JianlaoStart;
	public static String JianlaoSuc;
	public static String JianlaoFail;
	
	public static String DujieStart;
	public static String BossAlert;
	public static String OutTeam;
	public static String LifeCycle;
	public static String Lunhui;
	
	
	
	/**国际化未翻译此字段*/
	public static String str_idioms[] = { "天地玄黄宇宙洪荒", "白日依山尽黄河入海流",
			"生当做人杰死亦为鬼雄", "溯游从之宛在水中央", "沧浪之水清兮可以濯吾缨", "长亭外古道边芳草碧连天",
			"古道西风瘦马小桥流水人家", "今宵酒醒何处杨柳岸晓风残月", "人生得意须尽欢莫使金樽空对月",
			 "关关雎鸠在河之洲窈窕淑女君子好逑", "何以解忧唯有杜康", "笔落惊风雨诗成泣鬼神","苟利国家生死以岂因祸福避趋之",
			"大漠孤烟直长河落日圆", "野火烧不尽春风吹又生", "本是同根生相煎何太急", "夕阳无限好只是近黄昏",
			"人生自古谁无死留取丹心照汗青", "在天愿作比翼鸟在地愿为连理枝", "长风破浪会有时直挂云帆济沧海",
			"尔曹身与名俱灭不废江河万古流", "但使龙城飞将在不教胡马度阴山", "江山代有才人出各领风骚数百年",
			"身无彩凤双飞翼心有灵犀一点通", "商女不知亡国恨隔江犹唱后庭花", "春潮带雨晚来急野渡无人舟自横",
			"战士军前半死生美人帐下犹歌舞", "借问酒家何处有牧童遥指杏花村", "莫愁前路无知己天下谁人不识君",
			"黄沙百战穿金甲不破楼兰终不还", "王师北定中原日家祭无忘告乃翁", "风吹细柳千条线日照龙鳞万点金",
			"桃李春风一杯酒江湖夜雨十年灯", "黑发不知勤学早白首方悔读书迟", "一万年来谁著史八千里外觅封侯" };

	/**
	 * 参数字符串国际化转换
	 * 
	 * @param str
	 * @param args
	 * @return
	 */
	public static String exChangeParamToString(String str, String... args) {
		String newstr = str;
		for (int i = 0; i < args.length; i++) {
			newstr = newstr.replace("$" + i, args[i]);
		}
		return newstr;
	}
	
	public void initData(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			logger.error("gamescript GlobalLanguage initdata error ...");
			return;
		}
		
		Field[] fields = GlobalLanguage.class.getDeclaredFields();
		if (fields == null || fields.length <= 0) {
			logger.error("gamescript GlobalLanguage initdata error ...");
			return;
		}
		for (Field field : fields) {
			if (map.containsKey(field.getName())) {
				try {
					field.set(null, map.get(field.getName()));
				} catch (Exception e) {
					logger.error("gamescript GlobalLanguage initdata error ...");
					logger.error(e.getMessage(),e);
				}
			}
		}
	}
}
