package net.snake.gamemodel.heroext.channel.logic;

import java.util.List;

import net.snake.commons.StringUtil;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Breakthrough;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.channel.bean.Channel;
import net.snake.gamemodel.heroext.channel.persistence.ChannelManager;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50210;

public class ChannelJiSuanTools {

	// 阳维脉：24个穴位（编号101-200）
	// 阴维脉：16个穴位（编号201-300）
	// 阳跷脉：23个穴位（编号301-400）
	// 阴跷脉：18个穴位（编号401-500）
	// 带脉：19个穴位（编号501-600）
	// 冲脉：24个穴位（编号601-700）
	// 任脉：24个穴位（编号701-800）
	// 督脉：24个穴位（编号801-900）
	// 8个斤脉每个斤脉第一个表示这条斤脉学习完了例101，201，302(这个就是学完2条斤脉第3条是第1个穴位)
	//
	/** 现在有八条斤脉 */
	private int countjingmaishu = 8;//
	private String jingmai;

	public ChannelJiSuanTools(String channelJingmai) {
		if (StringUtil.isEmpty(channelJingmai)) {
			channelJingmai = "";
		}
		this.jingmai = channelJingmai;
	}

	public String getJingmai() {
		return jingmai;
	}

	public void setJingmai(String jingmai) {
		this.jingmai = jingmai;
	}

	/** 打通的筋脉的数量 **/
	public int getCountDaTongJinmai() {
		int count = 0;
		// 因为一共8条所以循环8次看看人物打通几条斤脉
		String shuString = "01";
		for (int i = 1; i < countjingmaishu + 1; i++) {
			String srt = String.valueOf(i) + shuString;
			if (jingmai.indexOf(srt) != -1) {
				count++;
			}
		}
		return count;
	}

	public int getCountDaTongJinmai2(String id) {
		int count = 0;
		// 因为一共8条所以循环8次看看人物打通几条斤脉
		String shuString = "01";
		for (int i = 1; i < countjingmaishu + 1; i++) {
			String srt = String.valueOf(i) + shuString;
			if (id.indexOf(srt) != -1) {
				count++;
			}
		}
		return count;
	}

	public String getguantongjinmai() {
		StringBuilder sb = new StringBuilder();
		// 因为一共8条所以循环8次看看人物打通几条斤脉
		String shuString = "01";
		for (int i = 1; i < countjingmaishu + 1; i++) {
			String srt = String.valueOf(i) + shuString;
			if (jingmai.indexOf(srt) != -1) {
				sb.append(srt + ",");
			}
		}
		return sb.toString();
	}

	/**
	 * 返回人物打通的经脉id
	 * 
	 * @param channelid
	 * @return
	 */
	public String getGuanTong_Id(int channelid) {
		String id = String.valueOf(channelid).substring(0, 1);
		id = id + "01";

		return id;
	}

	/**
	 * 替换人物经脉打通经脉
	 * 
	 * @param channelid
	 * @return
	 */
	public String getDaTongJingMai_Id(int channelid) {
		String id = String.valueOf(channelid).substring(0, 1);
		id = id + "01";
		String chString = jingmai.replace(String.valueOf(channelid), id);
		return chString;
	}

	/**
	 * 查询当前穴位id 是不是已经充通过了
	 * 
	 * @return
	 */
	public boolean getXueWeiZaiBuZaiJingMaiLi(String xueweiId) {
		boolean type = false;
		// String []jinmailiSt = {"702","502","305"};

		int a = getCountDaTongJinmai2(xueweiId);
		if (a > 0) {
			type = true;
		} else {
			if (jingmai.length() > 3) {
				String[] jinmailiSt = jingmai.split(",");
				String aString = xueweiId.substring(0, 1);
				for (String string : jinmailiSt) {
					if (aString.equals(string.substring(0, 1))) {
						int b = Integer.valueOf(string);
						int c = Integer.valueOf(xueweiId);
						if (b >= c) {
							type = true;
							break;
						} else {
							String guantongidString = getguantongjinmai();
							aString = aString + "01";
							if (StringUtil.isNotEmpty(guantongidString) && guantongidString.indexOf(aString) == 0) {

								type = true;
								break;
							}

						}

					}
				}

			}
		}
		return type;
	}

