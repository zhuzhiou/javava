package cn.javava.wrapper;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collection;

@RestControllerAdvice
public class ResourcesControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Page.class.isAssignableFrom(returnType.getParameterType()) || Collection.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Resources resources = new Resources(0, "成功");
        if (Page.class.isAssignableFrom(returnType.getParameterType())) {
            Page page = (Page) body;
            resources.setMetadata(new Resources.PageMetadata(page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages()));
            resources.setContent(page.getContent());
        } else if (Iterable.class.isAssignableFrom(returnType.getParameterType())) {
            resources.setContent((Collection) body);
        }
        return resources;
    }
}
