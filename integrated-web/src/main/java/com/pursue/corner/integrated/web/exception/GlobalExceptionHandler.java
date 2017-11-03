package com.pursue.corner.integrated.web.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pursue.corner.integrated.common.dto.ErrorInfo;
import com.pursue.corner.integrated.common.enums.ReturnCode;
import com.pursue.corner.integrated.common.exception.IntegratedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = IntegratedException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, IntegratedException e) throws Exception {
    	ReturnCode.ERROR.setMsg(e.getMessage());
    	ErrorInfo<String> r = new ErrorInfo<>(ReturnCode.ERROR);
        r.setData("error");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}

