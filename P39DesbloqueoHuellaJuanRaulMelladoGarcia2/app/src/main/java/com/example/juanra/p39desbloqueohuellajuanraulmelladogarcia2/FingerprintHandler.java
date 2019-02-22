package com.example.juanra.p39desbloqueohuellajuanraulmelladogarcia2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FingerprintHandler extends FingerprintManager.AuthenticationCallback{

    private CancellationSignal cancelar;
    private Context context;
    private TextView tvMensajem;
    private ImageView ivOkm;

    public FingerprintHandler(Context mContext) {
        context = mContext;
    }
    //este metodo inicia el proceso de auteticacion de la huella
    public void autenticar(FingerprintManager manager,
                           FingerprintManager.CryptoObject cryptoObject) {
        cancelar = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.USE_FINGERPRINT)

                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancelar, 0, this, null);
    }

    //metodo en caso de que exista un error al autenticar la huella
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Toast.makeText(context, "Error de autenticación" + errString, Toast.LENGTH_LONG).show();
    }
    //metodo en caso de que falle la autenticacion
    public void onAuthenticationFailed() {
        Toast.makeText(context, "Autenticación fallida",
                Toast.LENGTH_LONG).show();
    }
    //este metodo es llamado cuando existe un error no fatal en la autenticacion de la huella
    //se muestra la ayuda que arroja el mismo metodo
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(context, "Ayuda\n" + helpString, Toast.LENGTH_LONG).show();
    }
    public void onAuthenticationSucceeded(
            FingerprintManager.AuthenticationResult result) {
        tvMensajem = (TextView) ((Activity) context).findViewById(R.id.tvMensaje);
        tvMensajem.setText("Huella reconocida con exito");
        ivOkm = (ImageView) ((Activity) context).findViewById(R.id.ivOk);
        ivOkm.setVisibility(View.VISIBLE);
    }
}
