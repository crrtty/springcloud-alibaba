package com.ly.order.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//@Component
public class RequestOriginParserDefinition implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String serverName = httpServletRequest.getParameter("serverName");
        if (StringUtils.isEmpty(serverName)){
            throw new RuntimeException("serverName is empty");
        }
        return serverName;
    }
}
