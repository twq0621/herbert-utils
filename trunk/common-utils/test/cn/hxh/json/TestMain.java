package cn.hxh.json;

import java.io.IOException;

import junit.framework.TestCase;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class TestMain extends TestCase {

	private ObjectMapper mapper = new ObjectMapper();

	public void testReadError() throws JsonParseException, JsonMappingException, IOException {
		ErrorResponse response = mapper.readValue("{\"errno\":1}", ErrorResponse.class);
		assertEquals("1", response.getErrno());
	}
}

class ErrorResponse {
	private String errno;

	public String getErrno() {
		return errno;
	}

	public void setErrno(String errno) {
		this.errno = errno;
	}
}