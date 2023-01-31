package com.example.store.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.R;
import com.example.store.adapterdata.DataAdapterRopa;
import com.example.store.databinding.FragmentHomeBinding;
import com.example.store.model.Ropa;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private DataAdapterRopa dataAdapterRopa;
    private Ropa ropa;
    private RecyclerView recyclerViewPrendas;
    ArrayList <Ropa> listaPrendas;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =  new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        recyclerViewPrendas = (RecyclerView) root.findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        ropa = new Ropa();
        listaPrendas = new ArrayList<>();
        getListaprenda();
        dataAdapterRopa = new DataAdapterRopa(listaPrendas);
        recyclerViewPrendas.setAdapter(dataAdapterRopa);
       // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void getListaprenda() {
        ropa.setId(1);
        ropa.setImagen(45+"");
        ropa.setNombre("Raton");
        ropa.setCodigo("uys554");
        ropa.setTalla("GRANDE");
        ropa.setPrecio(45.0);
        ropa.setStock(85);

        listaPrendas.add(new Ropa(ropa.getId(), ropa.getCodigo(), ropa.getNombre(), ropa.getImagen(), ropa.getTalla(),ropa.getPrecio(),ropa.getStock()));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}