package fr.iim.rendukotlinmartindeboutray

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), LoginFormFragment.LoginListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onLogin(username: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.login_form_fragment, WelcomeFragment.newInstance(username))
            .commitNow()
    }


}