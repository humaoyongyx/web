package issac.demo.annotation;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

public class ScanInvokeMethodImp extends ScanInvokeMethodAbstractImp {

	@Override
	void invoke(String methodValue, Map<String, Object> paramValues, Method method) {
		System.out.println(method.getDeclaringClass().getName()+"->"+method.getName());
		System.out.println("\t->@ScanMethod(\""+methodValue+"\")");
		System.out.print("\t\t->");
		for (Entry<String, Object>entry:paramValues.entrySet()) {
			if (entry.getKey().startsWith("arg")) {
				System.out.print(entry.getKey()+":"+entry.getValue()+" , ");
			}else {
				System.out.print("@ScanParam(\""+entry.getKey()+"\")"+":"+entry.getValue()+" , ");
			}
			
		}
		System.out.println("");
		
	}





}
