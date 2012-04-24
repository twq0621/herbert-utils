package net.snake.gamemodel.fight.bean;

import net.snake.consts.Property;
import net.snake.ibatis.IbatisEntity;

public class Totem implements IbatisEntity {

	/**
	 * 战纹编号 t_totem.f_id
	 */
	private Integer id;
	/**
	 * 战纹名称 t_totem.f_name
	 */
	private String name;
	/**
	 * 战纹出现几率(最小值) t_totem.f_min_probility
	 */
	private Integer minProbility;
	/**
	 * 战纹出现几率(最大值) t_totem.f_max_probility
	 */
	private Integer maxProbility;
	/**
	 * 攻击力增强区间 t_totem.f_attack_enhance
	 */
	private String attackEnhance;
	/**
	 * 反弹伤害区间 t_totem.f_rebound_hurt
	 */
	private String reboundHurt;
	/**
	 * 忽视防御区间 t_totem.f_ignore_defence
	 */
	private String ignoreDefence;
	/**
	 * 伤害减免区间 t_totem.f_hurt_derate
	 */
	private String hurtDerate;
	/**
	 * 暗器免伤区间 t_totem.f_hidden_derate
	 */
	private String hiddenDerate;
	/**
	 * 图腾ico t_totem.f_ico_id
	 */
	private Integer icoId;
	/**
	 * 战纹名称国际化 t_totem.f_name_i18n
	 */
	private String nameI18n;

	/**
	 * 战纹编号 t_totem.f_id
	 * 
	 * @return the value of t_totem.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 战纹编号 t_totem.f_id
	 * 
	 * @param id
	 *            the value for t_totem.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 战纹名称 t_totem.f_name
	 * 
	 * @return the value of t_totem.f_name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 战纹名称 t_totem.f_name
	 * 
	 * @param name
	 *            the value for t_totem.f_name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 战纹出现几率(最小值) t_totem.f_min_probility
	 * 
	 * @return the value of t_totem.f_min_probility
	 */
	public Integer getMinProbility() {
		return minProbility;
	}

	/**
	 * 战纹出现几率(最小值) t_totem.f_min_probility
	 * 
	 * @param minProbility
	 *            the value for t_totem.f_min_probility
	 */
	public void setMinProbility(Integer minProbility) {
		this.minProbility = minProbility;
	}

	/**
	 * 战纹出现几率(最大值) t_totem.f_max_probility
	 * 
	 * @return the value of t_totem.f_max_probility
	 */
	public Integer getMaxProbility() {
		return maxProbility;
	}

	/**
	 * 战纹出现几率(最大值) t_totem.f_max_probility
	 * 
	 * @param maxProbility
	 *            the value for t_totem.f_max_probility
	 */
	public void setMaxProbility(Integer maxProbility) {
		this.maxProbility = maxProbility;
	}

	/**
	 * 攻击力增强区间 t_totem.f_attack_enhance
	 * 
	 * @return the value of t_totem.f_attack_enhance
	 */
	public String getAttackEnhance() {
		return attackEnhance;
	}

	/**
	 * 攻击力增强区间 t_totem.f_attack_enhance
	 * 
	 * @param attackEnhance
	 *            the value for t_totem.f_attack_enhance
	 */
	public void setAttackEnhance(String attackEnhance) {
		this.attackEnhance = attackEnhance;
	}

	/**
	 * 反弹伤害区间 t_totem.f_rebound_hurt
	 * 
	 * @return the value of t_totem.f_rebound_hurt
	 */
	public String getReboundHurt() {
		return reboundHurt;
	}

	/**
	 * 反弹伤害区间 t_totem.f_rebound_hurt
	 * 
	 * @param reboundHurt
	 *            the value for t_totem.f_rebound_hurt
	 */
	public void setReboundHurt(String reboundHurt) {
		this.reboundHurt = reboundHurt;
	}

	/**
	 * 忽视防御区间 t_totem.f_ignore_defence
	 * 
	 * @return the value of t_totem.f_ignore_defence
	 */
	public String getIgnoreDefence() {
		return ignoreDefence;
	}

	/**
	 * 忽视防御区间 t_totem.f_ignore_defence
	 * 
	 * @param ignoreDefence
	 *            the value for t_totem.f_ignore_defence
	 */
	public void setIgnoreDefence(String ignoreDefence) {
		this.ignoreDefence = ignoreDefence;
	}

	/**
	 * 伤害减免区间 t_totem.f_hurt_derate
	 * 
	 * @return the value of t_totem.f_hurt_derate
	 */
	public String getHurtDerate() {
		return hurtDerate;
	}

	/**
	 * 伤害减免区间 t_totem.f_hurt_derate
	 * 
	 * @param hurtDerate
	 *            the value for t_totem.f_hurt_derate
	 */
	public void setHurtDerate(String hurtDerate) {
		this.hurtDerate = hurtDerate;
	}

	/**
	 * 暗器免伤区间 t_totem.f_hidden_derate
	 * 
	 * @return the value of t_totem.f_hidden_derate
	 */
	public String getHiddenDerate() {
		return hiddenDerate;
	}

	/**
	 * 暗器免伤区间 t_totem.f_hidden_derate
	 * 
	 * @param hiddenDerate
	 *            the value for t_totem.f_hidden_derate
	 */
	public void setHiddenDerate(String hiddenDerate) {
		this.hiddenDerate = hiddenDerate;
	}

