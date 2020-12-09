package validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CategoryValidator implements ConstraintValidator<CategoryAttribute, String>{

    List<String> valueList = null;

    @Override
    public boolean isValid(String category, ConstraintValidatorContext context) {
        return valueList.contains(category.toUpperCase());
    }

    @Override
    public void initialize(CategoryAttribute constraintAnnotation) {
        valueList = new ArrayList<String>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();
        Enum[] enumArr = enumClass.getEnumConstants();
        for (Enum enumVal : enumArr) {
            valueList.add(enumVal.toString().toUpperCase());
        }
    }
}