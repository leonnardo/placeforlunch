package com.github.leonnardo.gateway;

import com.github.domain.RestaurantResponse;
import com.github.leonnardo.usecases.SearchPlacesNearby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Leonnardo on 26/05/17.
 */
@RestController
public class HomeController {

    public SearchPlacesNearby searchPlacesNearby;

    @Autowired
    public HomeController(final SearchPlacesNearby searchPlacesNearby) {
        this.searchPlacesNearby = searchPlacesNearby;
    }

    @RequestMapping("/")
    public List<RestaurantResponse> home(Integer distance, Double lat, Double lng) {
        return searchPlacesNearby.search(distance, lat, lng);
    }
}
