package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.JobCostInfo;
import cc.mrbird.febs.cos.dao.JobCostInfoMapper;
import cc.mrbird.febs.cos.service.IJobCostInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class JobCostInfoServiceImpl extends ServiceImpl<JobCostInfoMapper, JobCostInfo> implements IJobCostInfoService {

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
        return null;
    }
}
