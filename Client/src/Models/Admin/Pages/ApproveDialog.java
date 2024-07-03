package src.Models.Admin.Pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.Models.Admin.*;
import src.Client;

public class ApproveDialog extends Dialog implements ActionListener {
    private final Button approveButton, nextButton, backButton;
    private ResultSet resultSet;
    private String objectType;
    private int currentID;
    private Label infoLabel;

    public ApproveDialog(Frame parent, ResultSet resultSet, String objectType) {
        super(parent, "Approve Object", true);
        this.resultSet = resultSet;
        this.objectType = objectType;

        setLayout(new BorderLayout());

        infoLabel = new Label();
        add(infoLabel, BorderLayout.CENTER);

        Panel panel = new Panel();
        approveButton = new Button("Approve");
        nextButton = new Button("Next");
        backButton = new Button("Back");

        approveButton.addActionListener(this);
        nextButton.addActionListener(this);
        backButton.addActionListener(this);

        panel.add(approveButton);
        panel.add(nextButton);
        panel.add(backButton);

        add(panel, BorderLayout.SOUTH);

        setSize(400, 200);
        showNext();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == approveButton) {
            // 调用 Admin 类中的审核方法
            if (objectType.equals("Patient")) {
                Admin.approvePatient(currentID);
            } else if (objectType.equals("Doctor")) {
                Admin.approveDoctor(currentID);
            } else if (objectType.equals("Appointment Info")) {
                Client.dbManager.executeUpdate("UPDATE information SET info_state = TRUE WHERE info_id = " + currentID);
            } else if (objectType.equals("Admin")) {
                Admin.approveAdmin(currentID);
            }

            // 显示下一个对象
            showNext();
        } else if (e.getSource() == nextButton) {
            // 显示下一个对象
            showNext();
        } else if (e.getSource() == backButton) {
            dispose(); // 关闭当前对话框
        }
    }

    private void showNext() {
        try {
            while (resultSet.next()) {
                boolean isPendingApproval = false;

                if (objectType.equals("Patient")) {
                    currentID = resultSet.getInt("p_id");
                    isPendingApproval = !resultSet.getBoolean("p_state");
                } else if (objectType.equals("Doctor")) {
                    currentID = resultSet.getInt("d_id");
                    isPendingApproval = !resultSet.getBoolean("d_state");
                } else if (objectType.equals("Appointment Info")) {
                    currentID = resultSet.getInt("info_id");
                    isPendingApproval = !resultSet.getBoolean("info_state");
                } else if (objectType.equals("Admin")) {
                    currentID = resultSet.getInt("a_id");
                    isPendingApproval = !resultSet.getBoolean("a_state");
                }

                if (isPendingApproval) {
                    StringBuilder objectInfo = new StringBuilder();
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        objectInfo.append(resultSet.getMetaData().getColumnName(i))
                                .append(": ")
                                .append(resultSet.getString(i))
                                .append("\n");
                    }
                    infoLabel.setText(objectInfo.toString());
                    validate();
                    return;
                }
            }
            infoLabel.setText("No more objects pending approval");
            approveButton.setEnabled(false);
            nextButton.setEnabled(false);
            validate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            showNoPendingApprovalsDialog();
        }
    }

    private void showNoPendingApprovalsDialog() {
        Dialog errorDialog = new Dialog(this, "No Pending Approvals", true);
        errorDialog.setLayout(new FlowLayout());
        errorDialog.add(new Label("No pending approvals or error in fetching data."));
        Button okButton = new Button("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                errorDialog.setVisible(false);
                errorDialog.dispose();
                dispose(); // 关闭审核对话框
            }
        });
        errorDialog.add(okButton);
        errorDialog.setSize(300, 150);
        errorDialog.setVisible(true);
    }
}
