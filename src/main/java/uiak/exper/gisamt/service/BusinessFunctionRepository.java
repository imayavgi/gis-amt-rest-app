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

package uiak.exper.gisamt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import uiak.exper.gisamt.jpa.domain.City;
import uiak.exper.gisamt.model.BusinessFunction;

public interface BusinessFunctionRepository extends CrudRepository<BusinessFunction, Long> {
	
	List<BusinessFunction> findAll();

	Page<BusinessFunction> findAll(Pageable pageable);

	Page<BusinessFunction> findByNameContainingAndLevelContainingAllIgnoringCase(String name,
			String level, Pageable pageable);


}
