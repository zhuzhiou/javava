package cn.javava.common.wrap;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.data.domain.Page;

import java.io.Serializable;

public class PageWrapper<T> implements Serializable {

    private Integer code;

    private String message;

    @JsonUnwrapped
    private Page<T> page;

    public PageWrapper(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public PageWrapper(Integer code, String message, Page<T> page) {
        this(code, message);
        this.page = page;
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

    public Page<T> getPage() {
        return page;
    }

}
