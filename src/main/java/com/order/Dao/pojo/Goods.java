package com.order.Dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Integer price;
    private String name;
    private String description;
    private String picture;
    private String belong;
    private Integer Id;
}
