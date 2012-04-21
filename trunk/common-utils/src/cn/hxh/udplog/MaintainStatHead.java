package cn.hxh.udplog;

public class MaintainStatHead {

	public byte httpHead = 0x00;
	public int nPackageLength; // 头部长度 + Body长度
	public String nUID;
	public char shFlag;
	public char shOptionalLen; // signature 加密串
	public char lpbyOptional;
	public short shHeaderLen;
	public char shMessageID;
	public char shMessageType;
	public char shVersionType;
	public char shVersion;
	public long nPlayerID;
	public long nSequence;

	public MaintainStatHead() {
		shFlag = 0;
		shOptionalLen = 0;
		lpbyOptional = 0;
		shHeaderLen = getSize();
		shMessageID = 0x0010;
		shMessageType = 0x03;
		shVersionType = 0x03;
		shVersion = 0;
		nPlayerID = -1;
		nSequence = 0;
	}

	public short getSize() {
		return 30;
	}

	public byte getHttpHead() {
		return httpHead;
	}

	public void setHttpHead(byte httpHead) {
		this.httpHead = httpHead;
	}

	public int getnPackageLength() {
		return nPackageLength;
	}

	public void setnPackageLength(int nPackageLength) {
		this.nPackageLength = nPackageLength;
	}

	public char getShFlag() {
		return shFlag;
	}

	public void setShFlag(char shFlag) {
		this.shFlag = shFlag;
	}

	public char getShOptionalLen() {
		return shOptionalLen;
	}

	public void setShOptionalLen(char shOptionalLen) {
		this.shOptionalLen = shOptionalLen;
	}

	public char getLpbyOptional() {
		return lpbyOptional;
	}

	public void setLpbyOptional(char lpbyOptional) {
		this.lpbyOptional = lpbyOptional;
	}

	public char getShMessageID() {
		return shMessageID;
	}

	public void setShMessageID(char shMessageID) {
		this.shMessageID = shMessageID;
	}

	public char getShMessageType() {
		return shMessageType;
	}

	public void setShMessageType(char shMessageType) {
		this.shMessageType = shMessageType;
	}

	public char getShVersionType() {
		return shVersionType;
	}

	public void setShVersionType(char shVersionType) {
		this.shVersionType = shVersionType;
	}

	public char getShVersion() {
		return shVersion;
	}

	public void setShVersion(char shVersion) {
		this.shVersion = shVersion;
	}

	public String getnUID() {
		return nUID;
	}

	public void setnUID(String nUID) {
		this.nUID = nUID;
	}

	public long getnPlayerID() {
		return nPlayerID;
	}

	public void setnPlayerID(long nPlayerID) {
		this.nPlayerID = nPlayerID;
	}

	public long getnSequence() {
		return nSequence;
	}

	public void setnSequence(long nSequence) {
		this.nSequence = nSequence;
	}

	public short getShHeaderLen() {
		return shHeaderLen;
	}

	public void setShHeaderLen(short shHeaderLen) {
		this.shHeaderLen = shHeaderLen;
	}

}
