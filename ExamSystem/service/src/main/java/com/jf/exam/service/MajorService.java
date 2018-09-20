package com.jf.exam.service;

import com.jf.exam.pojo.data.MajorDO;
import com.jf.exam.pojo.vo.GradeVO;
import com.jf.exam.pojo.vo.MajorVO;

import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;

import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/19
 */
public interface MajorService {

	Result addMajor(MajorVO majorVO) throws Exception;

	Result updateMajor(MajorVO majorVO) throws Exception;
	
	Result findDetailMajor(MajorVO majorVO) throws Exception;
	
	PageBean<MajorDO> listMajor(MajorVO majorVO) throws Exception;

    Result listMajorPage(MajorVO majorVO) throws Exception;
	
	Result countMajor(MajorVO majorVO) throws Exception;
	
	Result deleteMajor(MajorVO majorVO) throws Exception;
	List<MajorDO> listAll();

	/**
	 * 根据年级id查询专业信息
	 */
	Result listMajorByGrade(GradeVO majorVO);
}