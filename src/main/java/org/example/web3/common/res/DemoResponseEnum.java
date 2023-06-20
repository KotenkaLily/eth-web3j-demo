package org.example.web3.common.res;

/**
 * 返回结果定义类
 */
public enum DemoResponseEnum implements DemoResponse {
    SUCCESS(200, "接口请求成功");
    /**
     * 错误码
     */
    private final int resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    DemoResponseEnum(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public int getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

}