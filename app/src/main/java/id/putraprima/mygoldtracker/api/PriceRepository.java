package id.putraprima.mygoldtracker.api;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceRepository {
    private ApiInterface apiInterface;
    private MutableLiveData<TokopediaEnvelope<PriceModel>> price = new MutableLiveData<>();

    private static PriceRepository priceRepository;

    public static PriceRepository getInstance(){
        if (priceRepository == null){
            priceRepository = new PriceRepository();
        }
        return  priceRepository;
    }

    public PriceRepository() {
        this.apiInterface = RetrofitServices.createService(ApiInterface.class);
    }

    public MutableLiveData<TokopediaEnvelope<PriceModel>> getPrice(){
        Call<TokopediaEnvelope<PriceModel>> priceData = this.apiInterface.getPrice();
        priceData.enqueue(new Callback<TokopediaEnvelope<PriceModel>>() {
            @Override
            public void onResponse(Call<TokopediaEnvelope<PriceModel>> call, Response<TokopediaEnvelope<PriceModel>> response) {
                price.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TokopediaEnvelope<PriceModel>> call, Throwable t) {
                price.setValue(null);
            }
        });
        return price;
    }
}
