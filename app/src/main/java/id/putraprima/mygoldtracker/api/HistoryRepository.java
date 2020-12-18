package id.putraprima.mygoldtracker.api;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryRepository {
    private ApiInterface apiInterface;
    private MutableLiveData<TokopediaDatabase<List<HistoryModel>>> history = new MutableLiveData<>();

    private static HistoryRepository historyRepository;

    public static HistoryRepository getInstance(){
        if (historyRepository == null){
            historyRepository = new HistoryRepository();
        }
        return  historyRepository;
    }

    public HistoryRepository() {
        this.apiInterface = RetrofitServices.createService(ApiInterface.class);
    }

    public MutableLiveData<TokopediaDatabase<List<HistoryModel>>> getHistory(){
        Call<TokopediaDatabase<List<HistoryModel>>> historyData = this.apiInterface.getHistory();
        historyData.enqueue(new Callback<TokopediaDatabase<List<HistoryModel>>>() {
            @Override
            public void onResponse(Call<TokopediaDatabase<List<HistoryModel>>> call, Response<TokopediaDatabase<List<HistoryModel>>> response) {
                history.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TokopediaDatabase<List<HistoryModel>>> call, Throwable t) {
                history.setValue(null);
            }
        });
        return history;
    }
}
