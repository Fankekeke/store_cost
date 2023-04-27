package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.JobCostOrder;
import cc.mrbird.febs.cos.service.IJobCostOrderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/job-cost-order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobCostOrderController {

    private final IJobCostOrderService jobCostOrderService;

    /**
     * 分页查询作业成本订单
     *
     * @param page        分页对象
     * @param jobCostOrder 作业成本订单
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<JobCostOrder> page, JobCostOrder jobCostOrder) {
        return R.ok(jobCostOrderService.selectJobCostOrderPage(page, jobCostOrder));
    }

    /**
     * 作业成本订单导出
     *
     * @param code 作业成本订单ID
     * @return 结果
     * @throws Exception 异常
     */
    @GetMapping("/export/{code}")
    public R export(@PathVariable("code") String code) throws Exception {
        return R.ok();
    }

    /**
     * 作业成本订单详情
     *
     * @param orderId 作业成本订单ID
     * @return 结果
     */
    @GetMapping("/{orderId}")
    public R detail(@PathVariable("orderId") String orderId) {
        return R.ok();
    }

    /**
     * 添加作业成本订单
     *
     * @param jobCostOrder 作业成本订单
     * @return 结果
     */
    @PostMapping
    public R add(JobCostOrder jobCostOrder) {
        return R.ok(jobCostOrderService.save(jobCostOrder));
    }

    /**
     * 修改作业成本订单
     *
     * @param jobCostOrder 作业成本订单
     * @return 结果
     */
    @PutMapping
    public R edit(JobCostOrder jobCostOrder) {
        return R.ok(jobCostOrderService.updateById(jobCostOrder));
    }

    /**
     * 删除作业成本订单
     *
     * @param ids 作业成本订单IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(jobCostOrderService.removeByIds(ids));
    }
    
}
