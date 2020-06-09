package org.ibs.cds.gode.codegenerator.entity;

import lombok.Data;
import org.ibs.cds.gode.codegenerator.artefact.Buildable;
import org.ibs.cds.gode.codegenerator.config.CodeGenerationComponent;
import org.ibs.cds.gode.codegenerator.model.build.BuildModel;
import org.ibs.cds.gode.entity.type.EntitySpec;
import org.ibs.cds.gode.entity.type.Specification;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CodeEntity extends Specification implements Buildable, CodeGenerationComponent {

    private CodeEntityField idField;
    private List<CodeEntityField> fields;
    private EntitySpec model;
    private CodeEntityStorePolicy storePolicy;
    private BuildModel buildModel;

    public CodeEntity(EntitySpec entity, BuildModel buildModel) {
        this.buildModel = buildModel;
        fields = entity.getFields().stream().map(field -> new CodeEntityField(field, buildModel)).collect(Collectors.toList());
        idField = new CodeEntityField(entity.getIdField(), buildModel);
        storePolicy = new CodeEntityStorePolicy(entity, this.buildModel);
        this.setName(entity.getName());
        this.setDescription(entity.getDescription());
        this.setVersion(entity.getVersion());
    }

    @Override
    public ComponentName getComponentName() {
        return ComponentName.ENTITY;
    }
}
