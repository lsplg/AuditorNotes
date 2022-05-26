import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MasterDetailsComponent;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.PopupChooserBuilder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.EditorTextField;
import com.intellij.util.ui.JBDimension;
import com.sun.xml.bind.v2.TODO;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;

public class SaveNoteAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        VirtualFile file = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        int line = editor.getCaretModel().getLogicalPosition().line;
//        String selectedText = editor.getSelectionModel().getSelectedText();

        JComponent myPanel = new JPanel();
        JTextField myTextField = new JTextField("", 20);
        JButton myButton = new JButton("Save");
        myButton.addActionListener(e -> {
//            String note = myTextField.getText();
            SavedNote savedNote = new SavedNote(myTextField.getText(), line, file.getName(), project.getName());

            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream("C:\\Diplom\\save.txt");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            ObjectOutputStream objectOutputStream = null;
            try {
                objectOutputStream = new ObjectOutputStream(outputStream);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                objectOutputStream.writeObject(savedNote);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                objectOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
//            System.out.println(myTextField.getText());
        });
        myPanel.add(myTextField);
        myPanel.add(myButton);
        JBPopupFactory.getInstance()
                .createComponentPopupBuilder(myPanel, myTextField)
                .setFocusable(true)
                .setRequestFocus(true)
                .createPopup()
                .showInBestPositionFor(editor);

    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
