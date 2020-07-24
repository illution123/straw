package cn.tedu.straw.portal.controller;


import cn.tedu.straw.portal.dto.QuestionDTO;
import cn.tedu.straw.portal.security.UserInfo;
import cn.tedu.straw.portal.service.IQuestionService;
import cn.tedu.straw.portal.vo.QuestionListItemVO;
import cn.tedu.straw.portal.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    @Autowired
    IQuestionService questionService;

    // http://localhost:8080/api/v1/questions/create?title=Java&content=HelloWord&tagIds=3&tagIds=9&tagIds=15&teacherIds=1&teacherIds=3
    @RequestMapping("/create")
    public R<Void> create(QuestionDTO questionDTO, @AuthenticationPrincipal UserInfo userInfo){
        questionService.create (questionDTO, userInfo.getId(), userInfo.getNickname());
        return R.ok();
    }

    @GetMapping("/hits")
    public R<List<QuestionListItemVO>> mostHits() {
        return R.ok(questionService.getMostHits());
    }
}
