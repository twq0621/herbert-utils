package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import net.snake.across.util.AcrossUtil;
import net.snake.commons.TimeExpression;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.activities.bean.DoubActivity;
import net.snake.ibatis.SystemFactory;
import net.snake.shell.Options;


public class DoubActivityManager implements CacheUpdateListener{
	private static final Logger logger = Logger.getLogger(DoubActivityManager.class);
	public static final long BeingNotifyTime1=30*60*1000;	//第一次提前通知
	public static final long BeingNotifyTime2=10*60*1000;	//第二次提前通知
	
	private static DoubActivityManager doubActivityManager = null;
	private static DoubActivity DefaultDoubActivity;
	private DoubActivity DBDoubActivity;
	private DoubActivity cacheDoubActivity;
	private boolean reload=false;
	
	private DoubActivityDAO doubActivityDAO = new DoubActivityDAO(
			SystemFactory.getGamedataSqlMapClient());
	

	public boolean isReload() {
		return reload;
	}
	public void setReload(boolean reload){
		this.reload=reload;
	}
	
	private DoubActivityManager() {
		swichDefaultDoubActivity();
	}
	public DoubActivity getCacheDoubActivity() {
		return cacheDoubActivity;
	}
	public DoubActivity getDBDoubActivity(){
		return DBDoubActivity;
	}
	public void swichDBDoubactivity(){
		//跨服版本只支持默认倍数
		AcrossUtil.checkNoAcross();
		cacheDoubActivity=getDBDoubActivity();
	}
	public void swichDefaultDoubActivity(){
		cacheDoubActivity=getDefaultDoubActivity();
	}
	private static DoubActivity getDefaultDoubActivity(){
		if(DefaultDoubActivity==null){
			DefaultDoubActivity=new DoubActivity();
			DefaultDoubActivity.setSid(Options.ServerId);
			DefaultDoubActivity.setDaguaiExp(0);
			DefaultDoubActivity.setBaolv(1);
			DefaultDoubActivity.setDazuoExp(1);
			DefaultDoubActivity.setDazuoZhenqi(1);
			DefaultDoubActivity.setTimeExp("[*][*][*][*]");
		}
		return DefaultDoubActivity;
	}
	public boolean checkSendTime1(long now){
		if(getDBDoubActivity()==null||getDBDoubActivity().getTimeExp()==null||getDBDoubActivity().getTimeExp().equals("")){
			return false;
		}
		TimeExpression te=new TimeExpression(getDBDoubActivity().getTimeExp());
		return te.isExpressionTime(now+BeingNotifyTime1);
	}
	public boolean checkSendTime2(long now){
		if(getDBDoubActivity()==null||getDBDoubActivity().getTimeExp()==null||getDBDoubActivity().getTimeExp().equals("")){
			return false;
		}
		TimeExpression te=new TimeExpression(getDBDoubActivity().getTimeExp());
		return te.isExpressionTime(now+BeingNotifyTime2);
	}
	public boolean checkStartTime(long now){
		if(getDBDoubActivity()==null||getDBDoubActivity().getTimeExp()==null||getDBDoubActivity().getTimeExp().equals("")){
			return false;
		}
		TimeExpression te=new TimeExpression(getDBDoubActivity().getTimeExp());
		return te.isExpressionTime(now);
	}

	public String getNext(){
		if(getDBDoubActivity()==null||getDBDoubActivity().getTimeExp()==null||getDBDoubActivity().getTimeExp().equals("")){
			return "unrefresh";
		}
		TimeExpression te=new TimeExpression(getDBDoubActivity().getTimeExp());
		return te.getStart(System.currentTimeMillis());
	}
	public static DoubActivityManager getInstance() {
		if (doubActivityManager == null) {
			doubActivityManager = new DoubActivityManager();
		}
		return doubActivityManager;
	}
	@Override
	public void reload() {
		try {
			//跨服版本只支持默认倍数
			reload=true;
//			DBDoubActivity = doubActivityDAO.selectByPrimaryKey(Options.ServerId);
			DBDoubActivity = doubActivityDAO.selectByPrimaryKey(0);
			if(DBDoubActivity==null||Options.IsCrossServ){
				swichDefaultDoubActivity();
				return;
			}
			if (checkStartTime(System.currentTimeMillis())) {
				swichDBDoubactivity();
			} else {
				swichDefaultDoubActivity();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "doubactivity";
	}

	
	
}
