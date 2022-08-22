package org.jeecg.modules.amlevel.controller;

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
import org.jeecg.modules.amlevel.entity.ContributorLevel;
import org.jeecg.modules.amlevel.service.IContributorLevelService;

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
 * @Description: contributor level
 * @Author: jeecg-boot
 * @Date:   2022-08-22
 * @Version: V1.0
 */
@Api(tags="contributor level")
@RestController
@RequestMapping("/amlevel/contributorLevel")
@Slf4j
public class ContributorLevelController extends JeecgController<ContributorLevel, IContributorLevelService> {
	@Autowired
	private IContributorLevelService contributorLevelService;
	
	/**
	 * 分页列表查询
	 *
	 * @param contributorLevel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "contributor level-分页列表查询")
	@ApiOperation(value="contributor level-分页列表查询", notes="contributor level-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ContributorLevel>> queryPageList(ContributorLevel contributorLevel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ContributorLevel> queryWrapper = QueryGenerator.initQueryWrapper(contributorLevel, req.getParameterMap());
		Page<ContributorLevel> page = new Page<ContributorLevel>(pageNo, pageSize);
		IPage<ContributorLevel> pageList = contributorLevelService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param contributorLevel
	 * @return
	 */
	@AutoLog(value = "contributor level-添加")
	@ApiOperation(value="contributor level-添加", notes="contributor level-添加")
	//@RequiresPermissions("org.jeecg.modules:contributor_level:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ContributorLevel contributorLevel) {
		contributorLevelService.save(contributorLevel);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param contributorLevel
	 * @return
	 */
	@AutoLog(value = "contributor level-编辑")
	@ApiOperation(value="contributor level-编辑", notes="contributor level-编辑")
	//@RequiresPermissions("org.jeecg.modules:contributor_level:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ContributorLevel contributorLevel) {
		contributorLevelService.updateById(contributorLevel);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "contributor level-通过id删除")
	@ApiOperation(value="contributor level-通过id删除", notes="contributor level-通过id删除")
	//@RequiresPermissions("org.jeecg.modules:contributor_level:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		contributorLevelService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "contributor level-批量删除")
	@ApiOperation(value="contributor level-批量删除", notes="contributor level-批量删除")
	//@RequiresPermissions("org.jeecg.modules:contributor_level:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.contributorLevelService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "contributor level-通过id查询")
	@ApiOperation(value="contributor level-通过id查询", notes="contributor level-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ContributorLevel> queryById(@RequestParam(name="id",required=true) String id) {
		ContributorLevel contributorLevel = contributorLevelService.getById(id);
		if(contributorLevel==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(contributorLevel);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param contributorLevel
    */
    //@RequiresPermissions("org.jeecg.modules:contributor_level:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ContributorLevel contributorLevel) {
        return super.exportXls(request, contributorLevel, ContributorLevel.class, "contributor level");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("contributor_level:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ContributorLevel.class);
    }

}
