package net.snake.gamemodel.across.logic;

import java.util.ArrayList;
import java.util.List;

import net.snake.AcrossServer;
import net.snake.ctsnet.CtsConnectSessionMananger;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.bean.CharacterAcross;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.across.response.LingquShouyiMsg1004;
import net.snake.gamemodel.hero.bean.Hero;

public class AccountCharacterAcrossController {
	private List<CharacterAcross> list = new ArrayList<CharacterAcross>();

	public List<CharacterAcross> getList() {
		return list;
	}

	public void setList(List<CharacterAcross> list) {
		this.list = list;
	}

	public AccountCharacterAcrossController() {

	}

	public void addCharacterAcross(CharacterAcross ca) {
		int characterId = ca.getCharacterId();
		for (CharacterAcross temp : list) {
			if (temp.getCharacterId() == characterId) {
				return;
			}
		}
		list.add(ca);
	}

	public void sendLingquShouyiAboutOhereRoles(Hero character) {
		int characterId = character.getId();
		for (CharacterAcross temp : list) {
			if (temp.getCharacterId() != characterId) {
				if (temp.getShouyiState() == 1) {
					AcrossServerDate asd = AcrossServerDateManager.getInstance().getAcrossServerDateById(temp.getAcrossServerId());
					CtsConnectSessionMananger.getInstance().sendMsgToServer(asd.getLoginServerIp(), AcrossServer.acrossPort, new LingquShouyiMsg1004(temp));
				}
			}
		}

	}
}
