package com.jf.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.exam.mapper.ClassMapper;
import com.jf.exam.mapper.ExamClassMapper;
import com.jf.exam.pojo.data.ClassDO;
import com.jf.exam.pojo.vo.ClassListVO;
import com.jf.exam.pojo.vo.ClassVO;
import com.jf.exam.pojo.vo.ExamClassVO;
import com.jf.exam.pojo.vo.ExamVO;
import com.jf.exam.service.ClassService;

import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.prefs.BackingStoreException;

/** 
 * <br/>
 * Created by chao on 2018/07/20
 */
@Service("classService")
public class ClassServiceImpl implements ClassService {

	private final static Logger LOG = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Resource
	private ClassMapper classMapper;
	//班级和试卷关系的Mapper
	@Autowired
	ExamClassMapper examClassMapper;

	@Override
	public Result addClass(ClassVO classVO) throws Exception {
		if(findGradeByCno(classVO)){
			return Result.getFailure("班级已存在，添加失败");
		}
		int count =classMapper.addClass(classVO);
		if(count>0){
			return new Result(Result.CODE_SUCCESS,"添加成功");
		}

		throw new RuntimeException("添加错误");
	}

	@Override
	public Result updateClass(ClassVO classVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailClass(ClassVO classVO) throws Exception{
		return null;
	}

	@Override
	public PageBean<ClassListVO> listClass(ClassVO classVO) throws Exception{
        //页码越界
        //获取数据的总个数
        int count = classMapper.countClass(classVO);
        //计算最大页数
        int max = (count + classVO.getPageSize() - 1) / classVO.getPageSize();
        if (classVO.getPageCode() > max) {
            classVO.setPageCode(max);
        }
        PageHelper.startPage(classVO.getPageCode(),classVO.getPageSize());
        List<ClassListVO> classDOS = classMapper.listClazzAssociation(classVO);

       PageInfo<ClassListVO> info =new PageInfo<>(classDOS);
       // PageInfo info =new PageInfo(classDOS);
        PageBean<ClassListVO> pageBean =new PageBean<>(classDOS,info.getPageSize(),classVO.getPageCode(),
                (int)info.getTotal(),classVO.getSize());
		return pageBean;
	}
	/**
	 * 根据年级名称查询班级是否存在
	 *
	 * @param classVO 班级信息
	 * @return true 存在， false 不存在
	 */
	public boolean findGradeByCno(ClassVO classVO){
		List<ClassDO> list = classMapper.listClass(classVO);
		if(list!=null&&!list.isEmpty()){
			return true;
		}
		return false;
	}
	
	@Override
	public Result listClassPage(ClassVO classVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countClass(ClassVO classVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteClass(ClassVO classVO) throws Exception{
		if(!findGradeByCno(classVO)){
			return Result.getFailure("此班级不存在");
		}
		classVO.setDelFlag(1);
		int count =classMapper.deleteClass(classVO);
		if(count>0){
			return new Result(Result.CODE_SUCCESS,"删除成功");
		}
		throw new BackingStoreException("删除错误");
	}

    @Override
    public List<ClassDO> listClassByGradeAndMajor(ClassVO classVO) {

        return classMapper.listClass(classVO);
    }

	@Override
	public Result addExamClass(String examId, List<ExamClassVO> vos) {
		//清空之前的记录
		int count=examClassMapper.deleteExamClassByExamId(examId);
		for (ExamClassVO vo:vos) {
			examClassMapper.addExamClass(vo);
		}
		return Result.getSuccess("添加成功");
	}

	@Override
	public Result listByExam(ExamVO teacherVO) {
		List<ClassDO> classDOS=classMapper.listByExam(teacherVO);
		return new Result(Result.CODE_SUCCESS,classDOS);
		//return null;
	}


}