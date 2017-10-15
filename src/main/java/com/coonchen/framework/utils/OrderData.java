package com.coonchen.framework.utils;



import org.springframework.core.PriorityOrdered;

public class OrderData<T> implements PriorityOrdered {
	private int order;
	private T data;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return data + "\t" + order;
	}
}
