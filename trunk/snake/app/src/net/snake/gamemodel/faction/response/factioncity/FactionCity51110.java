package net.snake.gamemodel.faction.response.factioncity;

import net.snake.gamemodel.faction.bean.FactionCity;
import net.snake.gamemodel.faction.bean.FactionCityConfig;
import net.snake.gamemodel.faction.persistence.FactionCityConfigManager;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.netio.ServerResponse;

/**
 * 返回税率与税收面板
 * 
 * @author serv_dev
 * 
 */
public class FactionCity51110 extends ServerResponse {

	public FactionCity51110(FactionCity factionCity) {
		this.setMsgCode(51110);
		FactionCityConfig fcc = FactionCityConfigManager.getInstance().getFactionCityConfig();
		if (factionCity == null) {
			this.writeInt(FactionCityManager.defaltTaxRate);
			this.writeDouble(0);
			this.writeDouble(0);
			this.writeByte(fcc.getAlltaxsScale() / 100);
			this.writeByte(fcc.getTaxrateMin() / 100);
			this.writeByte(fcc.getTaxrateMax() / 100);
		} else {
			this.writeInt(factionCity.getTaxRate());
			this.writeDouble(factionCity.getYdTaxes());
			this.writeDouble(factionCity.getAllTaxes());
			this.writeByte(fcc.getAlltaxsScale() / 100);
			this.writeByte(fcc.getTaxrateMin() / 100);
			this.writeByte(fcc.getTaxrateMax() / 100);
		}
	}
}
