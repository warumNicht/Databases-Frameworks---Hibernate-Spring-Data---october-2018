package app.retake.services.api;

import app.retake.domain.dto.query2.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.importDtos.procedures.ProcedureXMLImportDTO;

import java.text.ParseException;

public interface ProcedureService {
    public void create(ProcedureXMLImportDTO dto) throws ParseException;

    ProcedureWrapperXMLExportDTO exportProcedures();
}
