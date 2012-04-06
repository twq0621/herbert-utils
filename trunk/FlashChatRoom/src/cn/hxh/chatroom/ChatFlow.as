package cn.hxh.chatroom {
	import cn.hxh.SocketClient;
	import cn.hxh.SocketData;
	import cn.hxh.chatroom.ui.ErrorScreen;
	import cn.hxh.chatroom.ui.LoginScreen;

	import game.chat.dto.Chat_C2S;
	import game.chat.dto.Chat_S2C;
	import game.chat.dto.ConnectChat_C2S;
	import game.chat.dto.ConnectChat_S2C;

	import mx.utils.UIDUtil;

	import flash.display.MovieClip;
	import flash.events.Event;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.net.registerClassAlias;

	/**
	 * ...
	 * @author Jobe Makar - jobe@electrotank.com
	 */
	public class ChatFlow extends MovieClip {
		private var socketClient : SocketClient;
		private var chatRoom : ChatRoom;

		// private var _chatRoom : ChatRoom;
		public function ChatFlow() {
			initialize();
		}

		private function initialize() : void {
			// load the server connection settings
			var loader : URLLoader = new URLLoader(new URLRequest("server.xml"));
			loader.addEventListener(Event.COMPLETE, onFileLoaded);

			registerClassAlias("game.chat.dto.Chat_C2S", Chat_C2S);
			registerClassAlias("game.chat.dto.Chat_S2C", Chat_S2C);
			registerClassAlias("game.chat.dto.ConnectChat_C2S", ConnectChat_C2S);
			registerClassAlias("game.chat.dto.ConnectChat_S2C", ConnectChat_S2C);
			socketClient = new SocketClient();
			socketClient.addCallback("chat", chatCallBack);
			socketClient.addCallback("connectChat", connectChatCallBack);
		}

		private function connectChatCallBack(ret : ConnectChat_S2C) : void {
			trace("connect chat response:" + ret.code);
			if(ret.code == 0)
			{
				createChatRoom();
			}
		}

		private function chatCallBack(ret : Chat_S2C) : void {
		}

		/**
		 * Called when the file loads
		 */
		private function onFileLoaded(e : Event) : void {
			var loader : URLLoader = e.target as URLLoader;
			var server : XML = new XML(loader.data);

			// grab the ip and port from the XML
			var ip : String = server.connection.@ip;
			var port : int = parseInt(server.connection.@port);

			// connect to chatServer
			var socketData : SocketData = new SocketData("default", ip, port);
			socketClient.connect(socketData);
			createLoginScreen();
		}

		/**
		 * Called when a user is connected and logged in. It creates a chat room screen.
		 */
		private function createChatRoom() : void {
			chatRoom = new ChatRoom();
			chatRoom._socketClient = socketClient;
			chatRoom.initialize();
			addChild(chatRoom);
		}

		/**
		 * This is used to display an error if one occurs
		 */
		private function showError(msg : String) : void {
			var alert : ErrorScreen = new ErrorScreen(msg);
			alert.x = 300;
			alert.y = 200;
			alert.addEventListener(ErrorScreen.OK, onErrorScreenOk);
			addChild(alert);
		}

		/**
		 * Called as the result of an OK event on an error screen. Removes the error screen.
		 */
		private function onErrorScreenOk(e : Event) : void {
			var alert : ErrorScreen = e.target as ErrorScreen;
			alert.removeEventListener(ErrorScreen.OK, onErrorScreenOk);
			removeChild(alert);
		}

		/**
		 * Creates a screen where a user can enter a username
		 */
		private function createLoginScreen() : void {
			var login : LoginScreen = new LoginScreen();
			login.x = 400 - login.width / 2;
			login.y = 300 - login.height / 2;
			addChild(login);
			login.addEventListener(LoginScreen.OK, onLoginSubmit);
		}

		/**
		 * Called as a result of the OK event on the login screen. Creates and sends a login request to the server
		 */
		private function onLoginSubmit(e : Event) : void {
			var screen : LoginScreen = e.target as LoginScreen;

			// create the request
			var beginChat : ConnectChat_C2S = new ConnectChat_C2S();
			beginChat.userName = screen.username;
			beginChat.roleName = screen.username + "_role";
			beginChat.sid = UIDUtil.createUID();
			socketClient.call(beginChat);

			screen.removeEventListener(LoginScreen.OK, onLoginSubmit);
			removeChild(screen);
		}
	}
}