package com.example.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteRepo


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalRepo