package com.atguigu.edu.service;

import com.atguigu.entity.EduChapter;
import com.atguigu.vo.ChapterVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author fh
 * @since 2020-11-09
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 添加章节
     * @param chapter
     * @return
     */
    boolean saveChapter(EduChapter chapter);

    /**
     * 根据id删除章节
     * @param chapterId
     * @return
     */
    boolean deleteChapter(String chapterId);

    /**
     * 查询所有章节信息
     * @return
     */
    List<ChapterVO> getChapterAndSection(String courseId);
}
