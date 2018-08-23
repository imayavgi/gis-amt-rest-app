/*
 * Copyright 2012-2016 the original author or authors.
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

package uiak.exper.gisamt.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uiak.exper.gisamt.model.BusinessFunction;
import uiak.exper.gisamt.model.BusinessSolution;
import uiak.exper.gisamt.service.BusinessFunctionService;

@RestController
@RequestMapping("/api")
public class BusinessFunctionController {

	@Autowired
	private BusinessFunctionService businessFunctionService;

	@GetMapping("/bfunc")
	public List<BusinessFunction> cities() {
		List<BusinessFunction> allbf = this.businessFunctionService.findAll();
		return allbf;
	}
	
	@GetMapping("/bfunc/{id}")
	public BusinessFunction getBusinessFunction(@PathVariable("id") long id) {
		return this.businessFunctionService.findById(id).get();
	}

	@GetMapping("/bfunc/{id}/subbfunc")
	public Set<BusinessFunction> getSubBusinessFunction(@PathVariable("id") long id) {
		BusinessFunction bf = this.businessFunctionService.findById(id).get();
		return bf.getSubBusinessFunctions();
	}

	@GetMapping("/bfunc/{id}/bussoln")
	public Set<BusinessSolution> getBusinessSolutions(@PathVariable("id") long id) {
		BusinessFunction bf = this.businessFunctionService.findById(id).get();
		return bf.getBusinessSolutions();
	}


}
