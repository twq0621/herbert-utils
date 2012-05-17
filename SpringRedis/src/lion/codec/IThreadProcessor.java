package lion.codec;

/**
 * 实现此接口的处理器可以使用线程池中的线程进行处理,非场景同步消息
 * (聊天、email、帮会、好友、网关、心跳、副本、进入或切换场景、踢人下线、md5、排行、停止服务器、系统服务消息等 )<br/>
 * 
 * 如不实现，则进入游戏循环线程进行处理
 * @author serv_dev
 *
 */
public interface IThreadProcessor {

}
