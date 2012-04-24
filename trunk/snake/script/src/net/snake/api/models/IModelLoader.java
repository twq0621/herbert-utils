package net.snake.api.models;

public interface IModelLoader<T> {
	public void load(ClassLoader loader);
	public T getService();
}
