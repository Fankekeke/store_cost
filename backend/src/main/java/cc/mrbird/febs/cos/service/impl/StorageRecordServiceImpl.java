package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.StorageRecord;
import cc.mrbird.febs.cos.dao.StorageRecordMapper;
import cc.mrbird.febs.cos.service.IStorageRecordService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class StorageRecordServiceImpl extends ServiceImpl<StorageRecordMapper, StorageRecord> implements IStorageRecordService {

    /**
     * 分页查询入库记录
     * @param page 分页对象
     * @param storageRecord 入库记录
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectStorageRecordPage(Page<StorageRecord> page, StorageRecord storageRecord) {
        return baseMapper.selectStorageRecordPage(page, storageRecord);
    }

    /**
     * 入库记录详情
     * @param code 入库单号
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> storageRecordDetail(String code) {
        return null;
    }

    /**
     * 添加入库记录
     *
     * @param storageRecord 入库记录
     * @return 结果
     */
    @Override
    public boolean saveStorageRecord(StorageRecord storageRecord) {
        return false;
    }
}
