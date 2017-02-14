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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class BusinessSolution implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long bSolid;
	
	@Column(nullable = false)
	private SolutionType solnType;

	@Column(nullable = false)
	private String solnName;

	@Column(nullable = false)
	private InvAssetType assetClass;

	@Column(nullable = false)
	private ProductType productType;
	
	@Column(nullable = false)
	private boolean globalSoln;

	@Column(nullable = false)
	private InvestmentFunctionRegion forRegion;
	
	@Column(nullable = true)
	private DeskType forDesk;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private BusinessFunction businessFunction;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<SolutionComponent> composedOf = new HashSet<>();

	protected BusinessSolution() {
	}

	public BusinessSolution(String solnName, SolutionType solnType, InvAssetType assetClass, ProductType productType, boolean global, InvestmentFunctionRegion forRegion,
			BusinessFunction businessFunction) {
		super();
		this.solnName = solnName;
		this.solnType = solnType;
		this.assetClass = assetClass;
		this.productType = productType;
		this.globalSoln = global;
		this.forRegion = forRegion;
		this.businessFunction = businessFunction;
	}

	public Long getbSolid() {
		return bSolid;
	}

	public void setbSolid(Long bSolid) {
		this.bSolid = bSolid;
	}

	public String getSolnName() {
		return solnName;
	}

	public void setSolnName(String solnName) {
		this.solnName = solnName;
	}

	public InvAssetType getAssetClass() {
		return assetClass;
	}

	public void setAssetClass(InvAssetType assetClass) {
		this.assetClass = assetClass;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public boolean isGlobal() {
		return globalSoln;
	}

	public void setGlobal(boolean global) {
		this.globalSoln = global;
	}

	public InvestmentFunctionRegion getForRegion() {
		return forRegion;
	}

	public void setForRegion(InvestmentFunctionRegion forRegion) {
		this.forRegion = forRegion;
	}

	public BusinessFunction getBusinessFunction() {
		return businessFunction;
	}
	
	@JsonProperty
	public String getBusinessFunctionName() {
		return businessFunction.getName();
	}
	

	public void setBusinessFunction(BusinessFunction businessFunction) {
		this.businessFunction = businessFunction;
	}

	public DeskType getForDesk() {
		return forDesk;
	}

	public void setForDesk(DeskType forDesk) {
		this.forDesk = forDesk;
	}
	
	public void addComponent(SolutionComponent comp) {
		composedOf.add(comp);
	}
	
	public void removeComponent(SolutionComponent comp) {
		composedOf.remove(comp);
	}
	
	public Set<SolutionComponent> getComposedOf() {
		return composedOf;
	}
}
