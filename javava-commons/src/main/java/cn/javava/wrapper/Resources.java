package cn.javava.wrapper;

import java.util.Collection;
import java.util.Iterator;

public class Resources<T> implements Iterable<T> {

    private Integer code;

    private String message;

    private Iterable<T> content;

    public Resources(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Resources(Integer code, String message, Iterable<T> content) {
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

    public static <T> Resources<T> wrap(Collection<T> content) {
        return new Resources<>(0, "ok", content);
    }
}
