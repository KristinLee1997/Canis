package com.aries.canis.service;

import com.aries.canis.model.po.Article;
import com.aries.canis.model.po.Course;

import java.util.List;

public interface StudyService {
    List<Course> getAllCourse();

    Long addCourse(Course course);

    int deleteCourseById(Long id);

    Course getCourseById(Long id);

    int editCourse(Course course);


    List<Article> getAllArticle();

    Long addArticle(Article article);

    Article getArticleById(Long id);

    int editArticle(Article article);

    int deleteArticle(Long id);
}
