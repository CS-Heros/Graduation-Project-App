package com.example.graduationproject.presentation.main.result;

import static com.example.graduationproject.common.Utils.getImageAsMultiBodyPart;
import static com.example.graduationproject.common.Utils.setImageUsingGlide;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.graduationproject.databinding.FragmentResultBinding;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListItem;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.MultipartBody;

@AndroidEntryPoint
public class ResultFragment extends Fragment {

    private FragmentResultBinding binding;
    private ResultViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ResultViewModel.class);
//        setDataToViews();
        binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
        MultipartBody.Part img = getImageAsMultiBodyPart(requireActivity(), Uri.parse(ResultFragmentArgs.fromBundle(getArguments()).getImage()), "img");
        viewModel.uploadPhoto(img);
        observeData();
        handleClicks();

    }

    private void observeData() {
        viewModel.diseaseImage.observe(getViewLifecycleOwner(), fakeListResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);
            binding.resultGroup.setVisibility(View.VISIBLE);

            List<FakeListItem> fakeList = fakeListResponse.getData().getDiseases();
            if (fakeList != null && fakeList.size() > 0) {
                setImageUsingGlide(binding.shapeableImageView, fakeList.get(0).getImg());
                binding.aboutTitleTv.setText(fakeList.get(0).getName());
                binding.aboutTextTv.setText(fakeList.get(0).getDescription());
            }
        });
    }

    private void setDataToViews() {
        setImageUsingGlide(binding.shapeableImageView, ResultFragmentArgs.fromBundle(getArguments()).getImage());
    }

    private void handleClicks() {

    }
}
