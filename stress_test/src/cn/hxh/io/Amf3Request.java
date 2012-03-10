package cn.hxh.io;

import java.util.HashMap;

import cn.hxh.codec.ASObject;
import cn.hxh.codec.Amf3Writer;

/**
 * Amf3 Request
 * 
 * @author bright
 * @version 20111109
 */
public class Amf3Request {

	private Object _first;

	private String _method;

	private Object[] _args;

	private boolean _isValid;

	public Amf3Request() {
		_isValid = false;
	}

	public Amf3Request(Object first) {
		_first = first;
		_isValid = false;
	}

	public Amf3Request(String method) {
		_method = method;
		_args = null;
		_isValid = true;
	}

	public Amf3Request(String method, Object... args) {
		_method = method;
		_args = args;
		_isValid = true;
	}

	public static Amf3Request create(Object first, Object obj) {
		Amf3Request request = new Amf3Request(first);
		request.parse(obj);
		return request;
	}

	public static Amf3Request create(Object obj) {
		Amf3Request request = new Amf3Request();
		request.parse(obj);
		return request;
	}

	public String getMethod() {
		return _method;
	}

	public Object[] getArgs() {
		return _args;
	}

	public void parse(Object obj) {
		_isValid = false;
		if (obj instanceof ASObject) {
			ASObject call = (ASObject) obj;
			if (call.containsKey("method")) {
				_method = (String) call.get("method");
				if (call.containsKey("args")) {
					Object[] args = (Object[]) call.get("args");
					if (_first != null) {
						_args = new Object[args.length + 1];
						_args[0] = _first;
						System.arraycopy(args, 0, _args, 1, args.length);
					} else {
						_args = args;
					}
				} else if (_first != null) {
					_args = new Object[] { _first };
				}
				_isValid = true;
			}
		}
	}

	public boolean isVaild() {
		return _isValid;
	}

	public HashMap<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("method", _method);
		map.put("args", _args);
		return map;
	}

	public byte[] toBytes() {
		if (_isValid) {
			return Amf3Writer.write(toMap());
		} else {
			return null;
		}
	}
}
