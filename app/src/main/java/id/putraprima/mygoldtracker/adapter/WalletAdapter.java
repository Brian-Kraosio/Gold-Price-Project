package id.putraprima.mygoldtracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.putraprima.mygoldtracker.databinding.BuyItemBinding;
import id.putraprima.mygoldtracker.model.Wallet;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletViewHolder> {
    List<Wallet> list;

    public WalletAdapter() {
    }

    public void setList(List<Wallet> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        BuyItemBinding buyItemBinding = BuyItemBinding.inflate(layoutInflater,parent,false);
        return new WalletAdapter.WalletViewHolder(buyItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder holder, int position) {
        Wallet wallet = list.get(position);
        holder.bind(wallet);
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else{
            return 0;
        }
    }

    public class WalletViewHolder extends RecyclerView.ViewHolder {
        BuyItemBinding binding;
        public WalletViewHolder(BuyItemBinding buyItemBinding) {
            super(buyItemBinding.getRoot());
            binding = buyItemBinding;
        }

        public void bind(Wallet wallet){
            binding.setWallet(wallet);
            binding.executePendingBindings();
        }
    }
}
