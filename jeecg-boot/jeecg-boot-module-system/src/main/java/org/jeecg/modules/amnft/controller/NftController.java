package org.jeecg.modules.amnft.controller;

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
import org.jeecg.modules.amnft.entity.Nft;
import org.jeecg.modules.amnft.service.INftService;

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
 * @Description: nft
 * @Author: jeecg-boot
 * @Date:   2022-09-09
 * @Version: V1.0
 */
@Api(tags="nft")
@RestController
@RequestMapping("/amnft/nft")
@Slf4j
public class NftController extends JeecgController<Nft, INftService> {
	@Autowired
	private INftService nftService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nft
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "nft-分页列表查询")
	@ApiOperation(value="nft-分页列表查询", notes="nft-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Nft>> queryPageList(Nft nft,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Nft> queryWrapper = QueryGenerator.initQueryWrapper(nft, req.getParameterMap());
		Page<Nft> page = new Page<Nft>(pageNo, pageSize);
		IPage<Nft> pageList = nftService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param nft
	 * @return
	 */
	@AutoLog(value = "nft-添加")
	@ApiOperation(value="nft-添加", notes="nft-添加")
	//@RequiresPermissions("org.jeecg.modules:nft:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Nft nft) {
		nftService.save(nft);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param nft
	 * @return
	 */
	@AutoLog(value = "nft-编辑")
	@ApiOperation(value="nft-编辑", notes="nft-编辑")
	//@RequiresPermissions("org.jeecg.modules:nft:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Nft nft) {
		nftService.updateById(nft);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "nft-通过id删除")
	@ApiOperation(value="nft-通过id删除", notes="nft-通过id删除")
	//@RequiresPermissions("org.jeecg.modules:nft:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		nftService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "nft-批量删除")
	@ApiOperation(value="nft-批量删除", notes="nft-批量删除")
	//@RequiresPermissions("org.jeecg.modules:nft:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nftService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "nft-通过id查询")
	@ApiOperation(value="nft-通过id查询", notes="nft-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Nft> queryById(@RequestParam(name="id",required=true) String id) {
		Nft nft = nftService.getById(id);
		if(nft==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(nft);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param nft
    */
    //@RequiresPermissions("org.jeecg.modules:nft:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Nft nft) {
        return super.exportXls(request, nft, Nft.class, "nft");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("nft:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Nft.class);
    }

}
