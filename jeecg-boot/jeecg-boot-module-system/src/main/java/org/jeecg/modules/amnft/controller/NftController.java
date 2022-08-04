package org.jeecg.modules.amnft.controller;

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
import org.jeecg.modules.amnft.entity.MintHistory;
import org.jeecg.modules.amnft.entity.DeliveredHistory;
import org.jeecg.modules.amnft.entity.Nft;
import org.jeecg.modules.amnft.service.INftService;
import org.jeecg.modules.amnft.service.IMintHistoryService;
import org.jeecg.modules.amnft.service.IDeliveredHistoryService;
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
 * @Description: nft
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
@Api(tags="nft")
@RestController
@RequestMapping("/amnft/nft")
@Slf4j
public class NftController extends JeecgController<Nft, INftService> {

	@Autowired
	private INftService nftService;

	@Autowired
	private IMintHistoryService mintHistoryService;

	@Autowired
	private IDeliveredHistoryService deliveredHistoryService;


	/*---------------------------------主表处理-begin-------------------------------------*/

	/**
	 * 分页列表查询
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
     * @param nft
     * @return
     */
    @AutoLog(value = "nft-添加")
    @ApiOperation(value="nft-添加", notes="nft-添加")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody Nft nft) {
        nftService.save(nft);
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     * @param nft
     * @return
     */
    @AutoLog(value = "nft-编辑")
    @ApiOperation(value="nft-编辑", notes="nft-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody Nft nft) {
        nftService.updateById(nft);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @AutoLog(value = "nft-通过id删除")
    @ApiOperation(value="nft-通过id删除", notes="nft-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name="id",required=true) String id) {
        nftService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AutoLog(value = "nft-批量删除")
    @ApiOperation(value="nft-批量删除", notes="nft-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.nftService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Nft nft) {
        return super.exportXls(request, nft, Nft.class, "nft");
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Nft.class);
    }
	/*---------------------------------主表处理-end-------------------------------------*/
	

    /*--------------------------------子表处理-mint history-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "mint history-通过主表ID查询")
	@ApiOperation(value="mint history-通过主表ID查询", notes="mint history-通过主表ID查询")
	@GetMapping(value = "/listMintHistoryByMainId")
    public Result<IPage<MintHistory>> listMintHistoryByMainId(MintHistory mintHistory,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<MintHistory> queryWrapper = QueryGenerator.initQueryWrapper(mintHistory, req.getParameterMap());
        Page<MintHistory> page = new Page<MintHistory>(pageNo, pageSize);
        IPage<MintHistory> pageList = mintHistoryService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param mintHistory
	 * @return
	 */
	@AutoLog(value = "mint history-添加")
	@ApiOperation(value="mint history-添加", notes="mint history-添加")
	@PostMapping(value = "/addMintHistory")
	public Result<String> addMintHistory(@RequestBody MintHistory mintHistory) {
		mintHistoryService.save(mintHistory);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param mintHistory
	 * @return
	 */
	@AutoLog(value = "mint history-编辑")
	@ApiOperation(value="mint history-编辑", notes="mint history-编辑")
	@RequestMapping(value = "/editMintHistory", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editMintHistory(@RequestBody MintHistory mintHistory) {
		mintHistoryService.updateById(mintHistory);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "mint history-通过id删除")
	@ApiOperation(value="mint history-通过id删除", notes="mint history-通过id删除")
	@DeleteMapping(value = "/deleteMintHistory")
	public Result<String> deleteMintHistory(@RequestParam(name="id",required=true) String id) {
		mintHistoryService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "mint history-批量删除")
	@ApiOperation(value="mint history-批量删除", notes="mint history-批量删除")
	@DeleteMapping(value = "/deleteBatchMintHistory")
	public Result<String> deleteBatchMintHistory(@RequestParam(name="ids",required=true) String ids) {
	    this.mintHistoryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportMintHistory")
    public ModelAndView exportMintHistory(HttpServletRequest request, MintHistory mintHistory) {
		 // Step.1 组装查询条件
		 QueryWrapper<MintHistory> queryWrapper = QueryGenerator.initQueryWrapper(mintHistory, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<MintHistory> pageList = mintHistoryService.list(queryWrapper);
		 List<MintHistory> exportList = null;

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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "mint history");
		 mv.addObject(NormalExcelConstants.CLASS, MintHistory.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("mint history报表", "导出人:" + sysUser.getRealname(), "mint history"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importMintHistory/{mainId}")
    public Result<?> importMintHistory(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<MintHistory> list = ExcelImportUtil.importExcel(file.getInputStream(), MintHistory.class, params);
				 for (MintHistory temp : list) {
                    temp.setNftId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 mintHistoryService.saveBatch(list);
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

    /*--------------------------------子表处理-mint history-end----------------------------------------------*/

    /*--------------------------------子表处理-delivered history-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "delivered history-通过主表ID查询")
	@ApiOperation(value="delivered history-通过主表ID查询", notes="delivered history-通过主表ID查询")
	@GetMapping(value = "/listDeliveredHistoryByMainId")
    public Result<IPage<DeliveredHistory>> listDeliveredHistoryByMainId(DeliveredHistory deliveredHistory,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<DeliveredHistory> queryWrapper = QueryGenerator.initQueryWrapper(deliveredHistory, req.getParameterMap());
        Page<DeliveredHistory> page = new Page<DeliveredHistory>(pageNo, pageSize);
        IPage<DeliveredHistory> pageList = deliveredHistoryService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param deliveredHistory
	 * @return
	 */
	@AutoLog(value = "delivered history-添加")
	@ApiOperation(value="delivered history-添加", notes="delivered history-添加")
	@PostMapping(value = "/addDeliveredHistory")
	public Result<String> addDeliveredHistory(@RequestBody DeliveredHistory deliveredHistory) {
		deliveredHistoryService.save(deliveredHistory);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param deliveredHistory
	 * @return
	 */
	@AutoLog(value = "delivered history-编辑")
	@ApiOperation(value="delivered history-编辑", notes="delivered history-编辑")
	@RequestMapping(value = "/editDeliveredHistory", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editDeliveredHistory(@RequestBody DeliveredHistory deliveredHistory) {
		deliveredHistoryService.updateById(deliveredHistory);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "delivered history-通过id删除")
	@ApiOperation(value="delivered history-通过id删除", notes="delivered history-通过id删除")
	@DeleteMapping(value = "/deleteDeliveredHistory")
	public Result<String> deleteDeliveredHistory(@RequestParam(name="id",required=true) String id) {
		deliveredHistoryService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "delivered history-批量删除")
	@ApiOperation(value="delivered history-批量删除", notes="delivered history-批量删除")
	@DeleteMapping(value = "/deleteBatchDeliveredHistory")
	public Result<String> deleteBatchDeliveredHistory(@RequestParam(name="ids",required=true) String ids) {
	    this.deliveredHistoryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportDeliveredHistory")
    public ModelAndView exportDeliveredHistory(HttpServletRequest request, DeliveredHistory deliveredHistory) {
		 // Step.1 组装查询条件
		 QueryWrapper<DeliveredHistory> queryWrapper = QueryGenerator.initQueryWrapper(deliveredHistory, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<DeliveredHistory> pageList = deliveredHistoryService.list(queryWrapper);
		 List<DeliveredHistory> exportList = null;

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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "delivered history");
		 mv.addObject(NormalExcelConstants.CLASS, DeliveredHistory.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("delivered history报表", "导出人:" + sysUser.getRealname(), "delivered history"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importDeliveredHistory/{mainId}")
    public Result<?> importDeliveredHistory(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<DeliveredHistory> list = ExcelImportUtil.importExcel(file.getInputStream(), DeliveredHistory.class, params);
				 for (DeliveredHistory temp : list) {
                    temp.setNftId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 deliveredHistoryService.saveBatch(list);
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

    /*--------------------------------子表处理-delivered history-end----------------------------------------------*/




}