	/**
	 * 图腾ico t_totem.f_ico_id
	 * 
	 * @return the value of t_totem.f_ico_id
	 */
	public Integer getIcoId() {
		return icoId;
	}

	/**
	 * 图腾ico t_totem.f_ico_id
	 * 
	 * @param icoId
	 *            the value for t_totem.f_ico_id
	 */
	public void setIcoId(Integer icoId) {
		this.icoId = icoId;
	}

	/**
	 * 战纹名称国际化 t_totem.f_name_i18n
	 * 
	 * @return the value of t_totem.f_name_i18n
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 战纹名称国际化 t_totem.f_name_i18n
	 * 
	 * @param nameI18n
	 *            the value for t_totem.f_name_i18n
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 获得图腾区间值
	 * 
	 * @param property
	 *            属性
	 * @param pos
	 *            位置
	 * @return
	 */
	public int getTotemValue(Property property, int pos) {
		switch (property) {
		case AQMS:
			if (hiddenDerate != null && !"".equals(hiddenDerate)) {
				String[] arr = hiddenDerate.split(";");
				if (arr.length > 0 && arr.length - 1 >= pos && !"".equals(arr[0])) {
					return Integer.parseInt(arr[pos]);
				}
			}
			break;
		case GJL:
			if (attackEnhance != null && !"".equals(attackEnhance)) {
				String[] arr = attackEnhance.split(";");
				if (arr.length > 0 && arr.length - 1 >= pos && !"".equals(arr[0])) {
					return Integer.parseInt(arr[pos]);
				}
			}
			break;
		case FTSH:
			if (reboundHurt != null && !"".equals(reboundHurt)) {
				String[] arr = reboundHurt.split(";");
				if (arr.length > 0 && arr.length - 1 >= pos && !"".equals(arr[0])) {
					return Integer.parseInt(arr[pos]);
				}
			}
			break;
		case HSFY:
			if (ignoreDefence != null && !"".equals(ignoreDefence)) {
				String[] arr = ignoreDefence.split(";");
				if (arr.length > 0 && arr.length - 1 >= pos && !"".equals(arr[0])) {
					return Integer.parseInt(arr[pos]);
				}
			}
			break;

		case SHJM:
			if (hurtDerate != null && !"".equals(hurtDerate)) {
				String[] arr = hurtDerate.split(";");
				if (arr.length > 0 && arr.length - 1 >= pos && !"".equals(arr[0])) {
					return Integer.parseInt(arr[pos]);
				}
			}
			break;

		default:
			break;
		}
		return 0;
	}

	/**
	 * 获得图腾区间值
	 * 
	 * @param property
	 *            属性
	 * @param pos
	 *            位置
	 * @return
	 */
	public int getTotemMaxValue(Property property) {
		switch (property) {
		case AQMS:
			if (hiddenDerate != null && !"".equals(hiddenDerate)) {
				String[] arr = hiddenDerate.split(";");
				int maxV = 0;

				if (arr.length > 0 && !"".equals(arr[0])) {
					for (int i = 0; i < arr.length; i++) {
						int _tmpV = Integer.parseInt(arr[i]);
						if (maxV < _tmpV) {
							maxV = _tmpV;
						}
					}
				}
				return maxV;
			}
			break;
		case GJL:
			if (attackEnhance != null && !"".equals(attackEnhance)) {
				String[] arr = attackEnhance.split(";");
				int maxV = 0;

				if (arr.length > 0 && !"".equals(arr[0])) {
					for (int i = 0; i < arr.length; i++) {
						int _tmpV = Integer.parseInt(arr[i]);
						if (maxV < _tmpV) {
							maxV = _tmpV;
						}
					}
				}
				return maxV;
			}
			break;
		case FTSH:
			if (reboundHurt != null && !"".equals(reboundHurt)) {
				String[] arr = reboundHurt.split(";");
				int maxV = 0;

				if (arr.length > 0 && !"".equals(arr[0])) {
					for (int i = 0; i < arr.length; i++) {
						int _tmpV = Integer.parseInt(arr[i]);
						if (maxV < _tmpV) {
							maxV = _tmpV;
						}
					}
				}
				return maxV;
			}
			break;
		case HSFY:
			if (ignoreDefence != null && !"".equals(ignoreDefence)) {
				String[] arr = ignoreDefence.split(";");
				int maxV = 0;

				if (arr.length > 0 && !"".equals(arr[0])) {
					for (int i = 0; i < arr.length; i++) {
						int _tmpV = Integer.parseInt(arr[i]);
						if (maxV < _tmpV) {
							maxV = _tmpV;
						}
					}
				}
				return maxV;
			}
			break;

		case SHJM:
			if (hurtDerate != null && !"".equals(hurtDerate)) {
				String[] arr = hurtDerate.split(";");
				int maxV = 0;
				if (arr.length > 0 && !"".equals(arr[0])) {
					for (int i = 0; i < arr.length; i++) {
						int _tmpV = Integer.parseInt(arr[i]);
						if (maxV < _tmpV) {
							maxV = _tmpV;
						}
					}
				}
				return maxV;
			}
			break;

		default:
			break;
		}
		return 0;
	}
}
