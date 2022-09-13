package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.SalaryRecords;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface SalaryRecordsMapper extends BaseMapper<SalaryRecords> {

    /**
     * 分页查询员工薪资发放记录
     *
     * @param page          分页对象
     * @param salaryRecords 员工薪资发放记录
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectSalaryRecordsPage(Page<SalaryRecords> page, @Param("salaryRecords") SalaryRecords salaryRecords);
}
