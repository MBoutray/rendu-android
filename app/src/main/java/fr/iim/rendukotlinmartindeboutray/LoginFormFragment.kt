package fr.iim.rendukotlinmartindeboutray

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import java.lang.RuntimeException

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFormFragment : Fragment(), TextWatcher, CompoundButton.OnCheckedChangeListener, TextView.OnEditorActionListener {
    private lateinit var listener: LoginListener
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var agreementInput: CheckBox
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ui
        initialize()
        submitButton.isEnabled = false

        submitButton.setOnClickListener {
            listener.onLogin(emailInput.text.toString())
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
        fun onLogin(email: String)
    }

    private fun initialize() {
        // Initialize attributes
        emailInput = requireView().findViewById<EditText>(R.id.login_email)
        passwordInput = requireView().findViewById<EditText>(R.id.login_password)
        agreementInput = requireView().findViewById<CheckBox>(R.id.login_agreement)
        submitButton = requireView().findViewById<Button>(R.id.btn_login)

        // Add text change listener
        emailInput.addTextChangedListener(this)
        passwordInput.addTextChangedListener(this)

        // Add checkbox change listener
        agreementInput.setOnCheckedChangeListener(this)

        // Add keyboard submit listener
        passwordInput.setOnEditorActionListener(this)
    }

    private fun isValidEmail(): Boolean {
        val email = emailInput.text.toString()
        if(email.isEmpty()) {
            emailInput.error = getString(R.string.login_empty_email_error_message)
            return false
        }
        if (!email.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
            emailInput.error = getString(R.string.login_invalid_email_error_message)
            return false
        }

        return true
    }

    private fun isValidPassword(): Boolean {
        val password = passwordInput.text.toString()

        if(password.isEmpty()) {
            passwordInput.error = getString(R.string.login_empty_password_error_message)
            return false
        }
        if (!password.matches(Regex("(?=.*?[A-Za-z]).*"))) {
            passwordInput.error = getString(R.string.login_password_must_contain_letters_error_message)
            return false
        }
        if (!password.matches(Regex("(?=.*?[0-9]).*"))) {
            passwordInput.error = getString(R.string.login_password_must_contain_a_number_error_message)
            return false
        }
        if (!password.matches(Regex("(?=.*?[#?!@\$%^&*-]).*"))) {
            passwordInput.error = getString(R.string.login_password_must_contain_a_symbol_error_message)
            return false
        }
        if (!password.matches(Regex("^.{8,}\$"))) {
            passwordInput.error = getString(R.string.login_password_must_be_at_least_8_char_long_error_message)
            return false
        }

        return true
    }

    private fun onLoginValid() {
        submitButton.isEnabled = isValidEmail() && isValidPassword() && agreementInput.isChecked
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(p0: Editable?) {
        onLoginValid()
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        onLoginValid()
    }

    override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            submitButton.performClick()
            return true
        }
        return false
    }
}