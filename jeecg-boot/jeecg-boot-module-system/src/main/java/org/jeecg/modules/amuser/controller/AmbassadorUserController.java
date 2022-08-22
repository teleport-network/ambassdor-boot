package org.jeecg.modules.amuser.controller;

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
import org.jeecg.modules.amuser.entity.AmUserPoint;
import org.jeecg.modules.amuser.entity.AmUserToken;
import org.jeecg.modules.amuser.entity.AmUserNft;
import org.jeecg.modules.amuser.entity.AmUserQuest;
import org.jeecg.modules.amuser.entity.AmbassadorUser;
import org.jeecg.modules.amuser.service.IAmbassadorUserService;
import org.jeecg.modules.amuser.service.IAmUserPointService;
import org.jeecg.modules.amuser.service.IAmUserTokenService;
import org.jeecg.modules.amuser.service.IAmUserNftService;
import org.jeecg.modules.amuser.service.IAmUserQuestService;
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
 * @Description: ambassador user list
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
@Api(tags="ambassador user list")
@RestController
@RequestMapping("/amuser/ambassadorUser")
@Slf4j
public class AmbassadorUserController extends JeecgController<AmbassadorUser, IAmbassadorUserService> {

	@Autowired
	private IAmbassadorUserService ambassadorUserService;

	@Autowired
	private IAmUserPointService amUserPointService;

	@Autowired
	private IAmUserTokenService amUserTokenService;

	@Autowired
	private IAmUserNftService amUserNftService;

	@Autowired
	private IAmUserQuestService amUserQuestService;


	/*---------------------------------主表处理-begin-------------------------------------*/

