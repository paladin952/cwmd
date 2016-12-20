package application.web.controller;

import application.core.model.User;
import application.core.model.rn.RNDocument;
import application.core.service.RNDocumentService;
import application.core.utils.UserUtil;
import application.web.converter.RNDocumentConverter;
import application.web.dto.RNDocumentDto;
import com.aspose.cells.Cell;
import com.aspose.cells.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/rn")
@RestController
public class RNDocumentController {

    @Autowired
    private RNDocumentService rnDocumentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<RNDocumentDto> getDocuments() {
        User currentUser = UserUtil.getCurrentUser();

        List<RNDocument> documents = new ArrayList<>();

        if (currentUser != null) {
            documents = rnDocumentService.getDocuments(currentUser.getUsername());
        }

        return new RNDocumentConverter().toDTOs(documents);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Integer uploadRN(@RequestParam("file") MultipartFile file) throws Exception {
        Workbook workbook = null;
        try {
            workbook = new Workbook(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (workbook != null) {
            Cell cell = workbook.getWorksheets().get(0).getCells().get("B8");

            System.out.println("cell.getValue().toString() = " + cell.getValue().toString());

            rnDocumentService.saveDocumentOnDisk(workbook);
            return rnDocumentService.saveDocumentInDB(workbook, file.getOriginalFilename());
        }

        return null;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<InputStreamResource> downloadRNSample() throws IOException {
        ClassPathResource file = new ClassPathResource("sample-files/RN_info_sample.xlsx");

        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
