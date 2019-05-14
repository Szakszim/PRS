package dtos.other;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModulePrivilegeDto {
    private Boolean plantsModulePrivilege;
    private Boolean animalsModulePrivilege;
    private Boolean machineryModulePrivilege;
    private Boolean accountantModulePrivilege;

    public ModulePrivilegeDto(){
        this.plantsModulePrivilege = Boolean.FALSE;
        this.animalsModulePrivilege = Boolean.FALSE;
        this.machineryModulePrivilege = Boolean.FALSE;
        this.accountantModulePrivilege = Boolean.FALSE;
    }
}
