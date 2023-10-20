package br.edu.unime.api2.controller.exceptions;

import br.edu.unime.api2.service.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.sql.Date;
import java.time.Instant;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> other(Exception exception) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT.value()).build();
    }

    /*
        @ExceptionHandler(ResourceNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Resource Not Found", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BadRequestException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Bad Request", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(
            NoSuchElementFoundException exception,
            WebRequest request
    ) {
        log.error("Failed to find the requested element", exception);
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }*/

    //@ExceptionHandler Dessa forma, “avisamos” ao Spring que esta função é um tratamento de exceção
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> EntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("EntityNotFoundException!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    /*
    @ExceptionHandler(Forbidden.class)
    public ResponseEntity<StandardError> ForbiddenException(Forbidden e) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new StandardError(HttpStatus.FORBIDDEN, e.getMessage())
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> BadRequestException(BadRequestException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new StandardError(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }
*/
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> NullPointerException(NullPointerException e, HttpServletRequest request) {

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("NullPointerException!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> IllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("IllegalArgumentException!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> DataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("DataIntegrityViolationException!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("HttpRequestMethodNotSupportedException!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> ConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("ConstraintViolationException!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("MethodArgumentTypeMismatchException!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }

    /*@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StandardError> AccessDeniedException(AccessDeniedException e, HttpServletRequest request) {

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("DataIntegrityViolationException!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo notFoundErrorHandler(HttpServletRequest req, BadRequestException e) {
        return this.businessErrorHandler(req, e);
    }

    @ExceptionHandler(value = ConflictException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorInfo conflictErrorHandler(HttpServletRequest req, ConflictException e) {
        return this.businessErrorHandler(req, e);
    }

    @ExceptionHandler(value = ForbiddenException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorInfo forbiddenErrorHandler(HttpServletRequest req, ForbiddenException e) {
        return this.businessErrorHandler(req, e);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInfo forbiddenErrorHandler(HttpServletRequest req, NotFoundException e) {
        return this.businessErrorHandler(req, e);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorInfo unauthorizedErrorHandler(HttpServletRequest req, UnauthorizedException e) {
        return this.businessErrorHandler(req, e);
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorInfo businessErrorHandler(HttpServletRequest req, BusinessException e) {
        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setCode(e.getError().code());
        errorInfo.setMessage(e.getError().message());
        errorInfo.setDetail(e.getDetail());
        errorInfo.setDate(DATE_FORMAT.format(System.currentTimeMillis()));
        errorInfo.setUrl(req.getRequestURL().toString());

        log.warn("business exception:errorInfo={}", errorInfo, e);
        return errorInfo;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorInfo mvcExceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        return mvcErrorInfo(req, e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorInfo mvcExceptionHandler(HttpServletRequest req, HttpMediaTypeNotSupportedException e) {
        return mvcErrorInfo(req, e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorInfo mvcExceptionHandler(HttpServletRequest req, HttpMediaTypeNotAcceptableException e) {
        return mvcErrorInfo(req, e.getMessage());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo mvcExceptionHandler(HttpServletRequest req, MissingPathVariableException e) {
        return mvcErrorInfo(req, e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo mvcExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException e) {
        return mvcErrorInfo(req, e.getMessage());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo mvcExceptionHandler(HttpServletRequest req, MissingRequestHeaderException e) {
        return mvcErrorInfo(req, e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInfo mvcExceptionHandler(HttpServletRequest req, NoHandlerFoundException e) {
        return mvcErrorInfo(req, e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest req, Exception e) {
        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setCode(CommonError.SERVER_ERROR.code());
        errorInfo.setMessage(CommonError.SERVER_ERROR.message());
        errorInfo.setDate(DATE_FORMAT.format(System.currentTimeMillis()));
        errorInfo.setUrl(req.getRequestURL().toString());

        log.error("server error:errorInfo={}", errorInfo, e);

        return errorInfo;
    }

    private ErrorInfo mvcErrorInfo(HttpServletRequest req, String detail) {
        return new ErrorInfo(CommonError.REQUEST_ERROR.code()
                , CommonError.REQUEST_ERROR.message()
                , detail, req.getRequestURL().toString()
                , DATE_FORMAT.format(System.currentTimeMillis())
        );
    }
     */

}
