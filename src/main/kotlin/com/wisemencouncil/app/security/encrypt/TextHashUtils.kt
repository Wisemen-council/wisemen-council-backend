package com.wisemencouncil.app.security.encrypt

import org.mindrot.jbcrypt.BCrypt

fun isSameTextWhenHashed(plainText: String, hashedText: String): Boolean = BCrypt.checkpw(plainText, hashedText)
