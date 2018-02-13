package com.mingshz.identify.repository

import com.mingshz.identify.entity.Identify
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @author CJ
 */
interface IdentifyRepository:JpaRepository<Identify,Long>, JpaSpecificationExecutor<Identify>