package com.aries.canis.controller;

import com.alibaba.fastjson.JSON;
import com.aries.canis.constants.SystemStatus;
import com.aries.canis.model.HttpResponse;
import com.aries.canis.model.po.Article;
import com.aries.canis.model.po.Course;
import com.aries.canis.service.StudyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/study")
@Slf4j
public class ArticleController {
    @Resource
    private StudyService studyService;

    @GetMapping("/article/list")
    public ModelAndView articleList() {
        List<Article> articleList = studyService.getAllArticle();
        ModelAndView modelAndView = new ModelAndView("article-list");
        modelAndView.addObject("articleList", articleList);
        return modelAndView;
    }

    @GetMapping("/article/upload")
    public ModelAndView upload() {
        ModelAndView modelAndView = new ModelAndView("article-add");
        List<Course> courseList = studyService.getAllCourse();
        if (CollectionUtils.isNotEmpty(courseList)) {
            modelAndView.addObject("courseList", courseList);
        }
        return modelAndView;
    }

    @PostMapping("/article/upload/detail")
    @ResponseBody
    public String uploadArticle(@RequestBody Article article) {
        Long id = studyService.addArticle(article);
        if (id < 0) {
            log.warn("添加文章失败，参数：{}", JSON.toJSONString(article));
            return HttpResponse.of(SystemStatus.DATABASE_ERROR);
        }
        return HttpResponse.of(SystemStatus.SUCCESS);
    }

    @GetMapping("/article/get/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("article-detail");
        if (id < 0) {
            log.warn("查询文章失败，参数：{}异常", id);
            return modelAndView;
        }
        Article article = studyService.getArticleById(id);
        if (article == null) {
            log.warn("id:{}文章不存在", id);
            return modelAndView;
        }
        Course course = studyService.getCourseById(article.getCourseId());
        modelAndView.addObject("article", article);
        if (course != null) {
            modelAndView.addObject("course", course);
        }
        return modelAndView;
    }

    @GetMapping("/article/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("article-edit");
        if (id < 0) {
            log.warn("查询文章失败，参数：{}异常", id);
            return modelAndView;
        }

        Article article = studyService.getArticleById(id);
        if (article == null) {
            log.warn("id:{}文章不存在", id);
            return modelAndView;
        }
        modelAndView.addObject("article", article);

        Course course = studyService.getCourseById(article.getCourseId());
        if (course != null) {
            modelAndView.addObject("course", course);
        }
        List<Course> courseList = studyService.getAllCourse();
        if (CollectionUtils.isNotEmpty(courseList)) {
            modelAndView.addObject("courseList", courseList);
        }
        return modelAndView;
    }

    @PostMapping("/article/edit")
    @ResponseBody
    public String editArticle(@RequestBody Article article) {
        if (article.getId() <= 0) {
            log.warn("编辑文章信息参数不合法，参数{}", JSON.toJSONString(article));
            return HttpResponse.of(SystemStatus.PARAM_ERROR);
        }
        int i = studyService.editArticle(article);
        if (i < 1) {
            log.warn("编辑文章失败，参数:{}", JSON.toJSONString(article));
            return HttpResponse.of(SystemStatus.DATABASE_ERROR);
        }
        return HttpResponse.of(SystemStatus.SUCCESS);
    }

    @GetMapping("/article/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("article-list");
        if (id <= 0) {
            log.warn("删除文章信息参数不合法，参数{}", id);
            return modelAndView;
        }
        int i = studyService.deleteArticle(id);
        if (i <= 0) {
            log.warn("删除文章失败，id:{}", id);
            return modelAndView;
        }
        return modelAndView;
    }
}
