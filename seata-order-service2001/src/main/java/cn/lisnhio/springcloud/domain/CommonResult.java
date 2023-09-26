package cn.lisnhio.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //状态码
    private Integer code;
    //携带的消息
    private String message;
    //携带的数据
    private T data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
