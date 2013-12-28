package com.hengyi.japp.crm.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmFile;

public interface UploadFileRepository extends GraphRepository<CrmFile>,
		NamedIndexRepository<CrmFile>,
		RelationshipOperationsRepository<CrmFile> {
	@Query("start crm=node({0}) match crm-[r:" + Crm.CRMFILE
			+ "]->file  return file")
	EndResult<CrmFile> findAllByCrm(Crm crm);
}
