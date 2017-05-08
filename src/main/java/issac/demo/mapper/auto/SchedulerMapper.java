package issac.demo.mapper.auto;

import issac.demo.model.auto.Scheduler;

public interface SchedulerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Scheduler record);

    int insertSelective(Scheduler record);

    Scheduler selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Scheduler record);

    int updateByPrimaryKey(Scheduler record);
}