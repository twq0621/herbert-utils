package net.snake.gamemodel.wedding.response.wedfeast;

import java.util.List;

import net.snake.GameServer;
import net.snake.commons.Language;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.FeastJoin;
import net.snake.gamemodel.wedding.bean.WedFeastJoin;
import net.snake.netio.ServerResponse;
import net.snake.serverenv.cache.CharacterCacheManager;

/**
 * 
 * @author serv_dev
 * Copyright (c) 2011 Kingnet
 */
public class WedFeastGiftListResponse52252 extends ServerResponse {
	public WedFeastGiftListResponse52252(String feastname,int count, int shouyi, int weilinqu,
			int binkeshu, int page, int pagecount, List<WedFeastJoin> l) {
		setMsgCode(52252);
		ServerResponse out = this;
		try {
			out.writeUTF(feastname);
			out.writeInt(count);
			out.writeInt(shouyi);
			out.writeInt(weilinqu);
			out.writeInt(binkeshu);
			out.writeByte(page);
			out.writeByte(pagecount);
			out.writeByte(l.size());
			for(int i=0;i<l.size();i++){
				out.writeByte(i);
				FeastJoin join=l.get(i);
				out.writeInt(join.getCharacterid());
				if(join.getCharacterid()!=-1){
					Hero c = GameServer.vlineServerManager.getCharacterById(join.getCharacterid());
					if(c!=null){
						out.writeUTF(c.getViewName());
						out.writeInt(c.getGrade());
						out.writeByte(c.getIsonline());
					}else{
						CharacterCacheEntry joiner = CharacterCacheManager.getInstance().getCharacterCacheEntryById(join.getCharacterid());
						out.writeUTF(joiner.getViewName());
						out.writeInt(joiner.getGrade());
						out.writeByte(joiner.getIsOnline());
					}					
				}else{
					out.writeUTF(Language.SYSTEMGIFT);
					out.writeInt(0);
					out.writeByte(0);
				}
				out.writeInt(join.getGift());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
