package com.yourcompany.platform.common.validation.validator;

import com.yourcompany.platform.common.validation.annotation.EnumValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
/*
 * @author josec
 * @project yourcompany-platform
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, CharSequence> {
    private Set<String> acceptedValues;
    private boolean ignoreCase;

    @Override
    public void initialize(final EnumValue annotation) {
        this.ignoreCase = annotation.ignoreCase();
        this.acceptedValues = Arrays.stream(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .map(value -> ignoreCase ? value.toLowerCase() : value)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        final String candidate = ignoreCase ? value.toString().toLowerCase() : value.toString();
        return acceptedValues.contains(candidate);
    }
}
