package com.order.Dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("goods")
public class Goods {
    @TableField("name")
    private String name;
    @TableField("price")
    private Integer price;
    @TableField("description")
    private String description;
    @TableField("picture")
    private Object picture;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("belong")
    private Long belong;
}
