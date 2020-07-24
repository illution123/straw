package cn.tedu.straw.portal.service.impl;

import cn.tedu.straw.portal.mapper.TagMapper;
import cn.tedu.straw.portal.model.Tag;
import cn.tedu.straw.portal.schedule.CacheSchedule;
import cn.tedu.straw.portal.service.ITagService;
import cn.tedu.straw.portal.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-07-20
 */
@Service
@Slf4j
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Autowired
    TagMapper tagMapper;

    private List<TagVO> tags = new CopyOnWriteArrayList<>();

    @Override
    public List<TagVO> getTags() {
        // 判断有没有必要锁住代码
        if (tags.isEmpty()) {
            // 锁住代码
            synchronized (CacheSchedule.LOCK_CACHE) {
                // 判断有没有必要重新加载数据
                if (tags.isEmpty()) {
                    tags.addAll(tagMapper.findAll());
                    log.debug("create tags cache ...");
                    log.debug(">>> tags : {}", tags);
                }
            }
        }
        return tags;
    }

    @Override
    public List<TagVO> getCachedTags() {
        return tags;
    }
}
