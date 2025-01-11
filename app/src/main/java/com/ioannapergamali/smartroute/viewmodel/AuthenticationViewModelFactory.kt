import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioannapergamali.smartroute.viewmodel.AuthenticationViewModel

class AuthenticationViewModelFactory(private val application : Application) :
    ViewModelProvider.Factory
{

    override fun <T : ViewModel> create(modelClass : Class<T>) : T
    {
        if (modelClass.isAssignableFrom(AuthenticationViewModel::class.java))
        {
            return AuthenticationViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
