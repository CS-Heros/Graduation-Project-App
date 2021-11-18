package com.example.graduationproject.presentation.adapter.home_adapter;

import static com.example.graduationproject.common.Utils.setImageUsingGlide;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationproject.databinding.RowDiseaseBinding;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListItem;

public class HomeRowAdapter extends ListAdapter<FakeListItem, HomeRowAdapter.HomeAdapterViewHolder> {
    private static final DiffUtilHomeItemCallBack CALL_BACK = new DiffUtilHomeItemCallBack();

    public HomeRowAdapter() {
        super(CALL_BACK);
    }

    @NonNull
    @Override
    public HomeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowDiseaseBinding binding = RowDiseaseBinding.inflate(
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
        RowDiseaseBinding binding;

        public HomeAdapterViewHolder(@NonNull RowDiseaseBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(FakeListItem currentItem) {
            binding.diseaseNameTv.setText("" + currentItem.getName());
            binding.diseaseDescriptionTv.setText("" + currentItem.getDescription());
            setImageUsingGlide(binding.diseaseImageIv, currentItem.getImg());
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
