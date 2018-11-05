package ar.com.instafood.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import ar.com.instafood.fragments.CheckFragment
import ar.com.instafood.fragments.MainFragment
import ar.com.instafood.fragments.MenuFragment
import ar.com.instafood.fragments.OrderFragment
import kotlinx.android.synthetic.main.activity_menu.*
import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.util.Log
import android.support.v4.app.ActivityCompat
import ar.com.instafood.application.SocketApplication
import io.socket.client.Socket


class MainActivity : AppCompatActivity() {

    private val mainFragment : MainFragment
    private var menuFragment : MenuFragment
    private val checkFragment : CheckFragment
    private val orderFragment : OrderFragment
    private val TAG = "Permisos"
    private val RECORD_REQUEST_CODE = 101
    init {
        mainFragment = MainFragment()
        menuFragment = MenuFragment()
        checkFragment = CheckFragment()
        orderFragment = OrderFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //SetActionBar()
        initialise()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, mainFragment)

        transaction.commit()

        setupPermissions()

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = supportFragmentManager.beginTransaction()
        val app = application as SocketApplication
        app.socket?.on(Socket.EVENT_CONNECT, { args -> run { app.socket?.emit("connectedSocket", "Juan") }})
        if (item.itemId == R.id.navigation_menu) {
            menuFragment = MenuFragment()
        }
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when (item.itemId){
            R.id.navigation_home -> transaction.replace(R.id.fragment_container, mainFragment)
            R.id.navigation_menu -> transaction.replace(R.id.fragment_container, menuFragment, menuFragment.tag).addToBackStack(null)
            R.id.navigation_check -> transaction.replace(R.id.fragment_container, checkFragment)
            R.id.navigation_order -> transaction.replace(R.id.fragment_container, orderFragment)
        }
        transaction.commit()
        true
    }

    private fun initialise() {
        //Todo en algun momento :D
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                val transaction = supportFragmentManager.beginTransaction()
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                transaction.replace(R.id.fragment_container, menuFragment, menuFragment.tag).addToBackStack(null)
                transaction.commitAllowingStateLoss()
            }
        }
    }

    fun switchContent(id: Int, frag: android.support.v4.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.replace(R.id.fragment_container, frag, frag.tag).addToBackStack(null)
        transaction.commit()
    }




    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                RECORD_REQUEST_CODE)
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permisos Denegados")
                } else {
                    Log.i(TAG, "Permisos Aceptados")
                }
            }
        }
    }





}
