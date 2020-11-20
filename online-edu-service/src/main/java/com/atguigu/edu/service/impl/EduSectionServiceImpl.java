package com.atguigu.edu.service.impl;

import com.atguigu.entity.EduSection;
import com.atguigu.edu.mapper.EduSectionMapper;
import com.atguigu.edu.openfeign.VideoServiceFeign;
import com.atguigu.edu.service.EduSectionService;
import com.atguigu.handler.MyRuntimeException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程小节 服务实现类
 * </p>
 *
 * @author fh
 * @since 2020-11-09
 */
@Service
public class EduSectionServiceImpl extends ServiceImpl<EduSectionMapper, EduSection> implements EduSectionService {

    @Autowired
    private VideoServiceFeign videoServiceFeign;
    @Override
    public void addSection(EduSection section) {
        //1.判断是否存在小节
        QueryWrapper<EduSection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",section.getCourseId());
        queryWrapper.eq("chapter_id",section.getChapterId());
        queryWrapper.eq("title",section.getTitle());
        EduSection existSection = baseMapper.selectOne(queryWrapper);
        if(existSection==null){
            baseMapper.insert(section);
        }else{
            throw new MyRuntimeException(20001,"存在重复的小节");
        }
    }

    @Override
    public void deleteSection(String id) {
        //删除视频
        EduSection eduSection = baseMapper.selectById(id);
        String videoSourceId = eduSection.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            videoServiceFeign.deleteSingleVideo(videoSourceId);
        }
        //删除小节
        baseMapper.deleteById(id);
    }
}
