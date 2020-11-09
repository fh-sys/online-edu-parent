package com.atguigu.edu.service;

import com.atguigu.edu.entity.EduSubject;
import com.atguigu.response.SubjectResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author fh
 * @since 2020-11-06
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 从Excel中导入分类信息
     * @param file
     * @return
     */
    void importByExcel(MultipartFile file) throws IOException;


    /**
     * 根据名称和id查询是否存在
     * @param title
     * @param parentId
     * @return
     */
    EduSubject existByTitleAndParentId(String title, String parentId);

    /**
     * 查询所有分类信息
     * @return
     */
    List<SubjectResponse> getAllSubject();
}
