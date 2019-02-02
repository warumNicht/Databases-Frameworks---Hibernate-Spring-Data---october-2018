package photography.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photography.domain.dto.PfotoStatDto;
import photography.domain.dto.PhotographerOrderedDto;
import photography.domain.dto.importDto.photographers.PhotographerImportDto;
import photography.domain.dto.query3.FotoDto;
import photography.domain.dto.query3.LenseExportDto;
import photography.domain.dto.query3.RootFotoExportDto;
import photography.domain.entities.Lens;
import photography.domain.entities.Photographer;
import photography.domain.entities.cameras.Camera;
import photography.repositories.CameraRepo;
import photography.repositories.LensRepo;
import photography.repositories.PhotographerRepo;
import photography.services.interfaces.PhotographerService;
import photography.util.TextUtil;
import photography.util.ValidationUtil;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

@Service
public class PhotographerServiceImpl implements PhotographerService {
    private PhotographerRepo photographerRepo;
    private LensRepo lensRepo;
    private CameraRepo cameraRepo;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;

    @Autowired
    public PhotographerServiceImpl(PhotographerRepo photographerRepo,
                                   LensRepo lensRepo, CameraRepo cameraRepo, ModelMapper mapper,
                                   ValidationUtil validationUtil) {
        this.photographerRepo = photographerRepo;
        this.lensRepo = lensRepo;
        this.cameraRepo = cameraRepo;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importPhotographers(PhotographerImportDto[] photographerImportDtos) {
        StringBuilder res = new StringBuilder();
        for (PhotographerImportDto fotogImportDto : photographerImportDtos) {
            if (!this.validationUtil.isValid(fotogImportDto)) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Photographer photographer = this.mapper.map(fotogImportDto, Photographer.class);
            photographer.setLenses(new LinkedHashSet<>());

            Camera primaryCamera = this.getRandomCamera();
            Camera secCamera = this.getRandomCamera();
            photographer.setPrimaryCamera(primaryCamera);
            photographer.setSecondaryCamera(secCamera);

            for (Integer integer : fotogImportDto.getLenses()) {
                if (integer <= 0) {
                    continue;
                }
                Lens lens = this.lensRepo.findById(integer).orElse(null);
                if (lens == null) {
                    continue;
                }
                String compatibleWith = lens.getCompatibleWith();
                if (primaryCamera.getMake().equals(compatibleWith) || secCamera.getMake().equals(compatibleWith)) {
                    photographer.getLenses().add(lens);
                }
            }
            this.photographerRepo.saveAndFlush(photographer);
            res.append(String.format("Successfully imported %s %s | Lenses: %d",
                    photographer.getFirstName(), photographer.getLastName(), photographer.getLenses().size()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    @Override
    public PhotographerOrderedDto[] query1() {
        List<Photographer> photographers = this.photographerRepo.query1();
        PhotographerOrderedDto[] res = new PhotographerOrderedDto[photographers.size()];
        for (int i = 0; i < res.length; i++) {
            Photographer photographer = photographers.get(i);
            PhotographerOrderedDto photographerOrderedDto = this.mapper.map(photographer, PhotographerOrderedDto.class);
            res[i]=photographerOrderedDto;
        }
        return res;
    }
    @Override
    public PfotoStatDto[] query2(){
        List<Photographer> objects = this.photographerRepo.query2();
        List<PfotoStatDto> filter= new ArrayList<>();
        for (Photographer object : objects) {
            long size = object.getLenses().size();
            long count = object.getLenses().stream()
                    .filter(s -> s.getFocalLength() <= 30)
                    .count();
            if(size!=count){
                continue;
            }
            PfotoStatDto pfotoStatDto = new PfotoStatDto();
            pfotoStatDto.setFirstName(object.getFirstName());
            pfotoStatDto.setLastName(object.getLastName());
            pfotoStatDto.setCamera(object.getPrimaryCamera().getMake());
            pfotoStatDto.setCount((int)count);
            filter.add(pfotoStatDto);
        }
        PfotoStatDto[] res=new PfotoStatDto[filter.size()];
        res=filter.toArray(res);
        return res;
    }
    @Override
    public RootFotoExportDto query3(){
        List<Photographer> photographers = this.photographerRepo.query3();
        RootFotoExportDto res=new RootFotoExportDto();
        List<FotoDto> fotoDtos=new ArrayList<>();
        for (Photographer photographer : photographers) {
            FotoDto fotoDto=new FotoDto();
            fotoDto.setCamera(photographer.getPrimaryCamera().getModel());
            fotoDto.setName(photographer.getFirstName()+" "+photographer.getLastName());
            List<LenseExportDto> lenseExportDtos=new ArrayList<>();
            for (Lens len : photographer.getLenses()) {
                LenseExportDto lens=new LenseExportDto();
                lens.setContent(String.format("%s %dmm f%.1f",
                        len.getMake(),len.getFocalLength(),len.getMaxAperture()));
                lenseExportDtos.add(lens);
            }
            fotoDto.setLenseExportDtos(lenseExportDtos);
            fotoDtos.add(fotoDto);
        }
        res.setFotoDtos(fotoDtos);
        return res;
    }

    private Camera getRandomCamera() {
        long count = this.cameraRepo.count();
        Random random = new Random();
        int nextInt = random.nextInt((int) count) + 1;
        Camera camera = this.cameraRepo.findById(nextInt).orElse(null);
        return camera;
    }
}
