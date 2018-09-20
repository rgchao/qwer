package com.jf.exam.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.exam.mapper.GradeMapper;
import com.jf.exam.pojo.data.GradeDO;
import com.jf.exam.pojo.vo.GradeVO;
import com.jf.exam.service.GradeService;


import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;
import com.jf.exam.utils.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 
 * <br/>
 * Created by weidong on 2018/07/19
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {

	private final static Logger LOG = LoggerFactory.getLogger(GradeServiceImpl.class);

	@Resource
	private GradeMapper gradeMapper;

	@Override
	public Result addGrade(GradeVO gradeVO) throws Exception {
		if(findByName(gradeVO)){
			return Result.getFailure("此年级存在");
		}
		//保存到数据库
		int count= gradeMapper.addGrade(gradeVO);
		if(count>0){
			return new Result(Result.CODE_SUCCESS,"添加年级成功");
		}
		throw new RuntimeException("添加错误");
	}

	@Override
	public Result updateGrade(GradeVO gradeVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailGrade(GradeVO gradeVO) throws Exception{
		return null;
	}

	@Override
	public PageBean<GradeDO> listGrade(GradeVO query) throws Exception{
		//页码越界
		//获取数据的总个数
		int count = gradeMapper.countGrade(query);
		//计算最大页数
		int max = (count + query.getPageSize() - 1) / query.getPageSize();
		if (query.getPageCode() > max) {
			query.setPageCode(max);
		}
		PageHelper.startPage(query.getPageCode(),query.getPageSize());
		List<GradeDO> gradeDOS = gradeMapper.listGrade(query);
		PageInfo<GradeDO> info =new PageInfo<>(gradeDOS);
		//根据插件提供的分页信息，构造我们自己的分页信息
		PageBean<GradeDO> pageBean =new PageBean<>(gradeDOS,info.getPageSize(),
				query.getPageCode(),(int)info.getTotal(),query.getSize());

		return pageBean;
	}
	@Override
	public List<GradeDO> listAll(){
		return gradeMapper.listGrade(new GradeVO());
	}
	/**
	 * 根据年级名称查询年级是否存在
	 * @param gradeVO
	 * @return
	 * @throws Exception
	 */
	public boolean findByName(GradeVO gradeVO){
		List<GradeDO> list = gradeMapper.listGrade(gradeVO);
		if(list != null && !list.isEmpty()){
			return true;
		}
		return false;
	}
	@Override
	public Result listGradePage(GradeVO gradeVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countGrade(GradeVO gradeVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteGrade(GradeVO gradeVO) throws Exception{
		if (!findByName(gradeVO)){
			return Result.getFailure("年级不存在，无法删除");
		}
		gradeVO.setDelFlag(1);
		Integer count=gradeMapper.deleteGrade(gradeVO);
		if(count>0){
			return new Result(Result.CODE_SUCCESS,"删除成功");
		}
		throw new BusinessException("添加错误");
	}

}