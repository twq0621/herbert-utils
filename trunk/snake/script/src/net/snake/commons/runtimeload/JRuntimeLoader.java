package net.snake.commons.runtimeload;

public interface JRuntimeLoader {

	public abstract Object runScript(String classname, String method, Object... args) throws Exception;
	public abstract Object newObject(String classname) throws Exception;
	public abstract Object newObject(String classname,Object...args) throws Exception;
}
