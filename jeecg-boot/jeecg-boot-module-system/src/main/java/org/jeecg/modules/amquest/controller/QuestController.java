package org.jeecg.modules.amquest.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.amquest.entity.ActionDef;
import org.jeecg.modules.amquest.entity.Quest;
import org.jeecg.modules.amquest.vo.QuestPage;
import org.jeecg.modules.amquest.service.IQuestService;
import org.jeecg.modules.amquest.service.IActionDefService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: quest
 * @Author: jeecg-boot
 * @Date:   2022-08-09
 * @Version: V1.0
 */
@Api(tags="quest")
@RestController
@RequestMapping("/amquest/quest")
@Slf4j
public class QuestController {
	@Autowired
	private IQuestService questService;
	@Autowired
	private IActionDefService actionDefService;
	
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
	 * @param questPage
	 * @return
	 */
	@AutoLog(value = "quest-添加")
	@ApiOperation(value="quest-添加", notes="quest-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody QuestPage questPage) {
		Quest quest = new Quest();
		BeanUtils.copyProperties(questPage, quest);
		questService.saveMain(quest, questPage.getActionDefList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param questPage
	 * @return
	 */
	@AutoLog(value = "quest-编辑")
	@ApiOperation(value="quest-编辑", notes="quest-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody QuestPage questPage) {
		Quest quest = new Quest();
		BeanUtils.copyProperties(questPage, quest);
		Quest questEntity = questService.getById(quest.getId());
		if(questEntity==null) {
			return Result.error("未找到对应数据");
		}
		questService.updateMain(quest, questPage.getActionDefList());
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
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		questService.delMain(id);
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
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.questService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
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
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "quest def-通过主表ID查询")
	@ApiOperation(value="quest def-通过主表ID查询", notes="quest def-通过主表ID查询")
	@GetMapping(value = "/queryActionDefByMainId")
	public Result<IPage<ActionDef>> queryActionDefListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ActionDef> actionDefList = actionDefService.selectByMainId(id);
		IPage <ActionDef> page = new Page<>();
		page.setRecords(actionDefList);
		page.setTotal(actionDefList.size());
		return Result.OK(page);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param quest
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Quest quest) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<Quest> queryWrapper = QueryGenerator.initQueryWrapper(quest, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

     //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
           List<String> selectionList = Arrays.asList(selections.split(","));
           queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<Quest>  questList = questService.list(queryWrapper);

      // Step.3 组装pageList
      List<QuestPage> pageList = new ArrayList<QuestPage>();
      for (Quest main : questList) {
          QuestPage vo = new QuestPage();
          BeanUtils.copyProperties(main, vo);
          List<ActionDef> actionDefList = actionDefService.selectByMainId(main.getId());
          vo.setActionDefList(actionDefList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "quest列表");
      mv.addObject(NormalExcelConstants.CLASS, QuestPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("quest数据", "导出人:"+sysUser.getRealname(), "quest"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
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
              List<QuestPage> list = ExcelImportUtil.importExcel(file.getInputStream(), QuestPage.class, params);
              for (QuestPage page : list) {
                  Quest po = new Quest();
                  BeanUtils.copyProperties(page, po);
                  questService.saveMain(po, page.getActionDefList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
