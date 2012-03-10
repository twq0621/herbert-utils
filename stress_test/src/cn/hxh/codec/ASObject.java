package cn.hxh.codec;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bright
 * @version 20111106
 */
public final class ASObject extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	private boolean inHashCode = false;

	private boolean inToString = false;

	String namedType = null;

	public ASObject() {
		super();
	}

	public ASObject(String name) {
		super();
		namedType = name;
	}

	public String getType() {
		return namedType;
	}

	public void setType(String type) {
		namedType = type;
	}

	public int hashCode() {
		int h = 0;
		if (!inHashCode) {
			inHashCode = true;
			try {
				for (Map.Entry<String, Object> entry : this.entrySet()) {
					h += entry.hashCode();
				}
			} finally {
				inHashCode = false;
			}
		}
		return h;
	}

	public String toString() {
		String className = getClass().getName();
		int dotIndex = className.lastIndexOf('.');
		StringBuffer buffer = new StringBuffer();
		buffer.append(className.substring(dotIndex + 1));
		buffer.append("(").append(System.identityHashCode(this)).append(')');
		buffer.append('{');
		if (!inToString) {
			inToString = true;
			try {
				boolean pairEmitted = false;
				for (Map.Entry<String, Object> entry : this.entrySet()) {
					if (pairEmitted) {
						buffer.append(",");
					}
					buffer.append(entry.getKey()).append('=')
							.append(entry.getValue());
					pairEmitted = true;
				}
			} finally {
				inToString = false;
			}
		} else {
			buffer.append("...");
		}
		buffer.append('}');
		return buffer.toString();
	}
}