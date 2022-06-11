package vazk.app.examenandroid.colaboladores.mapa.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientMap {

    public static final String base_url ="https://mocki.io";

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

    if(retrofit== null){
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    return retrofit;

}

}
