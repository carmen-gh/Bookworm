package com.caminaapps.bookworm.testing

import com.caminaapps.bookworm.core.common.decoder.StringDecoder
import javax.inject.Inject

class FakeStringDecoder @Inject constructor() : StringDecoder {
    override fun decode(encodedString: String): String = encodedString
}
