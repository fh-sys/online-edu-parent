package com.atguigu.edu.listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.edu.entity.EduSubject;
import com.atguigu.edu.service.EduSubjectService;
import com.atguigu.edu.vo.SubjectImportVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName My
 * @Description: Excel导入数据监听器
 * @Author Hao
 * @Date 2020/11/6 21:01
 * @Version V1.0
 **/
@Component
public class MyAnalysisEventListener extends AnalysisEventListener<SubjectImportVO> {

    @Autowired
    private EduSubjectService subjectService;

    @Override
    public void invoke(SubjectImportVO subjectImportVO, AnalysisContext analysisContext) {
        //判断一级分类是否存在
        EduSubject eduSubjectParent = subjectService.existByTitleAndParentId(subjectImportVO.getParentSubjectName(), "0");
       if (eduSubjectParent==null){
           //如果一级分类不存在，则插入一级分类，插入完成之后mp会自动将主键分装到插入对象中
           eduSubjectParent = new EduSubject();
           eduSubjectParent.setTitle(subjectImportVO.getParentSubjectName());
           eduSubjectParent.setParentId("0");
           subjectService.save(eduSubjectParent);
       }
       //判断二级分类时候存在,此时eduSubject中有Id
        EduSubject eduSubjectChild = subjectService.existByTitleAndParentId(subjectImportVO.getChildSubjectName(), eduSubjectParent.getId());
        if (eduSubjectChild == null){
            //二级分类不存在则插入
            eduSubjectChild = new EduSubject();
            eduSubjectChild.setTitle(subjectImportVO.getChildSubjectName());
            eduSubjectChild.setParentId(eduSubjectParent.getId());
            subjectService.save(eduSubjectChild);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
