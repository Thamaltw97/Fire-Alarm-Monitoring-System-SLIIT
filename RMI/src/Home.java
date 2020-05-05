
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Color;
import java.awt.Component;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Thamal Wijetunge
 */

public class Home extends javax.swing.JFrame {

    public static Service service = null;
    Timer timer = new Timer();

    public Home() {
        initComponents();
        this.setTitle("Admin Panel");
        try {
            getSensorDetails();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    getSensorDetails();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 0, 30000);

    }

    public void getSensorDetails() throws Exception {

        boolean booDanger = false;

        StringBuffer response = service.returnSensorDetailsApi();

        ArrayList<Alarm> alarm = new ArrayList<>();
        alarm = getSensors(response.toString());

        DefaultTableModel model = (DefaultTableModel) sensorDetailsTable.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[6];

        ArrayList<String> dangerSensorArray = new ArrayList<String>();

        for (int i = 0; i < alarm.size(); i++) {
            rowData[0] = alarm.get(i).alarmId;

            rowData[1] = alarm.get(i).floorNumber;
            rowData[2] = alarm.get(i).roomNumber;
            

            if (alarm.get(i).status.equals("A")) {
                rowData[3] = alarm.get(i).smokeLevel;
                rowData[4] = alarm.get(i).co2Level;
                rowData[5] = "Active";
            } else {
                rowData[3] = "-";
                rowData[4] = "-";
                rowData[5] = "Inactive";
            }

            if (alarm.get(i).status.equals("A")) {
                if (alarm.get(i).smokeLevel > 5 || alarm.get(i).co2Level > 5) {
                    dangerSensorArray.add(String.valueOf(alarm.get(i).alarmId));
                    booDanger = true;
                }
            }

            if (booDanger == true) {
                sensorStateNotify.setText("Danger zone sensor IDs : " + dangerSensorArray);
            } else {
                sensorStateNotify.setText("");
            }

            model.addRow(rowData);
        }

    }

