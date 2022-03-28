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
import java.lang.RuntimeException

private const val ARG_EMAIL = "email"

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
    private var email: String? = null
    private lateinit var listener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.login_message).text = getString(R.string.login_message, email)
        view.findViewById<Button>(R.id.btn_location).setOnClickListener {
            val location = view.findViewById<EditText>(R.id.location_input).text.toString()

            listener.onLocate(location)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is LocationListener) {
            listener = context
        } else {
            throw RuntimeException("$context needs to be implement the LocationListener interface.")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(email: String) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                }
            }
    }

    interface LocationListener {
        fun onLocate(location: String)
    }
}