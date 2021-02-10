package com.tok.data.mining.mongodb.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionColumnDataModel {

	private String column;
	private List<Object> data;
}
