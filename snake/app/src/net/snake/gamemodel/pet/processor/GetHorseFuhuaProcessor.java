//package net.snake.gamemodel.pet.processor;
//
//import net.snake.consts.CommonUseNumber;
//import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
//import net.snake.gamemodel.common.response.PrompMsg;
//import net.snake.gamemodel.common.response.TipMsg;
//import net.snake.gamemodel.goods.bean.Goodmodel;
//import net.snake.gamemodel.goods.persistence.GoodmodelManager;
//import net.snake.gamemodel.hero.bean.Hero;
//import net.snake.gamemodel.pet.bean.CharacterHorse;
//import net.snake.gamemodel.pet.bean.HorseModel;
//import net.snake.gamemodel.pet.logic.HorseContainer;
//import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
//import net.snake.netio.message.RequestMsg;
//
///**
// * 收获内丹孵化的灵宠
// * 
// * @author jack
// * 
// */
//public class GetHorseFuhuaProcessor extends CharacterMsgProcessor {
//	@Override
//	public void process(final Hero character, RequestMsg request) throws Exception {
//		if (character.getFuhuaNeidanId() == CommonUseNumber.int0) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60048));
//			return;
//		}
//		long nowtime = System.currentTimeMillis();
//		if (nowtime - character.getFuhuaStarttime() < character.getFuhuaCdtime()) {
//			return;
//		}
//
//		// 没空间的情况下不能收获
//		if (character.getCharacterHorseController().getBagHorseContainer().getLeaveSpace() < CommonUseNumber.int1) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 893));
//			return;
//		}
//
//		final CharacterHorse characterHorse = character.getFuhuaCharacterHorse();
//		if (characterHorse == null) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60045));
//			return;
//		}
//		HorseModel horseModel = HorseModelDataProvider.getInstance().getHorseModelByID(characterHorse.getHorseModelId());
//		int neidanid = horseModel.getChangeModelId();
//		Goodmodel goodmodel = GoodmodelManager.getInstance().get(neidanid);
//		character.setFuhuaCdtime((goodmodel.getCoolingtime() / 2)*1000);
//		character.setFuhuaStarttime(nowtime);
//		character.setFuhuaNeidanId(CommonUseNumber.int0);
//		
//		characterHorse.setNeidanStarttime(nowtime);
//		characterHorse.setNeidanCdtime(horseModel.getNeidanCdtime() * 1000);
//		characterHorse.setNeidanUsetime(0);
//		
//		character.getMyCharacterAchieveCountManger().getHorseCount().catchHorse(horseModel);
//		character.getDayInCome().dealGethorse(1);
//		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
//			@Override
//			public void run() {
//				HorseContainer horsecontainer = character.getCharacterHorseController().getBagHorseContainer();
//				horsecontainer.addHorse(characterHorse);
//			}
//		});
//	}
//}
