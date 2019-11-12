package mx.ipn.cic.geo.sesion_network_location

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback, View.OnClickListener {

    private var mMap: GoogleMap? = null
    private var sitios : Array<LatLng?> = arrayOfNulls(6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initGoogleMaps()
    }

    private fun initGoogleMaps() {
        //Obtain the SupportMapFragment and get notified whem the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    private fun moveCameraToPosition(newPosition: LatLng) {
        // Zoom in, animating the camera.
        mMap!!.animateCamera(CameraUpdateFactory.zoomIn())

        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(10f), 2000, null)

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        val cameraPosition = CameraPosition.Builder()
                .target(newPosition)      // Sets the center of the map to Mountain View
                .zoom(14f)                   // Sets the zoom
                .bearing(90f)                // Sets the orientation of the camera to east
                .tilt(30f)                   // Sets the tilt of the camera to 30 degrees
                .build()                   // Creates a CameraPosition from the builder
        mMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        initMarkers()
    }

    private fun initMarkers() {
        sitios[0] = LatLng(48.635957,-1.5136537)
        sitios[1] = LatLng(51.5007758,-0.1247803)
        sitios[2] = LatLng(48.8583701,2.2922926)
        sitios[3] = LatLng(43.722952,10.3944083)
        sitios[4] = LatLng(48.8737917,2.2928388)
        sitios[5] = LatLng(41.8905691,12.4901099)
    }

    override fun onClick(view: View) {
        mMap!!.addMarker(MarkerOptions().position(sitios[Integer.valueOf(view.tag.toString())]!!))
        moveCameraToPosition(sitios[Integer.valueOf(view.tag.toString())]!!)
    }
}
