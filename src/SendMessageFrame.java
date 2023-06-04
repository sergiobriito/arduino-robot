import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class SendMessageFrame extends JFrame {
    private JButton btYes;
    private JButton btNo;
    private JLabel label;
    private String message;

    public SendMessageFrame(String directionsMessage) {
        super("Frame");
        label = new JLabel("Enviar rota para o Arduino? ");
        btYes = new JButton("Sim");
        btNo = new JButton("Não");
        message = directionsMessage;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(btYes, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(btNo, constraints);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);

        btYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Rota: " + message);
                SendMessageToBluetoothDevice sendMessageToBluetoothDevice = new SendMessageToBluetoothDevice();
                sendMessageToBluetoothDevice.sendMessage(message);
                System.exit(0);
            }
        });

        btNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Rota: " + message);
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public JButton getBtYes() {
        return btYes;
    }

    public void setBtYes(JButton btYes) {
        this.btYes = btYes;
    }

    public JButton getBtNo() {
        return btNo;
    }

    public void setBtNo(JButton btNo) {
        this.btNo = btNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

