package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.domain.TaskInfo;
import com.bupt.repository.TaskInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by bupt626 on 17-4-12.
 */
@Service
@Transactional
public class TaskInfoService extends BasePageService<TaskInfo,String> {
	@Autowired
	private TaskInfoRepository taskInforepository;

	public void save(TaskInfo entity) {
		taskInforepository.save(entity);
	}

	public void deleteById(String id) {
		taskInforepository.delete(id);
	}
	
	public TaskInfo findOne(String id) {
		return taskInforepository.findOne(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param pageEntity
	 * @param paramaMap
	 */
	public void pageByHql(PageEntity<TaskInfo> pageEntity, Map<String, Object> paramaMap) {
		StringBuilder sql = new StringBuilder(" from TaskInfo where 1=1 ");
		if (paramaMap.containsKey("name")) { // 任务名称
			sql.append(" and name like:name ");
		}
		if (paramaMap.containsKey("frequency")) { // 任务频率
			sql.append(" and frequency =:frequency ");
		}
		if (paramaMap.containsKey("member")) { // 接受任务人员
			sql.append(" and member =:member ");
		}
		super.pageByHql(sql.toString(), pageEntity, paramaMap);
	}
}
