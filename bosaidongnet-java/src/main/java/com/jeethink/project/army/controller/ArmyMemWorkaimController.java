package com.jeethink.project.army.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jeethink.framework.aspectj.lang.annotation.Log;
import com.jeethink.framework.aspectj.lang.enums.BusinessType;
import com.jeethink.project.army.domain.ArmyMemWorkaim;
import com.jeethink.project.army.service.IArmyMemWorkaimService;
import com.jeethink.framework.web.controller.BaseController;
import com.jeethink.framework.web.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.framework.web.page.TableDataInfo;

/**
 * 求职意向Controller
 * 
 * @author miao
 * @date 2020-09-18
 */
@RestController
@RequestMapping("/army/workaim")
public class ArmyMemWorkaimController extends BaseController {
    @Autowired
    private IArmyMemWorkaimService armyMemWorkaimService;

    /**
     * 查询求职意向列表
     */
    @PostMapping("/list")
    public TableDataInfo list(ArmyMemWorkaim armyMemWorkaim) {
        startPage();
        List<ArmyMemWorkaim> list = armyMemWorkaimService.selectArmyMemWorkaimList(armyMemWorkaim);
        return getDataTable(list);
    }

    /**
     * 导出求职意向列表
     */
    @Log(title = "求职意向", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ArmyMemWorkaim armyMemWorkaim) {
        List<ArmyMemWorkaim> list = armyMemWorkaimService.selectArmyMemWorkaimList(armyMemWorkaim);
        ExcelUtil<ArmyMemWorkaim> util = new ExcelUtil<ArmyMemWorkaim>(ArmyMemWorkaim.class);
        return util.exportExcel(list, "workaim");
    }

    /**
     * 获取求职意向详细信息
     */
    @PostMapping(value = "/getinfo")
    public AjaxResult getInfo(Long workaimId) {
        return AjaxResult.success(armyMemWorkaimService.selectArmyMemWorkaimById(workaimId));
    }

    /**
     * 新增求职意向
     */
    @Log(title = "求职意向", businessType = BusinessType.INSERT)
    @PostMapping("insert")
    public AjaxResult add(ArmyMemWorkaim armyMemWorkaim) {
        return toAjax(armyMemWorkaimService.insertArmyMemWorkaim(armyMemWorkaim));
    }

    /**
     * 修改求职意向
     */
    @Log(title = "求职意向", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(ArmyMemWorkaim armyMemWorkaim) {
        return toAjax(armyMemWorkaimService.updateArmyMemWorkaim(armyMemWorkaim));
    }


    /**
     * 删除求职意向
     */
    @Log(title = "求职意向", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workaimIds}")
    public AjaxResult remove(@PathVariable Long[] workaimIds) {
        return toAjax(armyMemWorkaimService.deleteArmyMemWorkaimByIds(workaimIds));
    }

    /**
     * 修改求职意向
     */
    @Log(title = "求职意向", businessType = BusinessType.UPDATE)
    @PostMapping("/delete")
    public AjaxResult update(Long workaimId) {
        return toAjax(armyMemWorkaimService.deleteArmyMemWorkaimById(workaimId));
    }

}
