package id.putraprima.mygoldtracker.screen.wallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.putraprima.mygoldtracker.api.WalletProfitModel;
import id.putraprima.mygoldtracker.databinding.ItemWalletBinding;
import id.putraprima.mygoldtracker.model.Wallet;
import id.putraprima.mygoldtracker.screen.transactions.TransactionsAdapter;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletViewHolder> {
    List<WalletProfitModel> walletList;

    public WalletAdapter() {
    }

    public void setWalletList(List<WalletProfitModel> walletList) {
        this.walletList = walletList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemWalletBinding itemWalletBinding = ItemWalletBinding.inflate(layoutInflater, parent, false);
        return new WalletAdapter.WalletViewHolder(itemWalletBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder holder, int position) {
        WalletProfitModel wallet = walletList.get(position);
        holder.bind(wallet);
    }

    @Override
    public int getItemCount() {
        if (walletList!=null){
            return walletList.size();
        }else{
            return 0;
        }
    }

    public class WalletViewHolder extends RecyclerView.ViewHolder {
        ItemWalletBinding binding;
        public WalletViewHolder(ItemWalletBinding itemWalletBinding) {
            super(itemWalletBinding.getRoot());
            binding = itemWalletBinding;
        }

        public void bind(WalletProfitModel wallet){
            binding.setPrice(wallet);
            binding.executePendingBindings();
        }
    }
}
