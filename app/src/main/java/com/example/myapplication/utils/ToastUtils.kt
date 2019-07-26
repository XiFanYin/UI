package com.example.myapplication.utils

import com.example.myapplication.app.App


inline fun toast(value: () -> String): Unit =
        App.ApplicationINSTANCE.toast(value)