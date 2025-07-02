import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ContactVault extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nameField, phoneField, emailField, searchField;
    private static final String FILE_NAME = "contacts.txt";

    public ContactVault() {
        setTitle("ContactVault");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new String[]{"Name", "Phone", "Email"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(new JLabel(""));
        inputPanel.add(nameField);
        inputPanel.add(phoneField);
        inputPanel.add(emailField);
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show All");
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);
        add(searchPanel, BorderLayout.WEST);

        // Load contacts
        loadContacts();

        // Listeners
        addButton.addActionListener(e -> addContact());
        editButton.addActionListener(e -> editContact());
        deleteButton.addActionListener(e -> deleteContact());
        clearButton.addActionListener(e -> clearFields());
        searchButton.addActionListener(e -> searchContacts());
        showAllButton.addActionListener(e -> loadContacts());
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    nameField.setText((String) tableModel.getValueAt(row, 0));
                    phoneField.setText((String) tableModel.getValueAt(row, 1));
                    emailField.setText((String) tableModel.getValueAt(row, 2));
                }
            }
        });
    }

    private void addContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }
        tableModel.addRow(new String[]{name, phone, email});
        saveContacts();
        clearFields();
    }

    private void editContact() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a contact to edit.");
            return;
        }
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }
        tableModel.setValueAt(name, row, 0);
        tableModel.setValueAt(phone, row, 1);
        tableModel.setValueAt(email, row, 2);
        saveContacts();
        clearFields();
    }

    private void deleteContact() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a contact to delete.");
            return;
        }
        tableModel.removeRow(row);
        saveContacts();
        clearFields();
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        table.clearSelection();
    }

    private void searchContacts() {
        String keyword = searchField.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a search term.");
            return;
        }
        DefaultTableModel searchModel = new DefaultTableModel(new String[]{"Name", "Phone", "Email"}, 0);
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String name = ((String) tableModel.getValueAt(i, 0)).toLowerCase();
            String phone = ((String) tableModel.getValueAt(i, 1)).toLowerCase();
            String email = ((String) tableModel.getValueAt(i, 2)).toLowerCase();
            if (name.contains(keyword) || phone.contains(keyword) || email.contains(keyword)) {
                searchModel.addRow(new String[]{
                        (String) tableModel.getValueAt(i, 0),
                        (String) tableModel.getValueAt(i, 1),
                        (String) tableModel.getValueAt(i, 2)
                });
            }
        }
        table.setModel(searchModel);
    }

    private void loadContacts() {
        tableModel.setRowCount(0);
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    tableModel.addRow(parts);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading contacts.");
        }
        table.setModel(tableModel);
    }

    private void saveContacts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                pw.println(tableModel.getValueAt(i, 0) + "," +
                        tableModel.getValueAt(i, 1) + "," +
                        tableModel.getValueAt(i, 2));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving contacts.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContactVault().setVisible(true));
    }
}
