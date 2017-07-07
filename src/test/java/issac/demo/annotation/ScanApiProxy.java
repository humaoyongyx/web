package issac.demo.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ScanApiProxy <T>implements InvocationHandler {
	
	private Class<T> clazz;
	
	private ScanInvokeMethod scanInvokeMethod=new ScanInvokeMethodImp();


	public ScanApiProxy(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}
	
	public ScanApiProxy(Class<T> clazz, ScanInvokeMethod scanInvokeMethod) {
		super();
		this.clazz = clazz;
		this.scanInvokeMethod = scanInvokeMethod;
	}
	
	@SuppressWarnings("unchecked")
	public  T  proxy(){
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return scanInvokeMethod.invoke(clazz, method, args);
	}

}

