tips:
1.与客户端通讯的DTO类，名字中必须包含DTO或C2S或者S2C，否则无法生成客户端的as文件。
Known issues:
-------------
Mixing arrays and generics does not work well. For example the following property 
would be translated wrong: "public ArrayList<Rollercoaster[]> rollerMatrix;"