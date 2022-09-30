package com.order.util;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Response {
    private String code;
    private String status;
    private Object result;

    public Response easyReturn(Object result) {
        Response response = new Response();
        response.status = "success";
        response.code = "200";
        response.result = result;
        return response;
    }
    public Response badReturn(Object result) {
        Response response = new Response();
        response.status = "500";
        response.code = "failed";
        response.result = result;
        return response;
    }
}
