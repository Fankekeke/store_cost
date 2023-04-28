package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.cos.entity.JobCostInfo;
import cc.mrbird.febs.cos.dao.JobCostInfoMapper;
import cc.mrbird.febs.cos.entity.JobCostOrder;
import cc.mrbird.febs.cos.entity.vo.MaterialCostVo;
import cc.mrbird.febs.cos.service.IJobCostInfoService;
import cc.mrbird.febs.cos.service.IJobCostOrderService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobCostInfoServiceImpl extends ServiceImpl<JobCostInfoMapper, JobCostInfo> implements IJobCostInfoService {

    private final IJobCostOrderService jobCostOrderService;

    /**
     * 分页查询作业成本
     *
     * @param page        分页对象
     * @param jobCostInfo 作业成本
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, String>> selectJobCostPage(Page<JobCostInfo> page, JobCostInfo jobCostInfo) {
        return baseMapper.selectJobCostPage(page, jobCostInfo);
    }

    /**
     * 作业成本详情
     *
     * @param orderId 作业成本ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectCostDetail(Integer orderId) {
        if (orderId == null) {
            return null;
        }
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("costOrder", jobCostOrderService.getById(orderId));
            }
        };
        // 作业成本项
        List<JobCostInfo> jobCostInfoList = this.list(Wrappers.<JobCostInfo>lambdaQuery().eq(JobCostInfo::getOrderId, orderId));
        result.putIfAbsent("costList", jobCostInfoList);
        return result;
    }

    /**
     * 添加作业成本订单
     *
     * @param jobCostOrder 作业成本订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveCostOrder(JobCostOrder jobCostOrder) throws FebsException {
        // 获取作业成本项
        List<JobCostInfo> costList = JSONUtil.toList(jobCostOrder.getCostList(), JobCostInfo.class);
        if (CollectionUtil.isEmpty(costList)) {
            throw new FebsException("作业成本项不能为空!");
        }
        costList.forEach(e -> {
            if (e.getJobCost() == null) {
                e.setJobCost(BigDecimal.ZERO);
            }
            // 计算成本动因率和合计
            List<MaterialCostVo> materialCostList = JSONUtil.toList(e.getMaterialCostNum(), MaterialCostVo.class);
            BigDecimal total = materialCostList.stream().map(MaterialCostVo::getNum).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            e.setTotal(total);
            e.setCostRate(e.getJobCost().divide(e.getTotal(), 2, RoundingMode.HALF_UP));
        });
        this.saveBatch(costList);
        return jobCostOrderService.save(jobCostOrder);
    }
}
