package cn.linshio.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//JSON封装体
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //代码响应值
    private Integer code;
    //信息值
    private String message;

    //携带的数据值
    private T data;

    //设计一个构造方法，可能会没有数据的返回值
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
