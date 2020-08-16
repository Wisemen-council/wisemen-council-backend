package com.wisemencouncil.app.security

import org.mindrot.jbcrypt.BCrypt

fun isSameTextWhenHashed(plainText: String, hashedText: String): Boolean = BCrypt.checkpw(plainText, hashedText)
