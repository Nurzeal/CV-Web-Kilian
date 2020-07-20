package com.kilian.cvweb;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.kilian.cvweb");

        noClasses()
            .that()
            .resideInAnyPackage("com.kilian.cvweb.service..")
            .or()
            .resideInAnyPackage("com.kilian.cvweb.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.kilian.cvweb.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
