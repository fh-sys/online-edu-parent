package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.EduTeacher;
import com.atguigu.edu.mapper.EduTeacherMapper;
import com.atguigu.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author fh
 * @since 2020-11-02
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Map<String, Object> getTeacherByPage(long pageNum, long pageSize) {
        Page<EduTeacher> teacherPage = new Page<>(pageNum, pageSize);
        baseMapper.selectPage(teacherPage,null);
        List<EduTeacher> teacherList = teacherPage.getRecords();
        long pages = teacherPage.getPages();
        long currentPage = teacherPage.getCurrent();
        long size = teacherPage.getSize();
        long totalNum = teacherPage.getTotal();
        boolean hasPrevious = teacherPage.hasPrevious();
        boolean hasNext = teacherPage.hasNext();
        
        Map<String, Object> retMap = new HashMap<>();

        retMap.put("teacherList", teacherList);
        retMap.put("currentPage", currentPage);
        retMap.put("pages", pages);
        retMap.put("size", size);
        retMap.put("totalNum", totalNum);
        retMap.put("hasNext", hasNext);
        retMap.put("hasPrevious", hasPrevious);
        return retMap;
    }
}
