package com.odercore.equipment.cyclotron.controller;

import com.odercore.common.controller.AbstractCrudController;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.equipment.cyclotron.dto.request.patch.CyclotronPatchDto;
import com.odercore.equipment.cyclotron.dto.request.upsert.CyclotronUpsertDto;
import com.odercore.equipment.cyclotron.dto.response.CyclotronDto;
import com.odercore.equipment.cyclotron.entity.Cyclotron;
import com.odercore.equipment.cyclotron.exitport.dto.ExitPortDto;
import com.odercore.equipment.cyclotron.exitport.service.ExitPortService;
import com.odercore.equipment.cyclotron.target.dto.request.upsert.TargetUpsertDto;
import com.odercore.equipment.cyclotron.target.dto.response.TargetDto;
import com.odercore.equipment.cyclotron.target.service.TargetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cyclotrons")
@Tag(name = "Cyclotrons", description = "Cyclotrons Management")
public class CyclotronController extends AbstractCrudController<
        Cyclotron,
        CyclotronDto,
        CyclotronUpsertDto,
        CyclotronPatchDto
        > {

    private final ExitPortService exitPortService;
    private final TargetService targetService;

    protected CyclotronController(
            AbstractCrudService<Cyclotron, CyclotronDto, CyclotronUpsertDto, CyclotronPatchDto> service, ExitPortService exitPortService, TargetService targetService) {
        super(service);
        this.exitPortService = exitPortService;
        this.targetService = targetService;
    }

    @GetMapping("/{id}/exit-ports")
    public ResponseEntity<List<ExitPortDto>> getExitPortsByCyclotronId(@PathVariable UUID id) {
        return ResponseEntity.ok(
                exitPortService.getAllByCyclotronId(id)
        );
    }

    @GetMapping("/{id}/targets")
    public ResponseEntity<List<TargetDto>> getTargetsByCyclotronId(@PathVariable UUID id) {
        return ResponseEntity.ok(
                targetService.getAllByCyclotronId(id)
        );
    }

    @GetMapping("/{id}/targets/{targetId}/")
    public ResponseEntity<TargetDto> getTargetById(
            @PathVariable UUID id, @PathVariable UUID targetId) {
        return ResponseEntity.ok(targetService.getById(id, targetId));
    }

    @PostMapping("/{id}/targets/")
    public ResponseEntity<TargetDto> createTarget(
            @PathVariable UUID id, @RequestBody TargetUpsertDto upsertDto) {
        return ResponseEntity.ok(targetService.create(id, upsertDto));
    }

    @DeleteMapping("/{id}/targets/{targetId}/")
    public ResponseEntity<Void> deleteTarget(
            @PathVariable UUID id, @PathVariable UUID targetId) {
        targetService.delete(id, targetId);
        return ResponseEntity.noContent().build();
    }

}
