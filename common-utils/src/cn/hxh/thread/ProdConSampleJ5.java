package cn.hxh.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Producer5 implements Runnable
{
  private Drop5 drop;
  private String importantInfo[] = {
    "Mares eat oats",
    "Does eat oats",
    "Little lambs eat ivy",
    "A kid will eat ivy too"
  };

  public Producer5(Drop5 drop) { this.drop = drop; }

  public void run()
  {
    for (int i = 0; i < importantInfo.length; i++)
    {
      drop.put(importantInfo[i]);
    }
    drop.put("DONE");
  }
}

class Consumer5 implements Runnable
{
  private Drop5 drop;

  public Consumer5(Drop5 drop) { this.drop = drop; }

  public void run()
  {
    for (String message = drop.take(); !message.equals("DONE");
         message = drop.take())
    {
      System.out.format("MESSAGE RECEIVED: %s%n", message);
    }
  }
}

class Drop5
{
  //Message sent from producer to consumer.
  private String message;
  
  //True if consumer should wait for producer to send message,
  //false if producer should wait for consumer to retrieve message.
  private boolean empty = true;

  //Java5 concurrency objects to allow for finer-grained concurrency
  private Lock lock = new ReentrantLock();
  private Condition notEmpty = lock.newCondition();
  private Condition notFull = lock.newCondition();

  public String take()
  {
    try
    {
      lock.tryLock(5, TimeUnit.MINUTES);
      
      //Wait until message is available.
      while (empty)
      {
        try
        {
          notEmpty.await();
        }
        catch (InterruptedException e) {}
      }
      //Toggle status.
      empty = true;
      //Notify producer that status has changed.
      notFull.signalAll();
      return message;
    }
    catch (InterruptedException intEx)
    {
      System.err.println("Attempting to acquire the take lock failed; deadlock?");
      intEx.printStackTrace();
    }
    finally
    {
      lock.unlock();
    }
    //Compiler requires this
    return "";
  }

  public void put(String message)
  {
    try
    {
      lock.tryLock(5, TimeUnit.MINUTES);
      
      //Wait until message has been retrieved.
      while (!empty)
      {
        try
        { 
          notFull.await();
        } catch (InterruptedException e) {}
      }
      //Toggle status.
      empty = false;
      //Store message.
      this.message = message;
      //Notify consumer that status has changed.
      notEmpty.signalAll();
    }
    catch (InterruptedException intEx)
    {
      System.err.println("Attempting to acquire the put lock failed; deadlock?");
      intEx.printStackTrace();
    }
    finally
    {
      lock.unlock();
    }
  }
}

public class ProdConSampleJ5
{
  public static void main(String[] args)
  {
    Drop5 drop = new Drop5();
    (new Thread(new Producer5(drop))).start();
    (new Thread(new Consumer5(drop))).start();
  }
}
