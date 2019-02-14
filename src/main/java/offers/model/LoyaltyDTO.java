package offers.model;

import io.swagger.annotations.ApiModel;

/**
 * @author aleksey.kovalenko@accenture.com
 */
@ApiModel
public class LoyaltyDTO {

    private String uuid;
    private Integer loyaltyPoints;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
