package main.kotlin.model.auth

import am.ik.yavi.builder.ValidatorBuilder
import am.ik.yavi.builder.konstraint
import am.ik.yavi.core.Validator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    val id: Int,
    val username: String? = null,
    val password: String? = null
) {
    companion object {
        val validator: Validator<User> = ValidatorBuilder.of<User>()
            .konstraint(User::username) {
                notNull()
                    .notEmpty()
                    .lessThanOrEqual(50)
            }
            .konstraint(User::password) {
                notNull()
                    .notEmpty()
                    .lessThanOrEqual(16)
            }.build()
    }
}