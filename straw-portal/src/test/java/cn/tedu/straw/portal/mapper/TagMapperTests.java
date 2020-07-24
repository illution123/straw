package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TagMapperTests {

    @Autowired
    TagMapper tagMapper;

    @Test
    void findTags(){
        List<TagVO> tags = tagMapper.findAll();
        for (TagVO tag:tags){
            log.debug("tag : {}",tag);
        }

    }
}
