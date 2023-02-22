package com.zhen.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.edu.entity.EduChapter;
import com.zhen.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * @author zhen
 * @since 2023-02-09 16:53:31
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}

