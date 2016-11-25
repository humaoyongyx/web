package issac.demo.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import issac.demo.model.auto.City;

public class CommonUtils {

	//去掉两端的空白，包括全角空格以及Excel特殊的空白
	public static String trim(String value) {
		String regex = "[\\u00a0\\u3000]";
		return value.replaceAll(regex, " ").trim();
	}
	public static String unicode2String(String asciicode) {
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

	/** 
	* 将字符串转成unicode 
	* @param str 待转字符串 
	* @return unicode字符串 
	*/
	public static String string2Unicode(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); //取出高8位 
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF); //取出低8位 
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);

		}
		return (new String(sb));
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

	public static <T> T transferClass(Object origin, Class<T> target) {
		
		if (origin == null || target == null) {
			return null;
		}

		Field[] originFields = origin.getClass().getDeclaredFields();
		Map<String, String> originMap = new HashMap<>();
		for (Field field : originFields) {
			originMap.put(field.getName(), field.getName());
		}
		Field[] targetFields = target.getDeclaredFields();

		T targetObject = null;
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

	public static <T> T setMethod(String fieldName, T target, Object value) {
		if (fieldName == null || fieldName.trim().equals("")) {
			return null;
		}

		String methodName="set"+ StringUtils.capitalize(fieldName);
		try {
			Method method = target.getClass().getDeclaredMethod(methodName, getMethodParamTypes(target, methodName));
			method.invoke(target, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;

	}

	public static Boolean checkNullObject(List<String> fieldNames, Object target) {
		if (fieldNames == null || fieldNames.isEmpty()) {
			return null;
		}
		boolean flag = true;
		for (String fieldName : fieldNames) {
			Object value = getMethod(fieldName, target);
			if (value != null) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public static Object getMethod(String fieldName, Object target) {
		if (fieldName == null || fieldName.trim().equals("")) {
			return null;
		}
		String methodName = "get" + StringUtils.capitalize(fieldName);
		Object returnValue = null;
		try {
			Method method = target.getClass().getDeclaredMethod(methodName);
			returnValue = method.invoke(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public static String base64Encode(String value) {
		String encodeBase64String = null;
		encodeBase64String = Base64.encodeBase64String(value.getBytes());
		return encodeBase64String;
	}

	public static String base64Decode(String value) {
		String decodeBase64String = null;
		byte[] decodeBase64 = Base64.decodeBase64(value);
		decodeBase64String = new String(decodeBase64);
		return decodeBase64String;
	}

	public static Map<String, Object> beanToMap(Object bean) {
		Field[] declaredFields = bean.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<>();
		for (Field field : declaredFields) {
			String name = field.getName();
			Object value = getMethod(name, bean);
			map.put(name, value);
		}
		return map;
	}

	public static boolean isNotEmpty(String... values) {
		if (values != null) {
			for (String value : values) {
				if (value == null || value.trim().equals("")) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public static String getUrlAction(String url) {
		if (url == null) {
			return "";
		} else {
			int lastIndexOf = url.lastIndexOf("/");
			String urlAction = url.substring(lastIndexOf + 1);
			return urlAction;
		}
	}

	public static String getUrlPath(String url) {
		if (url == null) {
			return "";
		} else {
			int lastIndexOf = url.lastIndexOf("/");
			String urlPath = url.substring(0, lastIndexOf + 1);
			return urlPath;
		}
	}

	public static String getFileExtension(String fileName) {
		if (isNotEmpty(fileName)) {
			String[] split = fileName.split("\\.");
			return "." + split[split.length - 1];
		}
		return null;
	}
    
	
	public static void outputToPage(String fileName, HttpServletResponse response,  byte[] data,String contentType) {
		OutputStream os = null;
		try {
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf8") );
			response.setContentType(contentType);
			os = response.getOutputStream();
			os.write(data);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void outputToPage(String fileName, HttpServletResponse response,  byte[] data) {
		OutputStream os = null;
		try {
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf8") );
			response.setContentType("application/octet-stream");
			os = response.getOutputStream();
			os.write(data);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
		if (map == null)
			return null;

		T obj = beanClass.newInstance();

		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}

			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}

		return obj;
	}

	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();

		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}

		return map;
	}

	public static void main(String[] args) {
		City city = new City();
		city.setId(1);
		city.setName("sdsd");
		try {
			Map<String, Object> objectToMap = objectToMap(city);
			System.out.println(objectToMap);
			City mapToObject = mapToObject(objectToMap, City.class);
			System.out.println(mapToObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}