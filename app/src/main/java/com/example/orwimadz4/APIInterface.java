package com.example.orwimadz4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    String END_POINT = "api/v1/products.json";

    @GET(END_POINT)
    Call<List<Product>> getProducts(@Query("brand") String brand);
}
