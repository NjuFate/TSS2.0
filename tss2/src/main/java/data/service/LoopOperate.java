package data.service;

import java.lang.reflect.Method;

public interface LoopOperate{
	public void operate(Object o1,Object o2, Method method, String field) throws Exception;
}