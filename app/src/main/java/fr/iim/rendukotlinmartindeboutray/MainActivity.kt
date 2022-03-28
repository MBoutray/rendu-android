package fr.iim.rendukotlinmartindeboutray

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

const val EXTRA_LOCATION = "fr.iim.rendukotlinmartindeboutray.LOCATION"

class MainActivity : AppCompatActivity(), LoginFormFragment.LoginListener, WelcomeFragment.LocationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onLogin(email: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.login_form_fragment, WelcomeFragment.newInstance(email))
            .commitNow()
    }

    override fun onLocate(location: String) {
        val intent = Intent(this, MapActivity::class.java).apply {
            putExtra(EXTRA_LOCATION, location)
        }
        startActivity(intent)
    }
}