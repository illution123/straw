package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.model.Question;
import cn.tedu.straw.portal.vo.QuestionListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-07-22
 */
@Repository
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 查询点击量最多的问题的列表
     * @return
     */
    List<QuestionListItemVO> findMostHits();
}
