package com.moviepur.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "firebaseclass")
@Getter
@Setter
@ToString
public class FirebaseClass {

	@Id
	private int id;
	private String buket;
	private String type;
	private String project_id;
	private String private_key_id;
	@Column(name="private_key", length=5000)
	private String private_key;
	private String client_email;
	private String client_id;
	private String auth_uri;
	private String token_uri;
	private String auth_provider_x509_cert_url;

	@Column(name="client_x509_cert_url", length=1000)
	private String client_x509_cert_url;
}
