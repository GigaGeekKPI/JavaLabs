package com.Jlabs.CommentClient.Error;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandler implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format(
                "Status code: %s => Exception Message: %s",
                statusCode, exception==null? "Not found": exception.getMessage());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}