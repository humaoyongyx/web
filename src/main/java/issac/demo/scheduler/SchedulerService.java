package issac.demo.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.dto.Result;
import issac.demo.mapper.SchedulerMapperDao;
import issac.demo.model.SchedulerBean;
import issac.demo.service.module2.IModule2Dao;
import issac.demo.service.module2.Module2Service;
import issac.demo.utils.CommonUtils;
import issac.demo.utils.SpringUtils;

@Service
public class SchedulerService extends Module2Service<SchedulerBean> {
	@Resource
	SchedulerUtils schedulerUtils;

	@Resource
	SchedulerMapperDao schedulerMapperDao;

	@PostConstruct
	public void init() {
		Map<String, Object> params=new HashMap<>();
		params.put("status", 1);
		params.put("runStatus", 1);
		List<SchedulerBean> schedulerBeans = show(params);
		if (schedulerBeans != null) {
			for (SchedulerBean schedulerBean : schedulerBeans) {
				if (SpringUtils.containsBean(schedulerBean.getBeanName())) {
					CommonJob job = SpringUtils.getBean(schedulerBean.getBeanName());
					schedulerUtils.addJob(schedulerBean, job.getClass());
				}
			}
			schedulerUtils.start();
		}
	}

	@Override
	public IModule2Dao<SchedulerBean> getModule() {
		return schedulerMapperDao;
	}

	public void startJob(Integer id) {
		SchedulerBean bean = getBean(id);
		bean.setRunStatus(1);
		schedulerUtils.addJob(bean);
		update(CommonUtils.beanToMap(bean));
	}

	public void stopJob(Integer id) {
		SchedulerBean bean = getBean(id);
		bean.setRunStatus(0);
		schedulerUtils.removeJob(bean);
		update(CommonUtils.beanToMap(bean));
	}

	public void refreshJob(Integer id) {
		SchedulerBean bean = getBean(id);
		schedulerUtils.modifyJob(bean);
	}

	@Override
	public Result checkDelete(List<Integer> ids) {
		List<SchedulerBean> beans = getBeans(ids);
		for (SchedulerBean schedulerBean : beans) {
			if (schedulerBean.getRunStatus() == 1) {
				return Result.fail("请先停止相关已经运行的Job，然后在删除！");
			}
		}
		return null;
	}

	@Override
	public Result checkUpdate(Map<String, Object> params) {
		Integer id = Integer.parseInt((String) params.get("id"));
		String status = (String) params.get("status");
		SchedulerBean schedulerBean = getBean(id);
		if (schedulerBean.getRunStatus() == 1) {
			if (CommonUtils.isNotEmpty(status) && status.equals("0")) {
				return Result.fail("更新为无效，只能在此Job停止运行时！");
			}
		}
		return null;
	}

}
