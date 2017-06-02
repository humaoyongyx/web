package issac.demo.test.utils;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import issac.demo.test.AbstractBaseTest;
import issac.demo.utils.RedisUtil;

public class RedisTest extends AbstractBaseTest {

	@Resource
	private RedisTemplate<Object, Object> redisTemplate;

	public static void main(String[] args) {
		RedisUtil instance = RedisUtil.getInstance();


	}

	@Test
	public void simpleTest() {
		System.out.println(redisTemplate);
		Object execute = redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] bs = connection.get(redisTemplate.getStringSerializer().serialize("test"));
				return bs;
			}
		});

		System.out.println(redisTemplate.getStringSerializer().deserialize((byte[]) execute));
	}

}
