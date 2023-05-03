package cc.mrbird.febs.cos.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MaterialCostVo implements Serializable {

    private Integer materialId;

    private String materialName;

    private BigDecimal num;

}
