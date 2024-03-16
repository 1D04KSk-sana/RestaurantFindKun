package com.example.restaurantfindkun.data.model

import androidx.annotation.Keep
import com.example.restaurantfindkun.R
import java.io.Serializable

//
//Messageダイアログのデータセット
//

@Keep
sealed class MessageDialogType : Serializable {
    abstract val title: Int?
    abstract val message: Int?
    abstract val positiveButtonText: Int
    abstract val negativeButtonText: Int?
    abstract val onConfirmButtonClick: () -> Unit
    abstract val onDismissButtonCLick: () -> Unit

    //
    //説明系
    //

    //権限を拒否しているときの確認
    data class PermissionExplanationDialog(
        override val title: Int? = R.string.permission_explaination_title,
        override val message: Int? = R.string.permission_explaination_message,
        override val positiveButtonText: Int = R.string.approval,
        override val negativeButtonText: Int? = R.string.denial,
        override val onConfirmButtonClick: () -> Unit = {},
        override val onDismissButtonCLick: () -> Unit = {}
    ) : MessageDialogType()
}