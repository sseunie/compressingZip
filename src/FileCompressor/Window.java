package FileCompressor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Stefany Martín Socas
 * @author Santiago Martínez Willi
 */
public class Window extends javax.swing.JFrame {
    
    JFileChooser fileChooser;
    ZipCompressor zipWorker;

    /**
     * Creates new form Window
     */
    public Window() {
        initComponents();
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screen.width/2-this.getSize().width/2, screen.height/2-this.getSize().height/2);
        
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        cancelButton.setVisible(false);
        progressBar.setMinimum(0);
        progressBar.setValue(0);
        UIManager.put("nimbusOrange", new Color(35, 150, 210)); //changes de Nimbus color for the progress bar
        
        compressButton.setEnabled(false);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                confirmExit();
            }
        });
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectFolderButton = new javax.swing.JButton();
        compressButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        originPathTextField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        destinationPathTextField = new javax.swing.JTextField();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compresión de carpeta");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        selectFolderButton.setText("Seleccionar carpeta");
        selectFolderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFolderButtonActionPerformed(evt);
            }
        });

        compressButton.setText("Comprimir");
        compressButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compressButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Kohinoor Telugu", 1, 18)); // NOI18N
        titleLabel.setText("COMPRESIÓN EN FORMATO ZIP");

        originPathTextField.setEditable(false);

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        destinationPathTextField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 110, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectFolderButton)
                        .addGap(89, 89, 89)
                        .addComponent(compressButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(titleLabel)))
                .addGap(127, 127, 127))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(originPathTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addComponent(destinationPathTextField))
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(titleLabel)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectFolderButton)
                    .addComponent(compressButton))
                .addGap(18, 18, 18)
                .addComponent(originPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(destinationPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void compressButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compressButtonActionPerformed
        progressBar.setValue(0);
        cancelButton.setVisible(true);
        selectFolderButton.setEnabled(false);
        compressButton.setEnabled(false);
        
        try {
            zipWorker = new ZipCompressor(originPathTextField.getText(), destinationPathTextField.getText());
            zipWorker.execute();
        } catch (IOException ex) {
            showErrorDialog(this, ex.toString());
        }
    }//GEN-LAST:event_compressButtonActionPerformed

    private void selectFolderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFolderButtonActionPerformed
        try {
            selectOriginFolder();
            selectDestinationFolder();
            compressButton.setEnabled(true);
        } catch (Exception e) {
            showErrorDialog(this, "Error en las rutas");
            compressButton.setEnabled(false);
            originPathTextField.setText("");
            destinationPathTextField.setText("");
        }
    }//GEN-LAST:event_selectFolderButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        zipWorker.cancel(true);
    }//GEN-LAST:event_cancelButtonActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }
    
    private void selectOriginFolder() throws Exception {
        fileChooser.setDialogTitle("Seleccionar carpeta a comprimir");
        int res = fileChooser.showOpenDialog(this);
        if(res == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().isDirectory()) {
            originPathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        } else {
            throw new Exception();
        }
    }
    
    private void selectDestinationFolder() throws Exception {
        fileChooser.setDialogTitle("Seleccionar dónde se guardará el zip");
        int res = fileChooser.showSaveDialog(this);
        if(res == JFileChooser.APPROVE_OPTION && 
                fileChooser.getSelectedFile().isDirectory() &&
                !(originPathTextField.getText().equals(fileChooser.getSelectedFile().getAbsolutePath()))) {
            destinationPathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        } else {
            throw new Exception();
        }
    } 
    
    private void confirmExit() {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        int res = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quiere salir de la aplicación?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);
        if(res == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    private void showErrorDialog(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Aviso", JOptionPane.PLAIN_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton compressButton;
    private javax.swing.JTextField destinationPathTextField;
    private javax.swing.JTextField originPathTextField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton selectFolderButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Does in background the zip compression using the current and worker threads provided by SwingWorker.
     * It also updates the JProgressBar in the process.
     */
    class ZipCompressor extends SwingWorker<Void, Integer> {
        private final static int BUFFER_SIZE = 8192; // JAVA's deffault buffer size for a BufferedInputStream instance
        private final static int PBAR_MAX = 1000;   // JProgressBar max value
        
        private final String originPath;
        private final String destinationPath;
        private final long nBytes;
        
        
        public ZipCompressor(String originPath, String destinationPath) throws IOException {
            this.originPath = originPath;
            this.destinationPath = destinationPath + File.separator + (new File(originPath)).getName() +".zip";
            nBytes = getDirSize(originPath);
        }
        
        @Override
        protected Void doInBackground() throws Exception {
            progressBar.setMaximum(PBAR_MAX);
            
            List<String> filenameList = getFiles(originPath);
            FileOutputStream outputStream = new FileOutputStream(destinationPath);
            ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(outputStream));
            byte[] data = new byte[BUFFER_SIZE];
            
            Iterator i = filenameList.iterator();
            try {
                double totalCount = 0;
                while (!isCancelled() && i.hasNext()) {
                    String filename = (String) i.next();
                    File file = new File(filename);
                    FileInputStream inputStream = new FileInputStream(file);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE);

                    ZipEntry zipEntry = new ZipEntry(filename.substring(originPath.length()+1));
                    zip.putNextEntry(zipEntry);

                    int count;
                    
                    while((count = bufferedInputStream.read(data, 0, BUFFER_SIZE)) != -1) {
                        zip.write(data, 0, count);
                        totalCount += (double) count / (double) nBytes * PBAR_MAX;
                        if(isCancelled()) {
                            throw (new InterruptedException());
                        }
                        publish((int) totalCount);
                    }
                    bufferedInputStream.close();
                }
                if(isCancelled()) {
                    throw (new InterruptedException());
                }
                zip.close();
            } catch (InterruptedException ie) {
                zip.close();
                (new File(destinationPath)).delete();
                progressBar.setValue(0);
            }
            return null;
        }
        
        @Override
        protected void done() {
            progressBar.setValue(PBAR_MAX);
            cancelButton.setVisible(false);
            selectFolderButton.setEnabled(true);
            compressButton.setEnabled(true);
            if (!isCancelled()) showMessage("¡Completado!");
        }
        
        @Override
        protected void process(List<Integer> chunks) {
            if(!isCancelled()) progressBar.setValue(chunks.get(0));
        }
        
        //gets all regular file paths from a file hierarchy
        private List<String> getFiles(String path) throws IOException {
            Path folder = Paths.get(path);
            return Files.walk(folder)               //Stream<Path>
                    .map(p -> p.toFile())           //File class
                    .filter(f -> f.isFile())        //regular files
                    .map(f -> f.getAbsolutePath())
                    .collect(Collectors.toList());
        }
        
        //returns the size in bytes of the directory passed
        private long getDirSize(String path) throws IOException {
            Path folder = Paths.get(path);
            return Files.walk(folder)           //Stream<Path>
                    .map(p -> p.toFile())       //File class
                    .filter(f -> f.isFile())    //regular files
                    .mapToLong(f -> f.length())
                    .sum();
        }
    }
}
