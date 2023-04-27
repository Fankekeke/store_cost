package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.JobCostOrder;
import cc.mrbird.febs.cos.dao.JobCostOrderMapper;
import cc.mrbird.febs.cos.service.IJobCostOrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class JobCostOrderServiceImpl extends ServiceImpl<JobCostOrderMapper, JobCostOrder> implements IJobCostOrderService {

    /**
     * 分页查询作业成本订单
     *
     * @param page        分页对象
     * @param jobCostOrder 作业成本订单
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectJobCostOrderPage(Page<JobCostOrder> page, JobCostOrder jobCostOrder) {
        return baseMapper.selectJobCostOrderPage(page, jobCostOrder);
    }
}
