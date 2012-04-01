package game.service {
	import cn.hxh.SocketClient;
	import cn.hxh.SocketData;

	import game.dto.CreateRole_C2S;
	import game.dto.CreateRole_S2C;
	import game.dto.Login_C2S;
	import game.dto.Login_S2C;
	import game.dto.RoleDTO;

	import mx.collections.ArrayCollection;

	import flash.net.registerClassAlias;

	/**
	 * @author Administrator
	 */
	public class LoginManager {
		private var socketClient : SocketClient;

		public function LoginManager() {
			registerClassAlias("game.dto.Login_C2S", Login_C2S);
			registerClassAlias("game.dto.Login_S2C", Login_S2C);
			registerClassAlias("game.dto.RoleDTO", RoleDTO);
			registerClassAlias("game.dto.CreateRole_C2S", CreateRole_C2S);
			registerClassAlias("game.dto.CreateRole_S2C", CreateRole_S2C);
			registerClassAlias("flex.messaging.io.ArrayCollection", mx.collections.ArrayCollection);
			socketClient = new SocketClient();
			socketClient.addCallback("Login", loginCallBack);
			socketClient.addCallback("CreateRole", createRoleCallBack);
			var socketData : SocketData = new SocketData("default", "127.0.0.1", 8653);
			socketClient.connect(socketData);
		}

		private function createRoleCallBack(ret : CreateRole_S2C) : void {
			trace("create role ret:" + ret.code);
		}

		private function loginCallBack(loginRet : Login_S2C) : void {
			trace("login ret code:" + loginRet.code);
			trace("role count:" + loginRet.roleList.length);
			var roleList : ArrayCollection = loginRet.roleList;
			if (roleList == null || roleList.length == 0) {
				// 创建角色
				createRole();
			} else {
				for (var i : int = 0; i < loginRet.roleList.length; i++) {
					var retRole : RoleDTO = loginRet.roleList.getItemAt(i) as RoleDTO;
					trace(retRole.name);
				}
			}
		}

		/**
		 * 创建角色
		 */
		private function createRole() : void {
			var reqMsg : CreateRole_C2S = new CreateRole_C2S();
			reqMsg.roleName = "机灰哥";
			reqMsg.gender = 2;
			reqMsg.characterId = 1;
			socketClient.call(reqMsg);
		}

		/**
		 * 调用登陆接口
		 */
		public function callLogin(name : String, pwd : String) : void {
			var loginMsg : Login_C2S = new Login_C2S();
			loginMsg.name = name;
			loginMsg.pwd = pwd;
			socketClient.call(loginMsg);
		}
	}
}
