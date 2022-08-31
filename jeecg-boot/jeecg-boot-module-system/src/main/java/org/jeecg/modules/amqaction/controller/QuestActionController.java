package org.jeecg.modules.amqaction.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.amqaction.entity.QuestAction;
import org.jeecg.modules.amqaction.service.IQuestActionService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: user action in quest
 * @Author: jeecg-boot
 * @Date:   2022-08-31
 * @Version: V1.0
 */
@Api(tags="user action in quest")
@RestController
@RequestMapping("/amqaction/questAction")
@Slf4j
public class QuestActionController extends JeecgController<QuestAction, IQuestActionService> {
	@Autowired
	private IQuestActionService questActionService;
	
	/**
	 * 分页列表查询
	 *
	 * @param questAction
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "user action in quest-分页列表查询")
	@ApiOperation(value="user action in quest-分页列表查询", notes="user action in quest-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<QuestAction>> queryPageList(QuestAction questAction,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<QuestAction> queryWrapper = QueryGenerator.initQueryWrapper(questAction, req.getParameterMap());
		Page<QuestAction> page = new Page<QuestAction>(pageNo, pageSize);
		IPage<QuestAction> pageList = questActionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param questAction
	 * @return
	 */
	@AutoLog(value = "user action in quest-添加")
	@ApiOperation(value="user action in quest-添加", notes="user action in quest-添加")
	//@RequiresPermissions("org.jeecg.modules:quest_action:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody QuestAction questAction) {
		questActionService.save(questAction);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param questAction
	 * @return
	 */
	@AutoLog(value = "user action in quest-编辑")
	@ApiOperation(value="user action in quest-编辑", notes="user action in quest-编辑")
	//@RequiresPermissions("org.jeecg.modules:quest_action:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody QuestAction questAction) {
		questActionService.updateById(questAction);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "user action in quest-通过id删除")
	@ApiOperation(value="user action in quest-通过id删除", notes="user action in quest-通过id删除")
	//@RequiresPermissions("org.jeecg.modules:quest_action:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		questActionService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "user action in quest-批量删除")
	@ApiOperation(value="user action in quest-批量删除", notes="user action in quest-批量删除")
	//@RequiresPermissions("org.jeecg.modules:quest_action:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.questActionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "user action in quest-通过id查询")
	@ApiOperation(value="user action in quest-通过id查询", notes="user action in quest-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<QuestAction> queryById(@RequestParam(name="id",required=true) String id) {
		QuestAction questAction = questActionService.getById(id);
		if(questAction==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(questAction);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param questAction
    */
    //@RequiresPermissions("org.jeecg.modules:quest_action:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, QuestAction questAction) {
        return super.exportXls(request, questAction, QuestAction.class, "user action in quest");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("quest_action:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, QuestAction.class);
    }

}
