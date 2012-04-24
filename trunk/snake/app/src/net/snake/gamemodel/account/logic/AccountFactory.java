package net.snake.gamemodel.account.logic;

import java.util.Date;
import java.util.UUID;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.account.bean.AuthData;

public class AccountFactory {

	public static Account createAccount(AuthData authData) {
		Date t = new Date();
		Account account = new Account();
		account.setLoginname("yunying" + authData.getSid() + "_" + authData.getOpenid());
		account.setServer(authData.getSid());
		account.setYunyingId(authData.getOpenid());
		account.setIsLimit(authData.isIndulge() ?CommonUseNumber.byte1 : 0);
		account.setLastloginIp(authData.getIp());
		account.setCreateIp(authData.getIp());// 注册ip
		
		account.setPassword(UUID.randomUUID().toString().substring(0, 16));
		account.setStatus(CommonUseNumber.byte1);
		account.setLastloginDate(t);
		account.setCreateDate(t);
		account.setLoginTime(0);// 登陆次数
		
		account.setGm(0);
		account.setGainYuanbao(0);
		account.setLostYuanbao(0);
		account.setConsumeYuanbao(0);
		account.setRechargeYuanbao(0);
		account.setYuanbao(0);
		account.setOnline(0);
		return account;
	}

}
