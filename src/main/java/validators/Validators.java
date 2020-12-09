package validators;

import model.Location;
import model.Weapon;
import org.jboss.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import javax.validation.Validator;

public class Validators {
     public Weapon validWeapon(Weapon weapon) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();;
        Set<ConstraintViolation<Weapon>> constraintViolations;
         constraintViolations = validator.validate(weapon);

        Logger log = Logger.getLogger(Weapon.class);
        for (ConstraintViolation<Weapon> violation : constraintViolations) {
            log.error(violation.getMessage());
            System.out.println(violation.getInvalidValue() + " " + violation.getConstraintDescriptor());
        }
        if (constraintViolations.size() == 0) {
            return weapon;
        }
        else {
            return null;
        }
    }

    public Location validLocation(Location location) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<Location>> constraintViolations = validator.validate(location);
        Logger log = Logger.getLogger(Location.class);
        for (ConstraintViolation<Location> violation : constraintViolations) {
            log.error(violation.getMessage());
            System.out.println(violation.getInvalidValue() + " " + violation.getConstraintDescriptor());
        }
        if (constraintViolations.size() == 0) {
            return location;
        }
        else {
            return null;
        }
    }
}
