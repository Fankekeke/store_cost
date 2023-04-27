package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.JobCostOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface JobCostOrderMapper extends BaseMapper<JobCostOrder> {

    /**
     * 分页查询作业成本订单
     *
     * @param page        分页对象
     * @param jobCostOrder 作业成本订单
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectJobCostOrderPage(Page<JobCostOrder> page, @Param("jobCostOrder") JobCostOrder jobCostOrder);

}
