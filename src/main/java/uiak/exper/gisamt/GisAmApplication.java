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

package uiak.exper.gisamt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import uiak.exper.gisamt.model.AppComponentType;
import uiak.exper.gisamt.model.BusinessFunction;
import uiak.exper.gisamt.model.BusinessSolution;
import uiak.exper.gisamt.model.ComponentRunsOn;
import uiak.exper.gisamt.model.InvAssetType;
import uiak.exper.gisamt.model.InvestmentFunctionRegion;
import uiak.exper.gisamt.model.ProductType;
import uiak.exper.gisamt.model.SolutionComponent;
import uiak.exper.gisamt.model.SolutionType;
import uiak.exper.gisamt.service.BusinessFunctionRepository;
import uiak.exper.gisamt.service.BusinessSolutionRepository;
import uiak.exper.gisamt.service.SolutionComponentRepository;

import java.util.Optional;

@SpringBootApplication
//@EntityScan("uiak.exper.gisamt.model")
//@EnableJpaRepositories("uiak.exper.gisamt.service")
public class GisAmApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GisAmApplication.class, args);
	}

	//@Bean
	public CommandLineRunner prePopulate(BusinessFunctionRepository bfuncRepo, BusinessSolutionRepository bsolRepo, SolutionComponentRepository compRepo) {
		return (args) -> {
			BusinessFunction l1pm = new BusinessFunction("Sample L1-1 Function", "L1");
			bfuncRepo.save(l1pm);

			BusinessFunction l21pm = new BusinessFunction("Sample L1-1-L2-1 Function", "L2");
			l21pm.setParentBusinessFunction(l1pm);
			bfuncRepo.save(l21pm);

			BusinessFunction l2131pm = new BusinessFunction("Sample L1-1-L2-1-L3-1 Function", "L3");
			l2131pm.setParentBusinessFunction(l21pm);
			bfuncRepo.save(l2131pm);

			{
				BusinessSolution l2131pm_sol_1 = new BusinessSolution("L1-1-L2-1-L3-1-Solution", SolutionType.ASP,
						InvAssetType.EQ, ProductType.ALL, true, InvestmentFunctionRegion.US, l2131pm);
				
				SolutionComponent l2131pm_sol_1_comp1 = new SolutionComponent(AppComponentType.UI_SERVER, "SOME_PORTAL",
						ComponentRunsOn.LINUX_DC, l2131pm_sol_1);
				l2131pm_sol_1_comp1.addUsedBySolution(l2131pm_sol_1);
				compRepo.save(l2131pm_sol_1_comp1);
				
				l2131pm_sol_1.addComponent(l2131pm_sol_1_comp1);
				bsolRepo.save(l2131pm_sol_1);

			}
			BusinessSolution l2131pm_sol_2 = new BusinessSolution("L1-1-L2-1-L3-1-Solution-2", SolutionType.ASP,
					InvAssetType.FI_TAXABLE, ProductType.ALL, false, InvestmentFunctionRegion.US, l2131pm);
			bsolRepo.save(l2131pm_sol_2);

			BusinessFunction l213141pm = new BusinessFunction("Sample L1-1-L2-1-L3-1-L4-1 Function", "L4");
			l213141pm.setParentBusinessFunction(l2131pm);
			bfuncRepo.save(l213141pm);

			BusinessFunction l12pm = new BusinessFunction("Sample L1-2 Function", "L1");
			bfuncRepo.save(l12pm);

			BusinessFunction l1221pm = new BusinessFunction("Sample L1-2-L2-1 Function", "L2");
			l1221pm.setParentBusinessFunction(l12pm);
			bfuncRepo.save(l1221pm);

			Optional<BusinessFunction> readback2 = bfuncRepo.findById(1L);
			System.out.println(readback2.get().getSubBusinessFunctions().size());

		};
	}

}
