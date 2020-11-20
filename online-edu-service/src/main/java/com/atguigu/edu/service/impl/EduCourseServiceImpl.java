package com.atguigu.edu.service.impl;

import com.atguigu.entity.*;
import com.atguigu.edu.mapper.EduCourseMapper;
import com.atguigu.edu.openfeign.VideoServiceFeign;
import com.atguigu.edu.service.*;
import com.atguigu.vo.CourseDetailInfoVO;
import com.atguigu.vo.CourseInfoVo;
import com.atguigu.vo.PublishCourseVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author fh
 * @since 2020-11-09
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private EduSectionService sectionService;
    @Autowired
    private EduSubjectService subjectService;
    @Autowired
    private EduTeacherService teacherService;

    /**
     * 通过feign远程调用
     */
    @Autowired
    private VideoServiceFeign videoServiceFeign;

    @Override
    public CourseInfoVo getCourseById(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        //创建前端课程信息交互实体
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        //判断该课程是否有课程描述信息，如果没有课程描述信息则显示位空
        if (!StringUtils.isEmpty(courseDescription)) {
            courseInfoVo.setDescription(courseDescription.getDescription());
        }
        return courseInfoVo;
    }

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        //添加课程信息
        baseMapper.insert(eduCourse);
        //添加课程描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        if (!StringUtils.isEmpty(courseInfoVo.getDescription())) {
            eduCourseDescription.setDescription(courseInfoVo.getDescription());
        }
        BeanUtils.copyProperties(courseInfoVo, eduCourseDescription);
        courseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public void updateCourseInfoById(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        //修改课程信息
        baseMapper.updateById(eduCourse);
        //修改课程描述信息
        if (!StringUtils.isEmpty(courseInfoVo.getDescription())) {
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setId(courseInfoVo.getId());
            eduCourseDescription.setDescription(courseInfoVo.getDescription());
            courseDescriptionService.updateById(eduCourseDescription);
        }
    }

    @Override
    public PublishCourseVO getPublishCourseInfo(String courseId) {
        PublishCourseVO publishCourseVO = baseMapper.selectPublishCourseInfoByCourseId(courseId);
        return publishCourseVO;
    }

    @Override
    public void deleteCourseByCourseId(String courseId) {
        //删除课程
        baseMapper.deleteById(courseId);
        //删除课程描述
        EduCourseDescription courseDescriptionById = courseDescriptionService.getById(courseId);
        if (courseDescriptionById != null) {
            courseDescriptionService.removeById(courseDescriptionById);
        }
        //删除章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        chapterService.remove(chapterQueryWrapper);

        QueryWrapper<EduSection> sectionQueryWrapper = new QueryWrapper<>();
        sectionQueryWrapper.eq("course_id", courseId);
        List<EduSection> sectionList = sectionService.list(sectionQueryWrapper);
        //获取视频id的集合
        List<String> videoIds = new ArrayList<>();
        for (EduSection eduSection : sectionList) {
            if (!StringUtils.isEmpty(eduSection.getVideoSourceId())) {
                videoIds.add(eduSection.getVideoSourceId());
            }
        }
        //删除每个小节所对应的视频
        videoServiceFeign.deleteMutilVideo(videoIds);
        //删除小节
        sectionService.remove(sectionQueryWrapper);
    }

    /*-----------前端-----------------*/
    @Override
    public Map<String, Object> getCourseByPage(long pageNum, long pageSize) {
        Page<EduCourse> coursePage = new Page<>(pageNum, pageSize);
        baseMapper.selectPage(coursePage, null);
        List<EduCourse> courseList = coursePage.getRecords();
        long pages = coursePage.getPages();
        long currentPage = coursePage.getCurrent();
        long size = coursePage.getSize();
        long totalNum = coursePage.getTotal();
        boolean hasPrevious = coursePage.hasPrevious();
        boolean hasNext = coursePage.hasNext();

        Map<String, Object> retMap = new HashMap<>();

        retMap.put("courseList", courseList);
        retMap.put("currentPage", currentPage);
        retMap.put("pages", pages);
        retMap.put("size", size);
        retMap.put("totalNum", totalNum);
        retMap.put("hasNext", hasNext);
        retMap.put("hasPrevious", hasPrevious);
        return retMap;
    }

    @Override
    public CourseDetailInfoVO getCourseDetailInfoById(String courseId) {
        //查询课程详细信息
        CourseDetailInfoVO courseDetailInfoVO = baseMapper.selectCourseDetailInfoById(courseId);
        return courseDetailInfoVO;
    }
}



