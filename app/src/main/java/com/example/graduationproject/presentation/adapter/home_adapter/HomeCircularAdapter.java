package com.example.graduationproject.presentation.adapter.home_adapter;

import static com.example.graduationproject.common.Utils.setImageUsingGlide;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationproject.databinding.ItemDiseaseBinding;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListItem;

public class HomeCircularAdapter extends ListAdapter<FakeListItem, HomeCircularAdapter.HomeAdapterViewHolder> {
    private static final DiffUtilHomeItemCallBack CALL_BACK = new DiffUtilHomeItemCallBack();

    public HomeCircularAdapter() {
        super(CALL_BACK);
    }

    @NonNull
    @Override
    public HomeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDiseaseBinding binding = ItemDiseaseBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new HomeAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapterViewHolder holder, int position) {
        FakeListItem currentItem = getItem(position);
        holder.bind(currentItem);
    }

    static class HomeAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemDiseaseBinding binding;

        public HomeAdapterViewHolder(@NonNull ItemDiseaseBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(FakeListItem currentItem) {
            binding.diseaseNameTv.setText("" + currentItem.getName());
            setImageUsingGlide(binding.diseaseImageIv, currentItem.getImg());

//            setImageUsingGlide(binding.diseaseImageIv, "http://dls-grad.spider-te8.com/MR-Dark.png");
        }
    }

    static class DiffUtilHomeItemCallBack extends DiffUtil.ItemCallback<FakeListItem> {

        @Override
        public boolean areItemsTheSame(@NonNull FakeListItem oldItem, @NonNull FakeListItem newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull FakeListItem oldItem, @NonNull FakeListItem newItem) {
            return false;
        }
    }
}
