package cn.tedu.straw.portal.controller;

import cn.tedu.straw.portal.service.ex.*;
import cn.tedu.straw.portal.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public R handLeException(Throwable e) {
        if (e instanceof ParameterValidateException){
            return R.failure(R.State.ERR_PARAMETER_VALIDATE, e);
        } else if (e instanceof InviteCodeException) {
            return R.failure(R.State.ERR_INVITE_CODE, e);
        } else if (e instanceof ClassDisabledException) {
            return R.failure(R.State.ERR_CLASS_DISABLED, e);
        } else if (e instanceof PhoneDuplicateException) {
            return R.failure(R.State.ERR_PHONE_DUPLICATE, e);
        } else if (e instanceof InsertException) {
            return R.failure(R.State.ERR_INSERT, e);
        } else if (e instanceof AccessDeniedException){
            return R.failure(R.State.ERR_ACESS_DENIED, e);
        } else {
            log.debug("Unkown Exception:{}",e);
            return R.failure(R.State.ERR_UNKNOWN, e);
        }
    }
}
