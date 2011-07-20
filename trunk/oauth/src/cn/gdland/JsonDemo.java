package cn.gdland;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonDemo {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		User user = mapper.readValue(new File("E:/user.json"), User.class);
		System.out.println(user.getName().getFirst());
		System.out.println(user.getName().getLast());
		System.out.println(user.getGender());
	}

}
