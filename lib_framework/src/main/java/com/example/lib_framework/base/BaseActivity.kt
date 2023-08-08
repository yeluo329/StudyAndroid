package com.example.lib_framework.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lib_framework.R


/**
 * 1.open知识点
 *   kotlin默认类是不可以被继承
 *   kotlin方法默认不可以重写
 *
 *
 *
 *
 */
abstract class BaseActivity : AppCompatActivity() {
    private var tag: String = this::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
        initView(savedInstanceState)
        initData()

    }

    /**
     * 设置布局
     */
    open fun setContentLayout() {
        setContentView(getLayoutResId())
    }

    /**
     * 初始化视图
     * @return Int 布局id
     * 仅用于不继承BaseDataBindActivity类的传递布局文件
     */
    abstract fun getLayoutResId(): Int


    /**
     * 初始化布局
     * @param savedInstanceState Bundle?
     */
    abstract fun initView(savedInstanceState: Bundle?)


    /**
     * 初始化数据
     */
    open fun initData() {

    }

    /**
     * 加载中……弹框
     */
    fun showLoading() {


    }
}