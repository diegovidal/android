package com.dvidal.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Constante
    private static int GROUP_PERMISSION_CAMERA = 1;

    private static int CAMERA_PHOTO = 2;
    private static int CAMERA_LIBRARY = 3;

    // Declarações
    private ImageView imgFoto;

    private ImageButton btnFoto;
    private ImageButton btnGaleria;

    private TextView txtMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instâncias
        imgFoto = (ImageView) findViewById(R.id.imgFoto);

        btnFoto = (ImageButton) findViewById(R.id.btnFoto);
        btnGaleria = (ImageButton) findViewById(R.id.btnGaleria);

        txtMensagem = (TextView) findViewById(R.id.txtMensagens);

        // Listeners
        btnFoto.setOnClickListener(this);
        btnGaleria.setOnClickListener(this);

        // Permission
        solicitarPermissao();
    }

    private void solicitarPermissao(){

        int checaPermissao = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (checaPermissao == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, GROUP_PERMISSION_CAMERA);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnFoto:
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, CAMERA_PHOTO);
                break;

            case R.id.btnGaleria:
                Intent libraryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                libraryIntent.setType("image/*");
                startActivityForResult(libraryIntent, CAMERA_LIBRARY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){

            // CAMERA
            if (requestCode == CAMERA_PHOTO){
                Bundle pacote = data.getExtras();
                if (pacote != null){
                    Bitmap imagem = (Bitmap) pacote.get("data");
                    imgFoto.setImageBitmap(imagem);
                    txtMensagem.setText("Foto da Câmera colocada com sucesso!");
            }
                else{
                    txtMensagem.setText("Pacote Vazio!");
                }

            }
            else if (requestCode == CAMERA_LIBRARY){
                imgFoto.setImageURI(data.getData());
                txtMensagem.setText("Foto da Galeria colocada com sucesso!");
            }
        }

        else{

            txtMensagem.setText("Dados Vazios!");
        }


    }
}
