import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SendMessageToBluetoothDevice {
    String comPort = "";

    public SendMessageToBluetoothDevice() {
        comPort = getConnectedBluetoothCOMPort();
    }

    public String getConnectedBluetoothCOMPort() {
        String comPort = "";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "wmic path Win32_SerialPort get DeviceID");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("COM")) {
                    comPort = line.trim();
                    break;
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return comPort;
    }
    
    public void sendMessage(String message) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "echo " + message + " > " + comPort);
            Process process = processBuilder.start();
            process.waitFor();
            if (!comPort.equals("")){
                System.out.println("Rota enviada para o Arduino com sucesso!");
            }else{
                System.out.println("Rota não enviada - Arduino desconectado");
            };
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }

}
