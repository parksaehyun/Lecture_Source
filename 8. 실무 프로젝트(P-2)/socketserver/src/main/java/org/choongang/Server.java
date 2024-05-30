package org.choongang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Server {

    private ExecutorService threadPool;
    private ObjectMapper om;

    public Server() {
        threadPool = new ThreadPoolExecutor(2, 100,120L, TimeUnit.SECONDS, new SynchronousQueue<>(){}); // 최소 2 ~ 100개 쓰레드 생성, 2분

        om = new ObjectMapper(); // 제이슨 형태로 데이터 보내기
        om.registerModule(new JavaTimeModule()); // 자바 타임 패키지 내용 인식
    }

    public void start() {
        try {
            ServerSocket server = new ServerSocket(9999);

            while(true) { //서버는 무한루프 돌 수 있기 때문에...?
                Socket socket = server.accept(); // 클라이언트가 접속하면 소켓을 한개 반환
                SocketHandler socketHandler = new SocketHandler(socket);

                // 수신처리
                socketHandler.inputHandler((data) -> {

                });

                // 무한루프돌면서 데이터 셋 할 때마다 전송 처리
                socketHandler.outputHandler();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class SocketHandler { //동시다발적으로 데이터 송수신 가능
        private Socket socket; // 소켓 받기
        private Object data; // 데이터 받기

        public SocketHandler(Socket socket) {
            this.socket = socket;
        }

        public void setData(Object data) {
            this.data = data; // 데이터 셋하기
        }

        //소켓 송수신 독립적으로 하기

        public void inputHandler(Consumer<String> handler){
            // 입력받기, 동시다발적 송수신
            threadPool.execute(() -> {
                try (DataInputStream dis = new DataInputStream(socket.getInputStream(

                ))) {// 소켓에서 데이터가 넘어옴
                    String data = dis.readUTF(); // 제이슨 형태로
                   handler.accept(data); // 함수형 인터페이스, 여기 뭐하는거지...
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            });
        }

        public void outputHandler() { // 내보내기, 동시다발적 송수신
            threadPool.execute(() -> {
                while(true) {
                    if (data == null) {
                        Thread.currentThread().yield(); // 일드 = 양보
                        continue;
                    }


                    try(DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
                        // 데이터를 셋하면 데이터 전송하기로
                        // 데이터가 설정되어 있으면 보내도록 무한루프 설정하기

                        String json = om.writeValueAsString(data); // 자바 객체 data가 제이슨 문자열로 변환됨
                        dos.writeUTF(json); // 문자열로 데이터 전송

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    data = null; // 전송 후 데이터 비우기
                }
            });
        }
    }
}