    public ArrayList<Alarm> getSensors(String json) {

        ArrayList<Alarm> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int count = 0; count < jsonArray.length(); count++) {
                Alarm alarmObject = new Alarm();
                JSONObject jsonObject = jsonArray.getJSONObject(count);
                alarmObject.setAlarmId(jsonObject.getInt("sensorId"));
                alarmObject.setFloorNumber(jsonObject.getInt("floorNo"));
                alarmObject.setRoomNumber(jsonObject.getInt("roomNo"));
                alarmObject.setSmokeLevel(jsonObject.getInt("smokeLevel"));
                alarmObject.setCo2Level(jsonObject.getInt("coLevel"));
                alarmObject.setStatus(jsonObject.getString("sensorStatus"));

                arrayList.add(alarmObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        submitBtn = new java.awt.Button();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        roomNoAdd = new java.awt.TextField();
        floorNoAdd = new java.awt.TextField();
        label8 = new java.awt.Label();
        cmbInsertStatus = new javax.swing.JComboBox<>();
        label10 = new java.awt.Label();
        sensorNameAdd = new java.awt.TextField();
        jPanel3 = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        updateBtn = new java.awt.Button();
        label5 = new java.awt.Label();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        idUpdate = new java.awt.TextField();
        roomNoUpdate = new java.awt.TextField();
        floorNoUpdate = new java.awt.TextField();
        label9 = new java.awt.Label();
        cmbUpdateStatus = new javax.swing.JComboBox<>();
        btnDelete = new java.awt.Button();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        sensorDetailsTable = new javax.swing.JTable();
        sensorStateNotify = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label1.setText("Add new Sensor");

        submitBtn.setLabel("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        label3.setText("Room No. :");

        label4.setText("Floor No. : ");

        label8.setText("Status:");

        cmbInsertStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inactive", "Active" }));
        cmbInsertStatus.setSelectedIndex(1);

        label10.setText("Sensor Name:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbInsertStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(roomNoAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(floorNoAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(sensorNameAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sensorNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(floorNoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roomNoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbInsertStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        label2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label2.setText("Edit Sensor");

        updateBtn.setLabel("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        label5.setText("Room No. :");

        label6.setText("Floor No. : ");

        label7.setText("ID:");

        idUpdate.setEditable(false);
        idUpdate.setEnabled(false);

        label9.setText("Status:");

        cmbUpdateStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inactive", "Active" }));
        cmbUpdateStatus.setSelectedIndex(1);

        btnDelete.setLabel("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(idUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                            .addComponent(cmbUpdateStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roomNoUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(floorNoUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(idUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(floorNoUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roomNoUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnDelete.getAccessibleContext().setAccessibleName("btnDelete");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        sensorDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sensor Id", "Floor No.", "Room No.", "Smoke Level", "CO2 Level", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sensorDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sensorDetailsTableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(sensorDetailsTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );

        sensorStateNotify.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sensorStateNotify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sensorStateNotify, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        if (validateInsertInputs()) {

            String sensorName = sensorNameAdd.getText();
            String roomNumber = roomNoAdd.getText();
            String floorNumber = floorNoAdd.getText();
            int status = cmbInsertStatus.getSelectedIndex();

            String finStatus = "";

            if (status == 1) {
                finStatus = "A";
            } else {
                finStatus = "I";
            }

            String jsonObj = "";
            try {
                jsonObj = new JSONObject()
                        .put("sensorName", sensorName)
                        .put("floorNo", floorNumber)
                        .put("roomNo", roomNumber)
                        .put("sensorStatus", finStatus).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String[] response = new String[3];

            try {
                response = service.addSensor(jsonObj);
            } catch (RemoteException ex) {
                System.out.println("RemoteException : " + ex);
            }

            if (response[0].equals("200")) {
                try {
                    service.getSensorDetailsApi();
                    getSensorDetails();
                } catch (RemoteException ex) {
                    System.out.println("RemoteException : " + ex);
                } catch (Exception ex) {
                    System.out.println("Exception : " + ex);
                }
                JOptionPane.showMessageDialog(jPanel1, "Successfully saved the sensor.", "SUCCESS!", JOptionPane.PLAIN_MESSAGE);
                roomNoAdd.setText("");
                floorNoAdd.setText("");
                cmbInsertStatus.setSelectedIndex(1);

                try {
                    this.getSensorDetails();
                } catch (Exception ex) {
                    System.out.println("Exception : " + ex);
                }

            } else if (!response[0].equals("200")) {
                JOptionPane.showMessageDialog(jPanel1, "Failed to save the sensor!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(jPanel1, "Validation error!", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    //This method is to perform the Alarm update task.
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        if (validateUpdateInputs()) {

            String id = idUpdate.getText().trim();
            int finId = Integer.parseInt(id);
            String roomNumber = roomNoUpdate.getText().trim();
            String floorNumber = floorNoUpdate.getText().trim();
            int status = cmbUpdateStatus.getSelectedIndex();
            String finStatus = "";
            if (status == 1) {
                finStatus = "A";
            } else {
                finStatus = "I";
            }

            String jsonObj = "";
            try {
                jsonObj = new JSONObject()
                        .put("sensorStatus", finStatus)
                        .put("roomNo", roomNumber)
                        .put("floorNo", floorNumber)
                        .put("sensorId", finId).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String[] response = new String[3];

            try {
                response = service.updateSensor(jsonObj);
            } catch (RemoteException ex) {
                System.out.println("RemoteException : " + ex);
            }

            if (response[0].equals("200")) {
                JOptionPane.showMessageDialog(jPanel1, "Successfully updated. (sensor id : " + id + ")", "SUCCESS!", JOptionPane.PLAIN_MESSAGE);
                idUpdate.setText("");
                roomNoUpdate.setText("");
                floorNoUpdate.setText("");
                cmbUpdateStatus.setSelectedIndex(1);

                try {
                    service.getSensorDetailsApi();
                    getSensorDetails();
                } catch (Exception ex) {
                    System.out.println("Exception : " + ex);
                }

            } else if (!response[0].equals("200")) {
                JOptionPane.showMessageDialog(jPanel1, "Failed to update! (sensor id : " + id + ")", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(jPanel1, "Validation error!", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_updateBtnActionPerformed

    public boolean validateUpdateInputs() {

        int count = 0;

        if (idUpdate.getText().toString().isEmpty()) {
            count++;
        }
        if ((roomNoUpdate.getText().toString().isEmpty()) || (!roomNoUpdate.getText().matches("[0-9]+"))) {
            count++;
        }
        if ((floorNoUpdate.getText().toString().isEmpty()) || (!floorNoUpdate.getText().matches("[0-9]+"))) {
            count++;
        }

        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateInsertInputs() {

        int count = 0;

        if (sensorNameAdd.getText().toString().isEmpty()) {
            count++;
        }
        if ((floorNoAdd.getText().toString().isEmpty()) || (!floorNoAdd.getText().matches("[0-9]+"))) {
            count++;
        }
        if ((roomNoAdd.getText().toString().isEmpty()) || (!roomNoAdd.getText().matches("[0-9]+"))) {
            count++;
        }

        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    private void sensorDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sensorDetailsTableMouseClicked

        DefaultTableModel model = (DefaultTableModel) sensorDetailsTable.getModel();

        int rowIndex = sensorDetailsTable.getSelectedRow();

        idUpdate.setText(model.getValueAt(rowIndex, 0).toString());
        if (model.getValueAt(rowIndex, 5).toString() == "Active") {
            cmbUpdateStatus.setSelectedIndex(1);
        } else {
            cmbUpdateStatus.setSelectedIndex(0);
        }
        floorNoUpdate.setText(model.getValueAt(rowIndex, 1).toString());
        roomNoUpdate.setText(model.getValueAt(rowIndex, 2).toString());


    }//GEN-LAST:event_sensorDetailsTableMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String id = idUpdate.getText().toString();

        if (!id.isEmpty()) {
            String[] response = new String[3];

            try {
                response = service.deleteSensor(id);
            } catch (RemoteException ex) {
                System.out.println("RemoteException : " + ex);
            }

            if (response[0].equals("200")) {
                try {
                    service.getSensorDetailsApi();
                    getSensorDetails();
                } catch (RemoteException ex) {
                    System.out.println("RemoteException : " + ex);
                } catch (Exception ex) {
                    System.out.println("Exception : " + ex);
                }
                JOptionPane.showMessageDialog(jPanel1, "Successfully deleted the sensor. (sensor id : " + id + ")", "SUCCESS!", JOptionPane.PLAIN_MESSAGE);
                idUpdate.setText("");
                roomNoUpdate.setText("");
                floorNoUpdate.setText("");
                cmbUpdateStatus.setSelectedIndex(1);

                try {
                    this.getSensorDetails();
                } catch (Exception ex) {
                    System.out.println("Exception : " + ex);
                }

            } else if (!response[0].equals("200")) {
                JOptionPane.showMessageDialog(jPanel1, "Failed to delete the sensor! (sensor id : " + id + ")", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(jPanel1, "Select a sensor to delete !", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    public static void main(String args[]) {

        System.setProperty("java.security.policy", "file:allowall.policy");
        try {
            service = (Service) Naming.lookup("//localhost/SensorService");
            service.returnSensorDetailsApi();

        } catch (NotBoundException ex) {
            System.err.println(ex.getMessage());
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnDelete;
    private javax.swing.JComboBox<String> cmbInsertStatus;
    private javax.swing.JComboBox<String> cmbUpdateStatus;
    private java.awt.TextField floorNoAdd;
    private java.awt.TextField floorNoUpdate;
    private java.awt.TextField idUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane8;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private java.awt.TextField roomNoAdd;
    private java.awt.TextField roomNoUpdate;
    private javax.swing.JTable sensorDetailsTable;
    private java.awt.TextField sensorNameAdd;
    private javax.swing.JLabel sensorStateNotify;
    private java.awt.Button submitBtn;
    private java.awt.Button updateBtn;
    // End of variables declaration//GEN-END:variables
}
