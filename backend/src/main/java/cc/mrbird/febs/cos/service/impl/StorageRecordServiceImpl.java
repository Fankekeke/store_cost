package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.StorageRecord;
import cc.mrbird.febs.cos.dao.StorageRecordMapper;
import cc.mrbird.febs.cos.entity.StorehouseInfo;
import cc.mrbird.febs.cos.service.IStorageRecordService;
import cc.mrbird.febs.cos.service.IStorehouseInfoService;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class StorageRecordServiceImpl extends ServiceImpl<StorageRecordMapper, StorageRecord> implements IStorageRecordService {

    private final IStorehouseInfoService storehouseInfoService;

    /**
     * 分页查询入库记录
     *
     * @param page          分页对象
     * @param storageRecord 入库记录
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectStorageRecordPage(Page<StorageRecord> page, StorageRecord storageRecord) {
        return baseMapper.selectStorageRecordPage(page, storageRecord);
    }

    /**
     * 入库记录详情
     *
     * @param code 入库单号
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> storageRecordDetail(String code) {
        return baseMapper.storageRecordDetail(code);
    }

    /**
     * 添加入库记录
     *
     * @param storageRecord 入库记录
     * @return 结果
     */
    @Override
    public boolean saveStorageRecord(StorageRecord storageRecord) {
        // 设置入库单号
        storageRecord.setCode("IN-"+System.currentTimeMillis());
        List<StorehouseInfo> infoList = Convert.toList(StorehouseInfo.class, storageRecord.getMaterial());
        // 获取物料库存
        List<String> materialNameList = infoList.stream().map(StorehouseInfo::getMaterialName).distinct().collect(Collectors.toList());
        List<StorehouseInfo> storehouseInfoList = storehouseInfoService.list(Wrappers.<StorehouseInfo>lambdaQuery().in(StorehouseInfo::getMaterialName, materialNameList).eq(StorehouseInfo::getTransactionType, 0));
        // 库存信息转MAP
        Map<String, StorehouseInfo> storehouseInfoMap = storehouseInfoList.stream().collect(Collectors.toMap(StorehouseInfo::getMaterialName, e -> e));
        List<StorehouseInfo> inStockList = new ArrayList<>();
        infoList.forEach(material -> {
            // 出库单号
            material.setDeliveryOrderNumber(storageRecord.getCode());
            // 库房类型
            material.setTransactionType(1);
            StorehouseInfo stockItem = storehouseInfoMap.get(material.getMaterialName());
            if (stockItem != null) {
                stockItem.setQuantity(stockItem.getQuantity().add(material.getQuantity()));
                inStockList.add(stockItem);
            }
        });
        storehouseInfoService.updateBatchById(inStockList);
        storehouseInfoService.saveBatch(infoList);
        return this.save(storageRecord);
    }
}
