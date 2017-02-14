/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uiak.exper.gisamt.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BusinessFunction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long bFuncid;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String level;

	@ManyToOne
	@JsonBackReference
	private BusinessFunction parentBusinessFunction;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parentBusinessFunction")
	@JsonManagedReference
	private Set<BusinessFunction> subBusinessFunctions = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "businessFunction")
	@JsonManagedReference
	private Set<BusinessSolution> businessSolutions = new HashSet<>();

	protected BusinessFunction() {
	}

	public BusinessFunction(String name, String level) {
		super();
		this.name = name;
		this.level = level;
	}
	
	public void setParentBusinessFunction(BusinessFunction pFunc) {
		this.parentBusinessFunction = pFunc;
	}
	
	public String getName() {
		return this.name;
	}

	public String getLevel() {
		return this.level;
	}
	
	public BusinessFunction getParentBusinessFunction() {
		return this.parentBusinessFunction;
	}
	
	public Set<BusinessFunction> getSubBusinessFunctions() {
		return this.subBusinessFunctions;
	}
	
	@Override
	public String toString() {
		return getName() + "," + getLevel();
	}
}
