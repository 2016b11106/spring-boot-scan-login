package com.ylyrrq.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ylyrrq.video.dao.QuestionDao;
import com.ylyrrq.video.pojo.Question;
import com.ylyrrq.video.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDao questionDao;
    @Override
    public List<Question> queryQuestionList(String suname) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("suname", suname);
        List<Question> questions = questionDao.selectList(queryWrapper);
        return questions;
    }

    @Override
    public Question queryQuestionById(int qid) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qid", qid);
        Question question = questionDao.selectOne(queryWrapper);
        return question;
    }

    @Override
    public void saveQuestion(Question question) {
        questionDao.insert(question);
    }
}
