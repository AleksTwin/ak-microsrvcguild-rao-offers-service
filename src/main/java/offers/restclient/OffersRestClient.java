package offers.restclient;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.sun.javafx.util.Logging;
import offers.model.DiscountDto;
import offers.model.DiscountsDto;
import offers.model.LoyaltyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author aleksey.kovalenko@accenture.com
 */
@Component
public class OffersRestClient {

    private final Logger logger = Logger.getLogger(Logging.class.getName());

    private final String loyaltyServiceUrl;
    private final String discountServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public OffersRestClient(@Value("${loyalty-service.host}") String loyaltyHost,
                            @Value("${loyalty-service.uri}") String loyaltyUri,
                            @Value("${discount-service.host}") String discountHost,
                            @Value("${discount-service.uri}") String discountUri) {
        loyaltyServiceUrl = loyaltyHost + loyaltyUri;
        discountServiceUrl = discountHost + discountUri;
    }

    public LoyaltyDTO getUserLoyaltyData(String uuid) {
        try {
            ResponseEntity<LoyaltyDTO> responseEntity = restTemplate.getForEntity(loyaltyServiceUrl + "/" + uuid, LoyaltyDTO.class);
            return responseEntity.getStatusCode().equals(HttpStatus.OK) ? responseEntity.getBody() : null;
        } catch (RuntimeException re) {
            logger.warning("Connection error or 'Loyalty service' is not available");
            return null;
        }
    }

    public List<DiscountDto> getUserAvailableDiscounts(Integer userPoints) {
        try {
            ResponseEntity<DiscountsDto> responseEntity = restTemplate.getForEntity(discountServiceUrl + "?belowPoints=" + userPoints, DiscountsDto.class);
            return responseEntity.getStatusCode().equals(HttpStatus.OK) ? responseEntity.getBody().getDiscounts() : Collections.emptyList();
        } catch (RuntimeException re) {
            logger.warning("Connection error or 'Discount service' is not available");
            return Collections.emptyList();
        }
    }
}
