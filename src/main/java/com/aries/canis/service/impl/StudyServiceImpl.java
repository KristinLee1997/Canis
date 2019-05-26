package com.aries.canis.service.impl;

import com.aries.canis.mapper.CourseMapper;
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
}