	/**
	 * 分页列表查询
	 * @param ambassadorUser
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "ambassador user list-分页列表查询")
	@ApiOperation(value="ambassador user list-分页列表查询", notes="ambassador user list-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AmbassadorUser>> queryPageList(AmbassadorUser ambassadorUser,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AmbassadorUser> queryWrapper = QueryGenerator.initQueryWrapper(ambassadorUser, req.getParameterMap());
		Page<AmbassadorUser> page = new Page<AmbassadorUser>(pageNo, pageSize);
		IPage<AmbassadorUser> pageList = ambassadorUserService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
     *   添加
     * @param ambassadorUser
     * @return
     */
    @AutoLog(value = "ambassador user list-添加")
    @ApiOperation(value="ambassador user list-添加", notes="ambassador user list-添加")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody AmbassadorUser ambassadorUser) {
        ambassadorUserService.save(ambassadorUser);
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     * @param ambassadorUser
     * @return
     */
    @AutoLog(value = "ambassador user list-编辑")
    @ApiOperation(value="ambassador user list-编辑", notes="ambassador user list-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody AmbassadorUser ambassadorUser) {
        ambassadorUserService.updateById(ambassadorUser);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @AutoLog(value = "ambassador user list-通过id删除")
    @ApiOperation(value="ambassador user list-通过id删除", notes="ambassador user list-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name="id",required=true) String id) {
        ambassadorUserService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AutoLog(value = "ambassador user list-批量删除")
    @ApiOperation(value="ambassador user list-批量删除", notes="ambassador user list-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.ambassadorUserService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AmbassadorUser ambassadorUser) {
        return super.exportXls(request, ambassadorUser, AmbassadorUser.class, "ambassador user list");
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AmbassadorUser.class);
    }
	/*---------------------------------主表处理-end-------------------------------------*/
	

    /*--------------------------------子表处理-points list-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "points list-通过主表ID查询")
	@ApiOperation(value="points list-通过主表ID查询", notes="points list-通过主表ID查询")
	@GetMapping(value = "/listAmUserPointByMainId")
    public Result<IPage<AmUserPoint>> listAmUserPointByMainId(AmUserPoint amUserPoint,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<AmUserPoint> queryWrapper = QueryGenerator.initQueryWrapper(amUserPoint, req.getParameterMap());
        Page<AmUserPoint> page = new Page<AmUserPoint>(pageNo, pageSize);
        IPage<AmUserPoint> pageList = amUserPointService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param amUserPoint
	 * @return
	 */
	@AutoLog(value = "points list-添加")
	@ApiOperation(value="points list-添加", notes="points list-添加")
	@PostMapping(value = "/addAmUserPoint")
	public Result<String> addAmUserPoint(@RequestBody AmUserPoint amUserPoint) {
		amUserPointService.save(amUserPoint);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param amUserPoint
	 * @return
	 */
	@AutoLog(value = "points list-编辑")
	@ApiOperation(value="points list-编辑", notes="points list-编辑")
	@RequestMapping(value = "/editAmUserPoint", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editAmUserPoint(@RequestBody AmUserPoint amUserPoint) {
		amUserPointService.updateById(amUserPoint);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "points list-通过id删除")
	@ApiOperation(value="points list-通过id删除", notes="points list-通过id删除")
	@DeleteMapping(value = "/deleteAmUserPoint")
	public Result<String> deleteAmUserPoint(@RequestParam(name="id",required=true) String id) {
		amUserPointService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "points list-批量删除")
	@ApiOperation(value="points list-批量删除", notes="points list-批量删除")
	@DeleteMapping(value = "/deleteBatchAmUserPoint")
	public Result<String> deleteBatchAmUserPoint(@RequestParam(name="ids",required=true) String ids) {
	    this.amUserPointService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportAmUserPoint")
    public ModelAndView exportAmUserPoint(HttpServletRequest request, AmUserPoint amUserPoint) {
		 // Step.1 组装查询条件
		 QueryWrapper<AmUserPoint> queryWrapper = QueryGenerator.initQueryWrapper(amUserPoint, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<AmUserPoint> pageList = amUserPointService.list(queryWrapper);
		 List<AmUserPoint> exportList = null;

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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "points list");
		 mv.addObject(NormalExcelConstants.CLASS, AmUserPoint.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("points list报表", "导出人:" + sysUser.getRealname(), "points list"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importAmUserPoint/{mainId}")
    public Result<?> importAmUserPoint(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<AmUserPoint> list = ExcelImportUtil.importExcel(file.getInputStream(), AmUserPoint.class, params);
				 for (AmUserPoint temp : list) {
                    temp.setAmbassadorUserId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 amUserPointService.saveBatch(list);
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

    /*--------------------------------子表处理-points list-end----------------------------------------------*/

    /*--------------------------------子表处理-token list-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "token list-通过主表ID查询")
	@ApiOperation(value="token list-通过主表ID查询", notes="token list-通过主表ID查询")
	@GetMapping(value = "/listAmUserTokenByMainId")
    public Result<IPage<AmUserToken>> listAmUserTokenByMainId(AmUserToken amUserToken,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<AmUserToken> queryWrapper = QueryGenerator.initQueryWrapper(amUserToken, req.getParameterMap());
        Page<AmUserToken> page = new Page<AmUserToken>(pageNo, pageSize);
        IPage<AmUserToken> pageList = amUserTokenService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param amUserToken
	 * @return
	 */
	@AutoLog(value = "token list-添加")
	@ApiOperation(value="token list-添加", notes="token list-添加")
	@PostMapping(value = "/addAmUserToken")
	public Result<String> addAmUserToken(@RequestBody AmUserToken amUserToken) {
		amUserTokenService.save(amUserToken);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param amUserToken
	 * @return
	 */
	@AutoLog(value = "token list-编辑")
	@ApiOperation(value="token list-编辑", notes="token list-编辑")
	@RequestMapping(value = "/editAmUserToken", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editAmUserToken(@RequestBody AmUserToken amUserToken) {
		amUserTokenService.updateById(amUserToken);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "token list-通过id删除")
	@ApiOperation(value="token list-通过id删除", notes="token list-通过id删除")
	@DeleteMapping(value = "/deleteAmUserToken")
	public Result<String> deleteAmUserToken(@RequestParam(name="id",required=true) String id) {
		amUserTokenService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "token list-批量删除")
	@ApiOperation(value="token list-批量删除", notes="token list-批量删除")
	@DeleteMapping(value = "/deleteBatchAmUserToken")
	public Result<String> deleteBatchAmUserToken(@RequestParam(name="ids",required=true) String ids) {
	    this.amUserTokenService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportAmUserToken")
    public ModelAndView exportAmUserToken(HttpServletRequest request, AmUserToken amUserToken) {
		 // Step.1 组装查询条件
		 QueryWrapper<AmUserToken> queryWrapper = QueryGenerator.initQueryWrapper(amUserToken, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<AmUserToken> pageList = amUserTokenService.list(queryWrapper);
		 List<AmUserToken> exportList = null;

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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "token list");
		 mv.addObject(NormalExcelConstants.CLASS, AmUserToken.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("token list报表", "导出人:" + sysUser.getRealname(), "token list"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importAmUserToken/{mainId}")
    public Result<?> importAmUserToken(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<AmUserToken> list = ExcelImportUtil.importExcel(file.getInputStream(), AmUserToken.class, params);
				 for (AmUserToken temp : list) {
                    temp.setAmbassadorUserId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 amUserTokenService.saveBatch(list);
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

    /*--------------------------------子表处理-token list-end----------------------------------------------*/

    /*--------------------------------子表处理-nft list-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "nft list-通过主表ID查询")
	@ApiOperation(value="nft list-通过主表ID查询", notes="nft list-通过主表ID查询")
	@GetMapping(value = "/listAmUserNftByMainId")
    public Result<IPage<AmUserNft>> listAmUserNftByMainId(AmUserNft amUserNft,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<AmUserNft> queryWrapper = QueryGenerator.initQueryWrapper(amUserNft, req.getParameterMap());
        Page<AmUserNft> page = new Page<AmUserNft>(pageNo, pageSize);
        IPage<AmUserNft> pageList = amUserNftService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param amUserNft
	 * @return
	 */
	@AutoLog(value = "nft list-添加")
	@ApiOperation(value="nft list-添加", notes="nft list-添加")
	@PostMapping(value = "/addAmUserNft")
	public Result<String> addAmUserNft(@RequestBody AmUserNft amUserNft) {
		amUserNftService.save(amUserNft);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param amUserNft
	 * @return
	 */
	@AutoLog(value = "nft list-编辑")
	@ApiOperation(value="nft list-编辑", notes="nft list-编辑")
	@RequestMapping(value = "/editAmUserNft", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editAmUserNft(@RequestBody AmUserNft amUserNft) {
		amUserNftService.updateById(amUserNft);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "nft list-通过id删除")
	@ApiOperation(value="nft list-通过id删除", notes="nft list-通过id删除")
	@DeleteMapping(value = "/deleteAmUserNft")
	public Result<String> deleteAmUserNft(@RequestParam(name="id",required=true) String id) {
		amUserNftService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "nft list-批量删除")
	@ApiOperation(value="nft list-批量删除", notes="nft list-批量删除")
	@DeleteMapping(value = "/deleteBatchAmUserNft")
	public Result<String> deleteBatchAmUserNft(@RequestParam(name="ids",required=true) String ids) {
	    this.amUserNftService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportAmUserNft")
    public ModelAndView exportAmUserNft(HttpServletRequest request, AmUserNft amUserNft) {
		 // Step.1 组装查询条件
		 QueryWrapper<AmUserNft> queryWrapper = QueryGenerator.initQueryWrapper(amUserNft, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<AmUserNft> pageList = amUserNftService.list(queryWrapper);
		 List<AmUserNft> exportList = null;

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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "nft list");
		 mv.addObject(NormalExcelConstants.CLASS, AmUserNft.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("nft list报表", "导出人:" + sysUser.getRealname(), "nft list"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importAmUserNft/{mainId}")
    public Result<?> importAmUserNft(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<AmUserNft> list = ExcelImportUtil.importExcel(file.getInputStream(), AmUserNft.class, params);
				 for (AmUserNft temp : list) {
                    temp.setAmbassadorUserId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 amUserNftService.saveBatch(list);
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

    /*--------------------------------子表处理-nft list-end----------------------------------------------*/

    /*--------------------------------子表处理-quest list-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "quest list-通过主表ID查询")
	@ApiOperation(value="quest list-通过主表ID查询", notes="quest list-通过主表ID查询")
	@GetMapping(value = "/listAmUserQuestByMainId")
    public Result<IPage<AmUserQuest>> listAmUserQuestByMainId(AmUserQuest amUserQuest,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<AmUserQuest> queryWrapper = QueryGenerator.initQueryWrapper(amUserQuest, req.getParameterMap());
        Page<AmUserQuest> page = new Page<AmUserQuest>(pageNo, pageSize);
        IPage<AmUserQuest> pageList = amUserQuestService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param amUserQuest
	 * @return
	 */
	@AutoLog(value = "quest list-添加")
	@ApiOperation(value="quest list-添加", notes="quest list-添加")
	@PostMapping(value = "/addAmUserQuest")
	public Result<String> addAmUserQuest(@RequestBody AmUserQuest amUserQuest) {
		amUserQuestService.save(amUserQuest);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param amUserQuest
	 * @return
	 */
	@AutoLog(value = "quest list-编辑")
	@ApiOperation(value="quest list-编辑", notes="quest list-编辑")
	@RequestMapping(value = "/editAmUserQuest", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editAmUserQuest(@RequestBody AmUserQuest amUserQuest) {
		amUserQuestService.updateById(amUserQuest);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "quest list-通过id删除")
	@ApiOperation(value="quest list-通过id删除", notes="quest list-通过id删除")
	@DeleteMapping(value = "/deleteAmUserQuest")
	public Result<String> deleteAmUserQuest(@RequestParam(name="id",required=true) String id) {
		amUserQuestService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "quest list-批量删除")
	@ApiOperation(value="quest list-批量删除", notes="quest list-批量删除")
	@DeleteMapping(value = "/deleteBatchAmUserQuest")
	public Result<String> deleteBatchAmUserQuest(@RequestParam(name="ids",required=true) String ids) {
	    this.amUserQuestService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportAmUserQuest")
    public ModelAndView exportAmUserQuest(HttpServletRequest request, AmUserQuest amUserQuest) {
		 // Step.1 组装查询条件
		 QueryWrapper<AmUserQuest> queryWrapper = QueryGenerator.initQueryWrapper(amUserQuest, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<AmUserQuest> pageList = amUserQuestService.list(queryWrapper);
		 List<AmUserQuest> exportList = null;

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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "quest list");
		 mv.addObject(NormalExcelConstants.CLASS, AmUserQuest.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("quest list报表", "导出人:" + sysUser.getRealname(), "quest list"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importAmUserQuest/{mainId}")
    public Result<?> importAmUserQuest(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<AmUserQuest> list = ExcelImportUtil.importExcel(file.getInputStream(), AmUserQuest.class, params);
				 for (AmUserQuest temp : list) {
                    temp.setAmbassadorUserId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 amUserQuestService.saveBatch(list);
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

    /*--------------------------------子表处理-quest list-end----------------------------------------------*/




}
