package com.aries.canis.service;

import com.aries.canis.model.po.Article;
import com.aries.canis.model.po.Course;

import java.util.List;

public interface StudyService {
    List<Course> getAllCourse();

    List<Article> getAllArticle();
}
