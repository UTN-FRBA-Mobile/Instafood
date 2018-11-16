package ar.com.instafood.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import ar.com.instafood.application.SocketApplication
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import ar.com.instafood.fragments.MenuFragment
import ar.com.instafood.models.Check
import ar.com.instafood.models.Product
import com.google.gson.GsonBuilder

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
         emitResultToSocket(rawResult.text)
         returnMain(rawResult.text)
    }

    private fun emitResultToSocket(resultFromScanner: String){
        val app  = application as SocketApplication
        val socket = app.socket

        socket?.emit("restaurantSelected", resultFromScanner)
    }

    private fun returnMain(text : String){
        /**
         * TODO: refactorizar ya que se le envia al socket previamente el texto como parametro en el
         * canal
         * */

        var intent_result = Intent()
        intent_result.putExtra("qrText", text)
        setResult(Activity.RESULT_OK, intent_result)
        finish()
    }

    override fun onBackPressed() {

        var intent_result = Intent()
        setResult(Activity.RESULT_CANCELED, intent_result)
        finish()
        //val transaction = supportFragmentManager.beginTransaction()
        //transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        //transaction.replace(R.id.fragment_container, menuFragment, menuFragment.tag).addToBackStack(null)
        //transaction.commitAllowingStateLoss()
    }

}