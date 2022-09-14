package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.SalaryRecords;
import cc.mrbird.febs.cos.service.ISalaryRecordsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/salary-records")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SalaryRecordsController {

    private final ISalaryRecordsService salaryRecordsService;

    /**
     * 分页查询员工薪资发放记录
     *
     * @param page          分页读写
     * @param salaryRecords 员工薪资发放记录
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<SalaryRecords> page, SalaryRecords salaryRecords) {
        return R.ok(salaryRecordsService.selectSalaryRecordsPage(page, salaryRecords));
    }

    /**
     * 根据员工编号获取薪资发放记录
     *
     * @param staffCode 员工编号
     * @param year      所属年份
     * @return 结果
     */
    @GetMapping("/salaryRecordsByStaff")
    public R selectSalaryRecordsByStaff(String staffCode, String year) {
        return R.ok(salaryRecordsService.selectSalaryRecordsByStaff(staffCode, year));
    }
    /**
     * 查询薪资发放详情
     *
     * @param recordId 薪资发放ID
     * @return 结果
     */
    @GetMapping("/{recordId}")
    public R detail(@PathVariable("recordId") Integer recordId) {
        return R.ok(salaryRecordsService.getById(recordId));
    }

    /**
     * 添加员工薪资发放记录
     *
     * @param salaryRecords 员工薪资发放记录
     * @return 结果
     */
    @PostMapping
    public R add(SalaryRecords salaryRecords) throws Exception {
        return R.ok(salaryRecordsService.saveSalaryRecords(salaryRecords));
    }

    /**
     * 修改员工薪资发放记录
     *
     * @param salaryRecords 员工薪资发放记录
     * @return 结果
     */
    @PutMapping
    public R edit(SalaryRecords salaryRecords) {
        return R.ok(salaryRecordsService.updateById(salaryRecords));
    }

    /**
     * 删除员工薪资发放记录
     *
     * @param ids 员工薪资发放记录IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(salaryRecordsService.removeByIds(ids));
    }

}
