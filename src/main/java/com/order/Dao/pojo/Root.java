package com.order.Dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Root {
    private String username;
    private String password;
    private Integer id;

}
