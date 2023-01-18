package com.example.securitymaster.security.annotation.employee;
import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.securitymaster.security.SecurityRoles.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Secured(ROLES_PREFIX+EMPLOYEES_DELETE)
public @interface EmployeeDelete {
}
