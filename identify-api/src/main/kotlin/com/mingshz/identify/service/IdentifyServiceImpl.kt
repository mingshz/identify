package com.mingshz.identify.service

import com.mingshz.identify.IdentifyService
import com.mingshz.identify.entity.Identify
import com.mingshz.identify.entity.Identify_
import org.apache.commons.logging.LogFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.NoResultException

/**
 * @author CJ
 */
@Service
class IdentifyServiceImpl(
        private val entityManager: EntityManager
) : IdentifyService {

    private val log = LogFactory.getLog(IdentifyServiceImpl::class.java)

    override fun loadUserByUsername(name: String?): UserDetails {
        if (name == null)
            throw UsernameNotFoundException("can not find Identify with null name.")
        val cb = entityManager.criteriaBuilder
        val cq = cb.createQuery(Long::class.java)
        val root = cq.from(Identify::class.java)
        try {
            return IdentifyUserDetails(entityManager.createQuery(
                    cq.where(cb.equal(root[Identify_.name], name))
                            .select(root[Identify_.id])
            ).singleResult)
        } catch (ex: NoResultException) {
            throw UsernameNotFoundException("can not find Identify by $name", ex)
        }
    }

    private inner class IdentifyUserDetails(
            private val id: Long
    ) : UserDetails {

        private lateinit var identify: Identify
        private var lastThread: Thread? = null

        private fun checkUpdate() {
            if (lastThread != Thread.currentThread()) {
                lastThread = Thread.currentThread()
                identify = entityManager.find(Identify::class.java, id)
                log.debug("refresh Identify.")
            }
        }

        override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
            checkUpdate()
            // TODO 身份->角色->权限
            return Collections.emptyList()
        }

        override fun isEnabled(): Boolean {
            checkUpdate()
            return identify.enabled
        }

        override fun getUsername(): String {
            checkUpdate()
            // TODO 应当允许客户端项目定制自己的username
            return identify.name ?: "unknown"
        }

        override fun getPassword(): String {
            checkUpdate()
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun isCredentialsNonExpired(): Boolean = true

        override fun isAccountNonExpired(): Boolean = true

        override fun isAccountNonLocked(): Boolean = true

    }

}