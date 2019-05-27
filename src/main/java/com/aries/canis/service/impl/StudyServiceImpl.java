package com.aries.canis.service.impl;

import com.aries.canis.mapper.ArticleMapper;
import com.aries.canis.mapper.CourseMapper;
import com.aries.canis.model.po.Article;
import com.aries.canis.model.po.Course;
import com.aries.canis.model.po.CourseExample;
import com.aries.canis.service.StudyService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class StudyServiceImpl implements StudyService {
    @Resource
    private CourseMapper courseMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<Course> getAllCourse() {
        CourseExample example = new CourseExample();
        example.createCriteria();
        List<Course> courseList = courseMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(courseList)) {
            return Collections.emptyList();
        }
        return courseList;
    }

    @Override
    public List<Article> getAllArticle() {
        List<Article> articles = articleMapper.selectAllArticle();
        if (CollectionUtils.isEmpty(articles)) {
            return Collections.emptyList();
        }
        return articles;
    }

    @Override
    public Long addCourse(Course course) {
        int i = courseMapper.insertSelective(course);
        if (i == 1) {
            return course.getId();
        } else {
            return 0L;
        }
    }

    @Override
    public int deletebyId(Long id) {
        int i = courseMapper.deleteByPrimaryKey(id);
        return i;
    }
}
