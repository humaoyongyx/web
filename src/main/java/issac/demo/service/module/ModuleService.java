package issac.demo.service.module;

import java.util.List;
import java.util.Map;

import issac.demo.utils.SpringUtils;

public class ModuleService<T> {
	private IModule<T> module;
	
	public ModuleService(String moduleId) {
		this.module = SpringUtils.getBean(moduleId + "MapperDao");
	}

	public List<T> getPageList(Map<String, Object> params) {
		
		return this.module.getModuleListByParams(params);

	}

	public void addOrUpdate(Map<String, Object> params) {
		Object id = params.get("id");
		if (id != null) {
			this.module.updateByPrimaryKeySelective(params);
		} else {
			this.module.insertSelective(params);
		}
	}

	public void delete(Map<String, Object> params) {
		String value = (String) params.get("id");
		Integer id = Integer.parseInt(value);
		this.module.deleteByPrimaryKey(id);
	}

	public void deleteAll(List<Integer> ids) {
		this.module.deleteAll(ids);
	}
}
