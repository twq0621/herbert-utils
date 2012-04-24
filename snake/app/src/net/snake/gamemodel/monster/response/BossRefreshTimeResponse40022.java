package net.snake.gamemodel.monster.response;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.snake.GameServer;
import net.snake.commons.Language;
import net.snake.commons.TimeExpression;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.persistence.BossLastKillManager;
import net.snake.netio.ServerResponse;
import net.snake.serverenv.vline.VLineServer;

/**
 * @author serv_dev
 */
public class BossRefreshTimeResponse40022 extends ServerResponse {
	public static BossComparator bossComparator = new BossComparator();

	public BossRefreshTimeResponse40022(List<SceneMonster> bosslist, VLineServer server) throws Exception {
		setMsgCode(40022);
		if (bosslist != null && bosslist.size() > 0) {
			Collections.sort(bosslist, bossComparator);
			writeByte(bosslist.size());
			for (SceneMonster boss : bosslist) {
				this.showBoss(boss, server);
			}
		} else {
			writeByte(0);
		}
	}

	private void showBoss(SceneMonster boss, VLineServer server) throws Exception {
		MonsterModel monsterModel = boss.getMonsterModel();
		writeInt(monsterModel.getId());
		String name = monsterModel.getNameI18n();
		int grade = monsterModel.getGrade();
		int scene = boss.getScene();
		Scene scene2 = server.getSceneManager().getScene(scene);
		String scenename = scene2.getShowName();
		TimeExpression time = monsterModel.getTimeExpression();
		// monsterModel.getTimeExpression();
		short originalX = boss.getOriginalX();
		short originalY = boss.getOriginalY();
		writeUTF(name.contains("_") ? name.substring(0, name.indexOf("_")) : name);
		writeShort(grade);
		writeUTF(scenename);
		if (boss.isDie() || boss.getObjectState() == VisibleObjectState.Dispose) {
			writeUTF(time.getStart(System.currentTimeMillis()));
		} else {
			writeUTF(Language.WEISIWANG);
		}
		writeInt(scene);
		writeInt(originalX);
		writeInt(originalY);
		writeByte(monsterModel.getProbabilitylevel());
		writeUTF(monsterModel.getDescI18n() == null ? Language.ZANWUMIAOSHU : monsterModel.getDescI18n());
		// ）,上轮该杀死该boss的玩家名（string），上轮杀死该boss的玩家id（int），boss的血量百分比（byte）0-100，-1为没有}
		int id = monsterModel.getId();
		int lineid = server.getLineid();
		CharacterCacheEntry lastKiller = BossLastKillManager.getInstance().getLastKiller(id, lineid);
		if (lastKiller == null) {
			writeUTF("");
			writeInt(-1);
			writeByte(0);
		} else {
			writeUTF(lastKiller.getViewName());
			writeInt(lastKiller.getId());
			Hero character = GameServer.vlineServerManager.getCharacterById(lastKiller.getId());
			if (character != null) {
				writeByte(1);
			} else {
				writeByte(0);
			}

		}

		if (boss.isDie() || boss.getObjectState() == VisibleObjectState.Dispose) {
			writeByte(-1);
		} else {
			float nowHpValue = boss.getNowHp();
			int nowHp = (int) ((nowHpValue / boss.getMaxHp()) * 100);
			writeByte(nowHp);
		}
		// 武功说明
		String skilldesc = boss.getMonsterModel().getSkilldescI18n();
		if (skilldesc == null) {
			writeUTF("");
		} else {
			writeUTF(skilldesc);
		}
	}
}

class BossComparator implements Comparator<SceneMonster> {
	@Override
	public int compare(SceneMonster o1, SceneMonster o2) {
		return o1.getGrade() - o2.getGrade();
	}
}
