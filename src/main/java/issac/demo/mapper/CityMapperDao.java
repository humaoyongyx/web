package issac.demo.mapper;

import java.util.List;

import issac.demo.model.CityBean;

public interface CityMapperDao {
    int deleteByPrimaryKey(Integer id);

	int insert(CityBean record);

	int insertSelective(CityBean record);

	CityBean selectByPrimaryKey(Integer id);

	List<CityBean> getByPid(Integer pid);

	List<CityBean> getAll();

	int updateByPrimaryKeySelective(CityBean record);

	int updateByPrimaryKey(CityBean record);
}