package com.example.store.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.DBRopaOp;
import com.example.store.R;
import com.example.store.adapterdata.DataAdapterRopa;
import com.example.store.databinding.FragmentHomeBinding;
import com.example.store.model.Ropa;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private DataAdapterRopa dataAdapterRopa;
    private Ropa ropa;
    private RecyclerView recyclerViewPrendas;
    private Button tbnSearch;
    private EditText txtSeach;
    ArrayList <Ropa> listaPrendas;

    private DBRopaOp dbRopaOp;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =  new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        txtSeach = binding.txtSeach;
        tbnSearch = (Button) root.findViewById(R.id.tbnSearch);

        tbnSearch.setOnClickListener(btnBuscar);

        recyclerViewPrendas = (RecyclerView) root.findViewById(R.id.recyclerViewPrendas);
        recyclerViewPrendas.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        ropa = new Ropa();
        listaPrendas = new ArrayList<>();
        dbRopaOp = new DBRopaOp(getContext());
        dbRopaOp.open();
        getListaprenda();
        dataAdapterRopa = new DataAdapterRopa(listaPrendas);
        recyclerViewPrendas.setAdapter(dataAdapterRopa);
       // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    View.OnClickListener btnBuscar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String keyWord =""+ txtSeach.getText();
            try {
                listaPrendas.clear();
                dataAdapterRopa = new DataAdapterRopa(listaPrendas);
                recyclerViewPrendas.setAdapter(dataAdapterRopa);

                Cursor cursor = dbRopaOp.getSearch(keyWord);
                while (cursor.moveToNext()) {
                    // ropa.setImagen( cursor.getInt(cursor.getColumnIndex("id")+"");
                    ropa.setId(R.mipmap.clothes);
                    ropa.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                    ropa.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
                    ropa.setTalla(cursor.getString(cursor.getColumnIndex("talla")));
                    ropa.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                    ropa.setStock(cursor.getInt(cursor.getColumnIndex("stock")));
                    listaPrendas.add(new Ropa(ropa.getId(), ropa.getCodigo(), ropa.getNombre(), ropa.getImagen(), ropa.getTalla(), ropa.getPrecio(), ropa.getStock()));
                }
                cursor.close();
            }catch (Exception e){
                Toast.makeText(getContext(), "Sin registos "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    };

    private void getListaprenda(){
        try {
            Cursor cursor = dbRopaOp.getAllRopa();
            while (cursor.moveToNext()) {
                // ropa.setImagen( cursor.getInt(cursor.getColumnIndex("id")+"");
                ropa.setId(R.mipmap.clothes);
                ropa.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                ropa.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
                ropa.setTalla(cursor.getString(cursor.getColumnIndex("talla")));
                ropa.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                ropa.setStock(cursor.getInt(cursor.getColumnIndex("stock")));
                listaPrendas.add(new Ropa(ropa.getId(), ropa.getCodigo(), ropa.getNombre(), ropa.getImagen(), ropa.getTalla(), ropa.getPrecio(), ropa.getStock()));
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getContext(), "Sin registos "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbRopaOp.close();
    }

    @Override
    public void onPause() {
        super.onPause();
        dbRopaOp.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        dbRopaOp.open();
    }
}