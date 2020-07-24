package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.dto.QuestionDTO;
import cn.tedu.straw.portal.service.ex.ServiceException;
import cn.tedu.straw.portal.vo.QuestionListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QuestionServiceTests {

    @Autowired
    IQuestionService service;

    @Test
    void create() {
        try {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setTitle("SpringSecurity验证时记录了用户的ID吗？");
            questionDTO.setContent("SpringSecurity自动完成验证，可以获取用户名，但是，用户ID在哪里获取？");
            questionDTO.setTagIds(new Integer[] { 5, 8, 13 });
            questionDTO.setTeacherIds(new Integer[] { 2, 3 });
            Integer userId = 5;
            String userNickname = "小周同学";
            service.create(questionDTO, userId, userNickname);
            log.debug("create question ok.");
        } catch (ServiceException e) {
            log.debug("create question failure.", e);
        }
    }

    @Test
    void getCachedMostHits(){
        List<QuestionListItemVO> questions = service.getMostHits();
        for (QuestionListItemVO question:questions){
            log.debug(">>>> {}",question);
        }
    }
}
