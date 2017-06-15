package com.example.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {
	@Id
	@GeneratedValue
	private Integer id;
	private String title;
	private String note;
	private Integer billboard_group_id;
	private Integer user_id;
	private String created_at;
	private String updated_at;
}
