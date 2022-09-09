package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.OutStockRecord;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IOutStockRecordService extends IService<OutStockRecord> {

    /**
     * 分页查询出库记录
     *
     * @param page           分页对象
     * @param outStockRecord 出库信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectOutStockRecordPage(Page<OutStockRecord> page, OutStockRecord outStockRecord);

    /**
     * 查询出库记录详情
     *
     * @param code 出库单号
     * @return 结果
     */
    LinkedHashMap<String, Object> outStockDetail(String code);

    /**
     * 添加物料出库信息
     *
     * @param outStockRecord 出库记录
     * @return 结果
     */
    boolean saveOutStock(OutStockRecord outStockRecord);
}
