package hiberspring.services;

import hiberspring.domain.dto.query4.BranchStatElDto;
import hiberspring.domain.dto.query4.BranchStatRootDto;
import hiberspring.domain.dto.importdto.BranchImportDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repositories.BranchRepository;
import hiberspring.repositories.TownRepository;
import hiberspring.services.interfaces.BranchService;
import hiberspring.util.TextUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    private BranchRepository branchRepository;
    private TownRepository townRepository;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;
@Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository,
                             ValidationUtil validationUtil, ModelMapper mapper) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public String importBranches(BranchImportDto[] branchImportDtos){
        StringBuilder res=new StringBuilder();
        for (BranchImportDto branchImportDto : branchImportDtos) {
            if(!this.validationUtil.isValid(branchImportDto)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.townRepository.findByName(branchImportDto.getTown()).orElse(null);
            if(town==null){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Branch branch = this.mapper.map(branchImportDto, Branch.class);
            branch.setTown(town);
            this.branchRepository.saveAndFlush(branch);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM,branch.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
    @Override
    public BranchStatRootDto getBranchStatistics(){
        List<Object[]> branchStatistics = this.branchRepository.getBranchStatistics();

        BranchStatRootDto res=new BranchStatRootDto();
        List<BranchStatElDto> branches=new ArrayList<>();
        for (Object[] branch : branchStatistics) {
            String name=(String)branch[0];
            String townname=(String)branch[1];
            BigDecimal totalClients=(BigDecimal)branch[2];
            BranchStatElDto current=new BranchStatElDto();
            current.setName(name);
            current.setTotalClients(totalClients);

            current.setTown(townname);
            branches.add(current);

        }
        res.setBranchStatElDtos(branches);
        return res;
    }
}
