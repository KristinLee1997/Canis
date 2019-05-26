package com.aries.canis.service;

import com.aries.canis.model.po.Category;
import com.aries.canis.model.po.Question;
import com.aries.canis.model.vo.QuestionDifficultyVO;
import com.aries.canis.model.vo.QuestionVO;

import java.util.List;

public interface QuestionService {
    List<QuestionVO> getQuestionList(int page, int pageSize);

    QuestionVO getQuestionById(Long id);

    Category selectCategoryByPrimary(Integer id);

    List<Category> getCategoryList();

    Long upload(Question question);

    Long update(Question question);

    List<Category> selectAllCategory();

    List<QuestionDifficultyVO> getDifficultyList();
}
