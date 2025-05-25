package com.ms.controller;

import com.ms.dto.DepartmentDto;
import com.ms.service.DepartmentService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
@Tag(name = "Department API", description = "Endpoints for managing departments")
@OpenAPIDefinition(
    info = @Info(
        title = "Department Management API",
        version = "1.0",
        description = "API for managing department data",
        contact = @Contact(
            name = "Praveen",
            email = "praveen@example.com"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8081", description = "Local Development Server"),
        @Server(url = "https://api.example.com", description = "Production API Server")
    }
)
public class DepartmentController {

    private final DepartmentService departmentService;

    // 🔹 Save department REST API
    @PostMapping
    @Operation(
        summary = "Create a new department",
        description = "Creates a new department and returns the saved entity.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Department created successfully",
                         content = @Content(schema = @Schema(implementation = DepartmentDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input format")
        }
    )
    public ResponseEntity<DepartmentDto> saveDepartment(
            @RequestBody @Parameter(description = "Department details") DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    // 🔹 Get department REST API
    @GetMapping("{department-code}")
    @Operation(
        summary = "Retrieve department by code",
        description = "Fetches department details using the department code.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                         content = @Content(schema = @Schema(implementation = DepartmentDto.class))),
            @ApiResponse(responseCode = "404", description = "Department not found")
        }
    )
    public ResponseEntity<DepartmentDto> getDepartment(
            @PathVariable("department-code") @Parameter(description = "Unique department code") String departmentCode) {
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
