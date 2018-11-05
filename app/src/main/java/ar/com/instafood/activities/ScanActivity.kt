package ar.com.instafood.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import ar.com.instafood.fragments.MenuFragment

/**
 * Created by mnavarro on 24/10/2018.
 */
class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    private var menuFragment = MenuFragment()

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)                // Set the scanner view as the content view
    }

    public override fun onResume() {
        super.onResume()

       mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
       mScannerView!!.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()           // Stop camera on pause
    }

     override fun handleResult(rawResult: Result) {

        Log.i("SCAN RESULT", rawResult.text)
        onBackPressed()

    }


}