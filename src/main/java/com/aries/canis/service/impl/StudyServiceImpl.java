package com.aries.canis.service.impl;

import com.aries.canis.mapper.ArticleMapper;
import com.aries.canis.mapper.CourseMapper;
import com.aries.canis.model.po.Article;
import com.aries.canis.model.po.Course;
import com.aries.canis.model.po.CourseExample;
import com.aries.canis.service.StudyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
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
    public int deleteCourseById(Long id) {
        int i = courseMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public Course getCourseById(Long id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        return course;
    }

    @Override
    public int editCourse(Course course) {
        int i = courseMapper.updateByPrimaryKeySelective(course);
        return i;
    }

    @Override
    public Long addArticle(Article article) {
        int i = articleMapper.insertSelective(article);
        if (i > 0) {
            return article.getId();
        }
        return -1L;
    }

    @Override
    public Article getArticleById(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return article;
    }

    @Override
    public int editArticle(Article article) {
        int i = articleMapper.updateByPrimaryKeySelective(article);
        return i;
    }

    @Override
    public int deleteArticle(Long id) {
        int i = articleMapper.deleteByPrimaryKey(id);
        return i;
    }
}
