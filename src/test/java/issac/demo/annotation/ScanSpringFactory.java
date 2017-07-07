package issac.demo.annotation;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.FactoryBean;

public class ScanSpringFactory<T> implements FactoryBean<T> {

	private static ConcurrentHashMap<String, Object> cacheBeans = new ConcurrentHashMap<>();
	
	private String clazzName;
	


	@SuppressWarnings("unchecked")
	@Override
	public T getObject() throws Exception {
		Class<?> clazz = Class.forName(clazzName);
		if (cacheBeans.get(clazz.getName()) == null) {
			synchronized (ScanSpringFactory.class) {
				if (cacheBeans.get(clazz.getName()) == null) {
					cacheBeans.put(clazz.getName(), new ScanApiProxy<>(clazz).proxy());
				}
				return (T) cacheBeans.get(clazz.getName());
			}

		} else {
			return (T) cacheBeans.get(clazz.getName());
		}
	}


	@Override
	public Class<?> getObjectType() {
		try {
			return Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean isSingleton() {
		return false;
	}


	public String getClazzName() {
		return clazzName;
	}


	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	

}
