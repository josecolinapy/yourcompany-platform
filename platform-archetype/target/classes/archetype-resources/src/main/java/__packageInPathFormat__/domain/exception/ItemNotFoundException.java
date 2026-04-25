package ${package}.domain.exception;

import com.yourcompany.platform.common.errors.exception.ResourceNotFoundException;

public class ItemNotFoundException extends ResourceNotFoundException {

    public ItemNotFoundException(final String message) {
        super(message);
    }
}
