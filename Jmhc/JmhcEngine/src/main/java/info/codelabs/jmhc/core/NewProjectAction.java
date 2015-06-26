/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.codelabs.jmhc.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "info.codelabs.jmhc.core.NewProjectAction"
)
@ActionRegistration(
        iconBase = "info/codelabs/jmhc/core/newProject.png",
        displayName = "#CTL_NewProjectAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1300, separatorAfter = 1350),
    @ActionReference(path = "Toolbars/File", position = 300)
})
@Messages("CTL_NewProjectAction=New project")
public final class NewProjectAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
