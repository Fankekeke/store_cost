package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.SalaryGain;
import cc.mrbird.febs.cos.dao.SalaryGainMapper;
import cc.mrbird.febs.cos.service.ISalaryGainService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class SalaryGainServiceImpl extends ServiceImpl<SalaryGainMapper, SalaryGain> implements ISalaryGainService {

    /**
     * 分页查询员工薪资涨幅情况
     *
     * @param page       分页对象
     * @param salaryGain 员工薪资涨幅信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectSalaryPage(Page<SalaryGain> page, SalaryGain salaryGain) {
        return baseMapper.selectSalaryPage(page, salaryGain);
    }

    /**
     * 根据员工编号查询员工薪资涨幅
     *
     * @param code 员工编号
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> salaryDetail(String code) {
        return null;
    }

    /**
     * 添加员工薪资涨幅
     *
     * @param salaryGain 员工薪资涨幅
     * @return 结果
     */
    @Override
    public boolean saveSalaryGain(SalaryGain salaryGain) {
        return false;
    }
}
