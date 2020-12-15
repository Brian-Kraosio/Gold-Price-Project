package id.putraprima.mygoldtracker.screen.transactions;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.putraprima.mygoldtracker.api.BuyModel;
import id.putraprima.mygoldtracker.databinding.TransactionItemBinding;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder> {
    List<BuyModel> list = new ArrayList<>();

    public TransactionsAdapter() {
    }

    public void setList(List<BuyModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TransactionItemBinding transactionItemBinding = TransactionItemBinding.inflate(layoutInflater, parent, false);
        return new TransactionViewHolder(transactionItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        BuyModel buyModel = list.get(position);
        holder.bind(buyModel);
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }else {
            return 0;
        }
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        TransactionItemBinding transactionItemBinding;

        public TransactionViewHolder(TransactionItemBinding transactionItemBinding) {
            super(transactionItemBinding.getRoot());
            this.transactionItemBinding = transactionItemBinding;
        }

        public void bind(BuyModel buyModel){
            transactionItemBinding.setPrice(buyModel);
            transactionItemBinding.executePendingBindings();
        }
    }


}
