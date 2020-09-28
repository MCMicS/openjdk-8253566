package de.mcmics.user.business;

import de.mcmics.common.domain.CreateTestDerbyDbRule;
import de.mcmics.user.domain.Workspace;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintDeclarationException;

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@Component
@Slf4j
@ContextConfiguration(locations = {"/test-context.xml"})
public class WorkspaceEditorTest {

    @ClassRule
    public static final CreateTestDerbyDbRule createDerbyDb = new CreateTestDerbyDbRule();
    private static final long WORKSPACE_ID = 2000L;

    @Autowired
    private WorkspaceEditor workspaceEditor;
    private final Workspace workspace = new Workspace(WORKSPACE_ID);

    /**
     * ConstraintDeclarationException should occurs on some builds
     */
    @Test
    public void shouldThrowConstraintDeclarationException() {
        try {
            workspaceEditor.isPreferred(workspace, WORKSPACE_ID);
            workspaceEditor.secondCall(workspace);
            workspaceEditor.secondCall(workspace);
            Assert.fail("ConstraintDeclarationException should occurs on JDK 15+");
        } catch (ConstraintDeclarationException e) {
            log.error(e.getMessage(), e);
        }
    }
}
