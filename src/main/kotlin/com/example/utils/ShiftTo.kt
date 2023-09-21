package org.camrent.utils

import org.camrent.utils.ConvertBase.decimalToHexLittleEndian


// Extension Function
object ShiftTo {

    fun String.toLittleEndian(decimal: Int, bytes: Int): String {
        return decimalToHexLittleEndian(decimal, bytes)
    }


}