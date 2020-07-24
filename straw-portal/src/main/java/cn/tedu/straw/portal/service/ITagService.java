package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.model.Tag;
import cn.tedu.straw.portal.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-07-20
 */
public interface ITagService extends IService<Tag> {

    /**
     * 获取标签列表
     * @return
     */
    List<TagVO> getTags();

    /**
     * 获取缓存的标签列表
     * @return
     */
    List<TagVO> getCachedTags();
}
