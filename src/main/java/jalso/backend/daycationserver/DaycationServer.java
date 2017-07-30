package jalso.backend.daycationserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jalso.backend.daycationserver.service.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "Content-Type" )
@RequestMapping("/v1")
public class DaycationServer {
  @Autowired DaycationService dbService;

  @RequestMapping(value = "/seed", method = RequestMethod.GET)
  public String seedData() {
    String[] users = {"rose", "trob", "david", "daniel", "ray", "james"};
    String[] passwords = {"rose123", "trob123", "david123", "daniel123", "ray123", "james123"};

    for (int i = 0; i < 6; i++) {
      dbService.signUp(users[i], passwords[i]);
    }

    return "Database is seeded!";
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public long signUp(@RequestParam("user") final String user, @RequestParam("pass") final String pass) {
    return dbService.signUp(user, pass);
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public List<Map<String,Object>> logIn(@RequestParam("user") final String user, @RequestParam("pass") final String pass) {
    System.out.println(user);
    System.out.println(pass);
    return dbService.logIn(user, pass);
  }

  @Autowired GooglePlacesService googleService;
  @RequestMapping(value="/getevents", method = RequestMethod.POST)
  public ArrayList<ArrayList<String>> googleEvents(@RequestParam("events") final String events, @RequestParam("food") final String food, @RequestParam("transportation") final String transportation, @RequestParam("money") final int money, @RequestParam("location") final String location) {
    System.out.println("inside request mapping for google event");
    return googleService.googleEvents(events, food, transportation, money, location);
  }
}