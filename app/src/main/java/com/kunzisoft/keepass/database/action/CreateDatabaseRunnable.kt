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
package com.kunzisoft.keepass.database.action

import android.content.Context
import android.net.Uri
import com.kunzisoft.keepass.database.element.Database
import com.kunzisoft.keepass.tasks.ActionRunnable

class CreateDatabaseRunnable(context: Context,
                             private val mDatabaseUri: Uri,
                             private val mDatabase: Database,
                             withMasterPassword: Boolean,
                             masterPassword: String?,
                             withKeyFile: Boolean,
                             keyFile: Uri?,
                             save: Boolean,
                             actionRunnable: ActionRunnable? = null)
    : AssignPasswordInDatabaseRunnable(context, mDatabase, withMasterPassword, masterPassword, withKeyFile, keyFile, save, actionRunnable) {

    override fun run() {
        try {
            // Create new database record
            mDatabase.apply {
                createData(mDatabaseUri)
                // Set Database state
                loaded = true
                // Commit changes
                super.run()
            }

            finishRun(true)
        } catch (e: Exception) {

            mDatabase.closeAndClear()
            finishRun(false, e.message)
        }
    }

    override fun onFinishRun(result: Result) {}
}
