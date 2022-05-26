import com.intellij.ProjectTopics;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.Serializable;

public class SavedNote implements Serializable {

    private static final long serialVersionUID = 1L;

    private String note;
    private int line;
    private String fileName;
    private String projectName;

    public SavedNote(String note, int line, String fileName, String projectName) {
        this.note = note;
        this.line = line;
        this.fileName = fileName;
        this.projectName = projectName;
    }
}
