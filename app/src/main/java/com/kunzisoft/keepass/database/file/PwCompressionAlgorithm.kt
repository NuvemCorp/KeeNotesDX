/*
 * Copyright 2019 Jeremy Jamet / Kunzisoft.
 *     
 * This file is part of KeePass DX.
 *
 *  KeePass DX is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  KeePass DX is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with KeePass DX.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.kunzisoft.keepass.database.file

// Note: We can get away with using int's to store unsigned 32-bit ints
//       since we won't do arithmetic on these values (also unlikely to
//       reach negative ids).
enum class PwCompressionAlgorithm constructor(val id: Int) {

    None(0),
    Gzip(1);

    companion object {

        fun fromId(num: Int): PwCompressionAlgorithm? {
            for (e in values()) {
                if (e.id == num) {
                    return e
                }
            }
            return null
        }
    }

}
