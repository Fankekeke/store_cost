package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.StorageRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface StorageRecordMapper extends BaseMapper<StorageRecord> {

    /**
     * 分页查询入库记录
     *
     * @param page          分页对象
     * @param storageRecord 入库记录
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectStorageRecordPage(Page<StorageRecord> page, @Param("storageRecord") StorageRecord storageRecord);

    /**
     * 入库记录详情
     *
     * @param code 入库单号
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> storageRecordDetail(@Param("code") String code);
}
