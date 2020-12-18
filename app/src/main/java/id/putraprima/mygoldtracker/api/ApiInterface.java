package id.putraprima.mygoldtracker.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/emas/api/gold/price")
    Call<TokopediaDatabase<PriceModel>> getPrice();

    @GET("/emas/api/gold/price/history")
    Call<TokopediaDatabase<List<HistoryModel>>> getHistory();
}
