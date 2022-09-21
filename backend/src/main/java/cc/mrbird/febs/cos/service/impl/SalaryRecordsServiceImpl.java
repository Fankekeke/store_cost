package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.cos.entity.SalaryRecords;
import cc.mrbird.febs.cos.service.ISalaryRecordsService;
import cc.mrbird.febs.cos.dao.SalaryRecordsMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

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
    public SalaryRecords selectSalaryDetail(Integer recordId) {
        return this.getById(recordId);
    }

    /**
     * 根据员工编号获取薪资发放记录
     *
     * @param staffCode 员工编号
     * @param year      所属年份
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> selectSalaryRecordsByStaff(String staffCode, String year) {
        return baseMapper.selectSalaryRecordsByStaff(staffCode, year);
    }

    /**
     * 添加员工薪资发放记录
     *
     * @param salaryRecords 员工薪资发放记录
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    public boolean saveSalaryRecords(SalaryRecords salaryRecords) throws Exception {
        // 校验是否本月度是否发放
        int count = this.count(Wrappers.<SalaryRecords>lambdaQuery().eq(SalaryRecords::getStaffCode, salaryRecords.getStaffCode()).eq(SalaryRecords::getYear, salaryRecords.getYear()).eq(SalaryRecords::getMonth, salaryRecords.getMonth()));
        if (count > 0) {
            throw new FebsException("本季度对该员工已发放工资！");
        }
        return this.save(salaryRecords);
    }


}
