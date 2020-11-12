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

    /**
     * 添加一级分类
     * @param eduSubject
     * @return
     */
    boolean saveParentSubject(EduSubject eduSubject);

    /**
     * 添加二级分类信息
     * @param eduSubject
     * @return
     */
    boolean saveChildrenSubject(EduSubject eduSubject);

    /**
     * 根据id删除课程分类信息，如果该课程分类有子节点删除失败
     * @param subjectId
     * @return
     */
    boolean deleteSubjectById(String subjectId);
}
