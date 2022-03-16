package fr.iim.rendukotlinmartindeboutray

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.RuntimeException

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFormFragment : Fragment() {
    private lateinit var listener: LoginListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            val username = view.findViewById<EditText>(R.id.login_username).text.toString()
            val email = view.findViewById<EditText>(R.id.login_email).text.toString()
            val password = view.findViewById<EditText>(R.id.login_password).text.toString()

            // Guard clauses to verify the content of the inputs
            if (username.isEmpty()) {
                Toast.makeText(context, getString(R.string.login_empty_username_error_message), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                Toast.makeText(context, getString(R.string.login_empty_email_error_message), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!email.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                Toast.makeText(context, getString(R.string.login_invalid_email_error_message), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(context, getString(R.string.login_empty_password_error_message), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Login the user if all the values are valid
            listener.onLogin(username)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is LoginListener) {
            listener = context
        } else {
            throw RuntimeException("$context needs to be implement the LoginListener interface.")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFormFragment()
    }

    interface LoginListener {
        fun onLogin(username: String)
    }
}