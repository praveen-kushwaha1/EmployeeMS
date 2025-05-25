package com.ms.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByOrganizationCode(String organizationCode);
}