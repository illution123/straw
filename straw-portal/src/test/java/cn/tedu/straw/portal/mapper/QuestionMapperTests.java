package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.vo.QuestionListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QuestionMapperTests {

    @Autowired
    QuestionMapper questionMapper;

    @Test
    void findMostHits(){
        List<QuestionListItemVO> questions = questionMapper.findMostHits();
        for (QuestionListItemVO question:questions){
            log.debug(">>>> {}",question);
        }

    }
}
