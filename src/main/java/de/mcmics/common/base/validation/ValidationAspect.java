package de.mcmics.common.base.validation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import javax.validation.groups.Default;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Order(3)
@Component
class ValidationAspect {

    private final Validator validator;
    private final ValidatorFactory validatorFactory;

    ValidationAspect(final Validator validator, final ValidatorFactory validatorFactory) {
        this.validator = validator;
        this.validatorFactory = validatorFactory;
    }

    //@Around("@annotation(de.mcmics.core.common.base.validation.WithValidation)")
    @Around("execution(public * @de.mcmics.common.base.validation.WithValidation java.lang.Object+.*(..))()")
    public Object validateArguments(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final Set<ConstraintViolation<Object>> violations = validate(proceedingJoinPoint);
        if (!violations.isEmpty()) {
            throwValidationException(violations);
        }
        // Close to clear beanMetaDataCache in org.hibernate.validator.internal.metadata.BeanMetaDataManagerImpl.getBeanMetaData
        validatorFactory.close();
        return proceedingJoinPoint.proceed();
    }

    private Class<?>[] getValidationGroups(int i) {
        return new Class<?>[] {Default.class};
    }

    private void throwValidationException(final Set<ConstraintViolation<Object>> pViolations) {
        final String message = pViolations.stream().map(ConstraintViolation::getMessage)
            .collect(Collectors.joining("\n"));
        throw new ValidationException(message);
    }

    private Set<ConstraintViolation<Object>> validate(ProceedingJoinPoint proceedingJoinPoint) {
        final Object target = proceedingJoinPoint.getTarget();
        final Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        final Object[] args = proceedingJoinPoint.getArgs();

        final Set<ConstraintViolation<Object>> violations = new HashSet<>();
        for (int i = 0, l = args.length; i < l; i++) {
            final Object arg = args[i];
            if (arg != null) {
                final Class<?>[] validationGroups = getValidationGroups(i);
                final Set<ConstraintViolation<Object>> argViolations = validator.validate(arg, validationGroups);
                violations.addAll(argViolations);
            }
        }
        final Validator baseValidator = validator.unwrap(Validator.class);
        final ExecutableValidator executableValidator = baseValidator.forExecutables();
        violations.addAll(executableValidator.validateParameters(target, method, args));

        return violations;
    }
}
