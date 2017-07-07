package issac.demo.annotation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ScanInvokeMethodAbstractImp implements ScanInvokeMethod {

	@Override
	public Object invoke(Class<?> clazz, Method method0, Object[] args) throws Throwable {

		Scan scan = clazz.getAnnotation(Scan.class);
		if (scan == null) {
			return null;
		}

		Method[] interfaceMethods = clazz.getMethods();
		
		for (Method method : interfaceMethods) {
			if (method0.getName().equals(method.getName())&&checkParams(method0, method)) {
				Map<String, Object> paramValues=new LinkedHashMap<>();
				String scanMethodValue="";
				if (method.isAnnotationPresent(ScanMethod.class)) {
					ScanMethod scanMethod = method.getAnnotation(ScanMethod.class);
					scanMethodValue=scanMethod.value();
					Parameter[] parameters = method.getParameters();
					int i=0;
					for (Parameter parameter : parameters) {
						if (parameter.isAnnotationPresent(ScanParam.class)) {
							ScanParam scanParam = parameter.getAnnotation(ScanParam.class);
							paramValues.put(scanParam.value(),args[i]);
						}else {
							paramValues.put(parameter.getName(),args[i]);
						}
						i++;
					}

				}
				invoke(scanMethodValue, paramValues, method0);
				
				break;
			}

		}

		return null;
	}

	private boolean checkParams(Method method0, Method method) {
		Class<?>[] method0Params = method0.getParameterTypes();
		Class<?>[] methodParams = method.getParameterTypes();
		if ((method0Params == null && methodParams == null) || (method0Params.length == 0 && methodParams.length == 0)) {
			return true;
		}

		if (method0Params.length != methodParams.length) {
			return false;
		}
		for (int i = 0; i < method0Params.length; i++) {
			if (method0Params[i].getName() != methodParams[i].getName()) {
				return false;
			}
		}

		return true;

	}

	abstract void invoke(String methodValue, Map<String, Object> paramValues, Method method);

}
