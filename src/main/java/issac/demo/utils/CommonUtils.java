package issac.demo.utils;

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

}
