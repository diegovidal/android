package br.com.vp.memberapp.models

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * @author diegovidal on 04/06/2018.
 */
class MemberDataManager @Inject
    constructor(val sharedPreferences: SharedPreferences,
                val networkManager: NetworkManager? = null) {

    private var memberStatus = ""
    private var members = mutableListOf<Member>()

    private var currentCount = 0
        get() {

            return sharedPreferences.getInt(COUNT_KEY, 0)
        }

    init {
        populateData()
    }

    fun checkMemberStatus(userInput: String): String {

        memberStatus = "Access Denied"

        for (m in members) {

            if (m.memberId == userInput) {

                updateAccessCount()
                memberStatus = "Access Granted: Access count is $currentCount"
            }
        }
        return memberStatus
    }

    private fun updateAccessCount() {

        sharedPreferences.edit()
                ?.putInt(COUNT_KEY, currentCount + 1)
                ?.apply()
    }

    private fun populateData() {

        members.add(Member("123", "Tom", "tom@gmail.com"))
        members.add(Member("127", "Sam", "sam@gmail.com"))
        members.add(Member("670", "Jack", "jack@gmail.com"))
        members.add(Member("230", "Frank", "frank@gmail.com"))
        members.add(Member("118", "Mary", "mary@gmail.com"))
        members.add(Member("602", "Sara", "sara@gmail.com"))
    }

    companion object {

        private const val COUNT_KEY = "count"
    }
}