package com.github.leonnardo.usecases;

import com.github.domain.RestaurantResponse;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Leonnardo on 26/05/17.
 */
@Service
public class SearchPlacesNearby {

    private final GeoApiContext context;

    // Netshoes coordinates
    private final LatLng coordinates = new LatLng(-23.5707022, -46.6395067);

    public SearchPlacesNearby(@Value("${google.apiKey}") String apiKey) {
        this.context = new GeoApiContext().setApiKey(apiKey);
    }

    public List<RestaurantResponse> search() {
        return convertFromPlacesToJson(Arrays.asList(PlacesApi.nearbySearchQuery(context, coordinates)
                .type(PlaceType.RESTAURANT)
                .radius(1000)
                .awaitIgnoreError()
                .results));
    }


    private List<RestaurantResponse> convertFromPlacesToJson(List<PlacesSearchResult> placesSearchResults) {
        final ArrayList<RestaurantResponse> response = new ArrayList<>();
        placesSearchResults.forEach(place -> response.add(new RestaurantResponse(place.name, place.vicinity, (double) place.rating)));
        response.sort(Comparator.comparing(RestaurantResponse::getRating).reversed());
        return response;
    }
}
