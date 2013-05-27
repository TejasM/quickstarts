/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.petclinic.web;

import org.jboss.as.quickstarts.petclinic.domain.Pet;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>Pet</code> forms.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class PetValidator {

    public void validate(Pet pet, Errors errors) {
        String name = pet.getName();
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", "required", "required");
        } else if (pet.isNew() && pet.getOwner().getPet(name, true) != null) {
            errors.rejectValue("name", "duplicate", "already exists");
        } else if (pet.isNew() && pet.getType() == null) {
            errors.rejectValue("type", "required", "required");
        }
    }

}
