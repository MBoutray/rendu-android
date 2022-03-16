package fr.iim.rendukotlinmartindeboutray

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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