package cn.javava.live.vo;

public final class ResultVo<T> {

    private int code;

    private String message;

    private T data;

    private ResultVo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultVo fail(String message) {
        return new ResultVo(1, message);
    }

    public static <T> ResultVo success(T data) {
        ResultVo<T> resultVo = new ResultVo<>(0, "成功");
        resultVo.setData(data);
        return resultVo;
    }

}
