package com.jf.exam.service;

import com.jf.exam.pojo.vo.ManageVO;
import com.jf.exam.utils.Result;


/** 
 * <br/>
 * Created by chao on 2018/07/19
 */
public interface ManagerService {

	Result addManager(ManageVO managerVO) throws Exception;

	Result updateManager(ManageVO managerVO) throws Exception;
	
	Result findDetailManager(ManageVO managerVO) throws Exception;
	
	Result listManager(ManageVO managerVO) throws Exception;

    Result listManagerPage(ManageVO managerVO) throws Exception;
	
	Result countManager(ManageVO managerVO) throws Exception;
	
	Result deleteManager(ManageVO managerVO) throws Exception;
}