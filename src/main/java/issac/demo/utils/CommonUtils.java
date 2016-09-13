package issac.demo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {

	public static String unicode2Chinese(String asciicode) {
		String[] asciis = asciicode.split("\\\\u");
		String nativeValue = asciis[0];
		try {
			for (int i = 1; i < asciis.length; i++) {
				String code = asciis[i];
				nativeValue += (char) Integer.parseInt(code.substring(0, 4), 16);
				if (code.length() > 4) {
					nativeValue += code.substring(4, code.length());
				}
			}
		} catch (NumberFormatException e) {
			return asciicode;
		}
		return nativeValue;
	}

	public static String normalizePath(String path, String... paths) {
		StringBuffer sb = new StringBuffer();
		if (path == null || "".equals(path.trim())) {
			return "";
		}
		path = path.replace("\\", "/");
		if (!path.endsWith("/")) {
			path = path + "/";
		}
		sb.append(path);
		if (paths == null) {
			return sb.toString();
		}
	
		for (String p : paths) {
			if (p != null && !"".equals(p.trim())) {
				p = p.replace("\\", "/");
				if (p.startsWith("/")) {
					p = p.substring(1);
				}
				if (!p.endsWith("/")) {
					p = p + "/";
				}
				sb.append(p);
			}
		}
		return sb.toString();
	}

	public static Class[] getMethodParamTypes(Object classInstance, String methodName) {
		Class[] paramTypes = null;
		Method[] methods = classInstance.getClass().getMethods();//全部方法
		for (int i = 0; i < methods.length; i++) {
			if (methodName.equals(methods[i].getName())) {//和传入方法名匹配 
				Class[] params = methods[i].getParameterTypes();
				paramTypes = new Class[params.length];
				for (int j = 0; j < params.length; j++) {
					try {
						paramTypes[j] = Class.forName(params[j].getName());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;
			}
		}
		return paramTypes;
	}
	public static Object transferClass(Object origin, Class<?> target) {
		
		if (origin == null || target == null) {
			return null;
		}

		Field[] originFields = origin.getClass().getDeclaredFields();
		Map<String, String> originMap = new HashMap<>();
		for (Field field : originFields) {
			originMap.put(field.getName(), field.getName());
		}
		Field[] targetFields = target.getDeclaredFields();

		Object targetObject = null;
		try {
			targetObject = target.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Field field : targetFields) {
			if (originMap.get(field.getName()) != null) {
				try {
					Method originMethod = origin.getClass().getDeclaredMethod("get" + StringUtils.capitalize(field.getName()));
					String methodName = "set" + StringUtils.capitalize(field.getName());
					Method targetMethod = target.getDeclaredMethod(methodName, getMethodParamTypes(targetObject, methodName));
					Object originResult = originMethod.invoke(origin);
					targetMethod.invoke(targetObject, originResult);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return targetObject;
	}

}