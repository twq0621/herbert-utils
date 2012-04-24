package net.snake.consts;
/**
 * 绑定类型。
 * @author serv_dev
 *
 */
public interface BindChangeType {
	/**不绑定*/
	public final static byte NOTBIND = 1;//
	/**掉落绑定,产生时绑定*/
	public final static byte BIND = 2;//
	/**佩戴绑定*/
	public final static byte DRESSBIND = 3;//
}
