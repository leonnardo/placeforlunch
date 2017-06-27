package com.github.leonnardo.usecases;

import com.github.domain.RestaurantResponse;
import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Leonnardo on 26/05/17.
 */
@Service
public class SearchPlacesNearby {

    private final GeoApiContext context;

    public SearchPlacesNearby(@Value("${google.apiKey}") String apiKey) {
        this.context = new GeoApiContext().setApiKey(apiKey);
    }

    public List<RestaurantResponse> search(final Integer distance, final Double lat, final Double lng) {
        final LatLng coordinates = new LatLng(lat, lng);

        PlacesSearchResponse res;
        String pageToken = "";
        final ArrayList<PlacesSearchResult> placesSearchResults = new ArrayList<>();
        do {
            NearbySearchRequest searchRequest = PlacesApi.nearbySearchQuery(context, coordinates)
                    .type(PlaceType.RESTAURANT)
                    .rankby(RankBy.DISTANCE)
                    .pageToken(pageToken);

            res = searchRequest.awaitIgnoreError();
            placesSearchResults.addAll(Arrays.asList(res.results));
            pageToken = Optional.ofNullable(res.nextPageToken).orElse("");

        } while (res.nextPageToken != null);

        return convertFromPlacesToJson(placesSearchResults);
    }


    private List<RestaurantResponse> convertFromPlacesToJson(List<PlacesSearchResult> placesSearchResults) {
        final ArrayList<RestaurantResponse> response = new ArrayList<>();
        placesSearchResults.forEach(place -> response.add(new RestaurantResponse(place.name, place.vicinity, (double) place.rating)));
        //response.sort(Comparator.comparing(RestaurantResponse::getRating).reversed());
        return response;
    }
}
