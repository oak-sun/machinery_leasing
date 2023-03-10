package nam.gor.machineryleasing.models.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = AfterPlatformLaunchImpl.class)
public @interface AfterPlatformLaunch {
    String message() default "Message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
