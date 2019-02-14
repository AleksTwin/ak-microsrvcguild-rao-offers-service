package offers.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import offers.model.DiscountDto;
import offers.model.LoyaltyDTO;
import offers.model.OfferDTO;
import offers.restclient.OffersRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author aleksey.kovalenko@accenture.com
 */
@Service
public class OffersService {

    @Autowired
    private OffersRestClient offersRestClient;

    public List<OfferDTO> getOffers(String uuid) {
        List<OfferDTO> offers = getGenericDiscountOffers();
        if (uuid != null) {
            offers.addAll(getPrivilegedDiscountffers(uuid));
        }

        return offers;
    }

    private static List<OfferDTO> getGenericDiscountOffers() {
        List<OfferDTO> offers = Lists.newArrayList();
        offers.add(new OfferDTO("Theater Play 1", 2, 0, 0));
        offers.add(new OfferDTO("Theater Play 2", 2, 0, 0));
        offers.add(new OfferDTO("Theater Play 3", 2, 0, 0));
        offers.add(new OfferDTO("Theater Play 4", 2, 0, 0));
        offers.add(new OfferDTO("Theater Play 5", 2, 0, 0));

        return offers;
    }

    private List<OfferDTO> getPrivilegedDiscountffers(String uuid) {
        List<OfferDTO> offers = Lists.newArrayList();

        // Fetch user loyalty data
        Optional<LoyaltyDTO> userLoyaltyData = Optional.ofNullable(offersRestClient.getUserLoyaltyData(uuid));

        // Fetch privileged user discounts
        userLoyaltyData.ifPresent((uld) -> {
            List<DiscountDto> discountsList = offersRestClient.getUserAvailableDiscounts(uld.getLoyaltyPoints());
            List<OfferDTO> privilegedOffers = discountsList.stream()
                    .map((discount -> new OfferDTO(discount.getPlay(), discount.getDiscount(), discount.getPoints(), uld.getLoyaltyPoints())))
                    .collect(Collectors.toList());
            offers.addAll(privilegedOffers);
        });

        return offers;
    }
}
