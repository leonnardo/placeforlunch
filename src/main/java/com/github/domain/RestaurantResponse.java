package com.github.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Leonnardo on 30/05/17.
 */
@Getter
@AllArgsConstructor
public class RestaurantResponse {

    private String name;

    private String address;

    private Double rating;


}
