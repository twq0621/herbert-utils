package stoptool;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class StopServer {
	public static void main(String[] args) throws Exception, UnknownHostException, IOException {
		String port = "23";
		if (args.length > 0) {
			port = args[0];
		}

		Socket ss = new Socket("127.0.0.1", Integer.parseInt(port));
		ss.setSoTimeout(1000);
		ss.getOutputStream().write(getContent());
		Thread.sleep(3000);
		ss.close();
	}

	public static byte[] getContent() throws IOException {
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bao);
		int length = 8;
		dos.writeShort(127);
		dos.writeShort(length);
		dos.writeInt(30107);
		return bao.toByteArray();
	}
}
