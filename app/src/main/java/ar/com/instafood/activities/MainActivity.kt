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
import android.R.attr.data
import ar.com.instafood.models.Restaurant


class MainActivity : AppCompatActivity() {

    val mainFragment : MainFragment
    var menuFragment : MenuFragment
    val TAG_MENU_FRAGMENT = "MENU_FRAGMENT"
    val checkFragment : CheckFragment
    val orderFragment : OrderFragment
    private val TAG = "Permisos"
    private val RECORD_REQUEST_CODE = 101
    init {
        mainFragment = MainFragment()
        menuFragment = MenuFragment()
        checkFragment = CheckFragment()
        orderFragment = OrderFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupPermissions()
        super.onCreate(savedInstanceState)
        val permission = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
        val permissionFine = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionCoarse = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
        if (permission == PackageManager.PERMISSION_GRANTED && permissionFine == PackageManager.PERMISSION_GRANTED && permissionCoarse == PackageManager.PERMISSION_GRANTED) {
            setContentView(R.layout.activity_menu)
            //SetActionBar()
            initialise()
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, mainFragment)
            transaction.commit()
    }

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = supportFragmentManager.beginTransaction()

        if (item.itemId == R.id.navigation_menu) {
            menuFragment = MenuFragment()
        }
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when (item.itemId){
            R.id.navigation_home -> transaction.replace(R.id.fragment_container, mainFragment).addToBackStack(null)
            R.id.navigation_menu -> transaction.replace(R.id.fragment_container, menuFragment, TAG_MENU_FRAGMENT).addToBackStack(null)
            R.id.navigation_check -> transaction.replace(R.id.fragment_container, checkFragment).addToBackStack(null)
            R.id.navigation_order -> transaction.replace(R.id.fragment_container, orderFragment).addToBackStack(null)
        }
        transaction.commit()
        true
    }

    private fun initialise() {
        //Todo en algun momento :D
        val app = application as SocketApplication
        app.socket?.on(Socket.EVENT_CONNECT, { _ -> run { app.socket?.emit("connectedSocket", "Juan") }})
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                var restaurant = data!!.getExtras().getSerializable("restaurant") as Restaurant
                var args = Bundle()
                args.putSerializable("restaurant",restaurant)
                menuFragment.setArguments(args)
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
        val permission = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
        val permissionFine = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionCoarse = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
        if (permission != PackageManager.PERMISSION_GRANTED || permissionFine != PackageManager.PERMISSION_GRANTED || permissionCoarse != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    override fun onPause() {
        super.onPause()

    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION),
                RECORD_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED
                        || grantResults[1] != PackageManager.PERMISSION_GRANTED
                        || grantResults[2] != PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permisos Denegados")
                } else {
                    Log.i(TAG, "Permisos Aceptados")
                    setContentView(R.layout.activity_menu)
                    //SetActionBar()
                    initialise()
                    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(R.id.fragment_container, mainFragment)
                    transaction.commit()
                }
            }
        }
    }





}
