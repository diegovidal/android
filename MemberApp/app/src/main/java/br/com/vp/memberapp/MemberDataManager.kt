package br.com.vp.memberapp

/**
 * @author diegovidal on 04/06/2018.
 */
class MemberDataManager {

    var memberStatus = ""
    var members = mutableListOf<Member>()

    init {
        populateData()
    }

    fun checkMemberStatus(userInput: String): String {

        memberStatus = "Access Denied"

        for (m in members) {

            if (m.memberId == userInput) {
                memberStatus = "Access Granted"
            }
        }
        return memberStatus
    }

    private fun populateData() {

        members.add(Member("123", "Tom", "tom@gmail.com"))
        members.add(Member("127", "Sam", "sam@gmail.com"))
        members.add(Member("670", "Jack", "jack@gmail.com"))
        members.add(Member("230", "Frank", "frank@gmail.com"))
        members.add(Member("118", "Mary", "mary@gmail.com"))
        members.add(Member("602", "Sara", "sara@gmail.com"))
    }
}