import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Cursor;
import java.awt.event.ActionEvent;

public class ToDoListGui extends JFrame implements ActionListener, MouseListener{
    private JPanel toDoListPanel, taskPanel, taskComponentPanel, toDoListComponentPanel;
    private ArrayList<ToDoListComponent> toDoLists;
    private ToDoListComponent currentToDoList;

    public ToDoListGui() {
        super("To Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        toDoLists = new ArrayList<>();
        addGuiComponents();
    }

    private void addGuiComponents() {
        // taskpanel
        taskPanel = new JPanel();
        
        // todolistpanel
        toDoListPanel = new JPanel();

        // taskcomponentpanel
        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        // todolistcomponentpanel
        toDoListComponentPanel = new JPanel();
        toDoListComponentPanel.setLayout(new BoxLayout(toDoListComponentPanel, BoxLayout.Y_AXIS));
        toDoListPanel.add(toDoListComponentPanel);

        // add scrolling to the task panel
        JScrollPane taskScrollPanel = new JScrollPane(taskPanel);
        taskScrollPanel.setBounds(CommonConstants.GUI_SIZE.width/2,7, CommonConstants.TASKPANEL_SIZE.width, CommonConstants.TASKPANEL_SIZE.height+CommonConstants.ADDTASK_BUTTON_SIZE.height+7);
        taskScrollPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        taskScrollPanel.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
        taskScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        taskScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // add scrolling to the to do list panel
        JScrollPane toDoListScrollPanel = new JScrollPane(toDoListPanel);
        toDoListScrollPanel.setBounds(7,7, CommonConstants.TASKPANEL_SIZE.width, CommonConstants.TASKPANEL_SIZE.height+CommonConstants.ADDTASK_BUTTON_SIZE.height);
        toDoListScrollPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        toDoListScrollPanel.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
        toDoListScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        toDoListScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // changing the speed of the scroll bar        
        JScrollBar verticalTaskScrollBar = taskScrollPanel.getVerticalScrollBar();
        verticalTaskScrollBar.setUnitIncrement(20);

        // changing the speed of the scroll bartoDoListScrollPane
        JScrollBar verticalToDoListScrollBar = toDoListScrollPanel.getVerticalScrollBar();
        verticalToDoListScrollBar.setUnitIncrement(20);

        // add task button
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.setBounds(CommonConstants.GUI_SIZE.width/2, CommonConstants.GUI_SIZE.height - 2*(CommonConstants.ADDTASK_BUTTON_SIZE.height),
                CommonConstants.ADDTASK_BUTTON_SIZE.width+7, CommonConstants.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this);

        // add to do list button
        JButton addToDoListkButton = new JButton("Add To Do List");
        addToDoListkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addToDoListkButton.setBounds(7, CommonConstants.GUI_SIZE.height - 2*(CommonConstants.ADDTASK_BUTTON_SIZE.height),
                CommonConstants.ADDTASK_BUTTON_SIZE.width-7, CommonConstants.ADDTASK_BUTTON_SIZE.height);
        addToDoListkButton.addActionListener(this);

        // add to frame
        this.getContentPane().add(toDoListScrollPanel);
        this.getContentPane().add(taskScrollPanel);
        this.getContentPane().add(addTaskButton);
        this.getContentPane().add(addToDoListkButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(currentToDoList!=null&&command.equalsIgnoreCase("Add Task")){
            // create a task component
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);
            currentToDoList.tasks.add(taskComponent);
            // make the previous task appear disabled
            if(taskComponentPanel.getComponentCount() > 1) {
                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);
            }

            // make the task field request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }

        if(command.equalsIgnoreCase("Add To Do List")){
            if (currentToDoList!=null) {
                for (int i = 0; i < currentToDoList.tasks.size(); i++){
                    currentToDoList.tasks.get(i).setVisible(false);
                }
            }
            // create a task component
            ToDoListComponent toDoListComponent = new ToDoListComponent(toDoListComponentPanel);
            toDoListComponent.addMouseListener(this);
            currentToDoList = toDoListComponent;
            toDoListComponentPanel.add(toDoListComponent);
            toDoLists.add(toDoListComponent);

            // make the previous task appear disabled
            if(toDoListComponentPanel.getComponentCount() > 1) {
                ToDoListComponent previousTask = (ToDoListComponent) toDoListComponentPanel.getComponent(
                        toDoListComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);
            }

            // make the task field request focus after creation
            toDoListComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < currentToDoList.tasks.size(); i++) {
            currentToDoList.tasks.get(i).setVisible(false);
        }
        ToDoListComponent temp = (ToDoListComponent)e.getComponent();
        for (int i = 0; i < temp.tasks.size(); i++) {
            temp.tasks.get(i).setVisible(true);
        }
        currentToDoList = temp;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
