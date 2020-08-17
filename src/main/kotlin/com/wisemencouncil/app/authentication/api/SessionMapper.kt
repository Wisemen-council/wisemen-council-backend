package com.wisemencouncil.app.authentication.api

import com.wisemencouncil.app.authentication.business.JwtSession
import com.wisemencouncil.app.authentication.business.Session

fun mapSessionToVM(session: Session): SessionVM = when (session) {
    is JwtSession -> SessionVM(session.token)
}
