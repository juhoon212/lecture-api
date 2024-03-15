package twentyoz.viven.config.swagger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Swagger UI Model 버그수정
 *
 * <pre>
 * 모델명이 동일한 경우 Swagger 에서 렌더링 버그가 있음
 * getSimpleName() 아닌 getName() 변경하여 전체이름이 표기되로록 수정
 * </pre>
 */
@Aspect
public class DefaultTypeNameProviderFixer {

  @Around("execution(* springfox.documentation.schema.DefaultTypeNameProvider.nameFor(..))")
  public Object around(ProceedingJoinPoint point) {
    Class<?> type = (Class<?>) point.getArgs()[0];

    String name = type.getName();
    name = name.substring(name.lastIndexOf('.') + 1);

    return name;
  }
}
