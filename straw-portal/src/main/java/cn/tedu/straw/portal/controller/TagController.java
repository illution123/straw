package cn.tedu.straw.portal.controller;


import cn.tedu.straw.portal.service.ITagService;
import cn.tedu.straw.portal.vo.R;
import cn.tedu.straw.portal.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    @Autowired
    ITagService tagService;

    @GetMapping("")
    public R<List<TagVO>> getTags(){
        return R.ok(tagService.getTags());
    }
}
