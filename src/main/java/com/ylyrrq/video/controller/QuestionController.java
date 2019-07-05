package com.ylyrrq.video.controller;

import com.ylyrrq.video.pojo.Oauth;
import com.ylyrrq.video.pojo.Question;
import com.ylyrrq.video.pojo.Subject;
import com.ylyrrq.video.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @RequestMapping("/alltimu")
    public String allTiMu(Model model){
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(1,"java","/yf-cssjsimg/public/images/upload/images/problemset_java.jpg",questionService.queryQuestionList("java").size()));
        subjects.add(new Subject(2,"python","/yf-cssjsimg/public/images/upload/images/problemset_python.jpg",questionService.queryQuestionList("python").size()));
        subjects.add(new Subject(3,"数据结构","/yf-cssjsimg/public/images/upload/images/problemset_datastructures.jpg",questionService.queryQuestionList("数据结构").size()));
        subjects.add(new Subject(4,"神经网络","/yf-cssjsimg/public/images/upload/images/problemset_algorithm.jpg",questionService.queryQuestionList("神经网络").size()));
        subjects.add(new Subject(5,"实习题","/yf-cssjsimg/public/images/upload/images/problemset_digitalcircuits.jpg",questionService.queryQuestionList("实习题").size()));
        model.addAttribute("subjects", subjects);
        return "alltimu";
    }

    @GetMapping("/timuliebiao/{suid}")
    public String tiMuLieBiao(Model model, @PathVariable("suid") int suid){
        String suname = "";
        if(suid == 1){
            suname = "java";
        }else if(suid == 2){
            suname = "python";
        }else if(suid == 3){
            suname = "数据结构";
        }else if(suid == 4){
            suname = "神经网络";
        }else{
            suname = "实习题";
        }
        List<Question> questions = questionService.queryQuestionList(suname);
        model.addAttribute("questions", questions);
        model.addAttribute("suname", suname);
        return "timuliebiao";
    }

    @GetMapping("/timuxiangqing/{qid}")
    public String tiMuXiangQing(@PathVariable("qid") int qid, Model model){
        Question question = questionService.queryQuestionById(qid);
        model.addAttribute("question", question);
        String suname = question.getSuname();
        int suid = 0;
        if(suname.equals("java")){
            suid = 1;
        }else if(suname.equals("python")){
            suid = 2;
        }else if(suname.equals("数据结构")){
            suid = 3;
        }else if(suname.equals("神经网络")){
            suid = 4;
        }else{
            suid = 5;
        }
        model.addAttribute("suid", suid);
        return "timuxiangqing";
    }

    @PostMapping("/question/add")
    @ResponseBody
    public void add(Question question, HttpSession session){
        question.setCreatetime(new Date());
        Oauth oauth = (Oauth) session.getAttribute("oauth");
        question.setNickname(oauth.getNickname());
        questionService.saveQuestion(question);
    }

}
