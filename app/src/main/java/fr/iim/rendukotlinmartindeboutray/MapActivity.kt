package fr.iim.rendukotlinmartindeboutray

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var address: Address
    private lateinit var returnLocation: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // get the value from input of previous activity
        returnLocation = intent.getStringExtra(EXTRA_LOCATION).toString()

        // Get the geocoder and return the address corresponding to the location value
        address = Geocoder(applicationContext, Locale.FRANCE).getFromLocationName(returnLocation, 1)[0]

        // Setup the map
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(address.latitude, address.longitude)

        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .title(returnLocation)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}