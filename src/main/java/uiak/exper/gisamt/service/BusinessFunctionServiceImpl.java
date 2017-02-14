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

package uiak.exper.gisamt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import uiak.exper.gisamt.model.BusinessFunction;

@Component("businessFunctionService")
@Transactional
class BusinessFunctionServiceImpl implements BusinessFunctionService {

	private final BusinessFunctionRepository businessFunctionRepository;

	public BusinessFunctionServiceImpl(BusinessFunctionRepository businessFunctionRepository) {
		this.businessFunctionRepository = businessFunctionRepository;
	}
	
	@Override
	public List<BusinessFunction> findAll() {
		return businessFunctionRepository.findAll();
	}

	@Override
	public Page<BusinessFunction> find(BusinessFunctionSearchCriteria criteria, Pageable pageable) {

		Assert.notNull(criteria, "Criteria must not be null");
		String name = criteria.getName();

		if (!StringUtils.hasLength(name)) {
			return this.businessFunctionRepository.findAll((Pageable)null);
		}

		String level = "";
		int splitPos = name.lastIndexOf(",");

		if (splitPos >= 0) {
			level = name.substring(splitPos + 1);
			name = name.substring(0, splitPos);
		}

		return this.businessFunctionRepository
				.findByNameContainingAndLevelContainingAllIgnoringCase(name.trim(),
						level.trim(), pageable);
	}


	@Override
	public BusinessFunction findById(Long id) {
		return businessFunctionRepository.findOne(id);
	}


}
