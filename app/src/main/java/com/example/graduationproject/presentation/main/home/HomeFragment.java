package com.example.graduationproject.presentation.main.home;

import static com.example.graduationproject.common.Utils.toastMe;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.graduationproject.common.SharedPreferenceManger;
import com.example.graduationproject.databinding.FragmentHomeBinding;
import com.example.graduationproject.domian.model.fakeListResponse.FakeListItem;
import com.example.graduationproject.presentation.adapter.home_adapter.HomeRowAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    private HomeRowAdapter historyAdapter = new HomeRowAdapter();

    @Inject
    SharedPreferenceManger sharedPreferenceManger;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        handleClicks();
        setUpRVs();
        observeData();
        binding.loadingPbView.loadingPb.setVisibility(View.VISIBLE);
        viewModel.getFakeListResponse();

    }

    private void setUpRVs() {
        binding.historyRv.setHasFixedSize(true);
        binding.historyRv.setAdapter(historyAdapter);
    }

    private void handleClicks() {
        binding.goToScanBtn.setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToScanFragment();
            Navigation.findNavController(requireView()).navigate(action);
        });

        historyAdapter.setOnDiseaseClickListener(id -> {
            Log.e("TAG", "home: "+id);
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToResultFragment(id, null);
            Navigation.findNavController(requireView()).navigate(action);
        });
    }

    private void observeData() {
        viewModel.fakeListResponseDataLiveData.observe(getViewLifecycleOwner(), fakeListResponse -> {
            binding.loadingPbView.loadingPb.setVisibility(View.GONE);

            if (fakeListResponse.getError().isEmpty()) {
                List<FakeListItem> fakeList = fakeListResponse.getData().getDiseases();
                if (fakeList != null && fakeList.size() > 0) {
                    binding.homeScreenGroup.setVisibility(View.VISIBLE);
                    historyAdapter.submitList(fakeList);
                }
            } else {
                toastMe(requireContext(), fakeListResponse.getError(), false);
            }
        });
    }

}
