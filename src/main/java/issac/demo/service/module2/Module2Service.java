package issac.demo.service.module2;

import java.util.List;
import java.util.Map;

import issac.demo.dto.Result;

public abstract class Module2Service<T> {
	public abstract IModule2Dao<T> getModule();

	public List<T> show(Map<String, Object> params) {
		return getModule().getModulePageByParams(params);
	}

	public T getBean(Map<String, Object> params) {
		return getModule().getModuleBeanByParams(params);
	}

	public T getBean(Integer id) {
		return getModule().getModuleBeanById(id);
	}

	public List<T> getBeans(List<Integer> ids) {
		return getModule().getModuleBeansByIds(ids);
	}

	public void add(Map<String, Object> params) {
		getModule().addModuleItemByParams(params);
	}

	public Result check(Map<String, Object> params) {
		return null;
	}

	public Result checkAdd(Map<String, Object> params) {
		return null;
	}

	public Result checkDelete(List<Integer> ids) {
		return null;
	}

	public Result checkUpdate(Map<String, Object> params) {
		return null;
	}

	public void update(Map<String, Object> params) {
		getModule().updateModuleItemByParams(params);
	}

	public void delete(Integer id) {
		getModule().deleteModuleItemById(id);
	}

	public void deleteAll(List<Integer> ids) {
		getModule().deleteModuleItemsByIds(ids);
	}

}
