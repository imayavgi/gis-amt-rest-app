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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class SolutionComponent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long solCompid;
	
	@Column(nullable = false)
	private AppComponentType compType;

	@Column(nullable = false)
	private String compName;

	@Column(nullable = false)
	private ComponentRunsOn runsOn;

	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonBackReference
	private BusinessSolution builtForSolution;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<BusinessSolution> usedBySolutions = new HashSet<>();

	protected SolutionComponent() {
	}


	public SolutionComponent(AppComponentType compType, String compName, ComponentRunsOn runsOn,
			BusinessSolution builtForSolution) {
		super();
		this.compType = compType;
		this.compName = compName;
		this.runsOn = runsOn;
		this.builtForSolution = builtForSolution;
	}
	
	@JsonProperty
	public Long getBuiltForSolutionId() {
		return builtForSolution.getbSolid();
	}
	
	@JsonProperty
	public Set<Long> getUsedBySolutionIds() {
		Set<Long> ids = new HashSet<>();
		usedBySolutions.forEach(sol -> ids.add(sol.getbSolid()));
		return ids;
	}


	public void setUsedBySolutions(Set<BusinessSolution> usedBySolutions) {
		this.usedBySolutions = usedBySolutions;
	}

	public void addUsedBySolution(BusinessSolution soln) {
		usedBySolutions.add(soln);
	}
	
	public void removeUsedBySolution(BusinessSolution soln) {
		usedBySolutions.remove(soln);
	}


	public Long getSolCompid() {
		return solCompid;
	}


	public AppComponentType getCompType() {
		return compType;
	}


	public String getCompName() {
		return compName;
	}


	public ComponentRunsOn getRunsOn() {
		return runsOn;
	}

}
