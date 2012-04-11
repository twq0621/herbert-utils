io采用netty框架，AMF3协议，数据库层采用redis和mysql。

运行mvn package会生成dto的as文件，客户端可以直接拿来用，https://herbert-utils.googlecode.com/svn/trunk/FlashChatRoom 项目是as客户端的实例代码

tips:
1.与客户端通讯的DTO类，名字中必须包含DTO或_C2S或者_S2C，否则无法生成客户端的as文件。
Known issues:
-------------
Mixing arrays and generics does not work well. For example the following property 
would be translated wrong: "public ArrayList<Rollercoaster[]> rollerMatrix;"