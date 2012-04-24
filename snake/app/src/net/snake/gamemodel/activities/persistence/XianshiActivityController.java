/**
 * 
 */
package net.snake.gamemodel.activities.persistence;

import java.util.ArrayList;
import java.util.List;

import net.snake.commons.TimeExpression;
import net.snake.gamemodel.activities.bean.XianshiActivity;
import net.snake.gamemodel.activities.bean.XianshiActivityReward;


/**
 * 限时活动类别子类数据控制 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */

public class XianshiActivityController {
	private XianshiActivity xianshiActivity;
	private List<XianshiActivityReward> list = new ArrayList<XianshiActivityReward>();

	public XianshiActivityController(XianshiActivity xianshiActivity) {
		this.xianshiActivity = xianshiActivity;
	}

	public void addXianshiActivityReward(XianshiActivityReward xar) {
		list.add(xar);
	}

	public List<XianshiActivityReward> getNowActivityList() {
		List<XianshiActivityReward> tempList = new ArrayList<XianshiActivityReward>();
		XianshiActivityReward xar = null;
		if(list==null){
			return tempList;
		}
		for (int i = 0; i < list.size(); i++) {
			XianshiActivityReward xa = list.get(i);
			if (xa.isTimeExpression()||xa.getType()==2) {
				if (xa.getType() != 2) {
					tempList.add(xa);
				} else {
					if (xar == null) {
						xar = new XianshiActivityReward();
						xar.setId(0);
						xar.setType(xa.getType());
						xar.setGoodLimite(xa.getGoodLimite());
						xar.setDesc(xa.getDescI18n());
						xar.setDescI18n(xa.getDescI18n());
						xar.setTimeLimit(xa.getTimeLimit());
						xar.setRewardGoods(xa.getRewardGoods());
						xar.setRewardLijin(xa.getRewardLijin());
						xar.setOrder(xa.getOrder());
						xar.init();
						tempList.add(xar);
					}
					xar.addLijinXa(xa);
				}
			}
		}
		return tempList;
	}

	/**
	 * 
	 * @param uinum
	 * @return
	 */
	public XianshiActivityReward getXianshiActivityRewardByItemId(int itemId) {
		List<XianshiActivityReward> tempList =getList();
		for (XianshiActivityReward xa : tempList) {
			if (xa.getId() == itemId) {
				return xa;
			}
		}
		return null;
	}

	public XianshiActivity getXianshiActivity() {
		return xianshiActivity;
	}

	public void setXianshiActivity(XianshiActivity xianshiActivity) {
		this.xianshiActivity = xianshiActivity;
	}

	public List<XianshiActivityReward> getList() {
		return list;
	}

	public void setList(List<XianshiActivityReward> list) {
		this.list = list;
	}
	public boolean isTimeExpression() {
		XianshiActivity xa=this.getXianshiActivity();
		if (xa== null ) {
			return false;
		}
		if(xa.getTimeLimit()==null||xa.getTimeLimit().length()<1){
			return true;
		}
		TimeExpression te = new TimeExpression(xa.getTimeLimit());
		return te.isExpressionTime(System.currentTimeMillis());
	}

}
