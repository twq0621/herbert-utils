package net.snake.gamemodel.team.bean;

import java.util.Comparator;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.logic.Team;

public class TeamComparator implements Comparator<Hero> {
	@Override
	public int compare(Hero c1, Hero c2) {
		Team o1 = c1.getMyTeamManager().getMyTeam();
		Team o2 = c2.getMyTeamManager().getMyTeam();
		if (o1 == null || o2 == null) {
			return 0;
		}
		if (o1.getTeamPopulation() > o2.getTeamPopulation()) {
			return -1;
		} else if (o1.getTeamPopulation() < o2.getTeamPopulation()) {
			return 1;
		} else {
			if (o1.getTeamLevel() > o2.getTeamLevel()) {
				return -1;
			}
			return 0;
		}
	}

}
