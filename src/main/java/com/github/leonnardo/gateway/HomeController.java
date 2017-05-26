package com.github.leonnardo.gateway;

import com.github.leonnardo.usecases.SearchPlacesNearby;
import com.google.maps.model.PlacesSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PlacesSearchResult[] home() {
        return searchPlacesNearby.search();
    }
}
