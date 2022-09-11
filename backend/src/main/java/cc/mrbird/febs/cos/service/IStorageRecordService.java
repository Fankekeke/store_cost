package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.StorageRecord;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IStorageRecordService extends IService<StorageRecord> {

    /**
     * 分页查询入库记录
     *
     * @param page          分页对象
     * @param storageRecord 入库记录
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectStorageRecordPage(Page<StorageRecord> page, StorageRecord storageRecord);

    /**
     * 入库记录详情
     *
     * @param code 入库单号
     * @return 结果
     */
    LinkedHashMap<String, Object> storageRecordDetail(String code);

    /**
     * 添加入库记录
     *
     * @param storageRecord 入库记录
     * @return 结果
     */
    boolean saveStorageRecord(StorageRecord storageRecord);
}