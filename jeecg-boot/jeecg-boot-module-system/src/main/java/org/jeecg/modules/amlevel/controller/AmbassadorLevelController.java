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
import org.jeecg.modules.amlevel.entity.AmbassadorLevel;
import org.jeecg.modules.amlevel.service.IAmbassadorLevelService;

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
 * @Description: ambassador level
 * @Author: jeecg-boot
 * @Date:   2022-08-22
 * @Version: V1.0
 */
@Api(tags="ambassador level")
@RestController
@RequestMapping("/amlevel/ambassadorLevel")
@Slf4j
public class AmbassadorLevelController extends JeecgController<AmbassadorLevel, IAmbassadorLevelService> {
	@Autowired
	private IAmbassadorLevelService ambassadorLevelService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ambassadorLevel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "ambassador level-分页列表查询")
	@ApiOperation(value="ambassador level-分页列表查询", notes="ambassador level-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AmbassadorLevel>> queryPageList(AmbassadorLevel ambassadorLevel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AmbassadorLevel> queryWrapper = QueryGenerator.initQueryWrapper(ambassadorLevel, req.getParameterMap());
		Page<AmbassadorLevel> page = new Page<AmbassadorLevel>(pageNo, pageSize);
		IPage<AmbassadorLevel> pageList = ambassadorLevelService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ambassadorLevel
	 * @return
	 */
	@AutoLog(value = "ambassador level-添加")
	@ApiOperation(value="ambassador level-添加", notes="ambassador level-添加")
	//@RequiresPermissions("org.jeecg.modules:ambassador_level:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AmbassadorLevel ambassadorLevel) {
		ambassadorLevelService.save(ambassadorLevel);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ambassadorLevel
	 * @return
	 */
	@AutoLog(value = "ambassador level-编辑")
	@ApiOperation(value="ambassador level-编辑", notes="ambassador level-编辑")
	//@RequiresPermissions("org.jeecg.modules:ambassador_level:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AmbassadorLevel ambassadorLevel) {
		ambassadorLevelService.updateById(ambassadorLevel);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ambassador level-通过id删除")
	@ApiOperation(value="ambassador level-通过id删除", notes="ambassador level-通过id删除")
	//@RequiresPermissions("org.jeecg.modules:ambassador_level:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		ambassadorLevelService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ambassador level-批量删除")
	@ApiOperation(value="ambassador level-批量删除", notes="ambassador level-批量删除")
	//@RequiresPermissions("org.jeecg.modules:ambassador_level:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ambassadorLevelService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "ambassador level-通过id查询")
	@ApiOperation(value="ambassador level-通过id查询", notes="ambassador level-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AmbassadorLevel> queryById(@RequestParam(name="id",required=true) String id) {
		AmbassadorLevel ambassadorLevel = ambassadorLevelService.getById(id);
		if(ambassadorLevel==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ambassadorLevel);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ambassadorLevel
    */
    //@RequiresPermissions("org.jeecg.modules:ambassador_level:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AmbassadorLevel ambassadorLevel) {
        return super.exportXls(request, ambassadorLevel, AmbassadorLevel.class, "ambassador level");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("ambassador_level:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AmbassadorLevel.class);
    }

}
