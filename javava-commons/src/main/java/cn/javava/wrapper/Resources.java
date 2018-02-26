package cn.javava.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Iterator;

public class Resources<T> implements Iterable<T> {

    private Integer code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonUnwrapped
    private PageMetadata metadata;

    private Iterable<T> content;

    public Resources(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Resources(Integer code, String message, Iterable<T> content) {
        this(code, message);
        this.content = content;
    }

    public Resources(Integer code, String message, Iterable<T> content, PageMetadata metadata) {
        this(code, message);
        this.content = content;
        this.metadata = metadata;
    }

    public PageMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PageMetadata metadata) {
        this.metadata = metadata;
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

    public static <T> Resources<T> wrap(Page<T> page) {
        return new Resources<>(0, "ok", page.getContent(), new PageMetadata(page));
    }

    public static class PageMetadata {

        private long totalElements;

        private long totalPages;

        private long page;

        private long pageSize;

        protected PageMetadata() {
        }

        public PageMetadata(Page<?> page) {
            this.page = page.getNumber();
            this.pageSize = page.getSize();
            this.totalElements = page.getTotalElements();
            this.totalPages = page.getTotalPages();
        }

        public PageMetadata(long page, long pageSize, long totalElements, long totalPages) {
            this.page = page;
            this.pageSize = pageSize;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }

        public PageMetadata(long size, long number, long totalElements) {
            this(size, number, totalElements, size == 0 ? 0 : (long) Math.ceil((double) totalElements / (double) size));
        }

        public long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public long getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(long totalPages) {
            this.totalPages = totalPages;
        }

        public long getPage() {
            return page;
        }

        public void setPage(long page) {
            this.page = page;
        }

        public long getPageSize() {
            return pageSize;
        }

        public void setPageSize(long pageSize) {
            this.pageSize = pageSize;
        }
    }
}
