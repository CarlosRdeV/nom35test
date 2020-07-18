package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Area;
import com.lofton.nom35.Entity.Branch;
import com.lofton.nom35.Repository.AreaRepository;
import com.lofton.nom35.Repository.BranchRepository;
import org.apache.commons.codec.binary.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CGCSTF08
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class AreaController {

    private final AreaRepository areaRepository;

    private final BranchRepository branchRepository;

    @Autowired
    public AreaController(AreaRepository areaRepository, BranchRepository branchRepository) {
        this.areaRepository = areaRepository;
        this.branchRepository = branchRepository;
    }

    @GetMapping("/areas")
    public List<Area> getFindAll(@RequestHeader String Authorization) {

        String[] tokenCompleto = Authorization.toString().split(" ");

        String token = tokenCompleto[1];
        getUserFromToken(token);
        return areaRepository.findAll();
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/areas/{areaId}")
    public Area getArea(@RequestHeader String Authorization, @PathVariable int areaId) {

        Optional<Area> result = areaRepository.findById(areaId);

        Area theArea = null;

        if (result.isPresent()) {
            theArea = result.get();
        } else {
            throw new CustomNotFoundException("Did not find area id - " + areaId);
        }

        return theArea;

    }

    // // add mapping for POST /users - add new user 
    @PostMapping("/areas")
    public Area addArea(@RequestHeader String Authorization, @RequestBody Area theArea) {

        if (theArea.getAreaname().length() < 4 || theArea.getAreaname().length() > 128) {
            throw new CustomBadRequestException("El nombre del area debe estar entre 4 y 128 caracteres");
        }

        theArea.setId(0);
        theArea.setStatus(Boolean.TRUE);

        areaRepository.save(theArea);

        Area tempArea = areaRepository.findByAreaname(theArea.getAreaname());

        return tempArea;

    }

    // add mapping for PUT /areas - add new user 
    @PutMapping("/areas")
    public Area updateArea(@RequestHeader String Authorization, @RequestBody Area theArea) {
        areaRepository.save(theArea);
        return theArea;
    }

    // add mapping for DELETE /users/{userId} - disable existing user
    @DeleteMapping("/areas/{areaId}")
    public Area disableArea(@RequestHeader String Authorization, @PathVariable int areaId) {

        Optional<Area> tempArea = areaRepository.findById(areaId);

        Area theArea = null;

        if (tempArea.isPresent()) {
            theArea = tempArea.get();
        } else {
            throw new RuntimeException("Area id not found - " + areaId);
        }

        theArea.setStatus(false);

        areaRepository.save(theArea);

        return theArea;
    }

    // add mapping for PUT /games/{gameId}/{genreId} - add existing genre to a existing game
    @PutMapping("/areas/branches/{areaId}/{branchId}")
    public List<Area> addAreaBranch(@RequestHeader String Authorization, @PathVariable int areaId, @PathVariable int branchId) {

        Optional<Branch> tempBranch = branchRepository.findById(branchId);

        Branch theBranch = null;

        Optional<Area> tempArea = areaRepository.findById(areaId);

        Area theArea = null;

        if (tempBranch.isPresent()) {

            if (tempArea.isPresent()) {
                theBranch = tempBranch.get();
                theArea = tempArea.get();

            } else {
                throw new RuntimeException("Area id not found - " + areaId);
            }

        } else {
            throw new RuntimeException("Branch id not found - " + branchId);
        }

        theArea.addBranch(theBranch);

        areaRepository.save(theArea);

        return theBranch.getAreas();
    }

    public String getUserFromToken(String jwtToken) {
        String[] split_string = jwtToken.split("\\.");

        String base64EncodedBody = split_string[1];

        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        body = body.replace("{", "");
        body = body.replace("}", "");
        String[] array = body.split(",");
        String[] array2 = array[0].split(":");
        String usuarioToken = array2[1] = array2[1].replace("\"", "");
        return usuarioToken;
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomNotFoundException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomBadRequestException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
