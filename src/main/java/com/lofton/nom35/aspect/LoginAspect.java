package com.lofton.nom35.aspect;

import com.lofton.nom35.Entity.ModulePermiso;
import com.lofton.nom35.Entity.Token;
import com.lofton.nom35.Entity.User;
import com.lofton.nom35.Repository.TokenRepository;
import com.lofton.nom35.Repository.UserRepository;
import com.lofton.nom35.process.permission.CheckPermissions;
import com.lofton.nom35.rest.CustomErrorResponse;
import com.lofton.nom35.rest.CustomNotFoundException;
import com.lofton.nom35.rest.CustomUnauthorizedException;
import java.util.List;
import java.util.Optional;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author CGCSTF08
 */
@Aspect
@Component
public class LoginAspect {

    @Autowired
    private TokenRepository tokenRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CheckPermissions checkPermissions;

    @Pointcut("execution(* com.lofton.nom35.rest.JwtAuthenticationController.*(..))")
    private void forAuthPackage() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.*.*(..))")
    private void forAllPackage() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.AreaController.get*(..))")
    private void forAreaPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.AreaController.add*(..))")
    private void forAreaPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.AreaController.update*(..))")
    private void forAreaPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.AreaController.disable*(..))")
    private void forAreaPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.BranchController.get*(..))")
    private void forBranchPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.BranchController.add*(..))")
    private void forBranchPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.BranchController.update*(..))")
    private void forBranchPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.BranchController.disable*(..))")
    private void forBranchPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.CategoryController.get*(..))")
    private void forCategoryPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.CategoryController.add*(..))")
    private void forCategoryPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.CategoryController.update*(..))")
    private void forCategoryPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.CategoryController.disable*(..))")
    private void forCategoryPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.DimensionController.get*(..))")
    private void forDimensionPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.DimensionController.add*(..))")
    private void forDimensionPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.DimensionController.update*(..))")
    private void forDimensionPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.DimensionController.disable*(..))")
    private void forDimensionPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.DomainController.get*(..))")
    private void forDomainPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.DomainController.add*(..))")
    private void forDomainPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.DomainController.update*(..))")
    private void forDomainPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.DomainController.disable*(..))")
    private void forDomainPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EmployeeController.get*(..))")
    private void forEmployeePackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EmployeeController.add*(..))")
    private void forEmployeePackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EmployeeController.update*(..))")
    private void forEmployeePackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EmployeeController.disable*(..))")
    private void forEmployeePackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EmployeeSurveyController.get*(..))")
    private void forEmployeeSurveyPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EmployeeSurveyController.add*(..))")
    private void forEmployeeSurveyPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EmployeeSurveyController.update*(..))")
    private void forEmployeeSurveyPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EmployeeSurveyController.disable*(..))")
    private void forEmployeeSurveyPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EnterpriseController.get*(..))")
    private void forEnterprisePackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EnterpriseController.add*(..))")
    private void forEnterprisePackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EnterpriseController.update*(..))")
    private void forEnterprisePackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.EnterpriseController.disable*(..))")
    private void forEnterprisePackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.JobController.get*(..))")
    private void forJobPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.JobController.add*(..))")
    private void forJobPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.JobController.update*(..))")
    private void forJobPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.JobController.disable*(..))")
    private void forJobPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ModuleController.get*(..))")
    private void forModulePackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ModuleController.add*(..))")
    private void forModulePackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ModuleController.update*(..))")
    private void forModulePackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ModuleController.disable*(..))")
    private void forModulePackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ModulePermissionController.get*(..))")
    private void forModulePermissionPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ModulePermissionController.add*(..))")
    private void forModulePermissionPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ModulePermissionController.update*(..))")
    private void forModulePermissionPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ModulePermissionController.disable*(..))")
    private void forModulePermissionPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.QuestionController.get*(..))")
    private void forQuestionPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.QuestionController.add*(..))")
    private void forQuestionPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.QuestionController.update*(..))")
    private void forQuestionPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.QuestionController.disable*(..))")
    private void forQuestionPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ResponseController.get*(..))")
    private void forResponsePackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ResponseController.add*(..))")
    private void forResponsePackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ResponseController.update*(..))")
    private void forResponsePackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ResponseController.disable*(..))")
    private void forResponsePackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ResponseTypeController.get*(..))")
    private void forResponseTypePackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ResponseTypeController.add*(..))")
    private void forResponseTypePackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ResponseTypeController.update*(..))")
    private void forResponseTypePackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.ResponseTypeController.disable*(..))")
    private void forResponseTypePackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.SurveyController.get*(..))")
    private void forSurveyPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.SurveyController.add*(..))")
    private void forSurveyPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.SurveyController.update*(..))")
    private void forSurveyPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.SurveyController.disable*(..))")
    private void forSurveyPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.UserController.get*(..))")
    private void forUserPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.UserController.add*(..))")
    private void forUserPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.UserController.update*(..))")
    private void forUserPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.UserController.disable*(..))")
    private void forUserPackageOnlyDisable() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.WorkdayController.get*(..))")
    private void forWorkdayPackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.WorkdayController.add*(..))")
    private void forWorkdayPackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.WorkdayController.update*(..))")
    private void forWorkdayPackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.WorkdayController.disable*(..))")
    private void forWorkdayPackageOnlyDisable() {
    }

    //
    @Pointcut("execution(* com.lofton.nom35.rest.RoleController.get*(..))")
    private void forRolePackageOnlyGet() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.RoleController.add*(..))")
    private void forRolePackageOnlyAdd() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.RoleController.update*(..))")
    private void forRolePackageOnlyUpdate() {
    }

    @Pointcut("execution(* com.lofton.nom35.rest.RoleController.disable*(..))")
    private void forRolePackageOnlyDisable() {
    }

    @Before("forAllPackage() && !forAuthPackage()")
    @Order(0)
    public void beforeAllMethodsExcAuth(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                Optional<Token> theToken = tokenRepo.findByToken(token);

                if (!theToken.isPresent()) {
                    throw new CustomNotFoundException("Parece que la direccion no existe");
                }
            }
        }
    }

    @Before("forAreaPackageOnlyGet()")
    @Order(10)
    public void beforeAllGetAreaMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 1);
            }
        }

    }

    @Before("forAreaPackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddAreaMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 0);
            }
        }

    }

    @Before("forAreaPackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateAreaMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 2);
            }
        }

    }

    @Before("forAreaPackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableAreaMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 3);
            }
        }
    }

    /////BRANCH///
    @Before("forBranchPackageOnlyGet()")
    @Order(10)
    public void beforeAllGetBranchMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 1);
            }
        }

    }

    @Before("forBranchPackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddBranchMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 0);
            }
        }

    }

    @Before("forBranchPackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateBranchMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 2);
            }
        }

    }

    @Before("forBranchPackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableBranchMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 3);
            }
        }
    }

    //CATEGORY
    @Before("forCategoryPackageOnlyGet()")
    @Order(10)
    public void beforeAllGetCategoryMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 1);
            }
        }

    }

    @Before("forCategoryPackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddCategoryMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 0);
            }
        }

    }

    @Before("forCategoryPackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateCategoryMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 2);
            }
        }

    }

    @Before("forCategoryPackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableCategoryMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 3);
            }
        }
    }

    //DIMENSION
    @Before("forDimensionPackageOnlyGet()")
    @Order(10)
    public void beforeAllGetDimensionMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 1);
            }
        }

    }

    @Before("forDimensionPackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddDimensionMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 0);
            }
        }

    }

    @Before("forDimensionPackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateDimensionMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 2);
            }
        }

    }

    @Before("forDimensionPackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableDimensionMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 3);
            }
        }
    }

    //Domain
    @Before("forDomainPackageOnlyGet()")
    @Order(10)
    public void beforeAllGetDomainMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 1);
            }
        }

    }

    @Before("forDomainPackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddDomainMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 0);
            }
        }

    }

    @Before("forDomainPackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateDomainMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 2);
            }
        }

    }

    @Before("forDomainPackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableDomainMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 3);
            }
        }
    }

    //Employee
    @Before("forEmployeePackageOnlyGet()")
    @Order(10)
    public void beforeAllGetEmployeeMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 1);
            }
        }

    }

    @Before("forEmployeePackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddEmployeeMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 0);
            }
        }

    }

    @Before("forEmployeePackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateEmployeeMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 2);
            }
        }

    }

    @Before("forEmployeePackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableEmployeeMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 3);
            }
        }
    }

    //Enterprise
    @Before("forEnterprisePackageOnlyGet()")
    @Order(10)
    public void beforeAllGetEnterpriseMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 1);
            }
        }

    }

    @Before("forEnterprisePackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddEnterpriseMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 0);
            }
        }

    }

    @Before("forEnterprisePackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateEnterpriseMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 2);
            }
        }

    }

    @Before("forEnterprisePackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableEnterpriseMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 3);
            }
        }
    }

    //Job
    @Before("forJobPackageOnlyGet()")
    @Order(10)
    public void beforeAllGetJobMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 1);
            }
        }

    }

    @Before("forJobPackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddJobMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 0);
            }
        }

    }

    @Before("forJobPackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateJobMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 2);
            }
        }

    }

    @Before("forJobPackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableJobMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 3);
            }
        }
    }

    //Module
    @Before("forModulePackageOnlyGet()")
    @Order(10)
    public void beforeAllGetModuleMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 1);
            }
        }

    }

    @Before("forModulePackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddModuleMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 0);
            }
        }

    }

    @Before("forModulePackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateModuleMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 2);
            }
        }

    }

    @Before("forModulePackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableModuleMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 3);
            }
        }
    }

    //Question
    @Before("forQuestionPackageOnlyGet()")
    @Order(10)
    public void beforeAllGetQuestionMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 1);
            }
        }

    }

    @Before("forQuestionPackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddQuestionMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 0);
            }
        }

    }

    @Before("forQuestionPackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateQuestionMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 2);
            }
        }

    }

    @Before("forQuestionPackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableQuestionMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 3);
            }
        }
    }

    //Response
    @Before("forResponsePackageOnlyGet()")
    @Order(10)
    public void beforeAllGetResponseMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 1);
            }
        }

    }

    @Before("forResponsePackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddResponseMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 0);
            }
        }

    }

    @Before("forResponsePackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateResponseMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 2);
            }
        }

    }

    @Before("forResponsePackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableResponseMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 3);
            }
        }
    }

    //Survey
    @Before("forSurveyPackageOnlyGet()")
    @Order(10)
    public void beforeAllGetSurveyMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 1);
            }
        }

    }

    @Before("forSurveyPackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddSurveyMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 0);
            }
        }

    }

    @Before("forSurveyPackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateSurveyMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 2);
            }
        }

    }

    @Before("forSurveyPackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableSurveyMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "ENCUESTAS", 3);
            }
        }
    }

    //User
    @Before("forUserPackageOnlyGet()")
    @Order(10)
    public void beforeAllGetUserMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "USUARIOS", 1);
            }
        }

    }

    @Before("forUserPackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddUserMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "USUARIOS", 0);
            }
        }

    }

    @Before("forUserPackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateUserMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "USUARIOS", 2);
            }
        }

    }

    @Before("forUserPackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableUserMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "USUARIOS", 3);
            }
        }
    }

    //workday
    @Before("forWorkdayPackageOnlyGet()")
    @Order(10)
    public void beforeAllGetWorkdayMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 1);
            }
        }

    }

    @Before("forWorkdayPackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddWorkdayMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 0);
            }
        }

    }

    @Before("forWorkdayPackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateWorkdayMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 2);
            }
        }

    }

    @Before("forWorkdayPackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableWorkdayMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "EMPRESAS", 3);
            }
        }
    }

    //Roles
    @Before("forRolePackageOnlyGet()")
    @Order(10)
    public void beforeAllGetRoleMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "USUARIOS", 1);
            }
        }

    }

    @Before("forRolePackageOnlyAdd()")
    @Order(10)
    public void beforeAllAddRoleMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "USUARIOS", 0);
            }
        }

    }

    @Before("forRolePackageOnlyUpdate()")
    @Order(10)
    public void beforeAllUpdateRoleMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "USUARIOS", 2);
            }
        }

    }

    @Before("forRolePackageOnlyDisable()")
    @Order(10)
    public void beforeAllDisableRoleMethods(JoinPoint theJoinPoint) {

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {

            if (tempArg.toString().startsWith("Bearer")) {
                String[] tokenCompleto = tempArg.toString().split(" ");

                String token = tokenCompleto[1];

                checkPermissions.getUserFromToken(token, "USUARIOS", 3);
            }
        }
    }