	public String getJinmaiLieBiao() {
		StringBuilder jinmai = new StringBuilder();

		// 因为一共8条所以循环8次
		if (jingmai.length() > 3) {
			String[] jinmailiSt = jingmai.split(",");
			// String []jinmailiSt = {"702","502","305"};
			for (int i = 1; i < countjingmaishu + 1; i++) {
				for (String string : jinmailiSt) {
					if (string.substring(0, 1).equals(String.valueOf(i))) {
						jinmai.append(i);
						jinmai.append(",");
						jinmai.append(string);
						jinmai.append(";");
						break;
					}
				}

			}
		}
		return jinmai.toString();
	}

	// 验证角色是不是第一次冲穴位
	public boolean yanzhengxueweione(String channelid) {
		boolean srt = false;
		String shuString = "02";
		for (int i = 1; i < countjingmaishu + 1; i++) {
			String srt2 = String.valueOf(i) + shuString;
			if (channelid.indexOf(srt2) != -1) {
				srt = true;
				break;
			}
		}

		return srt;
	}

	// 验证所学穴位是不是上一个的下一级
	public boolean yanzhengxueweiguanxi(int channelid) {
		boolean srt = false;
		if (channelid > 100 && channelid < 900) {
			int id = channelid - 1;

			if (jingmai.indexOf(String.valueOf(id)) != -1) {
				srt = true;
			}
		}
		return srt;
	}

	// 返回充穴是不是这个穴位的最后一个
	public boolean getxuewei(String channelid) {
		boolean srt = false;
		int id = Integer.valueOf(channelid);
		Channel channel = null;
		id = id + 1;
		channel = ChannelManager.getInstance().getCharactergradeMap().get(id);
		if (channel != null) {
			srt = true;
		}

		return srt;
	}

	/**
	 * 验证经脉贯通
	 * 
	 * @return
	 */
	public boolean yanZhengJinMaiGuanTong(int channelid) {
		boolean type = false;
		String id = String.valueOf(channelid).substring(0, 1);
		List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(Integer.valueOf(id));
		int size = Integer.valueOf(String.valueOf(channelid).substring(1, 3));
		if (size == listChannel.size()) {
			type = true;
		}
		return type;

	}

	public boolean yanZhengBreakthrough(Breakthrough breakthrough, Hero character) {
		String goodmodel[] = breakthrough.getGoomodeIdCount().split(",");
		for (String string : goodmodel) {
			String goodmodel2[] = string.split("[*]");
			for (int a = 0; a < goodmodel2.length; a++) {
				int countJMTR = character.getCharacterGoodController().getBagGoodsCountByModelID(Integer.valueOf(goodmodel2[a]));
				if (countJMTR < Integer.valueOf(goodmodel2[a + 1])) {
					// 物品不够
					character.sendMsg(new ChannelResponse50210(CommonUseNumber.byte0));
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60001));
					return false;
				}
				a = a + 1;
			}
		}
		return true;
	}

	public boolean deleteBreakthrough(Breakthrough breakthrough, Hero character) {
		String goodmodel[] = breakthrough.getGoomodeIdCount().split(",");
		for (String string : goodmodel) {
			String goodmodel2[] = string.split("[*]");
			for (int a = 0; a < goodmodel2.length; a++) {
				int countJMTR = character.getCharacterGoodController().getBagGoodsCountByModelID(Integer.valueOf(goodmodel2[a]));
				if (countJMTR < Integer.valueOf(goodmodel2[a + 1])) {
					character.sendMsg(new ChannelResponse50210(CommonUseNumber.byte0));
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60001));
					return false;
				} else {
					// 物品够扣除
					int goodid = Integer.valueOf(goodmodel2[a]);
					int goodcount = Integer.valueOf(goodmodel2[a + 1]);
					boolean type = character.getCharacterGoodController().deleteGoodsFromBag(goodid, goodcount);
					if (!type) {
						character.sendMsg(new ChannelResponse50210(CommonUseNumber.byte0));
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 528));
						return false;
					}
				}
				a = a + 1;
			}
		}
		return true;
	}

}
