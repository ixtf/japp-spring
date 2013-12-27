package com.hengyi.japp.crm.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class UploadFile extends AbstractNeo4j {
	private static final long serialVersionUID = 4280588161632544621L;
	@NotBlank
	@Indexed
	private String fileName;
	@NotBlank
	private String contentType;
	private long size;
	@NotBlank
	@Indexed
	private String name;
	@RelatedTo(type = Crm.UPLOADFILE, direction = Direction.INCOMING)
	private Crm crm;

	public String getName() {
		if (name == null)
			return getFileName();
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Crm getCrm(Neo4jOperations template) {
		return template.fetch(getCrm());
	}

	public Crm getCrm() {
		return crm;
	}

	public void setCrm(Crm crm) {
		this.crm = crm;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
