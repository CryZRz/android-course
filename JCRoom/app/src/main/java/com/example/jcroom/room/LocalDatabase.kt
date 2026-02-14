package com.example.jcroom.room

import com.example.jcroom.room.entities.Insect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.jcroom.R
import com.example.jcroom.room.entities.User
import com.example.jcroom.room.entities.UserAuth

class LocalDatabase {
    private val insectDao: InsectDao by lazy { RoomApp.db.insectDao() }
    private val userDao: UserDao by lazy { RoomApp.db.userDao() }

    suspend fun getAllInsects(onResult: (List<Insect>) -> Unit) = withContext(Dispatchers.IO) {
        onResult(insectDao.getAllInsects())
    }

    suspend fun addInsects(insects: List<Insect>, onResult: suspend (Boolean) -> Unit){
        var newIds = insectDao.addInsects(insects)
        onResult(newIds.isNotEmpty())
    }

    suspend fun addInsect(insect: Insect, onResult: (Boolean, Int) -> Unit) = withContext(Dispatchers.IO){
        insect.uid = RoomApp.auth.id
        val newId = insectDao.addInsect(insect)
        onResult(newId > 0, R.string.add_save_error)
    }

    suspend fun deleteInsect(insect: Insect, onResult: suspend (Boolean, Int) -> Unit) = withContext(Dispatchers.IO){
        val deletedRows = insectDao.deleteInsect(insect)
        val isDeleted = deletedRows>0
        val msg = if (isDeleted){
            R.string.main_insect_delete_success
        }else{
            R.string.main_insect_delete_error
        }
        onResult(isDeleted, msg)
    }


    //USER
    suspend fun registerUser(user: User, onResult: suspend (Boolean, Int) -> Unit) = withContext(Dispatchers.IO){
        findUser(user.email){ isReg ->
            if (isReg){
                onResult(false, R.string.register_error_is_register)
            }else{
                val newId = userDao.addUser(user)
                onResult(newId > 0, R.string.login_register_error)
            }
        }

    }

    suspend fun login(email: String, pin: Int, onResult: suspend (UserAuth?) -> Unit) = withContext(Dispatchers.IO){
        val user = userDao.login(email, pin)
        onResult(user)
    }

    private suspend fun findUser(email: String, onResult: suspend (Boolean) -> Unit)  = withContext(Dispatchers.IO) {
        val user = userDao.findUserByEmail(email)
        onResult(user != null)
    }

    suspend fun getMyInsects(onResult: (List<Insect>) -> Unit) = withContext(Dispatchers.IO){
        onResult(insectDao.getInsectsByUserId(RoomApp.auth.id))
    }

    suspend fun getInsectsWithFilter(onlyMine: Boolean, onResult: (List<Insect>) -> Unit) = withContext(Dispatchers.IO){
        if (onlyMine){
            onResult(insectDao.getInsectsByUserId(RoomApp.auth.id))
        }else{
            onResult(insectDao.getAllInsects())
        }
    }

}