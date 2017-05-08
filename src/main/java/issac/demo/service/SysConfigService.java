package issac.demo.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import issac.demo.mapper.SysConfigMapperDao;
import issac.demo.model.sys.SysConfigBean;
import issac.demo.model.sys.SysConfigModuleBean;
import issac.demo.service.module2.IModule2Dao;
import issac.demo.service.module2.Module2Service;
import issac.demo.utils.CommonUtils;

public abstract class SysConfigService<T> extends Module2Service<SysConfigBean> {
	@Resource
	protected SysConfigMapperDao sysConfigMapperDao;

	protected SysConfigModuleBean sysConfigModuleBean;

	@Override
	public IModule2Dao<SysConfigBean> getModule() {
		return sysConfigMapperDao;
	}

	public T getConfig(String category, Class<T> configBeanClass) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		List<SysConfigBean> sysConfigBeans = sysConfigMapperDao.getModuleBeansByParams(params);
		if (sysConfigBeans != null) {
			Map<String, SysConfigBean> sysConfigBeanMap = new HashMap<>();
			for (SysConfigBean sysConfigBean : sysConfigBeans) {
				sysConfigBeanMap.put(sysConfigBean.getKey(), sysConfigBean);
			}
			Field[] declaredFields = configBeanClass.getDeclaredFields();
			T config = null;
			try {
				config = configBeanClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			for (Field field : declaredFields) {
				String fieldName = field.getName();
				SysConfigBean sysConfigBean = sysConfigBeanMap.get(fieldName);
				if (sysConfigBean != null) {
					CommonUtils.setMethod(fieldName, config, sysConfigBean.getValue());
				}
			}
			return config;
		}

		return null;

	}
	
	public T getConfig() {
		return getConfig(getCategory(), getConfigBeanClass());

	}

	public void updateConfig() {
		Field[] declaredFields = this.sysConfigModuleBean.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			String fieldName = field.getName();
			Object value = CommonUtils.getMethod(fieldName, this.sysConfigModuleBean);
			if (value!=null) {
				HashMap<String, Object> params = new HashMap<>();
				params.put("category", getCategory());
				params.put("key", fieldName);
				params.put("value", value);
				update(params);
			}
		
		}
	}

	abstract String getCategory();

	abstract Class<T> getConfigBeanClass();

}
