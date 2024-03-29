package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 作业成本
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JobCostInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 作业名称
     */
    private String jobName;

    /**
     * 作业成本
     */
    private BigDecimal jobCost;

    /**
     * 作业动因
     */
    private String jobMotivation;

    /**
     * 作业动因数
     */
    private String jobMotivationNum;

    /**
     * 合计
     */
    private BigDecimal total;

    /**
     * 成本动因率
     */
    private BigDecimal costRate;

    /**
     * 作业成本所属
     */
    private Integer orderId;

    /**
     * 各个产品动因数
     */
    private String materialCostNum;
}
