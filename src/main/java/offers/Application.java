package offers;

import offers.service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Autowired
    private OffersService service;

    public Application(OffersService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}