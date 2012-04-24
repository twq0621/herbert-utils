package net.snake.gamemodel.pet.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.logic.HorseContainer;
import net.snake.netio.ServerResponse;

/**
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class HorseListResponse60018 extends ServerResponse {

	private static class HorseDatesortComparator implements Comparator<Horse> {
		@Override
		public int compare(Horse o1, Horse o2) {
			return o2.getCharacterHorse().getGrade().compareTo(o1.getCharacterHorse().getGrade());
		}
	}

	public HorseListResponse60018(HorseContainer horsecontainer) {
		setMsgCode(60018);
		try {
			ServerResponse out = this;
			// 马的列表
			out.writeInt(horsecontainer.getCharacter().getId());
			out.writeByte(horsecontainer.getCharacter().getMaxHorseAmount());
			ArrayList<Horse> horselist = new ArrayList<Horse>(horsecontainer.getHorseCollection());
			out.writeByte(horselist.size());
			if (horselist.size() > 0) {
				Collections.sort(horselist, new HorseDatesortComparator());
				for (Horse horse : horselist) {
					out.writeInt(horse.getId());
					out.writeInt(horse.getHorseModel().getId());
					out.writeUTF(horse.getCharacterHorse().getName());
					out.writeShort(horse.getCharacterHorse().getGrade());
					out.writeByte(horse.getCharacterHorse().getPin());
					out.writeInt(horse.getCharacterHorse().getLivingness());
					out.writeInt(horse.getCharacterHorse().getLivingnessMax());
					out.writeByte(horse.getCharacterHorse().getStatus());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
