package com.jf.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.jf.exam.mapper.TeacherMapper;
import com.jf.exam.pojo.data.TeacherDO;
import com.jf.exam.pojo.vo.TeacherClassVO;
import com.jf.exam.pojo.vo.TeacherVO;
import com.jf.exam.service.TeacherService;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * Created by weidong on 2018/07/18
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    //操作教师表
    @Resource
    private TeacherMapper teacherMapper;


    //操作教师和班级对应表 teacher_class


    /**
     * 添加教师
     */
    @Override
    public Result addTeacher(TeacherVO teacherVO) throws Exception {
        TeacherDO teacher = teacherMapper.findDetailTeacher(teacherVO);
        if (teacher != null) {
            return Result.getFailure("教师编号已经存在");
        }
        int count = teacherMapper.addTeacher(teacherVO);
        if (count > 0) {
            return Result.getSuccess("添加成功");
        }
        return Result.getFailure("添加失败");
    }

    /**
     * 更新教师信息
     */
    @Override
    public Result updateTeacher(TeacherVO teacherVO) throws Exception {
        Integer count = teacherMapper.updateTeacher(teacherVO);
        if (count > 0) {
            return Result.getSuccess("更新成功");
        }
        return Result.getFailure("更新成功");
    }

    @Override
    public Result findDetailTeacher(TeacherVO teacherVO) throws Exception {
        return null;
    }


    /**
     * 查询教师列表
     */
    @Override
    public PageBean<TeacherDO> listTeacher(TeacherVO query) throws Exception {

        //防止页码越界
        Integer count = teacherMapper.countTeacher(query);
        int max = (count + query.getPageSize() - 1) / query.getPageSize();
        if (query.getPageCode() > max) {
            query.setPageCode(max);
        }

        PageHelper.startPage(query.getPageCode(), query.getPageSize());
        List<TeacherDO> classLists = teacherMapper.listTeacher(query);
        PageInfo<TeacherDO> info = new PageInfo<>(classLists);
        PageBean<TeacherDO> pageBean = new PageBean<TeacherDO>(classLists, info.getPageSize()
                , query.getPageCode(), (int) info.getTotal(), query.getSize());
        System.out.println(classLists);
        return pageBean;
    }

    @Override
    public Result listTeacherPage(TeacherVO teacherVO) throws Exception {
        return null;
    }

    @Override
    public Result countTeacher(TeacherVO teacherVO) throws Exception {
        return null;
    }

    /**
     * 删除教师
     */
    @Override
    public Result deleteTeacher(TeacherVO teacherVO) throws Exception {
        int count = teacherMapper.deleteTeacher(teacherVO);
        if (count > 0) {
            return Result.getSuccess("删除成功");
        }
        return Result.getFailure("删除失败");
    }

    @Override
    public Result addClass(ArrayList<TeacherClassVO> ids, String tid) {
        return null;
    }

    /**
     * 更改教师所教班级
     * @param ids
     * @param tid
     * @return
     */


    /**
     * 通过教师名称获取到教师信息
     */
    @Override
    public TeacherDO getTeacherByName(String username) {
        List<TeacherDO> teacherDOS = teacherMapper.listTeacher(new TeacherVO(username));
        if (teacherDOS != null && !teacherDOS.isEmpty()) {
            return teacherDOS.get(0);
        }
        return null;
    }

}