package hiberspring.services.interfaces;

import hiberspring.domain.dto.importdto.BranchImportDto;
import hiberspring.domain.dto.query4.BranchStatRootDto;

public interface BranchService {
    String importBranches(BranchImportDto[] branchImportDtos);

    BranchStatRootDto getBranchStatistics();
}
