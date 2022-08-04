package org.jeecg.modules.amquest.controller;

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
import org.jeecg.modules.amquest.entity.Quest;
import org.jeecg.modules.amquest.service.IQuestService;

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
 * @Description: quest
 * @Author: jeecg-boot
 * @Date:   2022-08-03
 * @Version: V1.0
 */
@Api(tags="quest")
@RestController
@RequestMapping("/amquest/quest")
@Slf4j
public class QuestController extends JeecgController<Quest, IQuestService> {
	@Autowired
	private IQuestService questService;
	
	/**
	 * 分页列表查询
	 *
	 * @param quest
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "quest-分页列表查询")
	@ApiOperation(value="quest-分页列表查询", notes="quest-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Quest>> queryPageList(Quest quest,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Quest> queryWrapper = QueryGenerator.initQueryWrapper(quest, req.getParameterMap());
		Page<Quest> page = new Page<Quest>(pageNo, pageSize);
		IPage<Quest> pageList = questService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param quest
	 * @return
	 */
	@AutoLog(value = "quest-添加")
	@ApiOperation(value="quest-添加", notes="quest-添加")
	//@RequiresPermissions("org.jeecg.modules:quest:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Quest quest) {
		questService.save(quest);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param quest
	 * @return
	 */
	@AutoLog(value = "quest-编辑")
	@ApiOperation(value="quest-编辑", notes="quest-编辑")
	//@RequiresPermissions("org.jeecg.modules:quest:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Quest quest) {
		questService.updateById(quest);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "quest-通过id删除")
	@ApiOperation(value="quest-通过id删除", notes="quest-通过id删除")
	//@RequiresPermissions("org.jeecg.modules:quest:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		questService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "quest-批量删除")
	@ApiOperation(value="quest-批量删除", notes="quest-批量删除")
	//@RequiresPermissions("org.jeecg.modules:quest:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.questService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "quest-通过id查询")
	@ApiOperation(value="quest-通过id查询", notes="quest-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Quest> queryById(@RequestParam(name="id",required=true) String id) {
		Quest quest = questService.getById(id);
		if(quest==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(quest);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param quest
    */
    //@RequiresPermissions("org.jeecg.modules:quest:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Quest quest) {
        return super.exportXls(request, quest, Quest.class, "quest");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("quest:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Quest.class);
    }

}
