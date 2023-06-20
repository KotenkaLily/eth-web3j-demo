package org.example.web3.common.res;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoResult<T> {

    /**
     * 响应代码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;


    /**
     * 响应结果
     */
    private T data;

    /**
     * 成功
     */
    public static <T> DemoResult<T> success() {
        return success(null);
    }

    /**
     * 成功
     */
    public static <T> DemoResult<T> success(T data) {
        DemoResult<T> demoResult = new DemoResult<>();
        demoResult.setCode(DemoResponseEnum.SUCCESS.getResultCode());
        demoResult.setMessage(DemoResponseEnum.SUCCESS.getResultMsg());
        demoResult.setData(data);
        return demoResult;
    }

    /**
     * 成功，且不用自己封装map了。
     * 前端返回通用结构：res.data内：
     * res.data.normal: 返回结果是否正常
     * res.data.resultList: 返回数据，list
     * res.data.resultVo: 返回数据，vo
     * res.data.resultMap: 返回数据，map
     * res.data.resultObject: 返回数据，obj,基本类型+String+json等
     */
    public static <T> DemoResult<Map<String, Object>> mSuccess(T data, String type) {
        DemoResult<Map<String, Object>> k3dResult = new DemoResult<>();
        k3dResult.setCode(DemoResponseEnum.SUCCESS.getResultCode());
        k3dResult.setMessage(DemoResponseEnum.SUCCESS.getResultMsg());
        Map<String, Object> map = new HashMap<>();
        if (data != null) {
            map.put("normal", true);
            switch (type) {
                case "list":
                    map.put("resultList", data);
                    break;
                case "vo":
                    map.put("resultVo", data);
                    break;
                case "map":
                    map.put("resultMap", data);
                    break;
                case "obj":
                    map.put("resultObject", data);
                    break;
                default:
                    ;
            }
        } else {
            map.put("normal", false);
            map.put("extraMsg", "异常空值结果");
        }
        k3dResult.setData(map);
        return k3dResult;
    }

    /**
     * 失败
     */
    public static <T> DemoResult<T> error(DemoResponse response) {
        DemoResult<T> demoResult = new DemoResult<>();
        demoResult.setCode(response.getResultCode());
        demoResult.setMessage(response.getResultMsg());
        demoResult.setData(null);
        return demoResult;
    }

    public static <T> DemoResult<T> error(int code) {
        DemoResult<T> demoResult = new DemoResult<>();
        demoResult.setCode(code);
        demoResult.setData(null);
        return demoResult;
    }

    public static <T> DemoResult<T> error(String message) {
        DemoResult<T> demoResult = new DemoResult<>();
        demoResult.setCode(-1);
        demoResult.setMessage(message);
        demoResult.setData(null);
        return demoResult;
    }

    public static <T> DemoResult<T> error(int code, String message) {
        DemoResult<T> demoResult = new DemoResult<>();
        demoResult.setCode(code);
        demoResult.setMessage(message);
        demoResult.setData(null);
        return demoResult;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}