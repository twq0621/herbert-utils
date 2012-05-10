package net.snake.dao.npc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;





public class NPCDataProviderImpl implements NPCDataProvider ,InitializingBean{

	private Map<Integer, NPC> map = new HashMap<Integer,NPC>();
	private List<NPC> list =null;
	private NPCDAO npcdao;
	public NPCDAO getNpcdao() {
		return npcdao;
	}

	public void setNpcdao(NPCDAO npcdao) {
		this.npcdao = npcdao;
	}

	@Override
	public NPC getNPCByID(int npc) {
		
		return map.get(npc);
	}

	@Override
	public List<NPC> getNPCList() {

		return list;
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		NPCExample example =new NPCExample();
		example.setOrderByClause("f_id");
		list = npcdao.selectByExample(example);
		map = net.snake.common.BeanTool.listToMap(list, "id");
		
		System.out.println("npc--------------"+list.size());
	}

}
