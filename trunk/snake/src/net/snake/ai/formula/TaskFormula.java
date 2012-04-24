package net.snake.ai.formula;

import net.snake.commons.script.SRole;
import net.snake.commons.script.EvtTaskFormula;

public class TaskFormula implements EvtTaskFormula{


	@Override
	public int taskExp10(SRole sCharacter) {
		return sCharacter.getGrade() * 400;
	}

	@Override
	public int taskExp20(SRole sCharacter) {
		return sCharacter.getGrade() * 1000;
	}

	@Override
	public int taskWeekExp100(SRole sCharacter) {
		return sCharacter.getGrade() * 3000;
	}

	@Override
	public int taskWeekExp200(SRole sCharacter) {
		return sCharacter.getGrade() * 8000;
	}

	@Override
	public int taskWeekZhenqi200(SRole sCharacter) {
		return sCharacter.getGrade() * 100;
	}

	@Override
	public int taskZhenqi20(SRole sCharacter) {
		return sCharacter.getGrade() * 100;
	}
}
