package com.aries.canis.controller;

import com.alibaba.fastjson.JSON;
import com.aries.canis.constants.SystemStatus;
import com.aries.canis.model.HttpResponse;
import com.aries.canis.model.po.Course;
import com.aries.canis.service.StudyService;
import lombok.extern.slf4j.Slf4j;
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
public class CourseController {
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
    @ResponseBody
    public String add(@RequestBody Course course) {
        ModelAndView modelAndView = new ModelAndView("study-list");
        if (course.getCourseName() == null || course.getTeacherName() == null) {
            log.warn("添加专栏操作参数不完整，参数{}", JSON.toJSONString(course));
            return HttpResponse.of(SystemStatus.SYSTEM_ERROR);
        }
        Long i = studyService.addCourse(course);
        if (i == 0) {
            log.warn("添加专栏失败，参数{}", course);
        }
        return HttpResponse.of(SystemStatus.SUCCESS);
    }

    @GetMapping("/course/add/detail")
    public ModelAndView addCourseDetail() {
        ModelAndView modelAndView = new ModelAndView("course-add");
        return modelAndView;
    }

    @GetMapping("/course/edit/{id}")
    public ModelAndView getCourseById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("course-edit");
        if (id <= 0) {
            log.warn("获取专栏信息异常，参数id:{}", id);
        }
        Course course = studyService.getCourseById(id);
        if (course == null) {
            log.warn("查询id:{}专栏不存在", id);
        }
        modelAndView.addObject("course", course);
        return modelAndView;
    }

    @PostMapping("/course/edit")
    @ResponseBody
    public String editCourse(@RequestBody Course course) {
        if (course.getId() <= 0) {
            log.warn("编辑专栏信息参数不合法，参数{}", JSON.toJSONString(course));
            return HttpResponse.of(SystemStatus.PARAM_ERROR);
        }
        int i = studyService.editCourse(course);
        if (i < 1) {
            log.warn("编辑专栏失败，参数:{}", JSON.toJSONString(course));
            return HttpResponse.of(SystemStatus.DATABASE_ERROR);
        }
        return HttpResponse.of(SystemStatus.SUCCESS);
    }

    @GetMapping("/course/delete/{id}")
    public ModelAndView deleteById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("study-list");
        if (id <= 0) {
            log.warn("删除专栏操作异常，id：", id);
            return modelAndView;
        }
        int i = studyService.deleteCourseById(id);
        if (i <= 0) {
            log.warn("删除文章失败，id:{}", id);
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping("/course/detail/{id}")
    public ModelAndView courseDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("course-detail");
        if (id <= 0) {
            log.warn("获取专栏信息异常，参数id:{}", id);
        }
        Course course = studyService.getCourseById(id);
        if (course == null) {
            log.warn("查询id:{}专栏不存在", id);
        }
        modelAndView.addObject("course", course);
        return modelAndView;
    }
}
