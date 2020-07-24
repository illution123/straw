package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.dto.QuestionDTO;
import cn.tedu.straw.portal.model.Question;
import cn.tedu.straw.portal.vo.QuestionListItemVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-07-22
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 发布问题
     * @param questionDTO   从客户端提交过来的数据
     * @param userId        当前登录的用户id
     * @param userNickname  用户登录的用户昵称
     */
    void create(QuestionDTO questionDTO, Integer userId, String userNickname);

    /**
     * 点击数量最多的问题的列表,将从缓存中获取列表，如果缓存中没有数据，直接去数据库查询
     * @return
     */
    List<QuestionListItemVO> getMostHits();
    List<QuestionListItemVO> getCachedMostHits();
}
