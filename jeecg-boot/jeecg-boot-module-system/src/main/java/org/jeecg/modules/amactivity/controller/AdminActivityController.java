package org.jeecg.modules.amactivity.controller;

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
import org.jeecg.modules.amactivity.entity.AdminActivity;
import org.jeecg.modules.amactivity.service.IAdminActivityService;

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
 * @Description: admin activity
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
@Api(tags="admin activity")
@RestController
@RequestMapping("/amactivity/adminActivity")
@Slf4j
public class AdminActivityController extends JeecgController<AdminActivity, IAdminActivityService> {
	@Autowired
	private IAdminActivityService adminActivityService;
	
	/**
	 * 分页列表查询
	 *
	 * @param adminActivity
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "admin activity-分页列表查询")
	@ApiOperation(value="admin activity-分页列表查询", notes="admin activity-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AdminActivity>> queryPageList(AdminActivity adminActivity,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AdminActivity> queryWrapper = QueryGenerator.initQueryWrapper(adminActivity, req.getParameterMap());
		Page<AdminActivity> page = new Page<AdminActivity>(pageNo, pageSize);
		IPage<AdminActivity> pageList = adminActivityService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param adminActivity
	 * @return
	 */
	@AutoLog(value = "admin activity-添加")
	@ApiOperation(value="admin activity-添加", notes="admin activity-添加")
	//@RequiresPermissions("org.jeecg.modules:admin_activity:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AdminActivity adminActivity) {
		adminActivityService.save(adminActivity);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param adminActivity
	 * @return
	 */
	@AutoLog(value = "admin activity-编辑")
	@ApiOperation(value="admin activity-编辑", notes="admin activity-编辑")
	//@RequiresPermissions("org.jeecg.modules:admin_activity:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AdminActivity adminActivity) {
		adminActivityService.updateById(adminActivity);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "admin activity-通过id删除")
	@ApiOperation(value="admin activity-通过id删除", notes="admin activity-通过id删除")
	//@RequiresPermissions("org.jeecg.modules:admin_activity:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		adminActivityService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "admin activity-批量删除")
	@ApiOperation(value="admin activity-批量删除", notes="admin activity-批量删除")
	//@RequiresPermissions("org.jeecg.modules:admin_activity:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.adminActivityService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "admin activity-通过id查询")
	@ApiOperation(value="admin activity-通过id查询", notes="admin activity-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AdminActivity> queryById(@RequestParam(name="id",required=true) String id) {
		AdminActivity adminActivity = adminActivityService.getById(id);
		if(adminActivity==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(adminActivity);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param adminActivity
    */
    //@RequiresPermissions("org.jeecg.modules:admin_activity:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AdminActivity adminActivity) {
        return super.exportXls(request, adminActivity, AdminActivity.class, "admin activity");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("admin_activity:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AdminActivity.class);
    }

}
