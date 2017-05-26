package com.github.leonnardo.usecases;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    public PlacesSearchResult[] search() {
        return PlacesApi.nearbySearchQuery(context, coordinates).radius(1000).awaitIgnoreError().results;
    }
}
