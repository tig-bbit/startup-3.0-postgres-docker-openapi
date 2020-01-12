package com.kaluzny.demo.api;

import com.kaluzny.demo.domain.Automobile;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Tag(name = "Automobile", description = "the Automobile API")
public interface AutomobileResource {

    @Operation(summary = "Add a new Automobile", description = " ", tags = {"Automobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Automobile created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Automobile already exists")})
    Automobile saveAutomobile(
            @Parameter(description = "Automobile", required = true, schema = @Schema(implementation = Automobile.class))
            @NotNull @RequestBody Automobile automobile);

    @Operation(summary = "Find all Automobiles", description = " ", tags = {"Automobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Automobile.class))))})
    Collection<Automobile> getAllAutomobiles();

    @Operation(summary = "Find automobile by ID", description = "Returns a single automobile", tags = {"Automobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Automobile.class))),
            @ApiResponse(responseCode = "404", description = "Contact not found")})
    Automobile getAutomobileById(
            @Parameter(description = "Id of the Automobile to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id);

    @Hidden //hidden
    @Operation(summary = "Find automobile by name", description = " ", tags = {"Automobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Automobile.class))))})
    Collection<Automobile> findAutomobileByName(
            @Parameter(description = "Name of the Automobile to be obtained. Cannot be empty.", required = true)
                    String name);

    @Operation(summary = "Update an existing Automobile", description = "", tags = {"Automobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Automobile not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    Automobile refreshAutomobile(
            @Parameter(description = "Id of the Automobile to be update. Cannot be empty.",
                    required = true) @PathVariable Long id,
            @Parameter(description = "Automobile to update.",
                    required = true, schema = @Schema(implementation = Automobile.class)) @RequestBody Automobile automobile);

    @Operation(summary = "Deletes a Automobile", description = "", tags = {"Automobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Automobile not found")})
    void removeAutomobileById(
            @Parameter(description = "Id of the Automobile to be delete. Cannot be empty.",
                    required = true) @PathVariable Long id);

    void removeAllAutomobiles();

}
