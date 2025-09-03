import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class BookHotel extends JFrame {
    private JPanel contentPane;
    JTextField t1, t2;
    Choice c1, c2, c3;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BookHotel frame = new BookHotel("Haroon"); // Test with a username
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BookHotel(String username) {
        setBounds(270, 100, 1000, 550);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Image Setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/book.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel la1 = new JLabel(i2);
        la1.setBounds(450, 100, 700, 300);
        add(la1);

        JLabel lblTitle = new JLabel("BOOK HOTEL");
        lblTitle.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblTitle.setBounds(118, 11, 300, 53);
        contentPane.add(lblTitle);

        // Username
        JLabel la2 = new JLabel("Username:");
        la2.setBounds(35, 70, 200, 14);
        contentPane.add(la2);

        JLabel lblUsername = new JLabel(username);
        lblUsername.setBounds(271, 70, 200, 14);
        contentPane.add(lblUsername);

        // Select Hotel
        JLabel lblHotel = new JLabel("Select Hotel:");
        lblHotel.setBounds(35, 110, 200, 14);
        contentPane.add(lblHotel);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM hotel");
            while (rs.next()) {
                c1.add(rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setBounds(271, 110, 150, 30);
        add(c1);

        // Total Persons
        JLabel lblPersons = new JLabel("Total Persons:");
        lblPersons.setBounds(35, 150, 200, 14);
        contentPane.add(lblPersons);

        t1 = new JTextField("0");
        t1.setBounds(271, 150, 150, 20);
        contentPane.add(t1);

        // Number of Days
        JLabel lblDays = new JLabel("Number of Days:");
        lblDays.setBounds(35, 190, 200, 14);
        contentPane.add(lblDays);

        t2 = new JTextField("0");
        t2.setBounds(271, 190, 150, 20);
        contentPane.add(t2);

        // AC / Non-AC
        JLabel lblAC = new JLabel("AC / Non-AC:");
        lblAC.setBounds(35, 230, 200, 14);
        contentPane.add(lblAC);

        c2 = new Choice();
        c2.add("AC");
        c2.add("Non-AC");
        c2.setBounds(271, 230, 150, 30);
        add(c2);

        // Food Included
        JLabel lblFood = new JLabel("Food Included:");
        lblFood.setBounds(35, 270, 200, 14);
        contentPane.add(lblFood);

        c3 = new Choice();
        c3.add("Yes");
        c3.add("No");
        c3.setBounds(271, 270, 150, 30);
        add(c3);

        // ID, Number, Phone
        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(35, 310, 200, 14);
        contentPane.add(lblID);

        JLabel lblIdValue = new JLabel();
        lblIdValue.setBounds(271, 310, 200, 14);
        contentPane.add(lblIdValue);

        JLabel lblNumber = new JLabel("Number:");
        lblNumber.setBounds(35, 350, 200, 14);
        contentPane.add(lblNumber);

        JLabel lblNumberValue = new JLabel();
        lblNumberValue.setBounds(271, 350, 200, 14);
        contentPane.add(lblNumberValue);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(35, 390, 200, 14);
        contentPane.add(lblPhone);

        JLabel lblPhoneValue = new JLabel();
        lblPhoneValue.setBounds(271, 390, 200, 14);
        contentPane.add(lblPhoneValue);

        // Total Price
        JLabel lblPrice = new JLabel("Total Price:");
        lblPrice.setBounds(35, 430, 200, 14);
        contentPane.add(lblPrice);

        JLabel lblTotalPrice = new JLabel();
        lblTotalPrice.setBounds(271, 430, 200, 14);
        lblTotalPrice.setForeground(Color.RED);
        contentPane.add(lblTotalPrice);

        // Fetch customer details
        try {
            Conn conn = new Conn();
            PreparedStatement pst = conn.c.prepareStatement("SELECT * FROM customer WHERE username = ?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lblIdValue.setText(rs.getString("id"));
                lblNumberValue.setText(rs.getString("number"));
                lblPhoneValue.setText(rs.getString("phone"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Check Price Button
        JButton btnCheckPrice = new JButton("Check Price");
        btnCheckPrice.addActionListener(e -> {
            try {
                Conn c = new Conn();
                PreparedStatement pst = c.c.prepareStatement("SELECT * FROM hotel WHERE name = ?");
                pst.setString(1, c1.getSelectedItem());
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int cost = Integer.parseInt(rs.getString("costperperson"));
                    int food = Integer.parseInt(rs.getString("foodincluded"));
                    int ac = Integer.parseInt(rs.getString("acroom"));

                    int persons = Integer.parseInt(t1.getText());
                    int days = Integer.parseInt(t2.getText());

                    int total = cost * persons * days;
                    if (c2.getSelectedItem().equals("AC")) total += ac * persons * days;
                    if (c3.getSelectedItem().equals("Yes")) total += food * persons * days;

                    lblTotalPrice.setText("Rs " + total);
                }
                rs.close();
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        });
        btnCheckPrice.setBounds(50, 470, 120, 30);
        btnCheckPrice.setBackground(Color.BLACK);
        btnCheckPrice.setForeground(Color.WHITE);
        contentPane.add(btnCheckPrice);

        // Book Hotel Button
        JButton btnBook = new JButton("Book");
        btnBook.addActionListener(e -> {
            try {
                Conn c = new Conn();
                PreparedStatement pst = c.c.prepareStatement(
                        "INSERT INTO bookhotel (username, name, persons, days, ac, food, id, number, phone, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                pst.setString(1, username);
                pst.setString(2, c1.getSelectedItem());
                pst.setString(3, t1.getText());
                pst.setString(4, t2.getText());
                pst.setString(5, c2.getSelectedItem());
                pst.setString(6, c3.getSelectedItem());
                pst.setString(7, lblIdValue.getText());
                pst.setString(8, lblNumberValue.getText());
                pst.setString(9, lblPhoneValue.getText());
                pst.setString(10, lblTotalPrice.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Hotel Booked Successfully!");
                setVisible(false);
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        });
        btnBook.setBounds(200, 470, 120, 30);
        btnBook.setBackground(Color.BLACK);
        btnBook.setForeground(Color.WHITE);
        contentPane.add(btnBook);

        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> setVisible(false));
        btnBack.setBounds(350, 470, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
}
