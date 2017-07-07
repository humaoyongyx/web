package issac.demo.annotation;

import java.lang.reflect.Method;

public interface ScanInvokeMethod {
	public Object invoke(Class<?>clazz,Method method, Object[] args) throws Throwable ;
}
