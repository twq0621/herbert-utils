package net.snake.gamemodel.map.logic;


import net.snake.gamemodel.map.bean.MapDate;
import net.snake.shell.Options;


/**
 * 地图解析
 * 
 * @author serv_dev
 * 
 */
public class DbMapParse {


	/**
	 * 初始化地图信息
	 * 
	 * @param mapdate
	 * @return
	 * @throws Exception
	 */
	public static GameMap initTSMapInfo(MapDate mapdate) {
		// LOGGER.info("正在加载地图：{}",mapdate.getMapId());
		// mapdate.get
		GameMap tsMap = null;
		if (mapdate.getGongchengType() < 1) {
			tsMap = new GameMap();
		} else if (mapdate.getGongchengType() == 1) {
			tsMap = new GongchengTsMap();
		} else if (mapdate.getGongchengType() == 2) {
			tsMap = new LunjiantaiTsMap();
		} else if (mapdate.getGongchengType() == 3) {
			tsMap = new KuafuZhanTsMap();
		}

		if (Options.IsCrossServ) {
			if (!(tsMap instanceof KuafuZhanTsMap)) {
				return null;
			}
		}
		tsMap.setId(mapdate.getMapId());
		tsMap.setPvpModel(mapdate.getPermitPvp());
		tsMap.setName(mapdate.getFileName());
		tsMap.setShowName(mapdate.getMapNameI18n());
		tsMap.setEnterX(mapdate.getEnterX().shortValue());
		tsMap.setEnterY(mapdate.getEnterY().shortValue());
		tsMap.setReliveX(mapdate.getReliveX().shortValue());
		tsMap.setReliveY(mapdate.getReliveY().shortValue());
		tsMap.setInstanceModelId(mapdate.getInstanceId());
		tsMap.setReliveSceneId(mapdate.getReliveMapId());
		tsMap.setCharacterEyeShotLimit(mapdate.getEyeshotLimit());
		tsMap.setPkProtect(mapdate.getPkProtect());
		tsMap.setPkGradeProtect(mapdate.getPkGradeProtect() == 0 ? true : false);
		tsMap.setBossDesc(mapdate.getBossDescI18n());
		tsMap.setBossDropGoods(mapdate.getBossDropGoodsI18n());
		tsMap.setBossTimeDesc(mapdate.getBossTimeDesc());
		tsMap.setExerciseDesc(mapdate.getExerciseDescI18n());
		tsMap.setMonsterDesc(mapdate.getMonsterDescI18n());
		tsMap.setEnterLevelLimit(mapdate.getEnterLevelLimit());
		initBlockPoint(tsMap, mapdate);
		// LOGGER.info("正在加载地图结束：{}",mapdate.getMapId());
		return tsMap;
	}

	private static void initBlockPoint(GameMap tsMap, MapDate mapDate) {
		int h = mapDate.getWidth();
		int v = mapDate.getHeight();
		tsMap.setGrid_h((short) h);
		tsMap.setGrid_v((short) v);
		short[][] tiles = new short[h][v];
		String block = mapDate.getBlock();
		String[] sms = block.split(";");
		int smlen = sms.length;
		int x =0;
		int y =0;
		for (int i = 0; i < smlen; i++) {
			String[] sm=sms[i].split(",");
			String s=sm[0];
			if (("1").equals(s)) {
				tiles[x][y] = 1;
			}
			y++;
			if (y==v) {
				y=0;
				x++;
			}
			
		}
		mapDate.setBlock(null);
		tsMap.setTiles(tiles);
		tsMap.initAllPoint();
	}

//	private static void initBlockPointForXML(GameMap tsMap, MapDate mapDate)
//			throws Exception {
//		SAXReader saxReader = new SAXReader();
//		Document document = saxReader.read(new ByteArrayInputStream(mapDate
//				.getBlock().getBytes()));
//		int h = mapDate.getWidth();
//		int v = mapDate.getHeight();
//		tsMap.setGrid_h((short) h);
//		tsMap.setGrid_v((short) v);
//		short[][] tiles = new short[h][v];
//		int[][] exptiles = new int[h][v];
//		int[][] fuhuotiles = new int[h][v];
//		List<int[]> wujianFuHuo = new ArrayList<int[]>();
//		List<int[]> youjianFuHuo = new ArrayList<int[]>();
//		Iterator<Element> tileiterator = document.getRootElement()
//				.elementIterator();
//		while (tileiterator.hasNext()) {
//			Element element = tileiterator.next();
//			short x = element.numberValueOf("@x").shortValue();
//			short y = element.numberValueOf("@y").shortValue();
//			if (element.attributeValue("s").equals("1")) {
//				// blockMap.put(ArithmeticUtils.shortToInt(x, y), (short) 1);
//				try {
//					tiles[x][y] = 1;
//				} catch (Exception e) {
//					logger.warn("地图:" + mapDate.getMapName() + "xml坐标越界");
//					continue;
//				}
//			} else {
//				// 经验区跟复活区肯定不是障碍物点
//				if (tsMap instanceof KuafuZhanTsMap) {
//					String exp = element.attributeValue("e");
//					if (exp != null) {
//						exptiles[x][y] = Integer.parseInt(exp);
//					}
//
//					String fuhuo = element.attributeValue("f");
//					if (fuhuo != null) {
//						int fuhuoflag = Integer.parseInt(fuhuo);
//						fuhuotiles[x][y] = fuhuoflag;
//						if (fuhuoflag == 1) {
//							wujianFuHuo.add(new int[] { x, y });
//						} else if (fuhuoflag == 2) {
//							youjianFuHuo.add(new int[] { x, y });
//						}
//					}
//				}
//			}
//		}
//		mapDate.setBlock(null);
//		tsMap.setTiles(tiles);
//		if (tsMap instanceof KuafuZhanTsMap) {
//			tsMap.setExpTiles(exptiles);
//			tsMap.setFuhuoTiles(fuhuotiles);
//			tsMap.setWujianFuHuo(wujianFuHuo);
//			tsMap.setYoujianFuHuo(youjianFuHuo);
//		}
//		tsMap.initAllPoint();
//	}

}
