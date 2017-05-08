package issac.demo.service.module2;

import java.util.List;
import java.util.Map;

public interface IModule2Dao<T> {

	public int addModuleItemByParams(Map<String, Object> params);

	public int deleteModuleItemById(Integer id);

	public int deleteModuleItemsByIds(List<Integer> ids);

	public int updateModuleItemByParams(Map<String, Object> params);

	@Deprecated
	public List<T> getModulePageByParams(Map<String, Object> params);

	public List<T> getModuleBeansByParams(Map<String, Object> params);

	public T getModuleBeanByParams(Map<String, Object> params);

	public T getModuleBeanById(Integer id);

	public List<T> getModuleBeansByIds(List<Integer> ids);

}
