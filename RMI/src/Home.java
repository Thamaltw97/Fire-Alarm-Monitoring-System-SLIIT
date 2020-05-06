
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

        ArrayList<Sensor> sensArr = new ArrayList<>();
        sensArr = getSensors(response.toString());

        DefaultTableModel model = (DefaultTableModel) sensorDetailsTable.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[7];

        ArrayList<String> dangerSensorArray = new ArrayList<String>();

        for (int i = 0; i < sensArr.size(); i++) {
            rowData[0] = sensArr.get(i).sensorId;
            rowData[1] = sensArr.get(i).sensorName;
            rowData[2] = sensArr.get(i).floorNumber;
            rowData[3] = sensArr.get(i).roomNumber;

            if (sensArr.get(i).status.equals("A")) {
                rowData[4] = sensArr.get(i).smokeLevel;
                rowData[5] = sensArr.get(i).co2Level;
                rowData[6] = "Active";
            } else {
                rowData[4] = "-";
                rowData[5] = "-";
                rowData[6] = "Inactive";
            }

            if (sensArr.get(i).status.equals("A")) {
                if (sensArr.get(i).smokeLevel > 5 || sensArr.get(i).co2Level > 5) {
                    dangerSensorArray.add(String.valueOf(sensArr.get(i).sensorId));
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

    public ArrayList<Sensor> getSensors(String jsonRes) {

        ArrayList<Sensor> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonRes);
            for (int count = 0; count < jsonArray.length(); count++) {
                Sensor sensorObj = new Sensor();
                JSONObject jsonObj = jsonArray.getJSONObject(count);
                sensorObj.setSensorId(jsonObj.getInt("sensorId"));
                sensorObj.setSensorName(jsonObj.getString("sensorName"));
                sensorObj.setFloorNumber(jsonObj.getInt("floorNo"));
                sensorObj.setRoomNumber(jsonObj.getInt("roomNo"));
                sensorObj.setSmokeLevel(jsonObj.getInt("smokeLevel"));
                sensorObj.setCo2Level(jsonObj.getInt("coLevel"));
                sensorObj.setStatus(jsonObj.getString("sensorStatus"));

                arrayList.add(sensorObj);
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
        label8 = new java.awt.Label();
        cmbInsertStatus = new javax.swing.JComboBox<>();
        label10 = new java.awt.Label();
        sensorNameAdd = new javax.swing.JTextField();
        floorNoAdd = new javax.swing.JTextField();
        roomNoAdd = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        updateBtn = new java.awt.Button();
        idUpdate = new java.awt.TextField();
        cmbUpdateStatus = new javax.swing.JComboBox<>();
        btnDelete = new java.awt.Button();
        roomNoUpdate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sensorNameUpdate = new javax.swing.JTextField();
        floorNoUpdate = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sensorNameAdd)
                            .addComponent(floorNoAdd)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbInsertStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 162, Short.MAX_VALUE))
                            .addComponent(roomNoAdd))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sensorNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(floorNoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomNoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        idUpdate.setEditable(false);
        idUpdate.setEnabled(false);

        cmbUpdateStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inactive", "Active" }));
        cmbUpdateStatus.setSelectedIndex(1);

        btnDelete.setLabel("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Sensor Name :");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Room No. :");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("ID :");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Floor No. :");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setText("Status :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(286, 286, 286))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                    .addComponent(floorNoUpdate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(roomNoUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(sensorNameUpdate))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(14, 14, 14))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(idUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(floorNoUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(sensorNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(roomNoUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                "Sensor ID", "Sensor Name", "Floor No.", "Room No.", "Smoke Level", "CO2 Level", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(sensorDetailsTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sensorStateNotify.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sensorStateNotify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sensorStateNotify, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    //This method is to perform the Sensor update task.
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        if (validateUpdateInputs()) {

            String id = idUpdate.getText().trim();
            int finId = Integer.parseInt(id);
            String sensorName = sensorNameUpdate.getText().trim();
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
                        .put("sensorName", sensorName)
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
                sensorNameUpdate.setText("");
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
                sensorNameUpdate.setText("");
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

    private void sensorDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sensorDetailsTableMouseClicked
        
        DefaultTableModel model = (DefaultTableModel) sensorDetailsTable.getModel();

        int rowIndex = sensorDetailsTable.getSelectedRow();

        idUpdate.setText(model.getValueAt(rowIndex, 0).toString());
        if (model.getValueAt(rowIndex, 6).toString() == "Active") {
            cmbUpdateStatus.setSelectedIndex(1);
        } else {
            cmbUpdateStatus.setSelectedIndex(0);
        }
        sensorNameUpdate.setText(model.getValueAt(rowIndex, 1).toString());
        floorNoUpdate.setText(model.getValueAt(rowIndex, 2).toString());
        roomNoUpdate.setText(model.getValueAt(rowIndex, 3).toString());
        
    }//GEN-LAST:event_sensorDetailsTableMouseClicked

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
    private javax.swing.JTextField floorNoAdd;
    private javax.swing.JTextField floorNoUpdate;
    private java.awt.TextField idUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label8;
    private javax.swing.JTextField roomNoAdd;
    private javax.swing.JTextField roomNoUpdate;
    private javax.swing.JTable sensorDetailsTable;
    private javax.swing.JTextField sensorNameAdd;
    private javax.swing.JTextField sensorNameUpdate;
    private javax.swing.JLabel sensorStateNotify;
    private java.awt.Button submitBtn;
    private java.awt.Button updateBtn;
    // End of variables declaration//GEN-END:variables
}
