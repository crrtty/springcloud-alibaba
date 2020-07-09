package com.ly.order.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.fastjson.JSON;
import common.ResponseCode;
import common.Result;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerPage implements UrlBlockHandler {
    /**
     * 流控异常：FlowException
     * 熔断降级异常：DegradeException
     * 系统保护异常：SystemBlockException
     * 热点参数限流异常：ParamFlowException
     * @param request
     * @param response
     * @param e
     * @throws IOException
     */
    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        Result result = null;

        if (e instanceof FlowException){
            result = new Result(ResponseCode.FlowException.getCode(),ResponseCode.FlowException.getMessage());
        }else if (e instanceof DegradeException){
            result = new Result(ResponseCode.DegradeException.getCode(),ResponseCode.DegradeException.getMessage());
        }else if (e instanceof ParamFlowException){
            result = new Result(ResponseCode.ParamFlowException.getCode(),ResponseCode.ParamFlowException.getMessage());
        }

        response.getWriter().write(JSON.toJSONString(result));
    }
}
