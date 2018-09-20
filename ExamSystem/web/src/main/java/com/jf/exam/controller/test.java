package com.jf.exam.controller;

import com.jf.exam.pojo.BaseUser;
import com.jf.exam.pojo.Grade;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestExecutionListeners;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {
        @Test
        public void test(){

            List<Grade> users = new ArrayList<Grade>();
            Grade user1 =new Grade();
            Grade user2 =new Grade();
             user1.setId(1);
             user2.setId(2);

            users.add(user1);
            users.add(user2);
            int length =users.size();
            //创建数组
            Grade[] users1 =new Grade[length];
            for (int i=0; i<users1.length;i++){
                users1[i] =users.get(i);
            }

            for (int i=0;i<users1.length;i++){
                System.out.println(users1[i].getId());
            }
            Date date =new Date();
            System.out.println(date);
        }


}
