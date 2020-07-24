package cn.tedu.straw.portal.service.impl;

import cn.tedu.straw.portal.dto.QuestionDTO;
import cn.tedu.straw.portal.mapper.QuestionMapper;
import cn.tedu.straw.portal.mapper.QuestionTagMapper;
import cn.tedu.straw.portal.mapper.UserQuestionMapper;
import cn.tedu.straw.portal.model.Question;
import cn.tedu.straw.portal.model.QuestionTag;
import cn.tedu.straw.portal.model.UserQuestion;
import cn.tedu.straw.portal.schedule.CacheSchedule;
import cn.tedu.straw.portal.service.IQuestionService;
import cn.tedu.straw.portal.service.ex.InsertException;
import cn.tedu.straw.portal.vo.QuestionListItemVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-07-22
 */
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionTagMapper questionTagMapper;
    @Autowired
    private UserQuestionMapper userQuestionMapper;

    @Transactional
    public void create(QuestionDTO questionDTO, Integer userid, String userNickname) {
        // 创建当前时间对象：now
        LocalDateTime now = LocalDateTime.now();

        // 将questionDTO中的tagIds转换成例如 2,7,9 这种格式的字符串，名为tagIdsStr
        String tagIdsStr = Arrays.toString(questionDTO.getTagIds());    // [2, 7, 9]
        tagIdsStr = tagIdsStr.substring(1, tagIdsStr.length() - 1);     // 2, 7, 9

        // 创建Question对象
        // 向Question对象中补全数据
        // - title / content > questionDTO的title / content
        // - userId / userNickName > 参数
        // - status > 0
        // - hits > 0
        // - isPublic > 1
        // - createdTime / modifiedTime > now
        // - isDelete > 0
        // - tagIds > tagIdsStr
        Question question = new Question()
                .setTitle(questionDTO.getTitle())
                .setContent(questionDTO.getContent())
                .setUserId(userid)
                .setUserNickName(userNickname)
                .setStatus(0).setHits(0).setIsPublic(1).setIsDelete(0)
                .setCreatedTime(now).setModifiedTime(now)
                .setTagIds(tagIdsStr);
        // 基于以上Question对象，调用questionMapper的insert()方法，向question表中插入数据，获取返回值
        int rows = questionMapper.insert(question);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("发布问题失败！当前服务器忙，请稍后再尝试！");
        }

        // 遍历questionDTO中的tagIds
        for (Integer tagId : questionDTO.getTagIds()) {
            // - 创建QuestionTag对象
            // - 补全属性：questionId > 以上插入Question对象的id
            // - 补全属性：tagId > 被遍历到的数据
            QuestionTag questionTag = new QuestionTag()
                    .setQuestionId(question.getId())
                    .setTagId(tagId);
            // - 基于以上QuestionTag对象，调用questionTagMapper的insert()方法，向question_tag表中插入数据，以记录“问题”与“标签”的对应关系，并需要获取当前调用方法的返回值
            rows = questionTagMapper.insert(questionTag);
            // - 判断返回值是否不为1
            if (rows != 1) {
                // 是：抛出InsertException
                throw new InsertException("发布问题失败！当前服务器忙，请稍后再尝试！");
            }
        }

        // 遍历questionDTO中的teacherIds
        for (Integer teacherId : questionDTO.getTeacherIds()) {
            // - 创建UserQuestion对象
            // - 补全属性：questionId > 以上插入Question对象的id
            // - 补全属性：userId > 被遍历到的数据
            // - 补全属性：createdTime > now
            UserQuestion userQuestion = new UserQuestion()
                    .setQuestionId(question.getId())
                    .setUserId(teacherId)
                    .setCreatedTime(now);
            // - 基于以上UserQuestion对象，调用userQuestionMapper的insert()方法，向user_question表中插入数据，以记录“问题”与“回答问题的老师”的对应关系，并需要获取当前调用方法的返回值
            rows = userQuestionMapper.insert(userQuestion);
            // - 判断返回值是否不为1
            if (rows != 1) {
                // 是：抛出InsertException
                throw new InsertException("发布问题失败！当前服务器忙，请稍后再尝试！");
            }
        }
    }

    private List<QuestionListItemVO> questions = new CopyOnWriteArrayList<>();

    @Override
    public List<QuestionListItemVO> getMostHits() {
        // 判断有没有必要锁住代码
        if (questions.isEmpty()) {
            // 锁住代码
            synchronized (CacheSchedule.LOCK_CACHE_QUESTION) {
                // 判断有没有必要重新加载数据
                if (questions.isEmpty()) {
                    questions.addAll(questionMapper.findMostHits());
                }
            }
        }
        return questions;
    }

    @Override
    public List<QuestionListItemVO> getCachedMostHits() {
        return questions;
    }
}
