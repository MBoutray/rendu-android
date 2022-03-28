package fr.iim.rendukotlinmartindeboutray

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val location = intent.getStringExtra(EXTRA_LOCATION)

        val textView = findViewById<TextView>(R.id.location_activity_text).apply { text = location }
    }
}