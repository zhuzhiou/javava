package cn.javava.common.wrap;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;

public class Wrapper<T> implements Serializable {

    private Integer code;

    private String message;

    //当 T 为基础类型时，该类没有作用。
    @JsonUnwrapped
    private T content;

    public Wrapper(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Wrapper(Integer code, String message, T content) {
        this(code, message);
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

}
