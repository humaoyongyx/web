package issac.demo.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
/**
 *   eclipse.ini / myeclipse.ini，在最后面插入以下两行并保存：
        -Xbootclasspath/a:lombok.jar
        -javaagent:lombok.jar
 * @author admin
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlainObject {
	
	private int id;
	@NonNull
	private String name;
}
