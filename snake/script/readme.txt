eclipse-快捷键
	alt+? 上下文提示,当不知道格式怎么写时用这个快捷键会很快得到帮助
	ctrl+shfit+f 格式化代码
	

脚本可以监听哪些事件?
	列在net.snake.script.events包中是脚本可以监听的事件
	监听事件时,implement 相应的事件接口,在生成的方法中,调用 对像的方法,或者SApi类的方法即可

启用脚本,需要把脚本类名,添加到AojianScriptDefine类中去
	
如果将脚本上传到202服务器上去
	AojianScript/build/build.xml 右键->Runas->Ant Build
