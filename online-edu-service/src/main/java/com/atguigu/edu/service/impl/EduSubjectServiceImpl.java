package com.atguigu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.entity.EduSubject;
import com.atguigu.edu.listener.MyAnalysisEventListener;
import com.atguigu.edu.mapper.EduSubjectMapper;
import com.atguigu.edu.service.EduSubjectService;
import com.atguigu.vo.SubjectImportVO;
import com.atguigu.handler.MyRuntimeException;
import com.atguigu.response.SubjectResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author fh
 * @since 2020-11-06
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private MyAnalysisEventListener myAnalysisEventListener;

    @Override
    public void importByExcel(MultipartFile file)  {
        //获取Excel文件输入流进行文件读取
        InputStream inputStream =null;
        try {
            inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectImportVO.class,myAnalysisEventListener).doReadAll();
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyRuntimeException();
        } finally {
            if (inputStream == null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public EduSubject existByTitleAndParentId(String title, String parentId) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id",parentId);

        EduSubject eduSubject = baseMapper.selectOne(queryWrapper);
        return eduSubject;
    }

    @Override
    public List<SubjectResponse> getAllSubject() {
        List<SubjectResponse> subjectResponseList = new ArrayList<>();
        //查询所有的分类信息
        List<EduSubject> allSubject = baseMapper.selectList(null);
        //将分类信息封装成subjectResponse
        List<EduSubject> parentSubjectList = allSubject.stream().filter(subject -> subject.getParentId().equals("0"))
                .collect(Collectors.toList());
        for (EduSubject parentSubject : parentSubjectList) {
            SubjectResponse subjectResponse = new SubjectResponse();
            BeanUtils.copyProperties(parentSubject,subjectResponse);
            //根据一级分类id,从所有的分类中查询二级分类
            List<EduSubject> childrenSubjectList = allSubject.stream()
                    .filter(subject -> subject.getParentId().equals(parentSubject.getId()))
                    .collect(Collectors.toList());
            for (EduSubject childrenSubject : childrenSubjectList) {
                SubjectResponse childrenSubjectResponse = new SubjectResponse();
                BeanUtils.copyProperties(childrenSubject,childrenSubjectResponse);
                subjectResponse.getChildren().add(childrenSubjectResponse);
            }
            subjectResponseList.add(subjectResponse);
        }
        return subjectResponseList;
    }

    @Override
    public boolean saveParentSubject(EduSubject eduSubject) {
        //添加一级分类之前首先判断一级分类是否存在，不存在才添加
        EduSubject parentSubject = existByTitleAndParentId(eduSubject.getTitle(),"0");
        if (parentSubject ==null){
            parentSubject = new EduSubject();
            parentSubject.setTitle(eduSubject.getTitle());
            parentSubject.setParentId("0");
            int row = baseMapper.insert(parentSubject);
            return row>0;
        }
        return false;
    }

    @Override
    public boolean saveChildrenSubject(EduSubject eduSubject) {
        //添加二级分类之前首先判断二级分类是否存在，如果不存在才添加
        EduSubject childrenSubject = existByTitleAndParentId(eduSubject.getTitle(), eduSubject.getParentId());
        if (childrenSubject==null){
            childrenSubject = new EduSubject();
            childrenSubject.setTitle(eduSubject.getTitle());
            childrenSubject.setParentId(eduSubject.getParentId());
            int row = baseMapper.insert(childrenSubject);
            return row>0;
        }
        return false;
    }

    @Override
    public boolean deleteSubjectById(String subjectId) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        //根据课程分类id统计字分类的数量，如果大于零则不能删除
        queryWrapper.eq("parent_id",subjectId);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count>0) {
            throw new MyRuntimeException(20001,"该课程分类存在子级分类不能删除");
        }else {
            return true;
        }
    }


}
