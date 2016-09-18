package issac.demo.mapper;

import java.util.List;

import issac.demo.bo.params.ResourceParams;
import issac.demo.model.ResourceBean;

public interface ResourceMapperDao {

	public List<ResourceBean> getResourceByMenuId(ResourceParams params);

}