package com.example.jcretrofit.retrofit

import android.content.Context
import android.util.Log
import com.example.jcretrofit.R
import com.example.jcretrofit.entities.SingleUserResponse
import com.example.jcretrofit.entities.UserInfo
import com.example.jcretrofit.entities.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataBase(
    private val scope: CoroutineScope,
    private val context: Context
) {

    private fun setupRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLoginService(): LoginService{
        return setupRetrofit().create(LoginService::class.java)
    }

    private fun getUserService(): UserService{
        return setupRetrofit().create(UserService::class.java)
    }

    fun login(user: UserInfo, onLogin: () -> Unit, onError: (String) -> Unit){
        val service = getLoginService()

        try {
            scope.launch(Dispatchers.IO) {
                val result = service.loginUser(user)

                if (result.token.isNotEmpty()) onLogin()
            }
        }catch (e: Exception){
            (e as? HttpException)?.let {
                val error = checkError(e)
                onError(error)
            }
        }
    }

    fun register(user: UserInfo, onRegister: (String) -> Unit, onError: (String) -> Unit) {
        val service = getLoginService()

        try {
            scope.launch(Dispatchers.IO) {
                val result = service.registerUser(user)
                Log.i("Chris", "register: ${result.id}")
                if (result.token.isNotEmpty()) onRegister("New id: ${result.id}")
            }
        } catch (e: Exception) {
            (e as? HttpException)?.let {
                val error = checkError(e)
                onError(error)
            }
        }
    }

    fun getSingleUser(onResult: (SingleUserResponse?) -> Unit){
        val service = getUserService()

        scope.launch(Dispatchers.IO) {
            try {
                val result = service.getSingleUser()
                onResult(result)
            } catch (e: Exception) {
                Log.e("Chris", "GetSingleUser ${e.message}")
                onResult(null)
            }
        }
    }

    fun getListUsers(onResult: (UserResponse?) -> Unit){
        val service = getUserService()

        scope.launch(Dispatchers.IO) {
            try {
                val result = service.getListUsers()
                onResult(result)
            } catch (e: Exception) {
                Log.e("Chris", "getListUsers ${e.message}")
                onResult(null)
            }
        }
    }

    private fun checkError(e: HttpException): String = when(e.code()){
        400 -> context.getString(R.string.common_error_server)
        else -> context.getString(R.string.main_error_response)
    }
}