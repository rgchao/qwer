package com.jf.exam.service.impl;


import com.jf.exam.mapper.ManagerMapper;
import com.jf.exam.pojo.vo.ManageVO;
import com.jf.exam.service.ManagerService;


import com.jf.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
 * <br/>
 * Created by chao on 2018/07/19
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	private final static Logger LOG = LoggerFactory.getLogger(ManagerServiceImpl.class);

	@Resource
	private ManagerMapper managerMapper;

	@Override
	public Result addManager(ManageVO managerVO) throws Exception {
		return null;
	}

	@Override
	public Result updateManager(ManageVO managerVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailManager(ManageVO managerVO) throws Exception{
		return null;
	}

	@Override
	public Result listManager(ManageVO managerVO) throws Exception{
		return null;
	}
	
	@Override
	public Result listManagerPage(ManageVO managerVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countManager(ManageVO managerVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteManager(ManageVO managerVO) throws Exception{
		return null;
	}

}