package cn.hxh.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class TestMain {

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		TestMain test = new TestMain();
		test.readError();
	}

	public void readError() throws JsonParseException, JsonMappingException, IOException {
		ErrorResponse response = mapper.readValue("{\"errno\":1}", ErrorResponse.class);
		System.out.println(response.getErrno());
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