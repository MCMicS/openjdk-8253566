package de.mcmics.user.business.impl;

import de.mcmics.common.base.validation.WithValidation;
import de.mcmics.user.business.WorkspaceEditor;
import de.mcmics.user.domain.Workspace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Slf4j
@WithValidation
@Component
public class WorkspaceEditorBean implements WorkspaceEditor {

    public boolean isPreferred(Workspace workspace, long preferredWorkspaceUserID) {
        return false;
    }

    @Override
    public void secondCall(@NotNull Workspace workspace) {
        log.trace("Workspace: {}", workspace.getDescription());
    }
}
