package com.order.Dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Orders {
    @TableField("buyId")
    private Long buyId;
    @TableField("goodsIds")
    private String goodsIds;
    @TableField("sellId")
    private Long sellId;
    @TableField("time")
    private Date time;
    @TableField("arriveOrNot")
    private Boolean arriveOrNot;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("destination")
    private String destination;

    @TableField(exist = false)
    List<String> goodsList;
}
