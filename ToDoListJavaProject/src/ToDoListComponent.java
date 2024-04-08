import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class ToDoListComponent extends JPanel implements ActionListener {
    private JTextPane taskField;
    private JButton deleteButton;
    private JButton editButton;
    ArrayList<TaskComponent> tasks;
    private int id;

    public JTextPane getTaskField() {
        return taskField;
    }

    // this panel is used so that we can make updates to the task component panel when deleting tasks
    private JPanel parentPanel;

    public ToDoListComponent(JPanel parentPanel, int size){
        this.parentPanel = parentPanel;
        tasks = new ArrayList<>();
        id = size;

        // task field
        taskField = new JTextPane();
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
        taskField.setContentType("text/html");
        taskField.setEditable(false);
        taskField.addFocusListener(new FocusListener() {
            // indicate which task field is currently being edited
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(null);
            }
        });

        // delete button
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this);

        // edit button
        editButton = new JButton("E");
        editButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
        editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(this);

        // add to this taskcomponent
        setBorder(BorderFactory.createLoweredBevelBorder());
        add(taskField);
        add(deleteButton);
        add(editButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("X")){
            for (int i = 0; i < tasks.size(); i++) {
                tasks.get(i).setVisible(false);
            }
            // delete this component from the parent panel
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }

        if (e.getActionCommand().equalsIgnoreCase("E")) {
            if (taskField.isEditable()) {
                taskField.setEditable(false);
                taskField.setBackground(null);
            } else {
                taskField.setEditable(true);
                this.taskField.requestFocus();
            }
        }
    }
}