package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TagServiceTests {

    @Autowired
    ITagService tagService;

    @Test
    void getTags(){
        List<TagVO> tags = tagService.getTags();
        for (TagVO tag:tags){
            log.debug("tag : {}",tag);
        }
    }
}
