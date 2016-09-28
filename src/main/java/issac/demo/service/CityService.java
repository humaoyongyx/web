package issac.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.mapper.CityMapperDao;
import issac.demo.model.CityBean;

@Service
public class CityService {
    @Resource
	CityMapperDao cityMapperDao;

	public void add(CityBean cityBean) {
		cityMapperDao.insertSelective(cityBean);
	}

	public void delete(Integer id) {
		cityMapperDao.deleteByPrimaryKey(id);
	}

	public void update(CityBean cityBean) {
		cityMapperDao.updateByPrimaryKeySelective(cityBean);
	}

	public List<CityBean> getByPid(Integer pid) {
		return cityMapperDao.getByPid(pid);
	}
}
