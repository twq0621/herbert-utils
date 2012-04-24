package net.snake.gamemodel.panel.persistence;

import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.response.CharacterUpGradeDescMsg50676;
import net.snake.gamemodel.panel.bean.RoleUpgradeDesc;

/**
 * 
 * 角色等级引导提示 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class RoleGradeExpDescController {
	private List<RoleUpgradeDesc> list = new ArrayList<RoleUpgradeDesc>();

	public void addRoleUpgradeDesc(RoleUpgradeDesc desc) {
		list.add(desc);
	}

	/**
	 * @param character
	 * @param priExp
	 */
	public void upGradeMSG(Hero character, long priExp) {
		for (int i = 0; i < list.size(); i++) {
			RoleUpgradeDesc desc = list.get(i);
			long tishiExp = desc.getNowExp();
			if (tishiExp >= priExp && tishiExp <= character.getNowExperience()) {
				character.sendMsg(new CharacterUpGradeDescMsg50676(desc));
			}
			if (tishiExp < character.getNowExperience()) {
				return;
			}
		}
	}
}
