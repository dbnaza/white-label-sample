package com.example.user.fingerprintapidemo;

import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;


public class FingerPrintAuthHelper extends FingerprintManager.AuthenticationCallback{
    private FingerPrintAuthListener listener;

    public FingerPrintAuthHelper(FingerPrintAuthListener listener) {
        this.listener = listener;
    }

    /**
     * @param manager This is the fingerPrint manager instance
     * @param cryptoObject This contains all the cryptography params(key, clipper etc)
     * */

    public void authenticate(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal = new CancellationSignal();
        manager.authenticate(cryptoObject,
                cancellationSignal,
                0,
                this,
                null);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        listener.onSuccessful();
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        listener.failed();
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        listener.error();
    }

    public interface FingerPrintAuthListener{
        void onSuccessful();
        void failed();
        void error();
    }
}
