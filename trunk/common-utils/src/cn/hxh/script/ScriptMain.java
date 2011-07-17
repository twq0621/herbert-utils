package cn.hxh.script;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptMain {

	public static ScriptEngine engine;
	/**
	 * @param args
	 * @throws ScriptException
	 */
	public static void main(String[] args) throws ScriptException {
		// create a script engine manager
		ScriptEngineManager factory = new ScriptEngineManager();
		// create a JavaScript engine
		engine = factory.getEngineByName("JavaScript");
		communicateWithJava();
	}
	public static void basic() throws ScriptException{
		// evaluate JavaScript code from String
		engine.eval("print('Hello, World')");
	}
	
	public static void usingJavaArray() throws ScriptException{
		engine.eval("var a = java.lang.reflect.Array.newInstance(java.lang.String, 5);");
		engine.eval("a[0] = \"scripting is great!\";");
		engine.eval("print(a.length);");
	}
	public static void communicateWithJava() throws ScriptException{
		engine.put("x", "ooxx");
		engine.eval("print(x.length);");
	}
	
}
