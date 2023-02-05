package nam.gor.machineryleasing.models.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AfterPlatformLaunchImpl implements
                          ConstraintValidator<AfterPlatformLaunch, LocalDate> {

    @Override
    public boolean isValid(final LocalDate date,
                           final ConstraintValidatorContext context) {
        return date.isAfter(LocalDate.of(
                2022, 1, 1));
    }

    @Override
    public void initialize(final AfterPlatformLaunch annotation) {
        ConstraintValidator
                .super
                .initialize(annotation);
    }
}
