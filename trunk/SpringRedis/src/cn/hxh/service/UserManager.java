package cn.hxh.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.netty.channel.Channel;
import org.springframework.stereotype.Component;

import cn.hxh.dto.GetOnlineNames_C2S;
import cn.hxh.dto.GetOnlineNames_S2C;
import cn.hxh.dto.NameDTO;
import cn.hxh.dto.TestPushMsg_S2C;

@Component
public class UserManager {

	public void getOnlineNames(Channel channel, GetOnlineNames_C2S queryMsg) {
		List<String> roleNames = new ArrayList<String>();
		roleNames.add("he");
		roleNames.add("xu");
		GetOnlineNames_S2C retMsg = new GetOnlineNames_S2C();
		retMsg.setRoleNames(roleNames);
		channel.write(retMsg);
		TestPushMsg_S2C retMsg2 = new TestPushMsg_S2C();
		NameDTO dto = new NameDTO();
		dto.setAge(18);
		dto.setMoney(250.4f);
		dto.setName("huijige");
		dto.setPasswprd("pwd");
		retMsg2.setNameDTO(dto);
		channel.write(retMsg2);
	}

}
