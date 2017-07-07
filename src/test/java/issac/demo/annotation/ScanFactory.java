package issac.demo.annotation;

import java.util.concurrent.ConcurrentHashMap;

public class ScanFactory {

	private static ConcurrentHashMap<String, Object> cacheBeans = new ConcurrentHashMap<>();
	

	@SuppressWarnings("unchecked")
	public static <T> T getScanApi(Class<T> clazz) {
		if (cacheBeans.get(clazz.getName()) == null) {
			synchronized (ScanFactory.class) {
				if (cacheBeans.get(clazz.getName()) == null) {
					cacheBeans.put(clazz.getName(), new ScanApiProxy<>(clazz).proxy());
				}
				return (T) cacheBeans.get(clazz.getName());
			}

		} else {
			return (T) cacheBeans.get(clazz.getName());
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> T getScanApi(Class<T> clazz,ScanInvokeMethod scanInvokeMethod) {
		if (cacheBeans.get(clazz.getName()) == null) {
			synchronized (ScanFactory.class) {
				if (cacheBeans.get(clazz.getName()) == null) {
					cacheBeans.put(clazz.getName(), new ScanApiProxy<>(clazz,scanInvokeMethod).proxy());
				}
				return (T) cacheBeans.get(clazz.getName());
			}

		} else {
			return (T) cacheBeans.get(clazz.getName());
		}
	}

	public static void main(String[] args) {
		ScanApi scanApi = ScanFactory.getScanApi(ScanApi.class);
		System.out.println(scanApi.getClass().getName());
		scanApi.say("@xx", "xx");
		ScanApi scanApi2 = ScanFactory.getScanApi(ScanApi.class);
		System.out.println(scanApi2.getClass().getName());
	}

}
