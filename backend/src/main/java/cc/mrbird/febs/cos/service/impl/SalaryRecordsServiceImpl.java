package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.SalaryRecords;
import cc.mrbird.febs.cos.service.ISalaryRecordsService;
import cc.mrbird.febs.cos.dao.SalaryRecordsMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class SalaryRecordsServiceImpl extends ServiceImpl<SalaryRecordsMapper, SalaryRecords> implements ISalaryRecordsService {

    /**
     * 分页查询员工薪资发放记录
     *
     * @param page          分页读写
     * @param salaryRecords 员工薪资发放记录
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectSalaryRecordsPage(Page<SalaryRecords> page, SalaryRecords salaryRecords) {
        return null;
    }

    /**
     * 查询薪资发放详情
     *
     * @param recordId 薪资发放ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectSalaryDetail(Integer recordId) {
        return null;
    }


}
