package com.example.graduationproject.presentation.main.profile;

import static com.example.graduationproject.common.Utils.setImageUsingGlide;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.graduationproject.R;
import com.example.graduationproject.databinding.FragmentProfileBinding;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListItem;
import com.example.graduationproject.domian.model.user.User;
import com.example.graduationproject.presentation.adapter.home_adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;
    private HomeAdapter profileAdapter = new HomeAdapter();
    private List<FakeListItem> fakeListItems = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        handleClicks();
        observeData();

        binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
        viewModel.getUser();
        setFakeData();
    }

    private void handleClicks() {

    }

    private void observeData() {
        viewModel.userData.observe(getViewLifecycleOwner(), userResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);
            binding.profileGroup.setVisibility(View.VISIBLE);

            User user = userResponse.getData();
            setImageUsingGlide(binding.shapeableImageView, user.getAvatar());
            binding.nameTv.setText("" + user.getName());

        });
    }

    private void setFakeData() {
        fakeListItems.add(new FakeListItem("Covid", "Lethal disease", "http://dls-grad.spider-te8.com/laravel_api.png"));
        fakeListItems.add(new FakeListItem("Covid", "Lethal disease", "http://dls-grad.spider-te8.com/laravel_api.png"));
        fakeListItems.add(new FakeListItem("Covid", "Lethal disease", "http://dls-grad.spider-te8.com/laravel_api.png"));
        fakeListItems.add(new FakeListItem("Covid", "Lethal disease", "http://dls-grad.spider-te8.com/laravel_api.png"));

        binding.historyRv.setAdapter(profileAdapter);
        profileAdapter.submitList(fakeListItems);
    }
}
