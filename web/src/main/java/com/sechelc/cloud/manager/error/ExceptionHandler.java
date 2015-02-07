package com.sechelc.cloud.manager.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class ExceptionHandler {
    final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    /**
     * Handle exceptions thrown by handlers.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request) {
        logger.info("puscatura:", exception);

        ModelAndView modelAndView = new ModelAndView("error/general");
        modelAndView.addObject("errorMessage", Throwables.getRootCause(exception));
        return modelAndView;
    }
}