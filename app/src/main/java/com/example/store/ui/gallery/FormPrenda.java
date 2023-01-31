package com.example.store.ui.gallery;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.store.DBRopaOp;
import com.example.store.R;
import com.example.store.databinding.FragmentRegisterBinding;
import com.example.store.model.Ropa;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormPrenda extends Fragment {
    private ImageView imgRopa;
    private EditText txtCodigo, txtName, txtPrecio, txtStock;
    private Spinner spnierTalla;
    private Button btnSave;
    private DBRopaOp dbRopaOp;
    private Ropa prenda;

    String rutaImagen;
    private FragmentRegisterBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FromPrendaViewModel fromPrendaViewModel = new ViewModelProvider(this).get(FromPrendaViewModel.class);

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        prenda = new Ropa();
        final TextView textView = binding.lblImagen;

        imgRopa = binding.imgRopa;
        txtCodigo =binding.txtCodigo;
        txtName = binding.txtName;
        spnierTalla = binding.spnierTalla;
        txtPrecio = binding.txtPrecio;
        txtStock = binding.txtStock;

        btnSave = root.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(btnGuardar);

        imgRopa.setOnClickListener(cargarIMG);

        dbRopaOp = new DBRopaOp(getContext());

      //  fromPrendaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
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
    View.OnClickListener cargarIMG = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                abrirCamara();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private File crearImagen() throws IOException {

        String nombreImagen = "foto_";
        File directorio;
        directorio = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);

        rutaImagen = imagen.getAbsolutePath();
        return imagen;
    }
    private void abrirCamara() throws IOException {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File imagenArchivo = null;

        try{
            imagenArchivo = crearImagen();

        }catch (IOException ex){
    //        Log.e("Error", ex.toString());
        }


        if(imagenArchivo != null)
        {
            Uri fotoUri = FileProvider.getUriForFile(getContext(), "com.example.myapplication.fileprovider", imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
        }

        startActivityForResult(intent, 1);

    }
     public void onActivityResult(int resquestCode, int resultCode, Intent data) {

        super.onActivityResult(resquestCode, resultCode, data);
        if (resquestCode==1 && resultCode==RESULT_OK){
            imgRopa.setImageURI(Uri.parse(rutaImagen));
        }
        if (resquestCode==1 && resultCode==RESULT_OK){
            imgRopa.setImageURI(Uri.parse(rutaImagen));
        }
    }


    View.OnClickListener btnGuardar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          /*
            String data = "";
            data += imgRopa.getTag() +"";
            data += txtCodigo.getText() +"\n";
            data += txtName.getText() +"\n";
            data += spnierTalla.getSelectedItem() +"";
            data += txtPrecio.getText()+"";
            data += txtStock.getText()+ "";
*/
            prenda.setCodigo(txtCodigo.getText()+"");
            prenda.setNombre(txtName.getText()+"");
            prenda.setTalla(spnierTalla.getSelectedItem()+"");
            prenda.setPrecio(Double.parseDouble(txtPrecio.getText()+""));
            prenda.setStock(Integer.parseInt(txtStock.getText()+ ""));
            prenda.setImagen(imgRopa.getTag() +"21");

            dbRopaOp.addRopa(prenda.getCodigo(),prenda.getNombre(),prenda.getImagen(),prenda.getTalla(),prenda.getPrecio(),prenda.getStock());
           // Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
        }
    };
}