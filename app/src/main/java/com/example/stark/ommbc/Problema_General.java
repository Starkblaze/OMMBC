package com.example.stark.ommbc;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.ImageFormat;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.*;
import java.io.*;
import java.util.*;
import android.util.*;
import android.content.pm.*;
import android.content.*;
import android.util.Log;
import android.Manifest;
import java.text.*;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class Problema_General extends AppCompatActivity{

    int fav = 0;
    private ImageView imageView = null;
    private Bitmap bitmap = null;
    private File destination = null;
    private InputStream inputStreamImg = null;
    private String imgPath = null;
    private final int REQUEST_CAMERA = 0, SELECT_FILE = 1,REQUEST_GALLERY_READ=2,REQUEST_GALLERY_WRITE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema__general);
        final int numero = getIntent().getIntExtra("Numero", 0);
        final String text = getIntent().getStringExtra("Texto");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Problema "+numero);
        TextView espacio_texto = (TextView)findViewById(R.id.espacio_texto);
        espacio_texto.setText(text);
        FloatingActionButton help = (FloatingActionButton)findViewById(R.id.help);
        FloatingActionButton send = (FloatingActionButton)findViewById(R.id.send);
        final FloatingActionButton favorite = (FloatingActionButton)findViewById(R.id.favorite);

        imageView = (ImageView)findViewById(R.id.imageView);

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fav==0) {
                    favorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite1));
                    fav=1;
                }
                else {
                    favorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite0));
                    fav=0;
                }
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","correo@ejemplo.mx", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ayuda problema "+numero);
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Necesito ayuda con el problema "+numero);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }

        });
    }

    // Select image from camera and gallery
    /*private void selectImage() {
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            final CharSequence[] options = {"Tomar Foto", "Seleccionar desde Galeria", "Cancelar"};
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
            builder.setTitle("Seleccionar Opcion");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (options[item].equals("Tomar Foto")) {
                        dialog.dismiss();
                        dispatchTakePictureIntent();
                    } else if (options[item].equals("Seleccionar desde Galeria")) {
                        dialog.dismiss();
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                    } else if (options[item].equals("Cancelar")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error catch", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }*/

    private void selectImage() {
        try {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            //int galleryPermissionCheck = ContextCompat.checkSelfPermission(Problema_General.this, Manifest.permission.READ_EXTERNAL_STORAGE);
            //int galleryPermissionCheck2 = ContextCompat.checkSelfPermission(Problema_General.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if ( permissionCheck == PERMISSION_GRANTED) /*&& galleryPermissionCheck == PERMISSION_GRANTED && galleryPermissionCheck2 == PERMISSION_GRANTED*/ {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery","Cancel"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, REQUEST_CAMERA);
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, SELECT_FILE);

                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else{
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CAMERA);
                /*ActivityCompat.requestPermissions(Problema_General.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_GALLERY_READ);
                ActivityCompat.requestPermissions(Problema_General.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_GALLERY_WRITE);*/
            }
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error without check", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (requestCode == REQUEST_CAMERA) {
            try {
                Uri selectedImage = data.getData();
                bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                Log.e("Activity", "Pick from Camera::>>> ");

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                destination = new File(Environment.getExternalStorageDirectory() + "/" +
                        getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imgPath = destination.getAbsolutePath();
                imageView.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == SELECT_FILE ) {
            if(data != null){
                Uri selectedImage = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                    Log.e("Activity", "Pick from Gallery::>>> ");

                    imgPath = getRealPathFromURI(selectedImage);
                    destination = new File(imgPath.toString());
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                this.closeContextMenu();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

}
