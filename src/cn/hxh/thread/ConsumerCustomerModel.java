package cn.hxh.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者模型，简单的消息队列机制，可以用作消息的缓冲，其实也是一种线程间通讯的方式
 * TODO 加入notify机制，在queue内容为0时线程sleep，有请求过来继续执行,wait,notify
 * @author hexuhui
 *
 */
public class ConsumerCustomerModel {

	private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);

	public void init() {
		Thread sendThread = new Thread(new SendThread(queue));
		sendThread.setDaemon(true);
		sendThread.start();
	}

	public void addToQueue(String s) {
		try {
			queue.offer(s, 50, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ConsumerCustomerModel model = new ConsumerCustomerModel();
		model.init();
		model.addToQueue("hxh1");
		model.addToQueue("hxh2");
		Thread.sleep(1000);
	}
}

class SendThread implements Runnable {

	private LinkedBlockingQueue<String> queue;

	public SendThread(LinkedBlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		while (true) {
			try {
				send();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				clearAll();
			}
		}
	}

	private void clearAll() {
		while (queue.size() > 0) {
			try {
				send();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void send() throws InterruptedException {
		if (queue.size() > 0) {
			String str = queue.poll(50, TimeUnit.MILLISECONDS);
			System.out.println(str);
		}
	}

}