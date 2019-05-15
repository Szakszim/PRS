package controllers.cardreader;

import controllers.general.MainScreenController;
import controllers.temporary.InitializeStudents;
import controllers.temporary.Student;
import javafx.stage.Stage;

import javax.smartcardio.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CardReaderThread implements Runnable {

    public static AtomicBoolean running;
    private Stage stage;
    public static void setRunning(AtomicBoolean running) {
        CardReaderThread.running = running;
    }

    @Override
    public void run() {
        running = new AtomicBoolean(Boolean.TRUE);
        try {
            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();
            System.out.println("Terminals: " + terminals);

            CardTerminal reader = terminals.get(0);

            System.out.println("zaczynam nasluchiwanie");

            while (running.get()) {
                while (!reader.isCardPresent()) {
                }

                Card card = reader.connect("*");
                card.beginExclusive();
                CardChannel channel = card.getBasicChannel();
                ResponseAPDU resp = channel.transmit(new CommandAPDU(128, 202, 0x9f, 0x7f, 256));
                byte[] data = resp.getData();
                byte[] ICSerialNumber = Arrays.copyOfRange(data, 15, 19);

                HashMap<String, Student> studentHashMap = InitializeStudents.initializeStudents();
                if (/*database.jest*/ studentHashMap.containsKey(hexToString(ICSerialNumber))) {
                    MainScreenController.addRow(hexToString(ICSerialNumber));
                } else {
                    System.out.println("Musisz dodać studenta");
                }
                while (reader.isCardPresent()) {
                }
            }

        } catch (CardException e) {
            e.printStackTrace();
        }
    }



    private String hexToString(final byte[] data) {
        char[] hexValue = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int length = data.length;
        char[] out = new char[length*2];
        for (int i = 0, j = 0; i < length; i++) {
            out[j++] = hexValue[(0xF0 & data[i]) >>> 4];
            out[j++] = hexValue[0x0F & data[i]];
        }
        return new String(out);
    }
}