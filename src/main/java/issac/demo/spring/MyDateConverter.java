package issac.demo.spring;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * SpringMVC时间转化
 * @author 程高伟
 *
 * @date 2016年6月24日 下午2:54:13 
 */
public class MyDateConverter implements Converter<String, Date> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
	private Date date = null;

    @Override
    public Date convert(String source) {
		System.out.println(source);
		if (source == null || "".equals(source.trim())) {
           return null;
		}
        try {
			date = sdf.parse(source);
        } catch (ParseException e) {
            logger.error("数据转换发生了异常,要转化的数据{}",source);
            e.printStackTrace();
        }
        return date;
    }

}