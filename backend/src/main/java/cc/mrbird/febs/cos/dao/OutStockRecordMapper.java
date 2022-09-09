package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.OutStockRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface OutStockRecordMapper extends BaseMapper<OutStockRecord> {

    /**
     * 分页查询出库记录
     *
     * @param page           分页对象
     * @param outStockRecord 出库信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectOutStockRecordPage(Page<OutStockRecord> page, @Param("outStockRecord") OutStockRecord outStockRecord);

}
