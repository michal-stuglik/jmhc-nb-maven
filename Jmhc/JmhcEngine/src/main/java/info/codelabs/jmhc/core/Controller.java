/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.codelabs.jmhc.core;

import info.codelabs.jmhc.forms.ProgramControler;
import info.codelabs.jmhc.objects.DBSettings;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 *
 * @author michal
 */
public class Controller {

    // declare my variable at the top of my Java class
    private Preferences prefs;
    public  ProgramControler ProgramControler;

    public Controller() {

        ProgramControler = new ProgramControler();

        // create a Preferences instance (somewhere later in the code)
        prefs = Preferences.userNodeForPackage(this.getClass());

        //logger (program) init:
        try {
            this.ProgramControler.setDBSettings(new DBSettings());
        } catch (SQLException ex) {
//            Logger.getLogger(JMHCView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void CloseConnection() {
        try {
            if (this.ProgramControler != null && this.ProgramControler.DBSettings != null && this.ProgramControler.DBSettings.getMSQLite() != null) {
                this.ProgramControler.DBSettings.getMSQLite().Close();
                if (this.ProgramControler.DBSettings.getMSQLite().connection == null || this.ProgramControler.DBSettings.getMSQLite().connection.isClosed()) {
//                    connectionStatusLabel.setText(notconnected);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBSettings.loggerProgram).log(Level.SEVERE, null, ex);
        }
    }

    private boolean isConnected() {
        boolean result = false;
        try {
            //Data base settings
            if (this.ProgramControler.DBSettings == null || this.ProgramControler.DBSettings.getMSQLite() == null) {
                return false;
            }

            if (this.ProgramControler.DBSettings.getMSQLite().getConnection() == null) {
                return false;
            } else {
                result = true;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBSettings.loggerProgram).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this,
//                    ex.getMessage(), this.getName(), JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(DBSettings.loggerProgram).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this,
//                    ex.getMessage(), this.getName(), JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

//    private void jButton_openDBActionPerformed(java.awt.event.ActionEvent evt) {                                               
//
//        JFileChooser chooser = null;
//        String extension = DBSettings.dataBase_extension;
//        String filePath = "";
//
//        try {
//
//            chooser = new JFileChooser();
//            chooser.setMultiSelectionEnabled(false);
//            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//            chooser.setFileFilter(new SQLiteFileFilter());
//            String lastDir = prefs.get("LAST_OUTPUT_DIR", null);
//            if (lastDir != null && !lastDir.isEmpty()) {
//                chooser.setCurrentDirectory(new File(lastDir));
//            }
//            int result = -1;
//            if (jRadioButton_new.isSelected()) {
//                result = chooser.showSaveDialog(this);
//            } else if (jRadioButton_open.isSelected()) {
//                result = chooser.showOpenDialog(this);
//            }
//
//            if (result == JFileChooser.APPROVE_OPTION) {
//                File file = chooser.getSelectedFile();
//                if (jRadioButton_new.isSelected()) {
//                    filePath = file.getAbsolutePath();
//                    if (!filePath.endsWith(extension)) {
//                        filePath += extension;
//                    }
//
//                    file = new File(filePath);
//
//                    if (file.exists()) {
//                        int reply = JOptionPane.showConfirmDialog(this, "overwrite existing file", "What to do?", JOptionPane.YES_NO_OPTION);
//                        if (reply == JOptionPane.YES_OPTION) {
//                            jTextField_dbPath.setText(file.getAbsolutePath());
//                            file.delete();
//                        } else {
//                            jTextField_dbPath.setText("");
//                            this.CloseConnection();
//                            return;
//                        }
//                    } else {
//                        jTextField_dbPath.setText(file.getAbsolutePath());
//                    }
//                } else if (jRadioButton_open.isSelected()) {
//                    if (!file.exists()) {
//                        throw new Exception("no such file");
//                    }
//                    jTextField_dbPath.setText(file.getAbsolutePath());
//
//                } //save last path
//                prefs.put("LAST_OUTPUT_DIR", file.getAbsolutePath());
//            } else {
//                jTextField_dbPath.setText("");
//            }
//
//            String path = jTextField_dbPath.getText();
//            if (!path.isEmpty()) {
//                ConnectToDataBase(path);
//            }
//
//            CheckConnectionStatus();
//
//        } catch (Exception ex) {
//            Logger.getLogger(DBSettings.loggerProgram).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this,
//                    ex.getMessage(), this.getName(), JOptionPane.ERROR_MESSAGE);
//        }
//    }                                              
    private void CheckConnectionStatus() {
        if (this.ProgramControler.DBSettings != null && this.ProgramControler.DBSettings.getMSQLite() != null) {
//            connectionStatusLabel.setText(connected);
        } else {
//            connectionStatusLabel.setText(notconnected);
        }
    }

    public void ConnectToDataBase(String path) {
        DBSettings mDBSettings = null;
       
        
        try {
            if (path.isEmpty()) {
                throw new Exception("Empty path!");
            } else {

//                if (jRadioButton_new.isSelected()) {
//                    mDBSettings = new DBSettings(path);
//                    mDBSettings.recreate_ExtractTable();
//                    mDBSettings.recreate_AllelTable();
//                    mDBSettings.recreate_InfoTable();
//                    mDBSettings.recreate_TagsTable();
//
//                    this.ProgramControler.setDBSettings(mDBSettings);
//
//                    if (mDBSettings.getMSQLite() != null) {
//                        JOptionPane.showMessageDialog(this,
//                                "Database connected", this.getName(), JOptionPane.INFORMATION_MESSAGE);
//                    }
//
//                } else if (jRadioButton_open.isSelected()) {
                mDBSettings = new DBSettings(path);
                this.ProgramControler.setDBSettings(mDBSettings);

                if (mDBSettings.getMSQLite() != null) {
//                        JOptionPane.showMessageDialog(this,
//                                "Database connected", this.getName(), JOptionPane.INFORMATION_MESSAGE);
//                    }
//                }

                    //PRAGMA_synchronus_OFF
                    if (mDBSettings.getMSQLite() != null) {
                        mDBSettings.PRAGMA_settings_ON_Start();
                    }

                    //logger init:
                    if (mDBSettings.getMSQLite() != null) {
//                    this.LoggerDataBaseInit(path);
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(DBSettings.loggerProgram).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this,
//                    ex.getMessage(), this.getName(), JOptionPane.ERROR_MESSAGE);
        }
    }

}
