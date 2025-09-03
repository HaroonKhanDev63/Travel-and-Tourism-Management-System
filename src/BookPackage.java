import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class BookPackage extends JFrame {
    private JPanel contentPane;
    JTextField t1;
    Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BookPackage frame = new BookPackage("Haroon"); // Test with a username
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BookPackage(String username) {
        setBounds(350, 120, 900, 450);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Setting up an image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bookpackage.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel la1 = new JLabel(i2);
        la1.setBounds(450, 50, 700, 300);
        add(la1);

        JLabel lblTitle = new JLabel("BOOK PACKAGE");
        lblTitle.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblTitle.setBounds(118, 11, 300, 53);
        contentPane.add(lblTitle);

        JLabel la2 = new JLabel("Username:");
        la2.setBounds(35, 70, 200, 14);
        contentPane.add(la2);

        JLabel lblUsername = new JLabel(username);
        lblUsername.setBounds(271, 70, 200, 14);
        contentPane.add(lblUsername);

        JLabel lblPackage = new JLabel("Select Package:");
        lblPackage.setBounds(35, 110, 200, 14);
        contentPane.add(lblPackage);

        c1 = new Choice();
        c1.add("Gold Package");
        c1.add("Silver Package");
        c1.add("Bronze Package");
        c1.setBounds(271, 110, 150, 30);
        add(c1);

        JLabel lblPersons = new JLabel("Total Persons:");
        lblPersons.setBounds(35, 150, 200, 14);
        contentPane.add(lblPersons);

        t1 = new JTextField("0");
        t1.setBounds(271, 150, 150, 20);
        contentPane.add(t1);
        t1.setColumns(10);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(35, 190, 200, 14);
        contentPane.add(lblId);

        JLabel lblID = new JLabel();
        lblID.setBounds(271, 190, 200, 14);
        contentPane.add(lblID);

        JLabel lblNumber = new JLabel("Number:");
        lblNumber.setBounds(35, 230, 200, 14);
        contentPane.add(lblNumber);

        JLabel lblPhoneNumber = new JLabel();
        lblPhoneNumber.setBounds(271, 230, 200, 14);
        contentPane.add(lblPhoneNumber);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(35, 270, 200, 14);
        contentPane.add(lblPhone);

        JLabel lblPhoneDetails = new JLabel();
        lblPhoneDetails.setBounds(271, 270, 200, 14);
        contentPane.add(lblPhoneDetails);

        JLabel lblPrice = new JLabel("Total Price:");
        lblPrice.setBounds(35, 310, 200, 14);
        contentPane.add(lblPrice);

        JLabel lblTotalPrice = new JLabel();
        lblTotalPrice.setBounds(271, 310, 200, 14);
        lblTotalPrice.setForeground(Color.RED);
        contentPane.add(lblTotalPrice);

        // Fetch Data from Database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE username = '" + username + "'");
            if (rs.next()) {
                lblID.setText(rs.getString("id"));
                lblPhoneNumber.setText(rs.getString("number"));
                lblPhoneDetails.setText(rs.getString("phone"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Button to Check Price
        JButton btnCheckPrice = new JButton("Check Price");
        btnCheckPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p = c1.getSelectedItem();
                int cost = 0;
                if (p.equals("Gold Package")) {
                    cost += 12000;
                } else if (p.equals("Silver Package")) {
                    cost += 25000;
                } else if (p.equals("Bronze Package")) {
                    cost += 32000;
                }

                int persons = Integer.parseInt(t1.getText());
                cost *= persons;
                lblTotalPrice.setText("Rs " + cost);  // Display the calculated price
            }
        });

        btnCheckPrice.setBounds(50, 350, 120, 30);
        btnCheckPrice.setBackground(Color.BLACK);
        btnCheckPrice.setForeground(Color.WHITE);
        contentPane.add(btnCheckPrice);

        // Book Package Button
        JButton btnBook = new JButton("Book");
        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Fetch the details
                    String username = lblUsername.getText();
                    String selectedPackage = c1.getSelectedItem();
                    String persons = t1.getText();
                    String customerId = lblID.getText();
                    String phoneNumber = lblPhoneNumber.getText();
                    String phoneDetails = lblPhoneDetails.getText();
                    String totalPrice = lblTotalPrice.getText();

                    // Ensure all fields are filled
                    if (selectedPackage.isEmpty() || persons.isEmpty() || customerId.isEmpty() || phoneNumber.isEmpty() || phoneDetails.isEmpty() || totalPrice.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all details and select a package.");
                        return;
                    }

                    // Prepare the database connection and query
                    Conn c = new Conn();
                    String query = "INSERT INTO bookpackage (username, package, person, id, number, phone, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = c.c.prepareStatement(query);

                    // Set the parameters for the SQL query
                    pst.setString(1, username);
                    pst.setString(2, selectedPackage);
                    pst.setString(3, persons);
                    pst.setString(4, customerId);
                    pst.setString(5, phoneNumber);
                    pst.setString(6, phoneDetails);
                    pst.setString(7, totalPrice);

                    // Execute the query
                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Package Booked Successfully!");
                        setVisible(false);  // Close the booking window after booking
                    } else {
                        JOptionPane.showMessageDialog(null, "Error occurred during booking.");
                    }
                } catch (SQLException ee) {
                    ee.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error occurred: " + ee.getMessage());
                }
            }
        });

        btnBook.setBounds(200, 350, 120, 30);
        btnBook.setBackground(Color.BLACK);
        btnBook.setForeground(Color.WHITE);
        contentPane.add(btnBook);

        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);  // Close the current window when clicked
            }
        });
        btnBack.setBounds(350, 350, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
}
