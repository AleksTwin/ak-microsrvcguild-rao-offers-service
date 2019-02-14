package offers.controller;

import java.util.List;

import io.swagger.annotations.Api;
import offers.model.OfferDTO;
import offers.service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aleksey.kovalenko@accenture.com
 */
@RestController
@RequestMapping("/v1/offers")
@Api(tags = "Offers")
public class OffersController {

    @Autowired
    private OffersService service;


    @GetMapping(produces = "application/json")
    public List<OfferDTO> getOffers(@RequestParam(value = "uuid", required = false) String uuid) {
        return service.getOffers(uuid);
    }

}
