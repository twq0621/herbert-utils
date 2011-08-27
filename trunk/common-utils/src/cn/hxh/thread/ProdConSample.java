package cn.hxh.thread;

class Producer implements Runnable
{
  private Drop drop;
  private String importantInfo[] = {
    "Mares eat oats",
    "Does eat oats",
    "Little lambs eat ivy",
    "A kid will eat ivy too"
  };

  public Producer(Drop drop) { this.drop = drop; }

  public void run()
  {
    for (int i = 0; i < importantInfo.length; i++)
    {
      drop.put(importantInfo[i]);
    }
    drop.put("DONE");
  }
}

class Consumer implements Runnable
{
  private Drop drop;

  public Consumer(Drop drop) { this.drop = drop; }

  public void run()
  {
    for (String message = drop.take(); !message.equals("DONE");
         message = drop.take())
    {
      System.out.format("MESSAGE RECEIVED: %s%n", message);
    }
  }
}

class Drop
{
  //Message sent from producer to consumer.
  private String message;
  
  //True if consumer should wait for producer to send message,
  //false if producer should wait for consumer to retrieve message.
  private boolean empty = true;

  //Object to use to synchronize against so as to not "leak" the
  //"this" monitor
  private Object lock = new Object();

  public String take()
  {
    synchronized(lock)
    {
      //Wait until message is available.
      while (empty)
      {
        try
        {
          lock.wait();
        }
        catch (InterruptedException e) {}
      }
      //Toggle status.
      empty = true;
      //Notify producer that status has changed.
      lock.notifyAll();
      return message;
    }
  }

  public void put(String message)
  {
    synchronized(lock)
    {
      //Wait until message has been retrieved.
      while (!empty)
      {
        try
        { 
          lock.wait();
        } catch (InterruptedException e) {}
      }
      //Toggle status.
      empty = false;
      //Store message.
      this.message = message;
      //Notify consumer that status has changed.
      lock.notifyAll();
    }
  }
}

public class ProdConSample
{
  public static void main(String[] args)
  {
    Drop drop = new Drop();
    (new Thread(new Producer(drop))).start();
    (new Thread(new Consumer(drop))).start();
  }
}
