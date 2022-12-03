package com.example.agepredictorapp.manager

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface StringResourceManager {
    fun getString(@StringRes resId:Int):String
    fun getString(@StringRes resId: Int, param:String):String
    fun getString(@StringRes resId: Int, param: String, param2:String):String
}
class DefaultStringResourceManager
@Inject constructor(@ApplicationContext private val  context: Context)
    : StringResourceManager
{
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, param: String): String {
        return context.getString(resId,param)
    }

    override fun getString(resId: Int, param: String, param2: String): String {
        return   context.getString(resId,param,param2)
    }

}