//
//    @Before("forAreaPackageOnlyAdd()")
//    @Order(10)
//    public void beforeAllAddAreaMethods(JoinPoint theJoinPoint) {
//
//        System.out.println("only add area");
//        Object[] args = theJoinPoint.getArgs();
//        System.out.println("Este es el usuario " + args[1]);
//
//    }
//    @AfterReturning("forAllPackage()")
//    public void afterAllMethods(JoinPoint theJoinPoint) {
//
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//        System.out.println("Esta es la hora: " + dateFormat.format(date));
//
//        Object[] args = theJoinPoint.getArgs();
//        System.out.println("Este es el usuario " + args[1]);
//
//        System.out.println("Level: INFO");
//
//        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
//        System.out.println("Este es el Method: " + methodSig);
//
//        ///estos son los atributos
//        for (Object tempArg : args) {
//
////            if (tempArg.) {
////                
////            }
//        }
//
//    }
//    @AfterThrowing("forAllPackage()")
//    public void afterAllExcMethods(JoinPoint theJoinPoint) {
//
//        System.out.println(">>>ERROR<<<");
//
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//        System.out.println("Esta es la hora: " + dateFormat.format(date));
//
//        Object[] args = theJoinPoint.getArgs();
//        System.out.println("Este es el usuario " + args[1]);
//
//        System.out.println("Level: WARNING");
//
//        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
//        System.out.println("Este es el Method: " + methodSig);
//
//    }

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

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
