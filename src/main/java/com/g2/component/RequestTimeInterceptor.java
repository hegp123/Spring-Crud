package com.g2.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.g2.repository.ILogRepository;

/**
 * Para que este interceptor funcione tenemos que toca darle de alta en el
 * WebMvcConfiguration
 * 
 * @author hector.garcia
 *
 */
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

    private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);

    @Autowired
    @Qualifier("logRepository")
    private ILogRepository logRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
//        LOGGER.info("request: " + request.get);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long startTime = (long) request.getAttribute("startTime");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = "";
        String details = "";
        if (auth != null && auth.isAuthenticated()) {
            userName = auth.getName();
            details = auth.getDetails().toString();
        }
        String url = request.getRequestURL().toString();
        com.g2.entity.Log log = new com.g2.entity.Log(new Date(), details, userName, url);
        logRepository.saveAndFlush(log);

        LOGGER.info(
                "--REQUEST URL: '" + url + "' -- TOTAL TIME: '" + (System.currentTimeMillis() - startTime) + "' ms");
    }

}
