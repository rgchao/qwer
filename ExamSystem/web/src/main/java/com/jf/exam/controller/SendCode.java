package com.jf.exam.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendCode {
    static final String product ="Dysmsapi";
    //产品域名，开发者无需更换
    static final String domain ="dysmasapi.aliyuncs.com";

    //此处需要替换开着自己的AK(在阿里云访问空置台寻找)
    static final String accessKeyId="LTAIp3txqPYOT8qs";
    static final String accessKeySecret="vphLX4J74uIcfa6chfFdSrUz3vThIb ";
    public static SendSmsResponse sendSms(String phone,String code) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout","5000");
        System.setProperty("sun.net.client.defaultReadTimeout","5000");

        //初始化acsClient
        IClientProfile profile =DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou","cn-hangzhou",product,domain);
        IAcsClient acsClient =new DefaultAcsClient(profile);

        //组装请求对象具体描述见控制台
        SendSmsRequest request =new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("戎超");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_141735137");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;

    }
    public static  void main(String[]args) throws ClientException {
       SendCode.sendSms("17772266331","123456");
    }
}
