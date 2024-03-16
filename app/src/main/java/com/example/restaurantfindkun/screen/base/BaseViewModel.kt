package com.example.restaurantfindkun.screen.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantfindkun.data.model.MessageDialogType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    //Messageダイアログ用変数
    private val _messageDialogEvent = MutableSharedFlow<MessageDialogType?>()
    val messageDialogEvent: SharedFlow<MessageDialogType?> = _messageDialogEvent

    //Messageダイアログ表示メソッド
    private fun showMessageDialog(messageDialogType: MessageDialogType) {
        viewModelScope.launch { _messageDialogEvent.emit(messageDialogType) }
    }

    //Messageダイアログ消去メソッド
    private fun hideMessageDialog() {
        viewModelScope.launch { _messageDialogEvent.emit(null) }
    }

    //Messageダイアログ　権限許可必要性説明
    private fun showPermissionExplanationDialog() {
        showMessageDialog(
            MessageDialogType.PermissionExplanationDialog(
                onConfirmButtonClick = { hideMessageDialog() },
                onDismissButtonCLick = { hideMessageDialog() }
            )
        )
    }



    //Messageダイアログ表示イベント　権限許可必要性説明
    private fun onPermissionExplanation(isShowErrorView: Boolean) = viewModelScope.launch {
        if (!isShowErrorView) {
            showPermissionExplanationDialog()
        }
    }
}