package net.snake;

import java.util.HashMap;
import java.util.Map;

import net.snake.across.acrserverprocessor.AcrossListBalanceHandler1006;
import net.snake.across.acrserverprocessor.AcrossWorldFirstHandler1011;
import net.snake.across.acrserverprocessor.CharacterDateInitHandle1002;
import net.snake.across.acrserverprocessor.CharacterOnlineInGSHandle1004;
import net.snake.across.acrserverprocessor.CharacterShouyiLingquSucessHandle1010;
import net.snake.across.gameprocessor.AcrossListResultHandler1007;
import net.snake.across.gameprocessor.AcrossWorldFirstHandler1012;
import net.snake.across.gameprocessor.CharacterTrantsResultHandler1003;
import net.snake.across.gameprocessor.CharacterTrantsShouyiDateHandler1005;
import net.snake.stsnet.STSHandler;
import net.snake.stsnet.STSIoHandler;
import net.snake.stsnet.STSNetIO;

public class AcrossServer {
	public static int acrossPort = 1188;
	public static Map<String, STSHandler> handlerMap;

	STSNetIO stsNetIO = new STSNetIO();
	STSIoHandler stsIoHandler = new STSIoHandler();

	public AcrossServer() {
		stsIoHandler.setHandlerMap(handlerMap);
		stsNetIO.setIoHandler(stsIoHandler);
		stsNetIO.startNetListen(acrossPort);
	}

	public static void initHandlerMap() {
		handlerMap = new HashMap<String, STSHandler>();
		// *****acrossserver
		handlerMap.put("1002", new CharacterDateInitHandle1002());
		handlerMap.put("1006", new AcrossListBalanceHandler1006());
		handlerMap.put("1004", new CharacterOnlineInGSHandle1004());
		handlerMap.put("1010", new CharacterShouyiLingquSucessHandle1010());
		handlerMap.put("1011", new AcrossWorldFirstHandler1011());

		// *****gameserver
		handlerMap.put("1003", new CharacterTrantsResultHandler1003());
		handlerMap.put("1005", new CharacterTrantsShouyiDateHandler1005());
		handlerMap.put("1007", new AcrossListResultHandler1007());
		handlerMap.put("1012", new AcrossWorldFirstHandler1012());

	}
}
