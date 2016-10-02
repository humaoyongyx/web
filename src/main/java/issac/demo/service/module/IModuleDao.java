package issac.demo.service.module;

import java.util.List;
import java.util.Map;

public interface IModuleDao<T> {

	public <T> List<T> getModuleListByParams(Map<String, Object> params);

	int deleteByPrimaryKey(Integer id);

	int insertSelective(Map<String, Object> params);

	int updateByPrimaryKeySelective(Map<String, Object> params);

	public void deleteAll(List<Integer> ids);

}
