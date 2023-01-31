package com.example.store.Interface;
import com.example.store.model.Ropa;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RopaApiInterfaz {
    @GET("/")
    Call<List<Ropa>> getPost();
}
