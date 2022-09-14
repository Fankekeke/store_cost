package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.StorehouseInfo;
import cc.mrbird.febs.cos.dao.StorehouseInfoMapper;
import cc.mrbird.febs.cos.service.IStorehouseInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class StorehouseInfoServiceImpl extends ServiceImpl<StorehouseInfoMapper, StorehouseInfo> implements IStorehouseInfoService {

    /**
     * 分页查询库房信息
     *
     * @param page           分页对象
     * @param storehouseInfo 库房信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectStorehousePage(Page<StorehouseInfo> page, StorehouseInfo storehouseInfo) {
        return baseMapper.selectStorehousePage(page, storehouseInfo);
    }

    /**
     * 根据物料名称查询出入库记录
     *
     * @param name 物料名称
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectStorehouseDetail(String name) {
        return new LinkedHashMap<String, Object>() {
            {
                put("in", baseMapper.selectStorehouseDetail(name, 1));
                put("out", baseMapper.selectStorehouseDetail(name, 2));
            }
        };
    }
}
