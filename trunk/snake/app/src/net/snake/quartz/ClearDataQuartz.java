package net.snake.quartz;

import java.util.Collection;
import java.util.Iterator;

import net.snake.GameServer;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.persistence.CharacterHorseDataProvider;
import net.snake.serverenv.vline.VLineServer;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ClearDataQuartz implements Job {
	private static Logger logger = Logger.getLogger(ClearDataQuartz.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("start clear data--xing fu value and dujie");
		Iterator<VLineServer> it = GameServer.vlineServerManager.getLineServersList().iterator();
		while (it.hasNext()) {
			VLineServer vl = it.next();
			Collection<Hero> heros = vl.getOnlineManager().getCharacterList();

			for (Hero hero : heros) {
				CharacterGoodController cgc = hero.getCharacterGoodController();
				Collection<CharacterGoods> cgs = cgc.getBodyGoodsList();
				clearDataGoods(cgs);
				clearDataGoods(cgc.getBagGoodsContiner().getGoodsList());
				clearDataGoods(cgc.getStallGoodsContainer().getGoodsList());
				clearDataGoods(cgc.getStorageGoodsContainer().getGoodsList());
				this.clearDataHorse(hero.getCharacterHorseController().getAllHorse());
			}
			CharacterHorseDataProvider.getInstance().updateXingfu();
			GoodsDataEntryManager.getInstance().updateXingfu();
		}
	}

	private void clearDataGoods(Collection<CharacterGoods> cgs) {
		for (CharacterGoods cg : cgs) {
			cg.setBornStrengthenCnt(0);
		}
	}

	private void clearDataHorse(Collection<Horse> horses) {
		for (Horse horse : horses) {
			horse.getCharacterHorse().setJinjieCount(0);
		}
	}

}
