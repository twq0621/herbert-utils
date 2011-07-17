package cn.hxh.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("xxx.xxx.xxx.xxx", 10000);
		InputStream is = socket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.flush();
		BufferedReader line = new BufferedReader(new InputStreamReader(
				System.in));
		out.println(line.readLine());
		line.close();
		out.close();
		in.close();
		socket.close();
	}
}
