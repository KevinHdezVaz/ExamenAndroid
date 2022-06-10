package vazk.app.examenandroid.colaboladores.mapa.network;

import retrofit2.Call;
import retrofit2.http.GET;
import vazk.app.examenandroid.colaboladores.mapa.model.ListLocation;

public interface ApiServiceMap {

    @GET("/v1/801f4111-ed01-4fb2-a7b9-a69f9e41834f")
    Call<ListLocation>  getallLocations();
}
