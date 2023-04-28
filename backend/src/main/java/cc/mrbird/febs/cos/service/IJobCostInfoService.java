package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.cos.entity.JobCostInfo;
import cc.mrbird.febs.cos.entity.JobCostOrder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IJobCostInfoService extends IService<JobCostInfo> {

    /**
     * 分页查询作业成本
     *
     * @param page        分页对象
     * @param jobCostInfo 作业成本
     * @return 结果
     */
    IPage<LinkedHashMap<String, String>> selectJobCostPage(Page<JobCostInfo> page, JobCostInfo jobCostInfo);

    /**
     * 作业成本详情
     *
     * @param orderId 作业成本ID
     * @return 结果
     */
    LinkedHashMap<String, Object> selectCostDetail(Integer orderId);

    /**
     * 添加作业成本订单
     *
     * @param jobCostOrder 作业成本订单
     * @return 结果
     */
    boolean saveCostOrder(JobCostOrder jobCostOrder) throws FebsException;
}
