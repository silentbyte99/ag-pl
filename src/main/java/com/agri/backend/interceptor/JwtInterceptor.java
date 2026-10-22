package com.agri.backend.interceptor;

import com.agri.backend.common.BusinessException;
import com.agri.backend.utils.JwtUtil;
import com.agri.backend.utils.UserContext;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT 登录拦截器
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 放行只读公开接口（GET 请求的列表/详情），写操作仍需登录
        String method = request.getMethod();
        String uri = request.getRequestURI();
        if ("GET".equalsIgnoreCase(method) && isPublicGet(uri)) {
            return true;
        }

        String header = request.getHeader(AUTH_HEADER);
        if (header == null || !header.startsWith(BEARER_PREFIX)) {
            throw new BusinessException("请先登录");
        }
        String token = header.substring(BEARER_PREFIX.length());
        if (!jwtUtil.validateToken(token)) {
            throw new BusinessException("登录已过期，请重新登录");
        }
        UserContext.setUserId(jwtUtil.getUserId(token));
        return true;
    }

    /**
     * 判断是否为公开的 GET 只读接口
     */
    private boolean isPublicGet(String uri) {
        // 去掉 context-path 前缀（/api）
        String path = uri.startsWith("/api") ? uri.substring(4) : uri;
        return "/category/list".equals(path)
                || "/news/page".equals(path)
                || path.matches("/news/\\d+")
                || "/tech/page".equals(path)
                || path.matches("/tech/\\d+")
                || "/wiki/page".equals(path)
                || path.matches("/wiki/\\d+");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.remove();
    }
}
