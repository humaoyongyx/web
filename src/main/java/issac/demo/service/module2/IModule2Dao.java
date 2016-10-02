package issac.demo.service.module2;

import java.util.List;
import java.util.Map;

public interface IModule2Dao<T> {

	int addModuleItemByParams(Map<String, Object> params);

	int deleteModuleItemById(Integer id);

	int deleteModuleItemsByIds(List<Integer> ids);

	int updateModuleItemByParams(Map<String, Object> params);

	public List<T> getModulePageByParams(Map<String, Object> params);

	public T getModuleBeanByParams(Map<String, Object> params);

	public T getModuleBeanById(Integer id);

	public List<T> getModuleBeansByIds(List<Integer> ids);

}
