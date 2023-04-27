package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.JobCostOrder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IJobCostOrderService extends IService<JobCostOrder> {

    /**
     * 分页查询作业成本订单
     *
     * @param page        分页对象
     * @param jobCostOrder 作业成本订单
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectJobCostOrderPage(Page<JobCostOrder> page, JobCostOrder jobCostOrder);

}
