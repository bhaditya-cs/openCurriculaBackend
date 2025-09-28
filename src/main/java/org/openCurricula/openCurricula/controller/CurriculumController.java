package org.openCurricula.openCurricula.controller;

import org.apache.coyote.Response;
import org.openCurricula.openCurricula.entity.Curriculum;
import org.openCurricula.openCurricula.entity.DTO.CurriculumItemDTO;
import org.openCurricula.openCurricula.entity.DTO.CurriculumWithItemsDTO;
import org.openCurricula.openCurricula.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/curriculum")
@CrossOrigin(origins = "http://localhost:3000")
public class CurriculumController {

    @Autowired
    CurriculumService curriculumService;

    @GetMapping("/get")
    public ResponseEntity<List<Curriculum>> getCurricula () {
        List<Curriculum> curricula = curriculumService.getCurricula().orElse(null);
        return ResponseEntity.ok(curricula);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Curriculum> getById (@PathVariable Long id) {
        Curriculum curriculum = curriculumService.getById(id).orElse(null);
        return ResponseEntity.ok(curriculum);
    }

    @GetMapping("/getPlans/{id}")
    public ResponseEntity<List<CurriculumItemDTO>> getPlans (@PathVariable Long id) {
        List<CurriculumItemDTO> list = curriculumService.getAllItemsForCurriculum(id);
        if (list != null) {
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.ok(new ArrayList<CurriculumItemDTO>());
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createCurriculumWithItems(@RequestBody CurriculumWithItemsDTO dto) {
        Curriculum curriculum = curriculumService.createCurriculumWithItems(dto);
        return ResponseEntity.ok(curriculum.getCurriculumId());
    }


}
