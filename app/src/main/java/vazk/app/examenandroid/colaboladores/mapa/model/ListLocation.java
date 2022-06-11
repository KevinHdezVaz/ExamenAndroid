package vazk.app.examenandroid.colaboladores.mapa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListLocation {

    @SerializedName("data")
    private List<LocationModel> mData;
    //esto es un objeto json
    //de esta manera se declara

    public List<LocationModel> getmData() {

        return mData;

    }
}
