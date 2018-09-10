package com.example.irfan.squarecamera;

import android.app.VoiceInteractor;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CameradActivity extends AppCompatActivity {

    private CameraView camerad;
    private CameraKitEventListener cameradListener;
    private Button btnCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camerad);
        final Intent intent = getIntent();
        cameradListener = new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                byte[] picture = cameraKitImage.getJpeg();
                Bitmap result = BitmapFactory.decodeByteArray(picture, 0, picture.length);
                result = Bitmap.createScaledBitmap(result, 512,512, true);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                String myBase64Image = encodeToBase64(result, Bitmap.CompressFormat.JPEG, 100);
                final ApiInterface api = Server.getclient().create(ApiInterface.class);
                Log.d("test", "onImage: "+myBase64Image);
                JSONObject paramObject = new JSONObject();
                Call<ResponseApi> kirim =api.kirim(intent.getStringExtra("nrp"),"data:image/jpeg;base64,"+myBase64Image);
                kirim.enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        Toast.makeText(CameradActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(CameradActivity.this, "anjir", Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        };

        camerad = (CameraView) findViewById(R.id.camerad);
        camerad.addCameraKitListener(cameradListener);

        btnCapture = (Button) findViewById(R.id.btn_capture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camerad.captureImage();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        camerad.start();
    }

    @Override
    protected void onPause() {
        camerad.stop();
        super.onPause();
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

}
