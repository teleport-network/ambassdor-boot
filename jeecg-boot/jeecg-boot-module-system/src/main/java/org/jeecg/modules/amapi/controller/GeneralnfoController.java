package org.jeecg.modules.amapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.amquest.controller.QuestController;
import org.jeecg.modules.amquest.entity.Quest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags="userprofile")
@RestController
@RequestMapping("/ambassador")
@Slf4j
public class GeneralnfoController {

    @Autowired
    private QuestController questController;


    @ApiOperation(value="quest-分页列表查询", notes="quest-分页列表查询")
    @GetMapping(value = "/quest/list")
    public Result<IPage<Quest>> queryPageList(Quest quest,
                                              @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                              @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                              HttpServletRequest req) {
        return questController.queryPageList(quest, pageNo, pageSize, req);
    }
}
