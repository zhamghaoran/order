package com.order.Dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private Integer buyId;
    private Integer goodsId;
    private Integer sellId;
    private Date time;
    private boolean ArriveOrNot;

}
