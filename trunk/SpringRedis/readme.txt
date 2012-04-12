io采用netty框架，AMF3协议，数据库层采用redis和mysql，redis用protobuf框架进行存储，操作采用了spring-data-redis结合jredis框架。
mysql采用了MyBatis ORM框架,oscache缓存框架。

运行mvn package会生成dto的as文件，客户端可以直接拿来用，https://herbert-utils.googlecode.com/svn/trunk/FlashChatRoom 项目是as客户端的实例代码

TODO:IO线程和逻辑线程放在一起，有个问题是如果逻辑线程发生阻塞，会影响io线程，进而影响io的吞吐量，因此改进方向是异步的io请求都存储在一个队列中，
逻辑线程只有一个，读取队列里的消息，然后进行处理。

TODO:添加事件机制，采用QUARTZ框架。

tips:
1.与客户端通讯的DTO类，名字中必须包含DTO或_C2S或者_S2C，否则无法生成客户端的as文件。
Known issues:
-------------
Mixing arrays and generics does not work well. For example the following property 
would be translated wrong: "public ArrayList<Rollercoaster[]> rollerMatrix;"