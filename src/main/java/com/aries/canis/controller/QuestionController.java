package com.aries.canis.controller;

import com.alibaba.fastjson.JSON;
import com.aries.canis.model.po.Category;
import com.aries.canis.model.po.Question;
import com.aries.canis.model.vo.QuestionVO;
import com.aries.canis.service.QuestionService;
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
@RequestMapping("/question")
@Slf4j
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @RequestMapping("/list")
    public ModelAndView list() {
        List<QuestionVO> questionList = questionService.getQuestionList(1, 20);
        ModelAndView modelAndView = new ModelAndView("question-list");
        modelAndView.addObject("questionvolist", questionList);
        return modelAndView;
    }

    @PostMapping("/upload")
    public String upload(@RequestBody Question question) {
        System.out.println(JSON.toJSONString(question));
        Long id = questionService.upload(question);
        log.info("上传题目成功，题号：{}", id);
        return "upload";
    }

    @GetMapping("/upload/detail")
    public ModelAndView uploadT() {
        List<Category> categoryList = questionService.selectAllCategory();
        ModelAndView modelAndView = new ModelAndView("question-add");
        modelAndView.addObject("categorylist", categoryList);
        return modelAndView;
    }

    @GetMapping("/get/{id}")
    public ModelAndView get(@PathVariable("id") Long id) {
        QuestionVO question = questionService.getQuestionById(id);
        List<Category> categoryList = questionService.selectAllCategory();
        ModelAndView modelAndView = new ModelAndView("question-edit");
        modelAndView.addObject("quesiton", question);
        modelAndView.addObject("categorylist", categoryList);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String update(@RequestBody Question question) {
        Long id = questionService.update(question);
        log.info("上传题目成功，题号：{}", id);
        return "update";
    }
}