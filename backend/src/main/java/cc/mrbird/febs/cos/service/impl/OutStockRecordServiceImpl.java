package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.OutStockRecord;
import cc.mrbird.febs.cos.dao.OutStockRecordMapper;
import cc.mrbird.febs.cos.service.IOutStockRecordService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class OutStockRecordServiceImpl extends ServiceImpl<OutStockRecordMapper, OutStockRecord> implements IOutStockRecordService {

    /**
     * 分页查询出库记录
     *
     * @param page           分页对象
     * @param outStockRecord 出库信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectOutStockRecordPage(Page<OutStockRecord> page, OutStockRecord outStockRecord) {
        return null;
    }

    /**
     * 查询出库记录详情
     *
     * @param code 出库单号
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> outStockDetail(String code) {
        return null;
    }

    /**
     * 添加物料出库信息
     *
     * @param outStockRecord 出库记录
     * @return 结果
     */
    @Override
    public boolean saveOutStock(OutStockRecord outStockRecord) {
        return false;
    }
}
