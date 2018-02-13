package com.mingshz.identify.entity

import java.time.LocalDateTime
import javax.persistence.*

/**
 * 身份
 * @author CJ
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
open class Identify(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long?
) {
    /**
     * 唯一识别名；可选
     */
    @Column(length = 50)
    var name: String? = null
    /**
     * 是否可用
     */
    var enabled = false
    /**
     * 创建的时间
     */
    @Column(columnDefinition = "timestamp")
    var createdTime = LocalDateTime.now()
}