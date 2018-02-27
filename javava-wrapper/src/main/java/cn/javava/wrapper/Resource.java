package cn.javava.wrapper;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;

public class Resource<T> implements Serializable {

    private Integer code;

    private String message;

    @JsonUnwrapped
    private T content;

    public Resource(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Resource(Integer code, String message, T content) {
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

    public void setContent(T content) {
        this.content = content;
    }

    public static <T> Resource<T> wrap(T content) {
        return new Resource<>(0, "ok", content);
    }
}
