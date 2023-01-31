package com.example.store.Interface;
import com.example.store.model.Ropa;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

// A method that is going to be used to get the data from the server.
public interface RopaApiInterfaz {
   /**
    * // Kotlin
    * @GET("/productos_prueba")
    * fun getRopa(): Call<List<Ropa>>
    * 
    * @return A list of Ropa objects.
    */
    @GET("/productos_prueba")
    Call<List<Ropa>> getRopa();

}
