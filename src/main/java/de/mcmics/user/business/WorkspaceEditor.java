package de.mcmics.user.business;

import de.mcmics.user.domain.Workspace;

import javax.validation.constraints.NotNull;

public interface WorkspaceEditor {

    boolean isPreferred(@NotNull Workspace workspace,  long preferredWorkspaceUserID);

    void secondCall(@NotNull Workspace workspace);
}
