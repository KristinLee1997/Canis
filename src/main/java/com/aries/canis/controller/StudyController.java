package com.aries.canis.controller;

import com.aries.canis.model.po.Course;
import com.aries.canis.service.StudyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/study")
public class StudyController {
    @Resource
    private StudyService studyService;

    @RequestMapping("/list")
    public ModelAndView list() {
        List<Course> courseList = studyService.getAllCourse();
        ModelAndView modelAndView = new ModelAndView("study-list");
        modelAndView.addObject("courseList", courseList);
        return modelAndView;
    }
}
