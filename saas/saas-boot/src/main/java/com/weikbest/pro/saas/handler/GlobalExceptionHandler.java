package com.weikbest.pro.saas.handler;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author wisdomelon
 * @date 2019/6/13
 * @project saas
 * @jdk 1.8
 * <p>
 * 统一异常处理
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    private static final String LOGEXCEPTIONFORMAT = "Capture Exception By GlobalExceptionHandler: Code: %s Msg: %s";

    /***
     * 运行时异常
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public DataResp runtimeExceptionHandler(RuntimeException ex) {
        return exceptionFormat(ResultConstant.RUNTIME_FAIL, ex);
    }

    /***
     * 空指针异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public DataResp nullPointerExceptionHandler(NullPointerException ex) {
        return exceptionFormat(ResultConstant.NULL_FAIL, ex);
    }

    /***
     * 类型转换异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    public DataResp classCastExceptionHandler(ClassCastException ex) {
        return exceptionFormat(ResultConstant.CLASSCAST_FAIL, ex);
    }

    /***
     * IO异常
     * @param ex
     * @return
     */
    @ExceptionHandler(IOException.class)
    public DataResp ioExceptionHandler(IOException ex) {
        return exceptionFormat(ResultConstant.IO_FAIL, ex);
    }

    /***
     * 未知方法异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public DataResp noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return exceptionFormat(ResultConstant.NOSUCHMETHOD_FAIL, ex);
    }

    /***
     * 数组越界异常
     * @param ex
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public DataResp indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return exceptionFormat(ResultConstant.INDEXOUTOFBOUNDS_FAIL, ex);
    }

    /***
     * 500错误
     * @param ex
     * @return
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public DataResp server500(RuntimeException ex) {
        return exceptionFormat(ResultConstant.SERVER_500_ERROR, ex);
    }

    /***
     * 栈溢出
     * @param ex
     * @return
     */
    @ExceptionHandler({StackOverflowError.class})
    public DataResp requestStackOverflow(StackOverflowError ex) {
        return exceptionFormat(ResultConstant.STACKOVERFLOW_FAIL, ex);
    }

    /***
     * JSR303校验错误
     * @param ex
     * @return
     */
    @ExceptionHandler({BindException.class})
    public DataResp bindException(BindException ex) {
        return exceptionFormat(ResultConstant.VALID_FAIL, ex);
    }

    /***
     * 数据库操作异常
     * @param ex
     * @return
     */
    @ExceptionHandler(DataAccessException.class)
    public DataResp dataAccessExceptionHandler(DataAccessException ex) {
        return exceptionFormat(ResultConstant.DB_OPERATE_ERROR, ex);
    }

    /***
     * 其他错误
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public DataResp exception(Exception ex) {
        return exceptionFormat(ResultConstant.EXCEPTION_FAIL, ex);
    }


    /***
     * 服务层异常捕获
     * @param ex
     * @return
     */
    @ExceptionHandler({WeikbestException.class})
    public DataResp MysteryBoxesException(WeikbestException ex) {
        return exceptionFormat(ex.getCode(), ex.getMsg(), ex);
    }


    /***
     * 输出异常
     * @param code
     * @param ex
     * @param <T>
     * @return
     */
    private <T extends Throwable> DataResp exceptionFormat(int code, T ex) {
        log.error(String.format(LOGEXCEPTIONFORMAT, code, ex.getMessage()), ex);
        return DataResp.error().code(code).msg(ex.getMessage());
    }


    /***
     * 输出异常
     * @param code
     * @param msg
     * @param ex
     * @param <T>
     * @return
     */
    private <T extends Throwable> DataResp exceptionFormat(int code, String msg, T ex) {
        log.error(String.format(LOGEXCEPTIONFORMAT, code, msg), ex);
        return DataResp.error().code(code).msg(msg);
    }

    /***
     * 输出异常
     * @param resultConstant
     * @param ex
     * @param <T>
     * @return
     */
    private <T extends Throwable> DataResp exceptionFormat(ResultConstant resultConstant, T ex) {
        log.error(String.format(LOGEXCEPTIONFORMAT, resultConstant.getCode(), ex.getMessage()), ex);
        if (StrUtil.isNotBlank(ex.getMessage())) {
            return DataResp.error().setCode(resultConstant.getCode()).setMsg(ex.getMessage());
        }
        return DataResp.error().infomation(resultConstant);
    }
}
