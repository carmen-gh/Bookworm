package com.caminaapps.bookworm.util

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

/**
 * If we should avoid showing a rationale, and we know the user has requested a permission before,
 * this means they have said deny and don't ask me again.
 *
 * On app startup this will be false, it's not until the user requests a permission at least once
 * that we can determine if they've denied it before.
 */
@ExperimentalPermissionsApi
fun PermissionState.hasPermanentlyDenied(): Boolean = permissionRequested && !shouldShowRationale
