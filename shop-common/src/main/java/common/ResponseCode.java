package common;

public enum ResponseCode {

    SUCCESS(200,"成功"),
    FAIL(500,"失败"),
    FlowException(10001,"接口流控"),
    DegradeException(10002,"接口熔断降级"),
    ParamFlowException(10003, "热点参数限流");

    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
