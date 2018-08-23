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
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import uiak.exper.gisamt.model.SolutionComponent;

@Component("solutionComponentService")
@Transactional
class SolutionComponentServiceImpl implements SolutionComponentService {

	private final SolutionComponentRepository solutionComponentRepo;

	public SolutionComponentServiceImpl(SolutionComponentRepository businessSolutionRepository) {
		this.solutionComponentRepo = businessSolutionRepository;
	}
	
	@Override
	public List<SolutionComponent> findAll() {
		List<SolutionComponent> solComps = solutionComponentRepo.findAll();
		return solComps;
	}


	@Override
	public Optional<SolutionComponent> findById(Long id) {
		return solutionComponentRepo.findById(id);
	}


}
