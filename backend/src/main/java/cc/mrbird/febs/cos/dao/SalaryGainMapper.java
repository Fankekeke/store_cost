package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.SalaryGain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface SalaryGainMapper extends BaseMapper<SalaryGain> {

    /**
     * 分页查询员工薪资涨幅情况
     *
     * @param page       分页对象
     * @param salaryGain 员工薪资涨幅信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectSalaryPage(Page<SalaryGain> page, @Param("salaryGain") SalaryGain salaryGain);
}
