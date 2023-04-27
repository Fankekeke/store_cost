package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.JobCostInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface JobCostInfoMapper extends BaseMapper<JobCostInfo> {

    /**
     * 分页查询作业成本
     *
     * @param page        分页对象
     * @param jobCostInfo 作业成本
     * @return 结果
     */
    IPage<LinkedHashMap<String, String>> selectJobCostPage(Page<JobCostInfo> page, @Param("jobCostInfo") JobCostInfo jobCostInfo);
}
