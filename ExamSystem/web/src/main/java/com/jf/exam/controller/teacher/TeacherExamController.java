package com.jf.exam.controller.teacher;

import com.jf.exam.pojo.StatisticsData;
import com.jf.exam.pojo.data.TeacherDO;
import com.jf.exam.pojo.vo.ExamVO;
import com.jf.exam.pojo.vo.QuestionManageVO;
import com.jf.exam.pojo.vo.StudentReportVO;
import com.jf.exam.service.ExamService;
import com.jf.exam.service.ExaminationResultService;
import com.jf.exam.service.QuestionService;
import com.jf.exam.utils.DataUtils;
import com.jf.exam.utils.ExamUtils;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teacher/exam")
public class TeacherExamController {
    @Autowired
    ExamService examService;
    @Autowired
    QuestionService questionService;

    @Autowired
    ExaminationResultService examinationResultService;
    @Value("#{properties['question.pageSize']}")
    private int pageSize;
    @Value("#{properties['question.pageNumber']}")
    private int pageNumber;
    @RequestMapping("/list")
    public String list(ExamVO examVO,Model model) throws Exception {
       examVO.setPageSize(pageSize);
       examVO.setSize(pageNumber);
       examVO.setPageCode(DataUtils.getPageCode(examVO.getPageCode()+""));
        PageBean<ExamVO> pageBean = examService.listExam(examVO);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("function",2);
        return "teacher/exam_list";
    }
    @RequestMapping("/add")
    @ResponseBody
    public Result add(ExamVO examVO, HttpSession session,Model model) throws Exception {
        model.addAttribute("function", 3);
        TeacherDO teacher = (TeacherDO) session.getAttribute("teacher");
        examVO.setFkTeacher(teacher.getId());
        examVO.setFkStatus(1);
        //todo有问题
        examVO.setEndtime(new Date());
        return   examService.addExam(examVO);
    }
    @RequestMapping("/remove")
    @ResponseBody
    public Result remove(Integer examId, HttpSession session,Model model) throws Exception{
        ExamVO examVO =new ExamVO();
        examVO.setId(examId);
        return examService.deleteExam(examVO);
    }
    @RequestMapping("/examManager/{eid}")
    public String examManager(@PathVariable Integer eid,Integer pageCode,Model model){
        model.addAttribute("function",2);
         pageCode= DataUtils.getPageCode(pageCode+"");
        //查询试卷所有的题目信息
        PageBean<QuestionManageVO> bean =questionService.listQuestionByExam(eid,DataUtils.getPageCode(pageCode+""),pageSize,pageNumber);

        model.addAttribute("pageBean",bean);
        model.addAttribute("examId",eid);
        return "teacher/question_manage";
    }
//获取试卷详情
    @RequestMapping("/get/{eid}")
    @ResponseBody
    public Result get(@PathVariable Integer eid) throws Exception {
        return examService.findDetailExam(ExamVO.getExamVOById(eid));
    }

    /**
     * 切换试卷状态
     * examId: 23
     * status: RUNNING
     * days: 1
     */
    @RequestMapping("/status")
    @ResponseBody
    public Result status(Integer examId,String status,Integer days) throws Exception {
        ExamVO examVO =new ExamVO();
        examVO.setId(examId);
        if(ExamUtils.RUNNING_EN.equals(status)){
            Calendar calendar =Calendar.getInstance();
            calendar.add(Calendar.DATE,days);
            examVO.setEndtime(calendar.getTime());
        }else if(ExamUtils.RUNNED_EN.equals(status)){
            examVO.setEndtime(new Date());
        }
        examVO.setFkStatus(ExamUtils.getStatusNumByEN(status));
        return examService.updateExam(examVO);
    }

