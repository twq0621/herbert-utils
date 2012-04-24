package net.snake.gamemodel.wedding.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.Language;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.WedFeastJoin;
import net.snake.gamemodel.wedding.persistence.WedFeast;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.gamemodel.wedding.response.wedfeast.WedFeastGiftListResponse52252;
import net.snake.netio.message.RequestMsg;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.shell.Options;


/**
 * 获取红包列表
 *@author serv_dev
 */
@MsgCodeAnn(msgcode = 52251,accessLimit=100)
public class WedFeastGiftListProcess52251 extends CharacterMsgProcessor{
	private final static int PAGE_COUNT=10;

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		byte page = request.getByte();
//		page=3;
		WedFeast feast= WedFeastManager.getInstance().getFeastByRoleid(character.getId());
		if(feast==null){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,50026));
			return ;
		}
		ConcurrentHashMap<Integer,WedFeastJoin> joiners = WedFeastManager.getInstance().getJoinerByFeast(feast.getTempID());
		List<WedFeastJoin> joinerByFeast=new ArrayList<WedFeastJoin>();
		for (Entry<Integer, WedFeastJoin> entry : joiners.entrySet()) {
			joinerByFeast.add(entry.getValue());
		}
		Collections.sort(joinerByFeast, new Comparator<WedFeastJoin>() {

			@Override
			public int compare(WedFeastJoin o1, WedFeastJoin o2) {
				int gift = o2.getGift().compareTo(o1.getGift());
				if (gift == 0) {
					CharacterCacheEntry c1 = CharacterCacheManager
							.getInstance().getCharacterCacheEntryById(
									o1.getCharacterid());
					CharacterCacheEntry c2 = CharacterCacheManager
							.getInstance().getCharacterCacheEntryById(
									o2.getCharacterid());
					if (c1 != null && c2 != null) {
						int live =new Short(c2.getGrade()).compareTo(c1.getGrade());
						if (live != 0) {
							return live;
						}
					}
					return o2.getCharacterid().compareTo(o1.getCharacterid());
				} else {
					return gift;
				}
			}
		});
		int pagesum=joinerByFeast.size()==0?1:joinerByFeast.size()/PAGE_COUNT;
		if(joinerByFeast.size()%PAGE_COUNT!=0){
			pagesum+=1;
		}
		int start=(page-1)*PAGE_COUNT;
		int end=(page*PAGE_COUNT);
		if(start>joinerByFeast.size()){
			start=(pagesum-1)*PAGE_COUNT;
		}
		if(end>joinerByFeast.size()){
			end=joinerByFeast.size();
		}	
		List<WedFeastJoin> subList = joinerByFeast.subList(start,end);
		int amount=feast.getGiftAmount()/2;
		if(feast.getApplyerId().equals(character.getId())){
			if(feast.getIsreceive1()){
				amount=0;
			}else{
				amount=feast.getGiftAmount()/2;
			}
		}
		if(feast.getMateId().equals(character.getId())){
			if(feast.getIsreceive2()){
				amount=0;
			}else{
				amount=feast.getGiftAmount()/2;
			}
		}
		CharacterCacheEntry applyer = CharacterCacheManager.getInstance().getCharacterCacheEntryById(feast.getApplyerId());
		CharacterCacheEntry mate = CharacterCacheManager.getInstance().getCharacterCacheEntryById(feast.getMateId());
		character.sendMsg(new WedFeastGiftListResponse52252(applyer.getViewName()+Language.AND+mate.getViewName(),joinerByFeast.size(),feast.getGiftAmount(),amount,joinerByFeast.size(),page,pagesum, subList));
	}
}

