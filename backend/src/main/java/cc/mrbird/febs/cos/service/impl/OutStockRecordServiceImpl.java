package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.OutStockRecord;
import cc.mrbird.febs.cos.dao.OutStockRecordMapper;
import cc.mrbird.febs.cos.entity.StorehouseInfo;
import cc.mrbird.febs.cos.service.IOutStockRecordService;
import cc.mrbird.febs.cos.service.IStorehouseInfoService;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OutStockRecordServiceImpl extends ServiceImpl<OutStockRecordMapper, OutStockRecord> implements IOutStockRecordService {

    private final IStorehouseInfoService storehouseInfoService;

    /**
     * 分页查询出库记录
     *
     * @param page           分页对象
     * @param outStockRecord 出库信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectOutStockRecordPage(Page<OutStockRecord> page, OutStockRecord outStockRecord) {
        return baseMapper.selectOutStockRecordPage(page, outStockRecord);
    }

    /**
     * 查询出库记录详情
     *
     * @param code 出库单号
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> outStockDetail(String code) {
        return baseMapper.outStockDetail(code);
    }

    /**
     * 添加物料出库信息
     *
     * @param outStockRecord 出库记录
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveOutStock(OutStockRecord outStockRecord) {
        // 设置出库编号
        outStockRecord.setCode("OUT-" + System.currentTimeMillis());
        List<StorehouseInfo> infoList = Convert.toList(StorehouseInfo.class, outStockRecord.getMaterial());
        // 获取物料库存
        List<String> materialNameList = infoList.stream().map(StorehouseInfo::getMaterialName).distinct().collect(Collectors.toList());
        List<StorehouseInfo> storehouseInfoList = storehouseInfoService.list(Wrappers.<StorehouseInfo>lambdaQuery().in(StorehouseInfo::getMaterialName, materialNameList).eq(StorehouseInfo::getTransactionType, 0));
        // 库存信息转MAP
        Map<String, StorehouseInfo> storehouseInfoMap = storehouseInfoList.stream().collect(Collectors.toMap(StorehouseInfo::getMaterialName, e -> e));
        List<StorehouseInfo> inStockList = new ArrayList<>();
        infoList.forEach(material -> {
            // 出库单号
            material.setDeliveryOrderNumber(outStockRecord.getCode());
            // 库房类型
            material.setTransactionType(2);
            StorehouseInfo stockItem = storehouseInfoMap.get(material.getMaterialName());
            if (stockItem != null) {
                stockItem.setQuantity(stockItem.getQuantity().subtract(material.getQuantity()));
                inStockList.add(stockItem);
            }
        });
        storehouseInfoService.updateBatchById(inStockList);
        storehouseInfoService.saveBatch(infoList);
        return this.save(outStockRecord);
    }
}
