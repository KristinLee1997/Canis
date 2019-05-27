package com.aries.canis.controller;

import com.alibaba.fastjson.JSON;
import com.aries.canis.model.po.Article;
import com.aries.canis.model.po.Course;
import com.aries.canis.service.StudyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/study")
@Slf4j
public class StudyController {
    @Resource
    private StudyService studyService;

    @RequestMapping("/course/list")
    public ModelAndView list() {
        List<Course> courseList = studyService.getAllCourse();
        ModelAndView modelAndView = new ModelAndView("study-list");
        modelAndView.addObject("courseList", courseList);
        return modelAndView;
    }

    @PostMapping("/course/add")
    public ModelAndView add(@RequestBody Course course) {
        ModelAndView modelAndView = new ModelAndView("study-list");
        if (course.getCourseName() == null || course.getTeacherName() == null) {
            log.warn("添加专栏操作参数不完整，参数{}", JSON.toJSONString(course));
            return modelAndView;
        }
        Long i = studyService.addCourse(course);
        if (i == 0) {
            log.warn("添加专栏失败，参数{}", course);
        }
        return modelAndView;
    }

    @GetMapping("/course/delete/{id}")
    public ModelAndView deleteById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("study-list");
        if (id <= 0) {
            log.warn("删除专栏操作异常，id：", id);
            return modelAndView;
        }
        studyService.deletebyId(id);
        return modelAndView;
    }

    @GetMapping("/article/list")
    public ModelAndView articleList() {
        List<Article> articleList = studyService.getAllArticle();
        ModelAndView modelAndView = new ModelAndView("article-list");
        modelAndView.addObject("articleList", articleList);
        return modelAndView;
    }
}
