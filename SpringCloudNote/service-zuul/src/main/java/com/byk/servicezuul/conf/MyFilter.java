package com.byk.servicezuul.conf;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.ZuulFilterResult;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author: bianyakun
 * @Date: 2018/6/12 9:28
 * @Todo:
 */
//@Component
public class MyFilter extends ZuulFilter {

    /**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    /**
     * 过滤器类型，此处是请求前进行拦截
     *
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }


    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }


    /**
     * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     */
    @Override
    public Object run() {
        //获取请求的路径信息
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //日志信息========>可有可无吧，暂时还看不懂
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        //获取路径信息的关键词”token"
        String token = request.getParameter("token");
        //如果token的数值小于100，就拦截，并返回“token is too small"
        if(Integer.parseInt(token) < 100) {
            //日志打印
            log.warn("token is too small");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                //响应
                ctx.getResponse().getWriter().write("token is too small~");
            }catch (Exception e){}

            return null;
        }
        log.info("ok");
        return null;
    }
}
