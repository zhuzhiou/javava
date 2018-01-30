package cn.javava.live.api;

import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

public class ApiResult {

    private int errno;

    private String errmsg;

    private ApiResult() {
    }

    private ApiResult(int errno, String errmsg) {
        this.errno = errno;
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public static ApiResult fail(String errmsg) {
        return new ApiResult(1, errmsg);
    }

    public static <T> ApiResult success(T object) {
        ApiResult apiResult = new ObjectResult<>(object);
        apiResult.setErrno(0);
        apiResult.setErrmsg("成功");
        return apiResult;
    }

    public static <T> ApiResult success(List<T> list) {
        ApiResult apiResult = new ListResult<>(list);
        apiResult.setErrno(0);
        apiResult.setErrmsg("成功");
        return apiResult;
    }

    public static <T> ApiResult success(Page<T> page) {
        ApiResult apiResult = new PageResult<>(page);
        apiResult.setErrno(0);
        apiResult.setErrmsg("成功");
        return apiResult;
    }

    public static class ObjectResult<T> extends ApiResult {

        private T data;

        public ObjectResult(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public static class ListResult<T> extends ApiResult {

        private int totalElements;

        private List<T> list;

        public ListResult(List<T> list) {
            this.list = list;
            this.totalElements = list == null ? 0 : list.size();
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }
    }

    public static class PageResult<T> extends ApiResult {

        private int totalPages;

        private long totalElements;

        private List<T> list;

        public PageResult(Page<T> page) {
            if (page == null) {
                this.totalElements = 0;
                this.totalPages = 0;
                list = Collections.EMPTY_LIST;
            } else {
                this.totalPages = page.getTotalPages();
                this.totalElements = page.getTotalElements();
                this.list = page.getContent();
            }
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }
    }
}
