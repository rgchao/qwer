package com.jf.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.exam.mapper.MajorMapper;
import com.jf.exam.pojo.data.GradeDO;
import com.jf.exam.pojo.data.MajorDO;
import com.jf.exam.pojo.vo.GradeVO;
import com.jf.exam.pojo.vo.MajorVO;
import com.jf.exam.service.MajorService;

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
 * Created by chao on 2018/07/19
 */
@Service("majorService")
public class MajorServiceImpl implements MajorService {

	private final static Logger LOG = LoggerFactory.getLogger(MajorServiceImpl.class);

	@Resource
	private MajorMapper majorMapper;

	@Override
	public Result addMajor(MajorVO majorVO) throws Exception {
		if(findMajorByName(majorVO)){
			return Result.getFailure("此专业存在");
		}
		Integer count = majorMapper.addMajor(majorVO);
		if(count>0){
			return new Result(Result.CODE_SUCCESS,"添加成功");
		}
		throw new RuntimeException("添加错误");
	}

	@Override
	public Result updateMajor(MajorVO majorVO) throws Exception {
		//根据id查询专业是否存在
        MajorDO detailMajor = majorMapper.findDetailMajor(majorVO);
		if(detailMajor==null){
		    return Result.getFailure("专业不存在,请重新刷新后在进行编辑");
        }
        //判断专业是否存在，只根据查询名称和删除状态进行查询
        MajorVO majorQuery =new MajorVO();
		majorQuery.setName(majorVO.getName());
		majorQuery.setDelFlag(0);
        List<MajorDO> majorDOS = majorMapper.listMajor(majorQuery);
        if(majorDOS !=null&&!majorDOS.isEmpty()){
            return Result.getFailure("专业已经存在，无法编辑");
        }

        Integer count  =majorMapper.updateMajor(majorVO);
		if(count>0){
			return new Result(Result.CODE_SUCCESS,"编辑成功");
		}

		throw new BusinessException("更新专业错误");
	}
	
	@Override
	public Result findDetailMajor(MajorVO majorVO) throws Exception{
		return null;
	}

	@Override
	public PageBean<MajorDO> listMajor(MajorVO majorVO) throws Exception{
//		页码越界
//		获取数据的总个数
		int count = majorMapper.countMajor(majorVO);
		//计算最大页数
		int max = (count + majorVO.getPageSize() - 1) / majorVO.getPageSize();
		if (majorVO.getPageCode() > max) {
			majorVO.setPageCode(max);
		}
		PageHelper.startPage(majorVO.getPageCode(),majorVO.getPageSize());
		List<MajorDO> majorDOS = majorMapper.listMajor(majorVO);
		PageInfo info =new PageInfo(majorDOS);
		//根据插件提供的分页信息，构造我们自己的分页信息
		PageBean<MajorDO> pageBean =new PageBean<>(majorDOS,info.getPageSize(),majorVO.getPageCode(),(int)info.getTotal(),majorVO.getSize());
		return pageBean;
	}
	public boolean findMajorByName(MajorVO majorVO){
		List<MajorDO> majorDOS = majorMapper.listMajor(majorVO);
		if(majorDOS!=null&&!majorDOS.isEmpty()){
			return true;
		}
		return false;
	}
	@Override
	public List<MajorDO> listAll(){
		return majorMapper.listMajor(new MajorVO());
	}


	@Override
	public Result listMajorByGrade(GradeVO majorVO) {
		List<MajorDO> list = majorMapper.listMajorByGrade(majorVO);
		return new Result(Result.CODE_SUCCESS,list);
	}

	@Override
	public Result listMajorPage(MajorVO majorVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countMajor(MajorVO majorVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteMajor(MajorVO majorVO) throws Exception{
		if(!findMajorByName(majorVO)){
			return Result.getFailure("此专业存在");
		}
		majorVO.setDelFlag(1);
		Integer count = majorMapper.deleteMajor(majorVO);
		if(count>0){
			return new Result(Result.CODE_SUCCESS,"删除成功");
		}
		throw  new BootstrapMethodError("删除错误");
	}

}