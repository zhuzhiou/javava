package cn.javava.common.wrap;

import java.util.Iterator;

public class ListWrapper<T> implements Iterable<T> {

    private Integer code;

    private String message;

    private Iterable<T> content;

    public ListWrapper(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ListWrapper(Integer code, String message, Iterable<T> content) {
        this(code, message);
        this.content = content;
    }

    public Iterable<T> getContent() {
        return content;
    }

    public void setContent(Iterable<T> content) {
        this.content = content;
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }
}
