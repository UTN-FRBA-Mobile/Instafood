package ar.com.instafood.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import ar.com.instafood.application.SocketApplication
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import ar.com.instafood.fragments.MenuFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import android.graphics.Bitmap
import android.widget.ImageView

/**
 * Created by mnavarro on 24/10/2018.
 */
class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    private var menuFragment = MenuFragment()
    internal var bitmap: Bitmap? = null
    private var iv: ImageView? = null

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)                // Set the scanner view as the content view
        convertToImage("lo que sea");
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

    private fun convertToImage(text: String){
            bitmap = TextToImageEncode(text)
            iv!!.setImageBitmap(bitmap)

    }


    @Throws(WriterException::class)
    private fun TextToImageEncode(Value: String): Bitmap? {
        val bitMatrix: BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            )

        } catch (Illegalargumentexception: IllegalArgumentException) {

            return null
        }

        val bitMatrixWidth = bitMatrix.getWidth()

        val bitMatrixHeight = bitMatrix.getHeight()

        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)

        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth

            for (x in 0 until bitMatrixWidth) {

                pixels[offset + x] = if (bitMatrix.get(x, y))
                    resources.getColor(R.color.abc_hint_foreground_material_dark)
                else
                    resources.getColor(R.color.white)
            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }

    companion object {

        val QRcodeWidth = 500

        private val IMAGE_DIRECTORY = "/QRcodeDemonuts"
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