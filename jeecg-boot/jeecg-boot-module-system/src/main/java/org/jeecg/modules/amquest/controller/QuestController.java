package org.jeecg.modules.amquest.controller;

import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.amquest.entity.ActionDef;
import org.jeecg.modules.amquest.entity.Quest;
import org.jeecg.modules.amquest.service.IQuestService;
import org.jeecg.modules.amquest.service.IActionDefService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

 /**
 * @Description: quest
 * @Author: jeecg-boot
 * @Date:   2022-09-01
 * @Version: V1.0
 */
@Api(tags="quest")
@RestController
@RequestMapping("/amquest/quest")
@Slf4j
public class QuestController extends JeecgController<Quest, IQuestService> {

	@Autowired
	private IQuestService questService;

	@Autowired
	private IActionDefService actionDefService;


	/*---------------------------------主表处理-begin-------------------------------------*/

	/**
	 * 分页列表查询
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
     * @param quest
     * @return
     */
    @AutoLog(value = "quest-添加")
    @ApiOperation(value="quest-添加", notes="quest-添加")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody Quest quest) {
        questService.save(quest);
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     * @param quest
     * @return
     */
    @AutoLog(value = "quest-编辑")
    @ApiOperation(value="quest-编辑", notes="quest-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody Quest quest) {
        questService.updateById(quest);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @AutoLog(value = "quest-通过id删除")
    @ApiOperation(value="quest-通过id删除", notes="quest-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name="id",required=true) String id) {
        questService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AutoLog(value = "quest-批量删除")
    @ApiOperation(value="quest-批量删除", notes="quest-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.questService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Quest quest) {
        return super.exportXls(request, quest, Quest.class, "quest");
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Quest.class);
    }
	/*---------------------------------主表处理-end-------------------------------------*/
	

    /*--------------------------------子表处理-quest def-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "quest def-通过主表ID查询")
	@ApiOperation(value="quest def-通过主表ID查询", notes="quest def-通过主表ID查询")
	@GetMapping(value = "/listActionDefByMainId")
    public Result<IPage<ActionDef>> listActionDefByMainId(ActionDef actionDef,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<ActionDef> queryWrapper = QueryGenerator.initQueryWrapper(actionDef, req.getParameterMap());
        Page<ActionDef> page = new Page<ActionDef>(pageNo, pageSize);
        IPage<ActionDef> pageList = actionDefService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param actionDef
	 * @return
	 */
	@AutoLog(value = "quest def-添加")
	@ApiOperation(value="quest def-添加", notes="quest def-添加")
	@PostMapping(value = "/addActionDef")
	public Result<String> addActionDef(@RequestBody ActionDef actionDef) {
		actionDefService.save(actionDef);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param actionDef
	 * @return
	 */
	@AutoLog(value = "quest def-编辑")
	@ApiOperation(value="quest def-编辑", notes="quest def-编辑")
	@RequestMapping(value = "/editActionDef", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editActionDef(@RequestBody ActionDef actionDef) {
		actionDefService.updateById(actionDef);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "quest def-通过id删除")
	@ApiOperation(value="quest def-通过id删除", notes="quest def-通过id删除")
	@DeleteMapping(value = "/deleteActionDef")
	public Result<String> deleteActionDef(@RequestParam(name="id",required=true) String id) {
		actionDefService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "quest def-批量删除")
	@ApiOperation(value="quest def-批量删除", notes="quest def-批量删除")
	@DeleteMapping(value = "/deleteBatchActionDef")
	public Result<String> deleteBatchActionDef(@RequestParam(name="ids",required=true) String ids) {
	    this.actionDefService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportActionDef")
    public ModelAndView exportActionDef(HttpServletRequest request, ActionDef actionDef) {
		 // Step.1 组装查询条件
		 QueryWrapper<ActionDef> queryWrapper = QueryGenerator.initQueryWrapper(actionDef, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<ActionDef> pageList = actionDefService.list(queryWrapper);
		 List<ActionDef> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "quest def");
		 mv.addObject(NormalExcelConstants.CLASS, ActionDef.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("quest def报表", "导出人:" + sysUser.getRealname(), "quest def"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importActionDef/{mainId}")
    public Result<?> importActionDef(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
       // 获取上传文件对象
			 MultipartFile file = entity.getValue();
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<ActionDef> list = ExcelImportUtil.importExcel(file.getInputStream(), ActionDef.class, params);
				 for (ActionDef temp : list) {
                    temp.setQuestFk(mainId);
				 }
				 long start = System.currentTimeMillis();
				 actionDefService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-quest def-end----------------------------------------------*/




}