    /**
     * 获取试卷详情
     * 先请求统计的地址，再调整统计页面，由统计页面发起真正的统计请求
     * 减少卡顿
     * @throws Exception
     */
    @RequestMapping("/statistics/{eid}")
    public String statistics(@PathVariable Integer eid,Model model) throws Exception {
        model.addAttribute("eid",eid);
        return "teacher/statistics";
    }
    @RequestMapping("/statistics/do/{eid}")
    @ResponseBody
    public Result statistics(@PathVariable Integer eid,Model model,HttpServletRequest request) throws Exception {
        //根据eid获取统计信息
        StatisticsData data= examinationResultService.getStatisticsData(eid);
        if(data==null){
            return Result.getFailure("没有考试记录");
        }
        //根据统计信息来生成图表
        //获取路径
        String path =request.getServletContext().getRealPath("/")+"/";
        checkPath(path+"charts");
        String imagePath="charts/pie_"+eid+".png";
        createCharts(path+imagePath,data);
        data.setUrl(imagePath);
        return new Result(Result.CODE_SUCCESS,data);

    }
    /**
     * 如果文件夹不存在，那么创建文件夹
     *
     * @param s
     */
    private void checkPath(String s) {
        File f = new File(s);
        if (!f.exists()) {
            f.mkdirs();
        }
    }
    public void createCharts(String path, StatisticsData data) throws IOException {
        //数据
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("<60%", data.getUnderSixty().size());
        dataset.setValue("60%-80&", data.getSixtyAndEighty().size());
        dataset.setValue("80%-90&", data.getEightyAndNinety().size());
        dataset.setValue(">90", data.getAboveNinety().size());

        //创建 Jfreechart对象
        //1. 标题
        //2. 数据源
        //3. 是否显示图例
        //4. 是否包含提示文本
        //5. 是否生成超链接
        JFreeChart jfreechart = ChartFactory.createPieChart(data.getTitle(),
                dataset, true, true, false);

        PiePlot piePlot = (PiePlot) jfreechart.getPlot();
        //5-------------------------------------------------
        //设置上面的样式,0表示KEY,1表示VALUE,2表示百分之几,DecimalFormat用来显示百分比的格式
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}=>{1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));


        //6-------------------------------------------------
        //设置下面方框的样式,0表示KEY,1表示VALUE,2表示百分之几,DecimalFormat用来显示百分比的格式
        piePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}=>{1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));

        //8------------------------------------------------
        //定义字体格式
        Font font = new Font("微软雅黑", Font.CENTER_BASELINE, 12);
        //定义图片标题
        TextTitle title = new TextTitle(data.getTitle());
        //设置标题的格式
        title.setFont(font);
        //把标题设置到图片里面
        jfreechart.setTitle(title);

        //9------------------------------------------------
        //设置字体,非常关键不然会出现乱码的,下方的字体
        jfreechart.getLegend().setItemFont(font);
        //Pie图的字体
        piePlot.setLabelFont(font);


        //保存到硬盘上
        ChartUtilities.saveChartAsPNG(new File(path), jfreechart, 500, 500);
    }
    /**
     * 下载成绩表
     */
    @RequestMapping("/download/{eid}")
    public String download(@PathVariable Integer eid, Model model) throws Exception {
        model.addAttribute("eid", eid);
        return "teacher/download";
    }
    /**
     * 真正生成表格
     */
    @RequestMapping("/report/{eid}")
    public ResponseEntity<byte[]> report (@PathVariable Integer eid,Model model,HttpServletRequest request) throws IOException, WriteException {
        //根据统计信息来生成图表
        //获取路径
        String path =request.getServletContext().getRealPath("/")+"/";
        checkPath(path+"report");
        String fileName="/report_"+eid+".xls";

        List<StudentReportVO> reportVOSList =examinationResultService.getReportData(eid);
        excel(reportVOSList,path+fileName);
        //下载文件
        String fileName2 =URLEncoder.encode(reportVOSList.get(0).getTitle()+".xls","UTF-8");
        HttpHeaders httpHeaders =new HttpHeaders();
        //文件名乱码
        httpHeaders.setContentDispositionFormData("attachment",fileName2);

        //通知浏览器打开文件的方式
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(FileUtils.readFileToByteArray(new File(path+fileName)),httpHeaders,HttpStatus.CREATED);
     }
     public void excel(List<StudentReportVO> reportData,String path) throws IOException, WriteException {
        String [] title ={"学号","姓名","分数"};
        //创建Excel文件
         File file =new File(path);
         try {
             //创建工作簿
             WritableWorkbook workbook =Workbook.createWorkbook(file);
             WritableSheet sheet =workbook.createSheet("sheet1",0);
             jxl.write.Label label =null;
             //第一行设置列名
             for(int i=0;i<title.length;i++){
                 label =new jxl.write.Label(i,0,title[i]);
                 sheet.addCell(label);
             }
             //追加数据
             for (int i=1;i<=reportData.size();i++){
                 label = new jxl.write.Label(0, i, reportData.get(i - 1).getId());
                 sheet.addCell(label);
                 label = new jxl.write.Label(1, i, reportData.get(i - 1).getName());
                 sheet.addCell(label);
                 label = new jxl.write.Label(2, i, reportData.get(i - 1).getPoint()+"");
                 sheet.addCell(label);
             }
             //写入数据
             workbook.write();
             workbook.close();
         } catch (IOException e) {
             e.printStackTrace();
         } catch (WriteException e) {
             e.printStackTrace();
         }
     }
}
