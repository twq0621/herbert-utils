package net.snake.commons.script;

import java.util.Map;

/**
 * 为了初始化脚本字串赋值，添加了此接口
 */

public interface IGlobalLanguage {
	void initData(Map<String, String> map);
}
