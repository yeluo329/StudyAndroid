package com.example.lib_network.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_network.flow.requestFlow

import com.example.lib_network.response.BaseResponse
import com.sum.network.callback.IApiErrorCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseViewModel : ViewModel() {


    fun <T> launchFlow(
        errorCall: IApiErrorCallback? = null,
        requestCall: suspend () -> BaseResponse<T>?,
        showLoading: ((Boolean) -> Unit)? = null,
        successBlock: (T?) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            val data = requestFlow(errorBlock = { code, error ->
                if (code == -1001) {
                    errorCall?.onLoginFail(code, error)
                } else {
                    errorCall?.onError(code, error)
                }

            }, requestCall, showLoading)
            successBlock(data)
//        }
        }
    }
}