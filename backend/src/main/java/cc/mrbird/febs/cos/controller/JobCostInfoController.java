package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.JobCostInfo;
import cc.mrbird.febs.cos.entity.JobCostOrder;
import cc.mrbird.febs.cos.service.IJobCostInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/job-cost-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobCostInfoController {

    private final IJobCostInfoService jobCostInfoService;

    /**
     * 分页查询作业成本
     *
     * @param page        分页对象
     * @param jobCostInfo 作业成本
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<JobCostInfo> page, JobCostInfo jobCostInfo) {
        return R.ok(jobCostInfoService.selectJobCostPage(page, jobCostInfo));
    }

    /**
     * 添加作业成本订单
     *
     * @param jobCostOrder 作业成本订单
     * @return 结果
     */
    @PostMapping("/cost/order")
    public R saveCostOrder(JobCostOrder jobCostOrder) throws FebsException {
        jobCostOrder.setOrderCode("CS-" + System.currentTimeMillis());
        jobCostOrder.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(jobCostInfoService.saveCostOrder(jobCostOrder));
    }

    /**
     * 获取成本表头
     *
     * @param costId 成本ID
     * @return 结果
     */
    @GetMapping("/header/{costId}")
    public R selectCostHeader(@PathVariable("costId") Integer costId) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("header", jobCostInfoService.selectCostHeader(costId));
        result.put("data", jobCostInfoService.selectCostData(costId));
        return R.ok(result);
    }

    /**
     * 作业成本导出
     *
     * @param code 作业成本ID
     * @return 结果
     * @throws Exception 异常
     */
    @GetMapping("/export/{code}")
    public R export(@PathVariable("code") String code) throws Exception {
        return R.ok();
    }

    /**
     * 作业成本详情
     *
     * @param code 作业成本ID
     * @return 结果
     */
    @GetMapping("/{code}")
    public R detail(@PathVariable("code") String code) {
        return R.ok();
    }

    /**
     * 添加作业成本
     *
     * @param jobCostInfo 作业成本
     * @return 结果
     */
    @PostMapping
    public R add(JobCostInfo jobCostInfo) {
        return R.ok(jobCostInfoService.save(jobCostInfo));
    }

    /**
     * 修改作业成本
     *
     * @param jobCostInfo 作业成本
     * @return 结果
     */
    @PutMapping
    public R edit(JobCostInfo jobCostInfo) {
        return R.ok(jobCostInfoService.updateById(jobCostInfo));
    }

    /**
     * 删除作业成本
     *
     * @param ids 作业成本IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(jobCostInfoService.removeByIds(ids));
    }

}
