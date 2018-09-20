package com.jf.exam.utils;

import java.util.Random;

public class randomUtil
{
	static String str = "0123456789";
     
	public static String math(int length)
	{
		StringBuffer sb=new StringBuffer();
		Random random = new Random();
		for(int i=0;i<length;i++){
			int res=random.nextInt(str.length());
			char a=str.charAt(res);
           sb.append(a);			
		}
		return sb.toString();
	}
	public static void main(String[] args)
	{
		randomUtil util=new randomUtil();
		String str=util.math(4);
		System.out.println(str);
		
	}

}
