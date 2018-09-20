package com.jf.exam.mapper;

import com.jf.exam.pojo.data.MajorDO;
import com.jf.exam.pojo.vo.GradeVO;
import com.jf.exam.pojo.vo.MajorVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/19
 */
@Repository
public interface MajorMapper {

    Integer addMajor(MajorVO majorVO);

    Integer updateMajor(MajorVO majorVO);

    MajorDO findDetailMajor(MajorVO majorVO);

    List<MajorDO> listMajor(MajorVO majorVO);

    List<MajorDO> listMajorPage(MajorVO majorVO);

    Integer countMajor(MajorVO majorVO);

    Integer deleteMajor(MajorVO majorVO);
    List<MajorDO> listMajorByGrade(GradeVO majorVO);
}
