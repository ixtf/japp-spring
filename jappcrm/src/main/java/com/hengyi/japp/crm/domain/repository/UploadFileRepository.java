package com.hengyi.japp.crm.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.UploadFile;

public interface UploadFileRepository extends GraphRepository<UploadFile>,
		NamedIndexRepository<UploadFile>,
		RelationshipOperationsRepository<UploadFile> {
	@Query("start crm=node({0}) match crm-[r:" + Crm.UPLOADFILE
			+ "]->file  return file")
	EndResult<UploadFile> findAllByCrm(Crm crm);
}
