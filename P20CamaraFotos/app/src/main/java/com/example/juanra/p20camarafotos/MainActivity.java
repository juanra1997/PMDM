package com.example.juanra.p20camarafotos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView ivVisorm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivVisorm=(ImageView) findViewById(R.id.imageView);

        //Preguntar por los permisos de guardar en memoria y uso de la camara

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this,  Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

    }
    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Crear un nombre de imagen
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "fotoP20" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int RESPUESTA_HACER_FOTO=1;

    public void HacerFotoClick(View view){

        //|^| Metodo para hacer foto al pulsar el boton |^|

        //Se cierra de forma momentánea el actual activity para mostrar lo que se ve en la camara

        Intent fotoIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(fotoIntent.resolveActivity(getPackageManager())!=null){
            //Crear un fichero para la foto
            File ficheroFoto=null;
            //Recuperar el nombre y la ruta del fichero
            try {
                ficheroFoto = createImageFile();
            }catch (IOException e){
                Toast.makeText(this,"Algo ha salido mal :(", Toast.LENGTH_SHORT).show();
            }

            if(ficheroFoto!=null){
                Uri fotoUri=FileProvider.getUriForFile(this, "com.example.android.fileprovider", ficheroFoto);
                fotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(fotoIntent, RESPUESTA_HACER_FOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESPUESTA_HACER_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivVisorm.setImageBitmap(imageBitmap);
        }
    }
}
