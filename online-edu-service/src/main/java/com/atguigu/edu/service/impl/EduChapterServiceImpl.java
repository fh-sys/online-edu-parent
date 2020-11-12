package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.EduChapter;
import com.atguigu.edu.entity.EduSection;
import com.atguigu.edu.mapper.EduChapterMapper;
import com.atguigu.edu.service.EduChapterService;
import com.atguigu.edu.service.EduSectionService;
import com.atguigu.edu.vo.ChapterVO;
import com.atguigu.edu.vo.SectionVO;
import com.atguigu.handler.MyRuntimeException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author fh
 * @since 2020-11-09
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduSectionService sectionService;

    @Override
    public boolean saveChapter(EduChapter chapter) {
        //判断是否存在
        EduChapter existChapter = existChapter(chapter.getCourseId(), chapter.getTitle());
        if(existChapter==null){
            int insert = baseMapper.insert(chapter);
            return insert>0;
        }else{
            throw new MyRuntimeException(20001,"章节已经重复");
        }
    }

    public EduChapter existChapter(String courseId, String chapterName) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("title", chapterName);
        EduChapter chapter = baseMapper.selectOne(queryWrapper);
        return chapter;

    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //判断是否有小节
        QueryWrapper<EduSection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        int count = sectionService.count(queryWrapper);
        //没有小节
        if(count==0){
            int i = baseMapper.deleteById(chapterId);
            return i>0;
        }else{
            throw new MyRuntimeException(20001,"该章节存在小节");
        }
    }

    @Override
    public List<ChapterVO> getChapterAndSection(String courseId) {
        //封装返回的章节小节集合
        List<ChapterVO> chapterVOList = new ArrayList<>();
        //查询所有章节信息
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(chapterQueryWrapper);
        //查询所有小节信息
        QueryWrapper<EduSection> sectionQueryWrapper = new QueryWrapper<>();
        sectionQueryWrapper.eq("course_id",courseId);
        List<EduSection> sectionList = sectionService.list(sectionQueryWrapper);
        for (EduChapter eduChapter : chapterList) {
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(eduChapter,chapterVO);
            for (EduSection eduSection : sectionList) {
                if (eduChapter.getId().equals(eduSection.getChapterId())){
                    SectionVO sectionVO = new SectionVO();
                    BeanUtils.copyProperties(eduSection,sectionVO);
                    chapterVO.getSectionVOList().add(sectionVO);
                }
            }
            chapterVOList.add(chapterVO);
        }
        return chapterVOList;
    }
}
