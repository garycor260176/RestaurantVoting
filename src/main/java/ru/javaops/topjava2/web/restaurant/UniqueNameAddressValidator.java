package ru.javaops.topjava2.web.restaurant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.to.RestaurantTo;

@Component
@AllArgsConstructor
public class UniqueNameAddressValidator implements Validator {
    public static final String EXCEPTION_DUPLICATE_NAME_AND_ADDRESS_OF_RESTAURANT = "Restaurant with this name & address already exists";
    private final RestaurantRepository restaurantRepository;
    private final HttpServletRequest request;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return RestaurantTo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        RestaurantTo restaurant = (RestaurantTo) target;
        if (StringUtils.hasText(restaurant.getName())) {
            restaurantRepository.findByNameAndAddressIgnoreCase(restaurant.getName(), restaurant.getAddress())
                    .ifPresent(dbRestaurant ->
                    {
                        if (request.getMethod().equals("PUT")) {
                            int dbId = dbRestaurant.id();

                            if (restaurant.getId() != null && dbId == restaurant.id()) return;

                            String requestURI = request.getRequestURI();
                            if (requestURI.endsWith("/" + dbId))
                                return;
                        }
                        errors.rejectValue("name", "", EXCEPTION_DUPLICATE_NAME_AND_ADDRESS_OF_RESTAURANT);
                    });
        }
    }
}
