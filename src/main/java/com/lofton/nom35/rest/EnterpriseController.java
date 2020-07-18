package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Branch;
import com.lofton.nom35.Entity.Enterprise;
import com.lofton.nom35.Repository.EnterpriseRepository;
import com.lofton.nom35.process.branch.ValidateRFC;
import com.lofton.nom35.templates.EmpresaNumBranches;
import java.util.ArrayList;
import java.util.List;
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EnterpriseController {

    private EnterpriseRepository enterpriseRepository;

    @Autowired
    public EnterpriseController(EnterpriseRepository theEnterpriseRepository) {
        enterpriseRepository = theEnterpriseRepository;
    }

    @CrossOrigin
    @GetMapping("/enterprises")
    public List<EmpresaNumBranches> getFindAll(@RequestHeader String Authorization) {
        
        
        List<EmpresaNumBranches> empresasOutput = new ArrayList<>();
        
        List<Enterprise> empresasInput = enterpriseRepository.findAll();
      
        for (Enterprise enterprise : empresasInput) {
                   EmpresaNumBranches empresa = new EmpresaNumBranches(enterprise.getId(), enterprise.getName(), enterprise.getRfc(), enterprise.getBusiness_role(), enterprise.getMax_branch(), enterprise.getBranches(), enterprise.getBranches().size(), enterprise.getStatus());

                   empresasOutput.add(empresa);
        }
        
        return empresasOutput;
    }

    @CrossOrigin
    @GetMapping("/enterprises/{enterpriseId}")
    public EmpresaNumBranches getEnterprise(@RequestHeader String Authorization, @PathVariable int enterpriseId) {
        Optional<Enterprise> result = enterpriseRepository.findById(enterpriseId);
        Enterprise theEnterprise = null;
        if (result.isPresent()) {
            theEnterprise = result.get();
        } else {
            throw new RuntimeException("Did not find enterprise id - " + enterpriseId);
        }
        
        EmpresaNumBranches empresaOut = new EmpresaNumBranches(theEnterprise.getId(), theEnterprise.getName(), theEnterprise.getRfc(), theEnterprise.getBusiness_role(), theEnterprise.getMax_branch(), theEnterprise.getBranches(), theEnterprise.getBranches().size(), theEnterprise.getStatus());
        return empresaOut;
    }

    @CrossOrigin
    @GetMapping("/enterprises/branches/{enterpriseId}")
    public List<Branch> getBranchesEnterprise(@RequestHeader String Authorization, @PathVariable int enterpriseId) {
        Optional<Enterprise> result = enterpriseRepository.findById(enterpriseId);
        Enterprise theEnterprise = null;
        if (result.isPresent()) {
            theEnterprise = result.get();
        } else {
            throw new RuntimeException("Did not find enterprise id - " + enterpriseId);
        }
        return theEnterprise.getBranches();
    }

    @CrossOrigin
    @PostMapping("/enterprises")
    public Enterprise addEnterprise(@RequestHeader String Authorization, @RequestBody Enterprise theEnterprise) {

        theEnterprise.setId(0);
        theEnterprise.setStatus(Boolean.TRUE);
        if (!ValidateRFC.validarRFC(theEnterprise.getRfc())) {
            throw new RuntimeException("RFC invalido");
        }

        enterpriseRepository.save(theEnterprise);

        Enterprise tempEnterprise = enterpriseRepository.findByRfc(theEnterprise.getRfc());

        return tempEnterprise;

    }

    @PutMapping("/enterprises")
    public Enterprise updateEnterprise(@RequestHeader String Authorization, @RequestBody Enterprise theEnterprise) {
        Optional<Enterprise> tempEnterprise = enterpriseRepository.findById(theEnterprise.getId());
        Enterprise actualEnterprise = null;
        if (tempEnterprise.isPresent()) {
            actualEnterprise = tempEnterprise.get();
        } else {
            throw new CustomNotFoundException("Did not find enterprise id - " + theEnterprise.getId());
        }

        theEnterprise.setBranches(actualEnterprise.getBranches());
        enterpriseRepository.save(theEnterprise);
        return theEnterprise;
    }

    @DeleteMapping("/enterprises/{enterpriseId}")
    public Enterprise disableEnterprise(@RequestHeader String Authorization, @PathVariable int enterpriseId) {
        Optional<Enterprise> result = enterpriseRepository.findById(enterpriseId);
        Enterprise theEnterprise = null;
        if (result.isPresent()) {
            theEnterprise = result.get();
        } else {
            throw new RuntimeException("Did not find enterprise id - " + enterpriseId);
        }
        theEnterprise.setStatus(false);
        enterpriseRepository.save(theEnterprise);
        return theEnterprise;
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
    public ResponseEntity<CustomErrorResponse> handlerException(CustomUnauthorizedException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    // add exception handler to catch all
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(Exception exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
