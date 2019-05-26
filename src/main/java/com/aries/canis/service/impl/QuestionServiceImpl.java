package com.aries.canis.service.impl;

import com.aries.canis.enums.QuestionDifficultyEnum;
import com.aries.canis.enums.QuestionTypeEnum;
import com.aries.canis.mapper.CategoryMapper;
import com.aries.canis.mapper.QuestionMapper;
import com.aries.canis.model.po.Category;
import com.aries.canis.model.po.CategoryExample;
import com.aries.canis.model.po.Question;
import com.aries.canis.model.po.QuestionExample;
import com.aries.canis.model.vo.QuestionDifficultyVO;
import com.aries.canis.model.vo.QuestionVO;
import com.aries.canis.service.QuestionService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<QuestionVO> getQuestionList() {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andAuditEqualTo(0);
        example.setOrderByClause("id asc");
        List<Question> questionList = questionMapper.selectByExample(example);
        List<QuestionVO> questionVOList = new ArrayList<>();
        for (Question question : questionList) {
            questionVOList.add(convert2QuestionVO(question));
        }
        return questionVOList;
    }

    @Override
    public List<QuestionVO> getAuditList() {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andAuditEqualTo(1);
        example.setOrderByClause("id asc");
        List<Question> questionList = questionMapper.selectByExample(example);
        List<QuestionVO> questionVOList = new ArrayList<>();
        for (Question question : questionList) {
            questionVOList.add(convert2QuestionVO(question));
        }
        return questionVOList;
    }

    @Override
    public QuestionVO getQuestionById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionVO questionVO = convert2QuestionVO(question);
        return questionVO;
    }

    @Override
    public Category selectCategoryByPrimary(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }

    @Override
    public List<Category> getCategoryList() {
        CategoryExample example = new CategoryExample();
        example.createCriteria();
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return categoryList;
    }

    @Override
    public List<Category> selectAllCategory() {
        List<Category> categoryList = categoryMapper.selectByExample(null);
        if (CollectionUtils.isEmpty(categoryList)) {
            return Collections.emptyList();
        }
        return categoryList;
    }

    @Override
    public List<QuestionDifficultyVO> getDifficultyList() {
        List<Integer> codeList = QuestionDifficultyEnum.getDifficultyList();
        List<QuestionDifficultyVO> difficultyVOList = new ArrayList<>();
        for (Integer code : codeList) {
            QuestionDifficultyVO difficultyVO = new QuestionDifficultyVO();
            difficultyVO.setCode(code);
            difficultyVO.setLevel(QuestionDifficultyEnum.getLevel(code));
            difficultyVOList.add(difficultyVO);
        }
        return difficultyVOList;
    }

    @Override
    public Long upload(Question question) {
        questionMapper.insertSelective(question);
        return question.getId();
    }

    @Override
    public Long update(Question question) {
        questionMapper.updateByPrimaryKeySelective(question);
        return question.getId();
    }

    private QuestionVO convert2QuestionVO(Question question) {
        QuestionVO questionVo = new QuestionVO();
        questionVo.setId(question.getId());
        questionVo.setType(QuestionTypeEnum.getTypeName(question.getType()));
        questionVo.setTitle(question.getTitle());
        questionVo.setDescription(question.getDescription());
        questionVo.setAnswer(question.getAnswer());
        questionVo.setCategoryId(question.getCategoryId());
        questionVo.setCategoryName(selectCategoryByPrimary(question.getCategoryId()).getCategoryName());
        questionVo.setDifficulty(QuestionDifficultyEnum.getLevel(question.getDifficulty()));
        if (question.getType().equals(QuestionTypeEnum.SelectionType.getId())) {
            questionVo.setSelection(question.getSelection());
        }
        questionVo.setPassNum(22);
        return questionVo;
    }

}
