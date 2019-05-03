package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.group.users.*
import org.slf4j.LoggerFactory

class DefaultUserMethodGroup : UsersMethodGroup {
    companion object {

        val LOG = LoggerFactory.getLogger(DefaultUserMethodGroup::class.java)
    }

    override fun info(authToken: String): UsersInfoMethod {
        return DefaultUsersInfoMethod(authToken)
    }

    override fun list(authToken: String): UserListMethod {
        return DefaultUserListMethod(authToken)
    }

    override fun conversations(authToken: String): DefaultUserConversationsMethod {
        return DefaultUserConversationsMethod(authToken)
    }

    override fun deletePhoto(authToken: String): UsersDeletePhotoMethod {
        return DefaultUsersDeletePhotoMethod(authToken)
    }

    override fun identity(authToken: String): DefaultUsersIdentityMethod {
        return DefaultUsersIdentityMethod(authToken)
    }

    override fun getProfile(authToken: String): UsersGetProfileMethod {
        return DefaultUsersGetProfileMethod(authToken)
    }
}

