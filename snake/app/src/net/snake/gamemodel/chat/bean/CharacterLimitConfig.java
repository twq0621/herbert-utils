package net.snake.gamemodel.chat.bean;

/**
 * 
 * 类型8byte(禁止他人向我发私聊),byte(1-选中,0-不选中),
 * 类型9byte(禁止他人加我好友),byte(1-选中,0-不选中),
 * 类型10byte(禁止他人向我发起交易),byte(1-选中,0-不选中),
 * 类型11byte(禁止他人邀请我加入帮派),byte(1-选中,0-不选中),
 * 类型12byte(禁止他人邀请我加入队伍),byte(1-选中,0-不选中)
 * @author serv_dev
 *
 */
public class CharacterLimitConfig {
	
	public boolean otherAbleSendMsgToMe = true;//他人向我发私聊
	public boolean otherAbleJoinMyFriend = true;//他人加我好友
	public boolean otherAbleTradeMe = true;//他人向我发起交易
	public boolean otherAbleInviteFaction = true;//他人邀请我加入帮派
	public boolean otherAbleInviteTeam = true;//他人邀请我加入队伍
	
	public boolean isOtherAbleSendMsgToMe() {
		return otherAbleSendMsgToMe;
	}
	public void setOtherAbleSendMsgToMe(boolean otherAbleSendMsgToMe) {
		this.otherAbleSendMsgToMe = otherAbleSendMsgToMe;
	}
	public boolean isOtherAbleJoinMyFriend() {
		return otherAbleJoinMyFriend;
	}
	public void setOtherAbleJoinMyFriend(boolean otherAbleJoinMyFriend) {
		this.otherAbleJoinMyFriend = otherAbleJoinMyFriend;
	}
	public boolean isOtherAbleTradeMe() {
		return otherAbleTradeMe;
	}
	public void setOtherAbleTradeMe(boolean otherAbleTradeMe) {
		this.otherAbleTradeMe = otherAbleTradeMe;
	}
	public boolean isOtherAbleInviteFaction() {
		return otherAbleInviteFaction;
	}
	public void setOtherAbleInviteFaction(boolean otherAbleInviteFaction) {
		this.otherAbleInviteFaction = otherAbleInviteFaction;
	}
	public boolean isOtherAbleInviteTeam() {
		return otherAbleInviteTeam;
	}
	public void setOtherAbleInviteTeam(boolean otherAbleInviteTeam) {
		this.otherAbleInviteTeam = otherAbleInviteTeam;
	}
	
	
}
