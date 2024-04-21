package com.ibrahimbayburtlu.RandomDataGenerate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Random;

@Service
@Slf4j
public class RandomDataService {

    private static final int PORT = 12345;

    public void startRandomDataService() {
        new Thread(this::startListening).start();
    }

    private void startListening() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            log.info("Server started. Port: " + PORT);
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    if (clientSocket != null) {
                        log.info("Client connection accepted.");
                        handleClientConnection(clientSocket);
                    }
                } catch (IOException e) {
                    log.error("Error accepting client connection.", e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error starting server.", e);
        }
    }

    private void handleClientConnection(Socket clientSocket) {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
            while (true) {
                String data = generateData();
                out.writeObject(data);
                out.flush();
                log.info("Data sent: " + data);

                Thread.sleep(1000); // Wait for 1 second
            }
        } catch (IOException | InterruptedException e) {
            log.error("Error handling client connection.", e);
        }
    }

    private String generateData() {
        long timestamp = System.currentTimeMillis() / 1000L;
        int randomInt = new Random().nextInt(101);
        String dataToHash = timestamp + String.valueOf(randomInt);
        String hashValue = Objects.requireNonNull(md5Hash(dataToHash));

        return timestamp + "," + randomInt + "," + hashValue;
    }


    private String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString().substring(sb.length() - 2); // Son iki karakteri al
        } catch (NoSuchAlgorithmException e) {
            log.error("Error generating MD5 hash.", e);
            return null;
        }
    }

}
