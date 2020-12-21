package id.putraprima.mygoldtracker.screen.wallet;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.putraprima.mygoldtracker.api.PriceModel;
import id.putraprima.mygoldtracker.databinding.ItemWalletBinding;
import id.putraprima.mygoldtracker.model.Wallet;

public class WalletHistoryAdapter extends RecyclerView.Adapter<WalletHistoryAdapter.WalletHistoryViewHolder> {
    List<Wallet> list;
    PriceModel priceModel;

    public void setList(List<Wallet> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setPriceModel(PriceModel priceModel) {
        this.priceModel = priceModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WalletHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemWalletBinding itemWalletBinding = ItemWalletBinding.inflate(layoutInflater,parent,false);
        return new WalletHistoryAdapter.WalletHistoryViewHolder(itemWalletBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletHistoryViewHolder holder, int position) {
        Wallet wallet = list.get(position);
        holder.bind(wallet, priceModel);
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else{
            return 0;
        }
    }

    public class WalletHistoryViewHolder extends RecyclerView.ViewHolder {
        ItemWalletBinding binding;
        public WalletHistoryViewHolder(ItemWalletBinding itemWalletBinding) {
            super(itemWalletBinding.getRoot());
            binding = itemWalletBinding;
        }

        public void bind(Wallet wallet, PriceModel priceModel){
            binding.setWallet(wallet);
            binding.setPrice(priceModel);
            binding.executePendingBindings();
        }
    }
}
