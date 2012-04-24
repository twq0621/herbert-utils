package net.snake.gamemodel.heroext.dantian.response;

import java.io.IOException;

import net.snake.netio.ServerResponse;


public class UpGradeInfo53154 extends ServerResponse{

	public UpGradeInfo53154(int grade,int luck,int maxluck,int prob,int needgood,int goodsum) throws IOException{
		setMsgCode(53154);
		writeByte(grade);
		writeInt(luck);
		writeInt(maxluck);
		writeInt(prob);
		writeInt(needgood);
		writeInt(goodsum);
	}
}
