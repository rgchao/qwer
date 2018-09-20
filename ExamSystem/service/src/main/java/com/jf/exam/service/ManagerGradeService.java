package com.jf.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.exam.mapper.GradeMapper;
import com.jf.exam.pojo.Grade;
import com.jf.exam.pojo.GradeExample;

import com.jf.exam.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerGradeService {
//    @Autowired
//    GradeMapper gradeMapper;
//    //查询分页数据的方法
//    public PageBean<Grade> pageSearch(GradeQuery query){
//        //设置分页参数
//        PageHelper.startPage(query.getPageCode(),query.getPageSize());
//
////        GradeExample gradeExample =new GradeExample();
////        GradeExample.Criteria criteria = gradeExample.createCriteria();
////        if (query.getSearch() != null) {
////            criteria.andGradeEqualTo(query.getSearch());
////        }
////        List<Grade> grades =gradeMapper.selectByExample(gradeExample);
//
//        //插件提供的分页信息
//        PageInfo<Grade> info =new PageInfo<>(grades);
//        //根据插件提供的分页信息
//        PageBean<Grade> pageBean =new PageBean<Grade>(grades,info.getPageSize()
//                ,info.getPageNum(),(int)info.getTotal(),query.getSize());
//        return pageBean;
//    }
}
