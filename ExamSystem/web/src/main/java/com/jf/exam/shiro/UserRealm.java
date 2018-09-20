package com.jf.exam.shiro;

import com.jf.exam.mapper.ManagerMapper;

import com.jf.exam.pojo.*;
import com.jf.exam.pojo.data.StudentDO;
import com.jf.exam.pojo.data.TeacherDO;
import com.jf.exam.service.StudentService;
import com.jf.exam.service.TeacherService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    ManagerMapper manageMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户信息，根据用户名查询账号数据
        UsernamePasswordTypeToken token = (UsernamePasswordTypeToken) authenticationToken;
        if(token.getUserType()==UsernamePasswordTypeToken.ADMIN){
            Manager manager =manageMapper.getManageByName(token.getUsername());
            SimpleAuthenticationInfo info =new SimpleAuthenticationInfo(manager,manager.getPassword(),getName());
            info.setCredentialsSalt(ByteSource.Util.bytes(token.getUsername().getBytes()));
            return info;
        }
        if(token.getUserType()==UsernamePasswordTypeToken.SUTDENT){
            StudentDO student = studentService.getStudentByName(token.getUsername());
            if(student!=null){
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(student, student.getPassword(), getName());
                info.setCredentialsSalt(ByteSource.Util.bytes(token.getUsername().getBytes()));
                return info;
            }
        }
        if(token.getUserType()==UsernamePasswordTypeToken.TEACHER){
            TeacherDO teacher = teacherService.getTeacherByName(token.getUsername());
            if(teacher!=null){
                SimpleAuthenticationInfo info =new SimpleAuthenticationInfo(teacher,teacher.getPassword(),getName());
                info.setCredentialsSalt(ByteSource.Util.bytes(token.getUsername().getBytes()));
                return info;
            }
        }
        return null;
    }
}
