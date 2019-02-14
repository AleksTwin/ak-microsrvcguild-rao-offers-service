package offers.model;

import io.swagger.annotations.ApiModel;

/**
 * @author aleksey.kovalenko@accenture.com
 */
@ApiModel
public class OfferDTO {

    private String play;
    private Integer discount;
    private Integer pointsCost;
    private Integer pointsOwned;

    public OfferDTO(String play, Integer discount, Integer pointsCost, Integer pointsOwned) {
        this.play = play;
        this.discount = discount;
        this.pointsCost = pointsCost;
        this.pointsOwned = pointsOwned;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getPointsCost() {
        return pointsCost;
    }

    public void setPointsCost(Integer pointsCost) {
        this.pointsCost = pointsCost;
    }

    public Integer getPointsOwned() {
        return pointsOwned;
    }

    public void setPointsOwned(Integer pointsOwned) {
        this.pointsOwned = pointsOwned;
    }
}
