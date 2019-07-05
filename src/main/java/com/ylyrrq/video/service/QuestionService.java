package com.ylyrrq.video.service;

import com.ylyrrq.video.pojo.Question;

import java.util.List;

public interface QuestionService {

    List<Question> queryQuestionList(String suname);
    Question queryQuestionById(int qid);
    void saveQuestion(Question question);
}
