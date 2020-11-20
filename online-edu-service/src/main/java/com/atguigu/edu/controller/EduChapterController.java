package com.atguigu.edu.controller;


import com.atguigu.entity.EduChapter;
import com.atguigu.edu.service.EduChapterService;
import com.atguigu.vo.ChapterVO;
import com.atguigu.response.RetVal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author fh
 * @since 2020-11-09
 */
@Api(tags = "章节管理")
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @ApiOperation("查询章节的信息")
    @GetMapping("/getChapterAndSection/{courseId}")
    public RetVal getChapterAndSection(@PathVariable("courseId")String courseId){
        List<ChapterVO> chapterVOList = chapterService.getChapterAndSection(courseId);
        return RetVal.success().data("chapterList",chapterVOList);
    }

    @ApiOperation("添加章节")
    @PostMapping
    public RetVal saveChapter(EduChapter chapter){
        boolean flag=chapterService.saveChapter(chapter);
        if(flag){
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }
    @ApiOperation("根据章节id查询章节信息")
    @GetMapping("getChapter/{id}")
    public RetVal getChapter(@PathVariable String id){
        EduChapter chapter = chapterService.getById(id);
        return RetVal.success().data("chapter",chapter);
    }

    @ApiOperation("更新章节信息")
    @PutMapping
    public RetVal updateChapter(EduChapter chapter){
        boolean flag = chapterService.updateById(chapter);
        if(flag){
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }

    @ApiOperation("删除章节信息")
    @DeleteMapping("{chapterId}")
    public RetVal deleteChapter(@PathVariable("chapterId") String chapterId){
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag){
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }
}

