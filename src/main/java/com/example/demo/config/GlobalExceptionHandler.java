package com.example.demo.config;

import com.example.demo.exception.BusinessException;
import com.example.demo.message.ServiceCode;
import com.example.demo.output.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.message.ServiceCode.FIELD_VALIDATE_ERROR;
import static com.example.demo.message.ServiceCode.GLOBAL_UNKNOWN_ERROR;
import static com.example.demo.output.AjaxResponse.error;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public AjaxResponse errorHandler(HttpServletRequest request, Exception e) {
        logger.error("request page url{}, params:{}, error:{}", request.getRequestURI(), request.getParameterMap(), e);
        return error(e.getMessage(), ServiceCode.GLOBAL_EXCEPTION_ERROR.getKey());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AjaxResponse validateErrorHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> errorList = bindingResult.getFieldErrors();
            String errorMsg = errorList.get(0).getField() + " 字段错误，错误原因:" + errorList.get(0).getDefaultMessage();
            return error(errorMsg, FIELD_VALIDATE_ERROR.getKey());
        } else {
            return error(GLOBAL_UNKNOWN_ERROR);
        }
    }
//    /**
//     * 处理所有不可知的异常
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    AjaxResponse handleException(Exception e){
//        logger.error(e.getMessage(), e);
//        return AjaxResponse.error("操作失败!","401");
//    }
    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    AjaxResponse handleBusinessException(BusinessException e){
        logger.error(e.getMessage(), e);
        return AjaxResponse.error(e.getMessage(),"4010");
    }
    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    AjaxResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // logger.error(e.getMessage(), e);
        BindingResult result = e.getBindingResult();
        List<EntryErrorInfo> errors = new ArrayList<EntryErrorInfo>();
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                EntryErrorInfo errorInfo = new EntryErrorInfo();
                errorInfo.setCode(error.getCode());
                errorInfo.setObjectName(error.getObjectName());
                errorInfo.setErrorMesage(error.getDefaultMessage());
                errorInfo.setFiled(error.getField());
                errors.add(errorInfo);
            }
        }
        return AjaxResponse.error("valid error", "4011", errors);
    }
}

