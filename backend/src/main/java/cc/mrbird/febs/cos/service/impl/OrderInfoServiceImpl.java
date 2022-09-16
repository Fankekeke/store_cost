package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.SalaryRecordsMapper;
import cc.mrbird.febs.cos.dao.StorageRecordMapper;
import cc.mrbird.febs.cos.entity.OrderInfo;
import cc.mrbird.febs.cos.dao.OrderInfoMapper;
import cc.mrbird.febs.cos.entity.SalaryRecords;
import cc.mrbird.febs.cos.entity.StorageRecord;
import cc.mrbird.febs.cos.entity.StorehouseInfo;
import cc.mrbird.febs.cos.service.IOrderInfoService;
import cc.mrbird.febs.cos.service.IStorehouseInfoService;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    private final IStorehouseInfoService storehouseInfoService;

    private final SalaryRecordsMapper salaryRecordsMapper;

    private final StorageRecordMapper storageRecordMapper;

    /**
     * 分页查询订单信息
     *
     * @param page      分页对象
     * @param orderInfo 订单信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectOrderPage(Page<OrderInfo> page, OrderInfo orderInfo) {
        return baseMapper.selectOrderPage(page, orderInfo);
    }

    /**
     * 添加订单信息
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveOrder(OrderInfo orderInfo) {
        // 添加订单编号
        orderInfo.setCode("ORDER-" + System.currentTimeMillis());
        orderInfo.setCreateTime(DateUtil.formatDateTime(new Date()));
        List<StorehouseInfo> infoList = Convert.toList(StorehouseInfo.class, orderInfo.getMaterial());
        // 设置出库编号
        infoList.forEach(e -> e.setDeliveryOrderNumber(orderInfo.getCode()));
        storehouseInfoService.saveBatch(infoList);
        // 添加订单信息
        return this.save(orderInfo);
    }

    /**
     * 查询订单详情
     *
     * @param code 订单编号
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> orderDetail(String code) {
        return baseMapper.orderDetail(code);
    }

    /**
     * 月度统计【收益，支出】
     *
     * @param year  年度
     * @param month 季度
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectStatisticsByMonth(String year, String month) {
        // 收入 【订单结余】
        List<OrderInfo> orderInfoList = baseMapper.selectOrderInfoByDate(year, month);
        // 支出【采收入库+员工薪资】
        List<StorageRecord> storageRecordList = storageRecordMapper.selectStorageRecordByDate(year, month);
        List<SalaryRecords> salaryRecordsList = salaryRecordsMapper.selectList(Wrappers.<SalaryRecords>lambdaQuery().eq(SalaryRecords::getYear, year).eq(StrUtil.isNotEmpty(month), SalaryRecords::getMonth, month));
        return new LinkedHashMap<String, Object>() {
            {
                put("orderTotal", orderInfoList.size());
                put("orderTotalPrice", orderInfoList.stream().map(OrderInfo::getTotalPrice).reduce(BigDecimal.ZERO,BigDecimal::add));
                put("inTotal", storageRecordList.size());
                put("inTotalPrice", storageRecordList.stream().map(StorageRecord::getTotalPrice).reduce(BigDecimal.ZERO,BigDecimal::add));
                put("salaryTotal", salaryRecordsList.size());
                put("salaryTotalPrice", salaryRecordsList.stream().map(SalaryRecords::getPayroll).reduce(BigDecimal.ZERO,BigDecimal::add));
            }
        };
    }

    /**
     * 获取订单产品列表比率
     *
     * @param year  年度
     * @param month 季度
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectMaterialTypeRate(String year, String month) {
        // 获取订单信息
        List<OrderInfo> orderInfoList = baseMapper.selectOrderInfoByDate(year, month);
        // 获取订单详情
        List<String> orderNumberList = orderInfoList.stream().map(OrderInfo::getCode).collect(Collectors.toList());
        List<StorehouseInfo> storehouseInfoList = storehouseInfoService.list(Wrappers.<StorehouseInfo>lambdaQuery().in(StorehouseInfo::getDeliveryOrderNumber, orderNumberList));

        return null;
    }
}
