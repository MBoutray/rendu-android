package fr.iim.rendukotlinmartindeboutray

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val location = intent.getStringExtra(EXTRA_LOCATION)

        findViewById<TextView>(R.id.location_activity_text).apply { text = getString(R.string.location_activity_text, location) }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(0.0, 0.0)

        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .title("Marker")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}