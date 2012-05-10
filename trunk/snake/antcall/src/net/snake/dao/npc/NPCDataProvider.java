package net.snake.dao.npc;

import java.util.List;

public interface NPCDataProvider {
	NPC getNPCByID(int npc);
	List<NPC> getNPCList();
}
