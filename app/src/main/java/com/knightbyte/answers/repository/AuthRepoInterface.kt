package com.knightbyte.answers.repository

interface AuthRepoInterface {
    suspend fun getToken():String
}