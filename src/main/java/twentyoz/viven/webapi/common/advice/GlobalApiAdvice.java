package twentyoz.viven.webapi.common.advice;

import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.script.ScriptException;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import twentyoz.viven.security.Jwt401Exception;
import twentyoz.viven.security.Jwt403Exception;
import twentyoz.viven.webapi.common.form.ErrorForm;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalApiAdvice {

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    /*
     * Controller 에 전달된 String 자료형 값 Trim 처리
     * {@link StringTrimmerEditor} 의 첫번째 인자 값이
     * true: 공백인 경우 Null 처리함
     * false: 공백인 경우 Null 처리하지 않음
     */
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple exception(Exception e) {
    log.warn(e.getClass().getSimpleName(), e);
    return ErrorForm.Output.Simple.builder()
        .retcode("500")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  @ExceptionHandler(value = DataAccessException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple dataAccessException(DataAccessException e) {
    log.warn(e.getClass().getSimpleName(), e);

    return ErrorForm.Output.Simple.builder()
        .retcode("500")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  @ExceptionHandler(value = IllegalStateException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple illegalStateException(IllegalStateException e) {
    log.warn(e.getClass().getSimpleName(), e);

    return ErrorForm.Output.Simple.builder()
        .retcode("500")
        .retmsg(String.format("%s", e.getMessage()))
        .build();
  }

  /**
   * 채점 오류 시
   */
  @ExceptionHandler(value = IllegalArgumentException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple illegalArgumentException(IllegalArgumentException e) {
    log.warn(e.getClass().getSimpleName(), e);
    return ErrorForm.Output.Simple.builder()
            .retcode("500")
            .retmsg(e.getMessage())
            .build();
  }

  @ExceptionHandler(value = IllegalAccessException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple illegalAccessException(IllegalAccessException e) {
    log.warn(e.getClass().getSimpleName(), e);
    return ErrorForm.Output.Simple.builder()
            .retcode("500")
            .retmsg(e.getMessage())
            .build();
  }

  @ExceptionHandler(value = DuplicateKeyException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple duplicateKeyException(DuplicateKeyException e) {
    log.warn(e.getClass().getSimpleName(), e);

    return ErrorForm.Output.Simple.builder()
        .retcode("500")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  @ExceptionHandler(value = ScriptException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple scriptException(ScriptException e) {
    log.warn(e.getClass().getSimpleName(), e);

    return ErrorForm.Output.Simple.builder()
        .retcode("500")
        .retmsg(String.format("%s", e.getMessage().replace("ReferenceError: ", "")))
        .build();
  }

  @ExceptionHandler(value = JsonProcessingException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple scriptException(JsonProcessingException e) {
    log.warn(e.getClass().getSimpleName(), e);

    return ErrorForm.Output.Simple.builder()
            .retcode("500")
            .retmsg(e.getMessage())
            .build();
  }

  @ExceptionHandler(value = PersistenceException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple persistenceException(PersistenceException e) {
    log.warn(e.getClass().getSimpleName(), e);

    String retcode = null;

    if (e.getCause().getClass().equals(SQLGrammarException.class)) {
      retcode = getRetCode(((SQLGrammarException) e.getCause()).getSQLState());
    } else if (e.getCause().getClass().equals(BadSqlGrammarException.class)) {
      retcode = getRetCode(((BadSqlGrammarException) e.getCause()).getSQLException().getSQLState());
    } else if (e.getCause().getClass().equals(SQLException.class)) {
      retcode = getRetCode(((SQLException) e.getCause()).getSQLState());
    }

    return ErrorForm.Output.Simple.builder()
        .retcode(retcode != null ? retcode : "500")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  @ExceptionHandler(value = SQLGrammarException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple sQLGrammarException(SQLGrammarException e) {
    log.warn(e.getClass().getSimpleName(), e);

    return ErrorForm.Output.Simple.builder()
        .retcode(getRetCode(e.getSQLException().getSQLState()))
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  @ExceptionHandler(value = BadSqlGrammarException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple badSqlGrammarException(BadSqlGrammarException e) {
    log.warn(e.getClass().getSimpleName(), e);

    return ErrorForm.Output.Simple.builder()
        .retcode(getRetCode(e.getSQLException().getSQLState()))
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  @ExceptionHandler(value = SQLException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple sQLException(SQLException e) {
    log.warn(e.getClass().getSimpleName(), e);

    return ErrorForm.Output.Simple.builder()
        .retcode(getRetCode(e.getSQLState()))
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  @ExceptionHandler(value = SocketTimeoutException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Simple socketTimeoutException(SocketTimeoutException e) {
    log.warn(e.getClass().getSimpleName(), e);

    return ErrorForm.Output.Simple.builder()
        .retcode("500")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  // =============== BAD_REQUEST 400

  /** 입력값 검증 실패 (@Valid) */
  @ExceptionHandler(value = BindException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorForm.Output.Simple bindException(BindException e) {
    log.debug(e.getClass().getSimpleName(), e);

    return ErrorForm.Output.Simple.builder()
        .retcode("400")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  /** 입력값 검증 실패 (@Valid) */
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorForm.Output.Field methodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.debug(e.getClass().getSimpleName(), e);

    String retMsg = String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage());

    List<ErrorForm.Output.Field.Detail> list = this.getFieldErrorsList(e.getBindingResult());
    if (list != null && list.size() > 0) {
      retMsg = "입력 값 확인이 필요합니다.";
    }

    return ErrorForm.Output.Field.builder()
        .retcode("400")
        .retmsg(retMsg)
        .errors(this.getFieldErrorsList(e.getBindingResult()))
        .build();
  }

  // =============== UNAUTHORIZED 401, FORBIDDEN 403

  /** 인증이 필요합니다. */
  @ExceptionHandler(value = Jwt401Exception.class)
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  public ErrorForm.Output.Simple authenticationException(Jwt401Exception e) {
    return ErrorForm.Output.Simple.builder()
        .retcode("401")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  /** 사용자 권한이 충분하지 않습니다 */
  @ExceptionHandler(value = Jwt403Exception.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public ErrorForm.Output.Simple accessDeniedException(Jwt403Exception e) {
    return ErrorForm.Output.Simple.builder()
        .retcode("403")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  // =============== NOT_FOUND 404

  /** 데이터를 찾을 수 없음 */
  @ExceptionHandler(value = EntityNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorForm.Output.Simple entityNotFoundException(EntityNotFoundException e) {
    return ErrorForm.Output.Simple.builder()
        .retcode("404")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  /** 파일을 찾을 수 없습니다. (잘못된 파일호출) */
  @ExceptionHandler(FileNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorForm.Output.Simple fileNotFoundException(FileNotFoundException e) {
    return ErrorForm.Output.Simple.builder()
        .retcode("404")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  /** 페이지를 찾을 수 없습니다. (잘못된 URL 호출) */
  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorForm.Output.Simple noHandlerFoundException(NoHandlerFoundException e) {
    return ErrorForm.Output.Simple.builder()
        .retcode("404")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }

  // =============== NOT_ACCEPTABLE 406

  /** 페이지를 찾을 수 없습니다. (잘못된 콘텐츠 요청) */
  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ErrorForm.Output.Simple httpMediaTypeNotAcceptableException(
      HttpMediaTypeNotAcceptableException e) {
    return ErrorForm.Output.Simple.builder()
        .retcode("406")
        .retmsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()))
        .build();
  }


  /** 에러 항목 리스트 추출 */
  private List<ErrorForm.Output.Field.Detail> getFieldErrorsList(BindingResult result) {
    List<ErrorForm.Output.Field.Detail> errorsList = new ArrayList<ErrorForm.Output.Field.Detail>();

    if (result.getFieldErrors() != null && result.getFieldErrors().size() > 0) {
      for (FieldError fe : result.getFieldErrors()) {
        ErrorForm.Output.Field.Detail detail = new ErrorForm.Output.Field.Detail();
        detail.setField(fe.getField());
        if (fe.getRejectedValue() != null) {
          detail.setValue(fe.getRejectedValue().toString());
        }
        detail.setReason(fe.getDefaultMessage());
        errorsList.add(detail);
      }
    }

    return errorsList;
  }



  /** sqlState 에 따른 응답코드 반환. */
  private String getRetCode(String sqlState) {

    String rst = "500";
    // none schema table and view
    if ("42P01".equals(sqlState) // postgresql
        || "ORA-00942".equals(sqlState) // oracle 옆 값이 아닌 경우 ORA-00942 적용
    ) {
      rst = "1190";
    }

    return rst;
  }



}
